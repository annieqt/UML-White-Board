package module.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

import javax.media.CaptureDeviceInfo;
import javax.media.CaptureDeviceManager;
import javax.media.Manager;
import javax.media.Processor;
import javax.media.control.FormatControl;
import javax.media.control.TrackControl;
import javax.media.format.AudioFormat;
import javax.media.protocol.ContentDescriptor;
import javax.media.protocol.DataSource;
import javax.media.protocol.PushBufferDataSource;
import javax.media.protocol.PushBufferStream;
import javax.media.rtp.InvalidSessionAddressException;
import javax.media.rtp.RTPManager;
import javax.media.rtp.SendStream;
import javax.media.rtp.SessionAddress;

import jmapps.util.StateHelper;

public class Sender  implements Runnable{

/*	public static void main(String[] args) { 
		try {
			SessionAddress local = new SessionAddress(InetAddress
					.getLocalHost(), 50000);
			SessionAddress target = new SessionAddress(InetAddress
					.getByName("playeye-sama"), 60000);
			System.out.println(InetAddress
					.getByName("playeye-sama"));
			new Sender(local, target);
			new Receiver(local, target);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}  */
	CaptureDeviceInfo info;
	Processor p;
	RTPManager mgr;
	SessionAddress local;
	 ArrayList<SessionAddress> target;
	public Sender(SessionAddress local, ArrayList<SessionAddress> target) {
		  info=null ;
		 p = null;
		 this.local=local;
		 this.target=target;
		Vector<?> audioCapDevList = CaptureDeviceManager.getDeviceList(new AudioFormat(AudioFormat.LINEAR));
	
	        if ((audioCapDevList.size() > 0)) {
	            //或许有几个CaptureDevice,这里取第一个
	        	  info = (CaptureDeviceInfo) audioCapDevList.elementAt(0);
	        } else {
	            System.out.println("找不到音频采集设备");
	            System.exit(0);
	        }
	
	}
	protected void addTarget(SessionAddress newAddr){
		try {
			mgr.addTarget(newAddr);
		} catch (InvalidSessionAddressException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	private static void doSomeAudioProcess(Processor p, StateHelper sh) {
		sh.configure(5000);
		p.setContentDescriptor(new ContentDescriptor(ContentDescriptor.RAW));
		setAudioTrackFormat(p.getTrackControls());
		sh.realize(5000);
	}
    
	private static void setAudioTrackFormat(TrackControl[] trackControls) {
		boolean ok = false;
		for (TrackControl t : trackControls) {
			if (!ok && t instanceof FormatControl) {
				if (((FormatControl) t).setFormat(new AudioFormat(
						AudioFormat.ULAW_RTP, 8000, 8, 1)) == null)
					t.setEnabled(false);
				else
					ok = true;
			} else {
				t.setEnabled(false);
			}
		}
	}
    protected void disconnect(){
    	if (p != null) {
			p.stop();
			p.close();
		}
		if (mgr != null) {
			mgr.removeTargets("client disconnnected");
			mgr.dispose();
			mgr = null;
		}
    }
	public void run() {
		try {
			p = Manager.createProcessor(info.getLocator());
		} catch (Exception e) {
			e.printStackTrace();
		}
		StateHelper sh = new StateHelper(p);
		doSomeAudioProcess(p, sh);
		 mgr = RTPManager.newInstance();
		try {
			mgr.initialize(local);
			for(int i=0;i<target.size();i++){        //增加需传输的目标
				mgr.addTarget(target.get(i));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		DataSource ds = p.getDataOutput();
		PushBufferDataSource pbds = (PushBufferDataSource) ds;
		PushBufferStream pbss[] = pbds.getStreams();
		for (int i = 0; i < pbss.length; i++) {
			try {
				SendStream ss = mgr.createSendStream(ds, i);
				ss.start();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		p.start();
		try {
			Thread.sleep(120000000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if (p != null) {
			p.stop();
			p.close();
		}
		if (mgr != null) {
			mgr.removeTargets("cliennt disconnnected");
			mgr.dispose();
			mgr = null;
		}
		
	}
	
}
