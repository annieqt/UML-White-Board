package module.client;

import java.net.InetAddress;
import java.util.ArrayList;

import javax.media.rtp.SessionAddress;

import module.inter.ClientInfoProvider;
import module.inter.SoundDAO;

public class SoundAssist extends Assistant implements SoundDAO {
	private Sender sender;

	private ArrayList<Receiver> receivers = new ArrayList<Receiver>();

	private static int sendPort = 50000;

	private static int receiveBasePort = 60000;

	public SoundAssist(ClientInfoProvider client) {
		super(client);
		sender = null;
	}

	// ------------------------------just for t&e------------------
	public static void main(String[] args) {
		try {
			String localIP = "mp-hp";
			ArrayList<String> ips = new ArrayList<String>();
			ips.add(localIP);
			SoundAssist sa = new SoundAssist(ClientInfoManager.getInstance());
			sa.audioConnect(ips);
			System.out.println("gogogo");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ----------------------------------------------------------
	@Override
	public void audioConnect(ArrayList<String> ip) {

		try {

			for (int i = 0; i < ip.size(); i++) {
				if (InetAddress.getByName(ip.get(i)) != InetAddress
						.getLocalHost()) 
					receivers
							.add(new Receiver(new SessionAddress(InetAddress
									.getLocalHost(), receiveBasePort + 2 * i),
									new SessionAddress(InetAddress.getByName(ip
											.get(i)), sendPort)));
			}// 打开接收端

			SessionAddress local = new SessionAddress(
					InetAddress.getLocalHost(), sendPort);
			ArrayList<SessionAddress> targets = new ArrayList<SessionAddress>();
			;
			for (int i = 0; i < ip.size(); i++) {
				if (InetAddress.getByName(ip.get(i)) != InetAddress
						.getLocalHost())
					targets.add(new SessionAddress(InetAddress.getByName(ip
							.get(i)), receiveBasePort + 2 * i));
			}

			sender = new Sender(local, targets);
			System.out.println("我开个sender");
			new Thread(sender).start();
			// 打开发送端
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void audioConnect(String ip,int No) { // 只和一个人连
		try {
			SessionAddress neoSTarget = new SessionAddress(
					InetAddress.getByName(ip), sendPort);
			if (sender == null) {
				System.out.println("我开个sender");
				SessionAddress local = new SessionAddress(
						InetAddress.getLocalHost(), sendPort);
				ArrayList<SessionAddress> targets = new ArrayList<SessionAddress>();
				targets.add(new SessionAddress(InetAddress.getByName(ip),
						receiveBasePort+2*No));     
				sender = new Sender(local, targets);
				new Thread(sender).start();
			}
			// -------------------------以上sender还未链接目标-----------------------------
			else {
				System.out.println("我加个target");
				sender.addTarget(new SessionAddress(
						InetAddress.getByName(ip), receiveBasePort + 2
						* (No)));
			}
			SessionAddress neoRTarget = new SessionAddress(
					InetAddress.getLocalHost(), receiveBasePort + 2
							* (No));
			receivers.add(new Receiver(neoRTarget, neoSTarget));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void audioDisconnect() {
		if (sender!=null) {
			sender.disconnect();
		}		
	}


	@Override
	public void setSoundVolume(int v) {
		for (int i = 0; i < receivers.size(); i++) {
			receivers.get(i).setVolumn((float) v/100);
		}

	}

	@Override
	public void setSoundMute(boolean mute,int volumn) {
		if(mute){
		for (int i = 0; i < receivers.size(); i++) {
			receivers.get(i).setVolumn(0);
		}
		}
		else{
			for (int i = 0; i < receivers.size(); i++) {
				receivers.get(i).setVolumn(volumn);
			}
		}
		
	}

}
