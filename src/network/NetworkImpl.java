package network;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.ServerNotActiveException;

import util.message.UserMsg;

import module.inter.RemoteMeetingServer;
import module.server.Server;

/**这是网络的接口实现
 * @author Playeye */
public class NetworkImpl implements NetworkInter {
	private RemoteMeetingServer server;			//本机所连接到的服务器的远程引用
	
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
