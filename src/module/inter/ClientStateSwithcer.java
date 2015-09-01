package module.inter;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.ServerNotActiveException;

public interface ClientStateSwithcer {
	/**将本客户端连接到服务器。
	 * @param clientReceiver 客户端远程引用
	 * @param ipString 服务器所在IP
	 * @return 连接到的服务器对象
	 * @throws MalformedURLException 无效的IP
	 * @throws RemoteException 远程连接出错
	 * @throws NotBoundException 找不到服务器
	 * @throws ServerNotActiveException 
	 */
	public RemoteMeetingServer connect(String ipString) throws MalformedURLException, RemoteException, NotBoundException, ServerNotActiveException;
	
	/**关闭此客户端和服务器的连接，并在关闭前告知服务器自己的编号。
	 * @throws RemoteException */
	public void close() throws RemoteException;
	
	/**让用户登录
	 * @param sname
	 * @param password
	 * @return 登录的用户
	 */
	public boolean logOn(String sname, String password, String ip)throws RemoteException,ServerNotActiveException;
	
	/**对应用户加入对应id的房间。
	 * @param roomId
	 * @return 是否加入成功 
	 * @throws RemoteException */
	public boolean joinRoom(int roomId) throws RemoteException;
	
	/**对应用户创建房间。
	 * @param id 不可重复的id
	 * @param name 房间名
	 * @param maxMem 最大人数
	 * @return 创建的房间信息	 
	 * @throws RemoteException */
	public boolean createRoom(String name,int maxMem) throws RemoteException;
	
	public void refreshRoomList() throws RemoteException;

}
