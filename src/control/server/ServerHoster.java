package control.server;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;

import util.message.UserMsg;

public interface ServerHoster {
	/**创建可用的服务器
	 * @throws RemoteException
	 * @throws MalformedURLException
	 * @throws AlreadyBoundException
	 * @throws SQLException 
	 */
	public void createServer(String sname, String password) throws RemoteException,
		MalformedURLException, AlreadyBoundException, SQLException;
	
	
	/**关闭服务器 */
	public void closeServer();
	
	/**按照给出的信息添加一个用户。
	 * @param usr */
	public void addUser(UserMsg usr);
	
	/**按照给出的信息，把对应id的用户信息以usr信息覆盖。
	 * @param usr */
	public void editUser(UserMsg usr);
	
	/**删掉对应id的用户。
	 * @param id */
	public void deleteUser(String id);
	
	public UserMsg[] getAllUsers() throws SQLException;
}
