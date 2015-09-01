package module.client;

import util.command.WhiteBoardCommand;
import util.message.UserMsg;
import whiteboard.umldraw.structure.StructureDiagram;
import module.inter.ClientInfoProvider;
import module.inter.WhiteBoardDAO;

import java.rmi.RemoteException;

/**
 * Created by IntelliJ IDEA.
 * User: qrs
 * Date: 12-3-31
 * Time: 7:40
 * To change this template use File | Settings | File Templates.
 */
public class WhiteBoardAssist extends Assistant implements WhiteBoardDAO {
    
	public WhiteBoardAssist(ClientInfoProvider client) {
		super(client);
	}

	@Override
	public void sendWBUpdateInfo(StructureDiagram diagram) throws RemoteException {
		if(getNowLog()==null){
			System.out.println("fuck!it's a null");
		}else{
			System.out.println("no!it's not a null");
		}
		WhiteBoardCommand command = new WhiteBoardCommand(diagram, getNowRoom().id, getNowLog());
		getServer().sendCommand(command);
		System.out.println("·¢ËÍcommand");
	}

	@Override
	public void getWBUpdateInfo(String senderId,StructureDiagram diagram) {
		ClientInfoManager infoManager = ClientInfoManager.getInstance();
		UserMsg msg = infoManager.getNowLog();
		if(!senderId.equals(msg.id)) {
			if(diagram == null)
				System.out.println("diagram1 is null");
			else {
				System.out.println("diagram1 is not null");
			}
			if(getClientView() == null)
				System.out.println("client is null");
			else {
				System.out.println("client is not null");
			}
			getClientView().updateWBInfo(diagram);			
		}		
	}
	@Override
	public void sendWBInitUpdate() throws RemoteException {
		StructureDiagram diagram = getClientView().getDiagram();
		if(diagram == null)
			System.out.println("diagram is null");
		else {
			System.out.println("diagram is not null");
		}
		WhiteBoardCommand command = new WhiteBoardCommand(diagram, getNowRoom().id, getNowLog());
		getServer().sendCommand(command);
	}

	@Override
	public void requestWBInitUpdate() throws RemoteException {
		getServer().requestRefreshWB(getNowLog());
	}

	
}
