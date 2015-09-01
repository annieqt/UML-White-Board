package control.server;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;

import util.message.UserMsg;

public interface ServerHoster {
	/**�������õķ�����
	 * @throws RemoteException
	 * @throws MalformedURLException
	 * @throws AlreadyBoundException
	 * @throws SQLException 
	 */
	public void createServer(String sname, String password) throws RemoteException,
		MalformedURLException, AlreadyBoundException, SQLException;
	
	
	/**�رշ����� */
	public void closeServer();
	
	/**���ո�������Ϣ���һ���û���
	 * @param usr */
	public void addUser(UserMsg usr);
	
	/**���ո�������Ϣ���Ѷ�Ӧid���û���Ϣ��usr��Ϣ���ǡ�
	 * @param usr */
	public void editUser(UserMsg usr);
	
	/**ɾ����Ӧid���û���
	 * @param id */
	public void deleteUser(String id);
	
	public UserMsg[] getAllUsers() throws SQLException;
}
