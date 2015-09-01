package module.inter;

import java.rmi.RemoteException;

public interface RoomMemberDAO {
	/**刷新房间成员列表。	 
	 * @throws RemoteException */
	public void refreshMemberList() throws RemoteException;
	
	public void exitRoom()throws RemoteException;

}
