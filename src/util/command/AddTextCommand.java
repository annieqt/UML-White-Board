package util.command;

import java.rmi.RemoteException;

import util.message.UserMsg;
import control.inter.ClientReceiver;

public class AddTextCommand implements Command {
	private static final long serialVersionUID = 1L;
	
	private String message = null;
	private UserMsg sender = null;	
	private int roomID  = 0;
	
	public AddTextCommand(UserMsg sender,int roomID,String text){
		this.message = text;
		this.sender = sender;
		this.roomID = roomID;
	}
	
	@Override
	public void excuteOnClient(ClientReceiver client) throws RemoteException {
		client.addText(sender, message);
	}

	@Override
	public int getRoomId() {
		return roomID;
	}

}
