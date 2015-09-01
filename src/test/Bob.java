package test;

import java.rmi.RemoteException;
import java.rmi.server.ServerNotActiveException;

import control.client.ClientController;
import control.client.RoomController;
import control.client.SignController;

public class Bob{
	private SignController signer;
	private RoomController room;
	private ClientController sender;
	
	
	public Bob(){
		init();
		
	}
	public void init(){
		signer = new SignController();
		room = new RoomController();
		try {
			sender = new ClientController();
			signer.sign("u3", "123","localhost");
			room.joinRoom(1);
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (ServerNotActiveException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args){
		Bob test = new Bob();
		test.testChat();
		//test.testDownload();
		//test.testUpload();
	}
	
	
	public void testChat(){
		try {
			sender.sendText("Œ“ «BOB");
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	
	public void testUpload(){
		
		
		
	}
	
	public void testDownload(){
		
		
	}
}