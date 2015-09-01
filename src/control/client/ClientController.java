package control.client;

import control.inter.*;
import module.client.ClientInfoManager;
import module.client.FileAssist;
import module.client.RoomAssist;
import module.client.SoundAssist;
import module.client.TextAssist;
import module.client.WhiteBoardAssist;
import module.inter.*;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import util.message.UserMsg;
import whiteboard.umldraw.structure.StructureDiagram;


/**客户端的控制器类，用以将界面上的信息转发给数据层。
 * @author Playeye */
public class ClientController extends UnicastRemoteObject 
		implements ClientSender,ClientReceiver{
	private static final long serialVersionUID = 1L;
	
	private ClientInfoManager swithcer;
	
	private FileDAO fileAssist;
	private RoomMemberDAO roomAssist;
	private TextDAO textAssist;
	private SoundDAO soundAssit;
    private WhiteBoardDAO whiteBoardAssist;

    public ClientController() throws RemoteException {
		super();
		swithcer=ClientInfoManager.getInstance();
		fileAssist=new FileAssist(swithcer);
		roomAssist=new RoomAssist(swithcer);
		textAssist=new TextAssist(swithcer);
		soundAssit=new SoundAssist(swithcer);
		whiteBoardAssist=new WhiteBoardAssist(swithcer);
	}

	@Override
	public void upload(String filePath, String fileName) throws RemoteException {
		fileAssist.upload(filePath, fileName);
	}

	@Override
	public void download(String putPath, String fileName) throws RemoteException {
		fileAssist.download(putPath, fileName);
	}	

	@Override
	public void sendText(String text) throws RemoteException {
		textAssist.sendText(text);
	}

	@Override
	public void refreshFileList() throws RemoteException {
		fileAssist.refreshFileList();
	}

	@Override
	public void refreshMemberList() throws RemoteException {
		roomAssist.refreshMemberList();
	}
	
	public void refreshAll() throws RemoteException {
		refreshFileList();
		refreshMemberList();
		whiteBoardAssist.requestWBInitUpdate();
	}

	@Override
	public void addText(UserMsg sender, String message) throws RemoteException{
		textAssist.addText(sender, message);
	}

	@Override
	public void audioConnect(String ip,int No) throws RemoteException{
		soundAssit.audioConnect(ip,No);
	}
	public void audioConnect(ArrayList<String> ips) throws RemoteException{
		soundAssit.audioConnect(ips);
	}
	@Override
	public void disconnect() throws RemoteException{
		roomAssist.exitRoom();
		soundAssit.audioDisconnect();
	}


	@Override
	public void setSoundVolume(int v) {
		soundAssit.setSoundVolume(v);
	}

	@Override
	public void setSoundMute(boolean mute,int volumn) {
		soundAssit.setSoundMute(mute,volumn);
	}

	@Override
	public void getWBUpdateInfo(String senderId , StructureDiagram diagram) throws RemoteException {
		whiteBoardAssist.getWBUpdateInfo(senderId,diagram);
	}

	@Override
	public void sendWBUpdateInfo(StructureDiagram diagram) throws RemoteException {
		whiteBoardAssist.sendWBUpdateInfo(diagram);
		System.out.println("发送方到达control");
	}

	@Override
	public void sendWBInitUpdate() throws RemoteException {
		whiteBoardAssist.sendWBInitUpdate();
	}

	
}
