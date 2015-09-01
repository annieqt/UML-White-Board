package control.inter;

import java.rmi.RemoteException;

/**用户在房间选择界面里的操作接口。提供用户加入房间的方法。
 * @author Playeye */
public interface RoomChooser {
	/**登录于此的用户加入对应id的房间。
	 * @param id
	 * @return 加入是否成功 */
	public boolean joinRoom(int id);
	
	/**登录的用户创建一个新的房间
	 * @param name 房间名称
	 * @param maxMem 最大人数
	 * @return 创建是否成功	 */
	public boolean createRoom(String name,int maxMem);

	/**刷新房间列表。	 
	 * @throws RemoteException */
	public void refreshRoomList() throws RemoteException;
}
