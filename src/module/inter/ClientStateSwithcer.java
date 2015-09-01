package module.inter;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.ServerNotActiveException;

public interface ClientStateSwithcer {
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
	public void close() throws RemoteException;
	
	/**���û���¼
	 * @param sname
	 * @param password
	 * @return ��¼���û�
	 */
	public boolean logOn(String sname, String password, String ip)throws RemoteException,ServerNotActiveException;
	
	/**��Ӧ�û������Ӧid�ķ��䡣
	 * @param roomId
	 * @return �Ƿ����ɹ� 
	 * @throws RemoteException */
	public boolean joinRoom(int roomId) throws RemoteException;
	
	/**��Ӧ�û��������䡣
	 * @param id �����ظ���id
	 * @param name ������
	 * @param maxMem �������
	 * @return �����ķ�����Ϣ	 
	 * @throws RemoteException */
	public boolean createRoom(String name,int maxMem) throws RemoteException;
	
	public void refreshRoomList() throws RemoteException;

}
