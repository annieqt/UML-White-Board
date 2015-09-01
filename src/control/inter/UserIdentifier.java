package control.inter;

import java.rmi.RemoteException;
import java.rmi.server.ServerNotActiveException;

/**���ڵ�¼����Ŀ������ӿڣ��ṩ��֤�û�������ķ�����
 * @author Playeye */
public interface UserIdentifier {
	/**��֤������û����������Ƿ���ȷ��
	 * @param sname
	 * @param password
	 * @return �Ƿ��¼�ɹ� 
	 * @throws ServerNotActiveException 
	 * @throws RemoteException */
	public boolean sign(String sname,String password, String ip) throws RemoteException, ServerNotActiveException;
}
