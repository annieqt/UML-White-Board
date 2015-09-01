package util.command;

import java.rmi.RemoteException;

import control.inter.ClientReceiver;

public class RefreshListCommand implements Command{
	private static final long serialVersionUID = 1L;
	
	private int roomID  = 0;
	
	public RefreshListCommand(int roomID){
		this.roomID = roomID;
	}
	@Override
	public void excuteOnClient(ClientReceiver client) throws RemoteException {
		client.refreshFileList();		
	}

	@Override
	public int getRoomId() {
		return roomID;
	}

}
