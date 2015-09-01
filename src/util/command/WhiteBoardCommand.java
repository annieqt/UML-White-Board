package util.command;

import java.rmi.RemoteException;

import util.message.UserMsg;
import whiteboard.umldraw.structure.StructureDiagram;

import control.inter.ClientReceiver;

public class WhiteBoardCommand implements Command {
	private static final long serialVersionUID = 1L;
	private int roomID  = 0;
	private StructureDiagram diagram;
	private UserMsg sender;
	
	public WhiteBoardCommand(StructureDiagram diagram1, int roomID, UserMsg sender) { 
		this.roomID = roomID;
		diagram = diagram1;
		this.sender = sender;
	}
	
	@Override
	public void excuteOnClient(ClientReceiver client) throws RemoteException {
		client.getWBUpdateInfo(sender.id,diagram);
	}

	@Override
	public int getRoomId() {
		return roomID;
	}

	
}
