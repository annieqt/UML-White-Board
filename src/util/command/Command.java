package util.command;

import java.io.Serializable;
import java.rmi.RemoteException;

import control.inter.ClientReceiver;

public interface Command extends Serializable{
	/**在服务端调用该方法，让其中一个客户端来执行此命令。
	 * @param client  
	 * @throws RemoteException */
	public void excuteOnClient(ClientReceiver client) throws RemoteException;
	
	/** @return id of the effecting room	 */
	public int getRoomId();
}
