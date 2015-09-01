package control.client;

import java.rmi.RemoteException;

import module.client.ClientInfoManager;
import module.inter.ClientStateSwithcer;
import control.inter.RoomChooser;

public class RoomController implements RoomChooser{
	private ClientStateSwithcer swithcer=ClientInfoManager.getInstance();
	
	@Override
	public boolean joinRoom(int roomId) {
		boolean success;
		try {
			success = swithcer.joinRoom(roomId);
		} catch (RemoteException e) {
			success=false;
		}
		return success;
	}

	@Override
	public boolean createRoom(String name,int maxMem) {
		boolean success;
		try {
			success = swithcer.createRoom(name,maxMem);
		} catch (RemoteException e) {
			success=false;
		}
		return success;
	}
	
	@Override
	public void refreshRoomList() throws RemoteException {
		swithcer.refreshRoomList();
	}

	
}
