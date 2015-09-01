package network;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.ServerNotActiveException;

import util.message.UserMsg;

import module.inter.RemoteMeetingServer;
import module.server.Server;

/**��������Ľӿ�ʵ��
 * @author Playeye */
public class NetworkImpl implements NetworkInter {
	private RemoteMeetingServer server;			//���������ӵ��ķ�������Զ������
	
	@Override
	public RemoteMeetingServer connect(String ipString) throws MalformedURLException,
			RemoteException, NotBoundException, ServerNotActiveException {
		server=(RemoteMeetingServer) Naming.lookup("rmi://"+ipString+":"+Server.SERVER_PORT+"/"+Server.SERVER_NAME);
		return server;		
	}
	
	@Override
	public void close(UserMsg nowLog) throws RemoteException {
		server.exitRoom(nowLog);
	}
	
	@Override
	public RemoteMeetingServer getServer() {
		return server;
	}	

}
