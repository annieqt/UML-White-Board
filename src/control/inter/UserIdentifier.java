package control.inter;

import java.rmi.RemoteException;
import java.rmi.server.ServerNotActiveException;

/**用于登录界面的控制器接口，提供验证用户名密码的方法。
 * @author Playeye */
public interface UserIdentifier {
	/**验证输入的用户名和密码是否正确。
	 * @param sname
	 * @param password
	 * @return 是否登录成功 
	 * @throws ServerNotActiveException 
	 * @throws RemoteException */
	public boolean sign(String sname,String password, String ip) throws RemoteException, ServerNotActiveException;
}
