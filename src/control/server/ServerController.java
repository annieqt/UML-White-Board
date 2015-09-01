package control.server;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;

import util.message.UserMsg;

import module.server.Server;
import module.server.ServerOp;

public class ServerController implements ServerHoster{
	private ServerOp server;
	
	@Override
	public void createServer(String sname,String password) throws RemoteException, MalformedURLException, AlreadyBoundException, SQLException {
		ServerOp newServer=new Server(sname,password);
		server=newServer;
	}

	@Override
	public void closeServer() {
		if (server!=null) {
			server.close();
		}
	}

	@Override
	public void addUser(UserMsg usr) {
		server.addUser(usr);
	}

	@Override
	public void editUser(UserMsg usr) {
		server.editUser(usr);
	}

	@Override
	public void deleteUser(String id) {
		server.deleteUser(id);
	}

	@Override
	public UserMsg[] getAllUsers() throws SQLException {
		return server.getAllUsers();
	}

}
