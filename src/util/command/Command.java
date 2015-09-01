package util.command;

import java.io.Serializable;
import java.rmi.RemoteException;

import control.inter.ClientReceiver;

public interface Command extends Serializable{
	/**�ڷ���˵��ø÷�����������һ���ͻ�����ִ�д����
	 * @param client  
	 * @throws RemoteException */
	public void excuteOnClient(ClientReceiver client) throws RemoteException;
	
	/** @return id of the effecting room	 */
	public int getRoomId();
}
