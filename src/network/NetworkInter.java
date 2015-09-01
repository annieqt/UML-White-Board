package network;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.ServerNotActiveException;

import util.message.UserMsg;

import module.inter.RemoteMeetingServer;

/**ϵͳ�ṩ�Ĺ�����������״̬�Ľӿڡ�
 * @author Playeye */
public interface NetworkInter {
	/**�����ͻ������ӵ���������
	 * @param clientReceiver �ͻ���Զ������
	 * @param ipString ����������IP
	 * @return ���ӵ��ķ���������
	 * @throws MalformedURLException ��Ч��IP
	 * @throws RemoteException Զ�����ӳ���
	 * @throws NotBoundException �Ҳ���������
	 * @throws ServerNotActiveException 
	 */
	public RemoteMeetingServer connect(String ipString) throws MalformedURLException, RemoteException, NotBoundException, ServerNotActiveException;
	
	/**�رմ˿ͻ��˺ͷ����������ӣ����ڹر�ǰ��֪�������Լ��ı�š�
	 * @throws RemoteException */
	public void close(UserMsg nowLog) throws RemoteException;
	
	public RemoteMeetingServer getServer();
}
