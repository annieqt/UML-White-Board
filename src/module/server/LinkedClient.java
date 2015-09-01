package module.server;

import util.message.RoomMsg;
import util.message.UserMsg;
import control.inter.ClientReceiver;

public class LinkedClient {
	final ClientReceiver receiver;
	final UserMsg userMsg;
	final String clientIp;
	private RoomMsg roomMsg;
	
	LinkedClient(ClientReceiver client,	UserMsg userMsg, String clientIp){
		this.receiver=client;
		this.userMsg=userMsg;
		this.clientIp=clientIp;
	}
	
	protected RoomMsg getRoomMsg() {
		return roomMsg;
	}

	protected void setRoomMsg(RoomMsg roomMsg) {
		this.roomMsg = roomMsg;
	}
	
}
