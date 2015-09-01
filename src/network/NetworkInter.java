package network;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.ServerNotActiveException;

import util.message.UserMsg;

import module.inter.RemoteMeetingServer;

/**系统提供的关于网络连接状态的接口。
 * @author Playeye */
public interface NetworkInter {
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
	public void close(UserMsg nowLog) throws RemoteException;
	
	public RemoteMeetingServer getServer();
}
