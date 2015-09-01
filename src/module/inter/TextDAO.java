package module.inter;

import java.rmi.RemoteException;

import util.message.UserMsg;

public interface TextDAO {
	/**����һ��������Ϣ��
	 * @param sender ������
	 * @param text ��Ϣ�����ݡ�	 
	 * @throws RemoteException */
	public void sendText(String text) throws RemoteException;
	
	
	/**���һ��������Ϣ�����ڡ�
	 * @param sender ������
	 * @param message ��Ϣ����
	 */
	public void addText(UserMsg sender, String message) ;
}
