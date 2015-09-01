
import java.net.InetAddress;
import java.net.UnknownHostException;
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
import javax.media.rtp.RTPManager;
import javax.media.rtp.SendStream;
import javax.media.rtp.SessionAddress;

import jmapps.util.StateHelper;

public class Sender {

	public static void main(String[] args) {
		try {// 设置本地地址和远端地址都为本机地址，自己传给自己，试验用
			SessionAddress local = new SessionAddress(InetAddress
					.getLocalHost(), 50000);
			SessionAddress target = new SessionAddress(InetAddress
					.getLocalHost(), 60000);
			new Sender(local, target);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

	public Sender(SessionAddress local, SessionAddress target) {
		 CaptureDeviceInfo info=null ;
		Processor p = null;
		Vector audioCapDevList = CaptureDeviceManager.getDeviceList(new AudioFormat(AudioFormat.LINEAR));

	        if ((audioCapDevList.size() > 0)) {
	            //或许有几个CaptureDevice,这里取第一个
	        	  info = (CaptureDeviceInfo) audioCapDevList.elementAt(0);
	        } else {
	            System.out.println("找不到音频采集设备");
	            System.exit(0);
	        }
		try {
			p = Manager.createProcessor(info.getLocator());
		} catch (Exception e) {
			e.printStackTrace();
		}
		StateHelper sh = new StateHelper(p);
		doSomeAudioProcess(p, sh);
		RTPManager mgr = RTPManager.newInstance();
		try {
			mgr.initialize(local);
			mgr.addTarget(target);
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
			Thread.sleep(60000);// 传送一分钟后关闭
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
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

}
