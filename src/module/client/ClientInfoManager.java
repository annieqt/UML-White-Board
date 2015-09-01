package module.client;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.ServerNotActiveException;

import control.client.ClientController;
import control.inter.ClientReceiver;

import network.NetworkImpl;
import network.NetworkInter;

import util.message.RoomMsg;
import util.message.RoomState;
import util.message.UserMsg;
import view.inter.RoomChooseRefresher;
import module.inter.ClientInfoProvider;
import module.inter.RemoteMeetingServer;

import module.inter.ClientStateSwithcer;

public class ClientInfoManager implements ClientStateSwithcer,ClientInfoProvider{
	private ClientController client;
	
	private NetworkInter networkInter;		//网络接口
	private UserMsg nowLog;					//现在登录的用户
	private RoomMsg nowRoom;				//现在所在的房间
	
	private RoomChooseRefresher roomView;	//房间的View
	private static ClientInfoManager instance = new ClientInfoManager();
	public static ClientInfoManager getInstance() {
		if (instance==null) {
			instance= new ClientInfoManager();
		}
		return instance;
		
	}
	private ClientInfoManager() {
		networkInter=new NetworkImpl();
	}
	
	@Override
	public boolean logOn(String sname, String password, String ip) throws RemoteException, ServerNotActiveException {
		try {
			connect(ip);
			nowLog=getServer().sign(sname, password);
			return nowLog!=null;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		return false;
	}

	public ClientController getClient() {
		return client;
	}
	private ClientReceiver getRemoteClient() {
		return client;
	}
	
	@Override
	public boolean joinRoom(int roomId) throws RemoteException {
		client = new ClientController();
		nowRoom=getServer().joinRoom(nowLog, roomId, getRemoteClient());
		return nowRoom!=null;
	}

	@Override
	public boolean createRoom(String name, int maxMem) throws RemoteException {
		client = new ClientController();
		nowRoom=getServer().createRoom(nowLog, name, maxMem, getRemoteClient());
		return nowRoom!=null;
	}
	
	@Override
	public RemoteMeetingServer getServer() {
		return networkInter.getServer();
	}
	@Override
	public UserMsg getNowLog() {
		return nowLog;
	}
	@Override
	public RoomMsg getNowRoom() {
		return nowRoom;
	}

	@Override
	public RemoteMeetingServer connect(String ipString)
			throws MalformedURLException, RemoteException, NotBoundException,
			ServerNotActiveException {
		return networkInter.connect(ipString);
	}

	@Override
	public void close() throws RemoteException {
		networkInter.close(nowLog);
	}

	@Override
	public void refreshRoomList() throws RemoteException {
		RoomState[] rooms = getServer().getRooms();
		roomView.refreshRoomlist(rooms);
	}

	public void setRoomView(RoomChooseRefresher roomView) {
		this.roomView = roomView;
	}
	
	
}
