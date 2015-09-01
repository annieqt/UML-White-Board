package module.client;

import util.message.RoomMsg;
import util.message.UserMsg;
import view.client.ClientGuiManager;
import view.inter.MainClientRefresher;
import module.inter.ClientInfoProvider;
import module.inter.RemoteMeetingServer;

/**����������ר�Ÿ������ͻ��������ִ�к�ʵ�����⡣
 * @author Playeye */
public class Assistant {
	private final ClientInfoProvider client;
	
	public Assistant(ClientInfoProvider client){
		this.client=client;
	}
	
	protected RemoteMeetingServer getServer() {
		return client.getServer();
	}

	protected MainClientRefresher getClientView() {
		return ClientGuiManager.getMainClientRefresher();
	}

	protected UserMsg getNowLog() {
		return client.getNowLog();
	}
	
	protected RoomMsg getNowRoom() {
		return client.getNowRoom();
	}
}
