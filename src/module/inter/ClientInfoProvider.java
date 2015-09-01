package module.inter;

import util.message.RoomMsg;
import util.message.UserMsg;

/**让Assist类查看客户端信息用的接口。
 * @author Playeye */
public interface ClientInfoProvider {
	/** @return 远程的服务器引用	 */
	public RemoteMeetingServer getServer();

	/** @return 现在登录着的用户的信息	 */
	public UserMsg getNowLog();
	
	/** @return 现在所在的房间的信息	 */
	public RoomMsg getNowRoom();
}
