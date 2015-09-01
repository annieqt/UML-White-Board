package module.client;

import java.rmi.RemoteException;

import util.message.UserMsg;
import module.inter.ClientInfoProvider;
import module.inter.RoomMemberDAO;


public class RoomAssist extends Assistant implements RoomMemberDAO{
	
	public RoomAssist(ClientInfoProvider client) {
		super(client);
	}
	
	@Override
	public void refreshMemberList() throws RemoteException {
		UserMsg[] members = getServer().getRoomUsers(ClientInfoManager.getInstance().getNowRoom().id);
		getClientView().refreshMemberList(members);
	}

	@Override
	public void exitRoom() throws RemoteException {
		getServer().exitRoom(getNowLog());
	}

}
