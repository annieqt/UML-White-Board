package module.inter;

import util.message.RoomMsg;
import util.message.UserMsg;

/**��Assist��鿴�ͻ�����Ϣ�õĽӿڡ�
 * @author Playeye */
public interface ClientInfoProvider {
	/** @return Զ�̵ķ���������	 */
	public RemoteMeetingServer getServer();

	/** @return ���ڵ�¼�ŵ��û�����Ϣ	 */
	public UserMsg getNowLog();
	
	/** @return �������ڵķ������Ϣ	 */
	public RoomMsg getNowRoom();
}
