package module.inter;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.ServerNotActiveException;

import control.inter.ClientReceiver;

import util.command.Command;
import util.message.RoomMsg;
import util.message.RoomState;
import util.message.UserMsg;


/**服务器端供客户端远程调用的接口。
 * @author Playeye */
public interface RemoteMeetingServer extends Remote {

	/**用户登录系统，获得自身的信息。
	 * @param sname 用户名
	 * @param password 密码
	 * @return 用户的信息
	 * @param clientReceiver 客户端的远程引用
	 * @throws RemoteException */
	public UserMsg sign(String sname,String password) throws RemoteException, ServerNotActiveException;
	
	/**用户加入一个房间
	 * @param user 用户信息
	 * @param roomId 房间号
	 * @return 加入的房间信息 */
	public RoomMsg joinRoom(UserMsg user, int roomId ,ClientReceiver clientReceiver)throws RemoteException;

	/**用户新建一个房间
	 * @param user 用户信息
	 * @param name 房间名
	 * @param maxMem 最大人数
	 * @return 创建的房间信息
	 */
	public RoomMsg createRoom(UserMsg user, String name, int maxMem,ClientReceiver clientReceiver)throws RemoteException;
	
	/**让某用户退出房间，返回大厅	 
	 * @param user 用户信息
	 * @param room 房间信息*/
	public boolean exitRoom(UserMsg user)throws RemoteException;
	

	/**将一个命令对象传输到服务器去。
	 * @param c */
	public void sendCommand(Command c) throws RemoteException;
	/**
	 * 处理客户端的文件下载操作
	 * @param fileName 文件名称
	 * @return 文件内容，byte[]
	 * @throws RemoteException 
	 */
	public byte[] handleDownload(String fileName) throws RemoteException;
	/**
	 * 处理客户端的文件上传操作
	 * @param content 文件内容
	 * @param fileName 文件名称
	 */
	public void handleUpload(byte[] content, String fileName)throws RemoteException;
	/**
	 * 取得服务器所有的文件名称列表
	 * @return 文件名称列表
	 */
	public String[] getFileNames()throws RemoteException;
	
	public RoomState[] getRooms()throws RemoteException;
	
	public UserMsg[] getRoomUsers(int roomId)throws RemoteException;
	
	public void requestRefreshWB(UserMsg user)throws RemoteException;
	
}
