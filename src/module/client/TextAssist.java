package module.client;

import java.rmi.RemoteException;

import util.command.AddTextCommand;
import util.message.UserMsg;
import module.inter.ClientInfoProvider;
import module.inter.TextDAO;

public class TextAssist extends Assistant implements TextDAO{
	public TextAssist(ClientInfoProvider client) {
		super(client);
	}

	public void sendText(String text) throws RemoteException {
		AddTextCommand c = new AddTextCommand(getNowLog(),getNowRoom().id,text);
		getServer().sendCommand(c);
	}
	
	public void addText(UserMsg sender, String message) {
		
		String complete;
		if (sender == null){
			complete = message;
		}else{
			complete = sender.name + "£º" + message;
		}
		getClientView().addText(complete);
		System.out.println(complete);
	}

}
