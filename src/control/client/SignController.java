package control.client;

import java.rmi.RemoteException;
import java.rmi.server.ServerNotActiveException;

import module.client.ClientInfoManager;
import module.inter.ClientStateSwithcer;
import control.inter.UserIdentifier;

public class SignController implements UserIdentifier{
	private ClientStateSwithcer swithcer = ClientInfoManager.getInstance();
	
	@Override
	public boolean sign(String sname, String password, String ip) throws RemoteException, ServerNotActiveException {
		boolean success=swithcer.logOn(sname, password, ip);
		return success;
	}
}
