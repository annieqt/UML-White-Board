package module.inter;

import java.rmi.RemoteException;

import util.message.UserMsg;

public interface TextDAO {
	/**发送一条文字信息。
	 * @param sender 发送者
	 * @param text 消息的内容。	 
	 * @throws RemoteException */
	public void sendText(String text) throws RemoteException;
	
	
	/**添加一条文字消息到窗口。
	 * @param sender 发送者
	 * @param message 信息内容
	 */
	public void addText(UserMsg sender, String message) ;
}
