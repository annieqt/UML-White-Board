package module.inter;

import java.rmi.RemoteException;

public interface RoomMemberDAO {
	/**ˢ�·����Ա�б�	 
	 * @throws RemoteException */
	public void refreshMemberList() throws RemoteException;
	
	public void exitRoom()throws RemoteException;

}
