package control.inter;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import util.message.UserMsg;
import whiteboard.umldraw.structure.StructureDiagram;




/**服务器端回调客户端所用的接口。客户端必须要实现这个接口。
 * @author Playeye */

public interface ClientReceiver extends Remote{
	
	/**刷新文件列表。	 */
	public void refreshFileList() throws RemoteException;
	
	/**刷新房间成员列表。	 */
	public void refreshMemberList() throws RemoteException;
	
	/**在文字信息窗口中添加一条信息。	 */
	public void addText(UserMsg sender,String message) throws RemoteException;
	
	/**一个人刚进来，和其他所有人连接。
	 * @param ip 其他人的ip*/
	public void audioConnect(ArrayList<String> ip)throws RemoteException;
	
	/**大家和一个刚进来的人连接。
	 * @param ip */
	public void audioConnect(String ip,int No)throws RemoteException;
	
	/**
	 * 更新白板内容
	 * @throws RemoteException
	 */
	public void getWBUpdateInfo(String senderId,StructureDiagram diagram) throws RemoteException;
	
	/** 服务器要求此白板主动发送初始化更新
	 */
	public void sendWBInitUpdate() throws RemoteException;
}
