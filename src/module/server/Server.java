package module.server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.NoSuchObjectException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.RemoteObject;
import java.rmi.server.RemoteServer;
import java.rmi.server.ServerNotActiveException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;

import util.command.Command;
import util.message.RoomMsg;
import util.message.RoomState;
import util.message.UserMsg;

import module.inter.RemoteMeetingServer;


import control.inter.ClientReceiver;


public class Server extends UnicastRemoteObject implements RemoteMeetingServer,ServerOp{
	private static final long serialVersionUID = 1L;
	
	public static final int SERVER_PORT=9527;
	public static final String SERVER_NAME="SoftwareMeeting";
	private static final String SERVER_IP="localhost";
	private static final String SERVER_URL="//"+SERVER_IP+":"+SERVER_PORT+"/"+SERVER_NAME;
	private static final String FILE_PUTPATH = "ServerFiles/";
	
	private IUserDataBase userDatabase;
	private ArrayList<MeetingRoom> rooms=new ArrayList<MeetingRoom>();
//	private ArrayList<LinkedClient> lobbyClients=new ArrayList<LinkedClient>();
	
	public Server(String sname,String password) throws RemoteException, MalformedURLException, AlreadyBoundException, SQLException {
		userDatabase=new UserDatabase(sname,password);
		initializeRMI();
	}
	
	
	private void initializeRMI() throws RemoteException, MalformedURLException, AlreadyBoundException {
		LocateRegistry.createRegistry(SERVER_PORT);
		Naming.rebind(SERVER_URL, this);
		
	}

	@Override
	public UserMsg sign(String userID, String password) throws RemoteException,ServerNotActiveException  {
		UserMsg user = null;
		try {
			UserMsg findedUser = userDatabase.getUserByID(userID);
			if (findedUser!=null 
					&& password.equals(findedUser.password)) {
				user=findedUser;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}


	private String getClientIp() throws ServerNotActiveException, UnknownHostException {
		String clienthost = RemoteServer.getClientHost();//获取RMI客户端主机
		InetAddress ia=java.net.InetAddress.getByName(clienthost);
		return ia.getHostAddress();
	}
	
	@Override
	public RoomMsg joinRoom(UserMsg user, int roomId, ClientReceiver clientReceiver) throws NoSuchObjectException {
		RoomMsg joinedRoom = null;
		ClientReceiver remoteReceiver = (ClientReceiver) RemoteObject.toStub(clientReceiver);
		for (MeetingRoom room : rooms) {
			room.exitRoom(user);	//先弃掉原来的人
			if (room.getRoomId()==roomId) {
				String clientIp;
				try {
					clientIp = getClientIp();
					boolean success=room.join(new LinkedClient(remoteReceiver, user, clientIp));	//找到房间以后进去
					if (success) {
						joinedRoom = room.getRoomMsg();
					}
				} catch (UnknownHostException e) {
					e.printStackTrace();
					dealUnlink(user);
				} catch (ServerNotActiveException e) {
					e.printStackTrace();
					dealUnlink(user);
				}
			}
		}
		return joinedRoom;
	}


	@Override
	public RoomMsg createRoom(UserMsg user, String name, int maxMem, ClientReceiver client) throws NoSuchObjectException {
		int nextId;
		ClientReceiver remoteReceiver = (ClientReceiver) RemoteObject.toStub(client);
		if (rooms.isEmpty()) {
			nextId=1;
		}else {
			nextId=rooms.get(rooms.size()-1).getRoomId()+1;
		}
		MeetingRoom newRoom = new MeetingRoom(new RoomMsg(nextId, name, user, maxMem));
		rooms.add(newRoom);
		String clientIp;
		try {
			clientIp = getClientIp();
			newRoom.join(new LinkedClient(remoteReceiver, user, clientIp));
			return newRoom.getRoomMsg();
		} catch (UnknownHostException e) {
			dealUnlink(user);
			return null;
		} catch (ServerNotActiveException e) {
			dealUnlink(user);
			return null;
		}		
	}

	@Override
	public void requestRefreshWB(UserMsg user) {
		for (MeetingRoom room : rooms) {
			LinkedClient targetClient = room.getClient(user);
			if (targetClient!=null) {
				room.requestRefreshWB(targetClient);
				break;
			}
		}
	}
	@Override
	public boolean exitRoom(UserMsg user) {
		MeetingRoom targetRoom = null;
		for (MeetingRoom room : rooms) {
			boolean have=room.exitRoom(user)!=null;
			if (have) {
				targetRoom=room;
				break;
			}
		}
		if (targetRoom!=null && targetRoom.nobodyLeft()) {
			rooms.remove(targetRoom);
		}
		return false;
	}


	@Override
	public RoomState[] getRooms(){
		MeetingRoom targetRoom;
		for (int i = 0; i < rooms.size(); i++) {
			targetRoom = rooms.get(i);
			if (targetRoom!=null && targetRoom.nobodyLeft()) {
				rooms.remove(targetRoom);
				i--;
			}
		}
		RoomState[] roomStates = new RoomState[rooms.size()];
		for (int i = 0; i < roomStates.length; i++) {
			roomStates[i]=new RoomState(rooms.get(i).getRoomMsg(), rooms.get(i).getNowMem());
		}
		return roomStates;
	}

	@Override
	public void sendCommand(Command c) {
		for (MeetingRoom room : rooms) {
			if (room.getRoomId()==c.getRoomId()) {
				room.addCommand(c);
				break;
			}
		}
	}


	@Override
	public void handleUpload(byte[] content, String fileName) {
		try {
			BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(FILE_PUTPATH + fileName));
			output.write(content, 0, content.length);
			output.flush();
			output.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}


	@Override
	public byte[] handleDownload(String fileName) throws RemoteException {
		File file = new File(FILE_PUTPATH + fileName);
		int fileSize = (int)file.length();
		if(fileSize > 1024*1024*10){
			throw new RemoteException("Error:<The File is too big!>");
		}
		byte content[] = new byte[fileSize];				

		try {
			BufferedInputStream input = new BufferedInputStream(new FileInputStream(file));
			input.read(content,0,content.length);
			input.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	return content;
	}


	@Override
	public String[] getFileNames() {
		File file = new File(FILE_PUTPATH);
		File[] files = file.listFiles();
		int size = files.length;
		String[] fileNames = new String[size];
		
		for(int i = 0; i < size;i++){
			fileNames[i] = files[i].getName();
		}
		return fileNames;
	}

	private void dealUnlink(UserMsg user) {
		exitRoom(user);
	}
	
	public void close(){
		try {
			Naming.unbind(SERVER_URL);
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean addUser(UserMsg usr) {
		try {
			userDatabase.addUser(usr);
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	@Override
	public boolean editUser(UserMsg usr) {
		try {
			userDatabase.updateUser(usr);
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	@Override
	public boolean deleteUser(String id) {
		try {
			userDatabase.deleteUser(id);
			return true;
		} catch (SQLException e) {
			return false;
		}
	}


	@Override
	public UserMsg[] getRoomUsers(int roomId) throws RemoteException {
		for (MeetingRoom room : rooms) {
			if (room.getRoomId()==roomId) {
				return room.getRoomUsers();
			}
		}
		return null;
	}

	@Override
	public UserMsg[] getAllUsers() throws SQLException {
		return userDatabase.getAllUser();
	}
	
}

	