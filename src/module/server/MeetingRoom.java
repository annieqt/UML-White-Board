package module.server;

import java.rmi.RemoteException;
import java.util.ArrayList;

import control.inter.ClientReceiver;

import util.ApplicationResources;
import util.command.Command;
import util.message.RoomMsg;
import util.message.UserMsg;

public class MeetingRoom {
	private RoomMsg roomMsg;
	private ArrayList<LinkedClient> roomClients=new ArrayList<LinkedClient>();
	
	public MeetingRoom(RoomMsg roomMsg){
		this.roomMsg=roomMsg;
	}
	
	protected int getRoomId() {
		return roomMsg.id;
	}
	
	protected RoomMsg getRoomMsg() {
		return roomMsg;
	}
	
	protected int getNowMem() {
		return roomClients.size();
	}
	
	protected UserMsg[] getRoomUsers() {
		UserMsg[] roomUsers = new UserMsg[roomClients.size()];
		for (int i = 0; i < roomClients.size(); i++) {
			roomUsers[i]=roomClients.get(i).userMsg;
		}
		return roomUsers;
	}

	protected synchronized void addCommand(Command c) {
		for (int i = 0; i < roomClients.size(); i++) {
			LinkedClient cl=roomClients.get(i);
			try {
				c.excuteOnClient(cl.receiver);
			} catch (RemoteException e) {
				dealDisconnect(cl.userMsg);
				i--;
				e.printStackTrace();
			}
		}
	}
	
	protected boolean join(LinkedClient newJoined){
		if (roomClients.size()<roomMsg.maxMem) {
			newJoined.setRoomMsg(roomMsg);
			roomClients.add(newJoined);
			ArrayList<String> ips = new ArrayList<String>();
			for (int i = 0; i < roomClients.size(); i++) {
				LinkedClient cl=roomClients.get(i);
				if (!cl.userMsg.equals(newJoined.userMsg)) {
					ips.add(cl.clientIp);
					try {
						cl.receiver.addText(null, newJoined.userMsg.name+" "+ApplicationResources.getInstance().getString("client.text.join"));
						cl.receiver.refreshMemberList();
						cl.receiver.audioConnect(newJoined.clientIp,i);
					} catch (RemoteException e) {
						dealDisconnect(cl.userMsg);
						i--;
						return false;
					}
				}
			}
			try {
				if(roomClients.size()>1){
					newJoined.receiver.audioConnect(ips);
				}
			} catch (RemoteException e) {
				return false;
			}
			return true;
		}else {
			return false;
		}
	}
	
	protected void requestRefreshWB(LinkedClient newJoined) {
		ClientReceiver refreshVolunteer=null;
		for (int i = 0; i < roomClients.size(); i++) {
			LinkedClient cl=roomClients.get(i);
			if (!cl.userMsg.equals(newJoined.userMsg)) {
				refreshVolunteer=cl.receiver;
				if (refreshVolunteer!=null) {
					try {
						refreshVolunteer.sendWBInitUpdate();
						break;
					} catch (RemoteException e) {
						dealDisconnect(cl.userMsg);
						i--;
					}
				}				
			}
		}
		
	}
	
	protected LinkedClient exitRoom(UserMsg user) {
		for (int i = 0; i < roomClients.size(); i++) {
			LinkedClient linkedClient=roomClients.get(i);
			if (linkedClient.userMsg.equals(user)) {
				roomClients.remove(linkedClient);
				linkedClient.setRoomMsg(null);
				
				try {
					for (LinkedClient lc : roomClients) {
						lc.receiver.refreshMemberList();
						lc.receiver.addText(null, linkedClient.userMsg.name+" "+ApplicationResources.getInstance().getString("client.text.exit"));
					}
				} catch (RemoteException e) {
					dealDisconnect(linkedClient.userMsg);
					i--;
					e.printStackTrace();
				}
				
				return linkedClient;
			}
		}
		return null;
	}
	
	protected boolean nobodyLeft(){
		return roomClients.isEmpty();
	}
	
	protected LinkedClient getClient(UserMsg user) {
		for (LinkedClient client : roomClients) {
			if (client.userMsg.equals(user)) {
				return client;
			}
		}
		return null;
	}

	/**处理某客户端断网的问题。*/
	private void dealDisconnect(UserMsg user) {
		exitRoom(user);
	}
}
