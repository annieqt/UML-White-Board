package control.inter;

import java.rmi.RemoteException;

import whiteboard.umldraw.structure.StructureDiagram;


/**客户端用于发送信息的接口。此接口负责与服务器接口进行连接。
 * @author Playeye */
public interface ClientSender {
	
	/**上传一个文件到服务器。
	 * @param filePath 上传的文件在上传者机器的地址
	 * @param filename 文件名 
	 * @throws RemoteException */
	public void upload(String filePath,String filename) throws RemoteException;
	
	/**下载一个文件到本机。
	 * @param putPath 要下载的文件放置在本机的地址
	 * @param filename 文件名
	 * @throws RemoteException */
	public void download(String putPath,String filename) throws RemoteException;
		
	/**发送一条文字信息。
	 * @param text 消息的内容。	 
	 * @throws RemoteException */
	public void sendText(String text) throws RemoteException;
	
	public void refreshAll() throws RemoteException;
	
	public void disconnect() throws RemoteException;

	
	/**调节音响音量
	 * @param v	 */
	public void setSoundVolume(int v);
	
	/**调节音响静音
	 * @param v	 */
	public void setSoundMute(boolean mute,int volumn);
	
	/**
	 * 发送白板的更新信息
	 */
	public void sendWBUpdateInfo(StructureDiagram diagram) throws RemoteException;
	
}
