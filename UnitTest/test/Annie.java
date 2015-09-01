package test;

import java.rmi.RemoteException;
import java.rmi.server.ServerNotActiveException;

import control.client.ClientController;
import control.client.RoomController;
import control.client.SignController;

public class Annie {
	private SignController signer;
	private ClientController sender;
	
	public Annie(){
		init();
		
	}
	public void init(){
		signer = new SignController();
		new RoomController();
		try {
			sender = new ClientController();
			
			signer.sign("u3", "123","localhost");
			//room.createRoom("annie's", 6);
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (ServerNotActiveException e) {
			e.printStackTrace();
		}
		
		
		
	}
	public static void main(String[] args){
		Annie test = new Annie();
		//test.testChat();
		//test.testDownload();
		test.testUpload();
	}
	
	
	public void testChat(){
		try {
			sender.sendText("我是安妮");
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public void testUpload(){
		try {
			sender.upload("F:\\", "intro.txt");
			sender.upload("F:\\", "intro1.txt");//从本地F盘上传文件
			sender.upload("F:\\", "intro2.txt");//从本地F盘上传文件
			sender.upload("F:\\", "intro3.txt");//从本地F盘上传文件
		} catch (RemoteException e) {
			e.printStackTrace();
		}//从本地F盘上传文件
		
		
	}
	
	public void testDownload(){
		try {
			sender.download("D:\\", "intro.txt");
		} catch (RemoteException e) {
			e.printStackTrace();
		}//从服务器下载文件到本地D盘，服务器默认存储路径是E盘
		
	}
}