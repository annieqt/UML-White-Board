import java.awt.*;
import java.io.*;
import java.util.*;
import javax.media.*;
import javax.media.format.*;
import javax.sound.sampled.TargetDataLine;
import javax.swing.*;

public class CaptureAndPlay implements ControllerListener {

    Vector audioCapDevList = null;
    CaptureDeviceInfo audioCapDevInfo = null;
    MediaLocator audioCapDevLoc = null;
    Player audioPlayer;
    TargetDataLine line;
    public void initAudioCapDevLoc() {
        //���������д��������Ƶ�����ʽ�������뿴AudioFormat��,Ϊ�����ô󲿷�¼���豸��ֻ�޶���ʽΪlinear
        audioCapDevList = CaptureDeviceManager.getDeviceList(new AudioFormat(AudioFormat.LINEAR));

        if ((audioCapDevList.size() > 0)) {
            //�����м���CaptureDevice,����ȡ��һ��
            audioCapDevInfo = (CaptureDeviceInfo) audioCapDevList.elementAt(0);
            audioCapDevLoc = audioCapDevInfo.getLocator();
        } else {
            System.out.println("�Ҳ�����Ƶ�ɼ��豸");
            System.exit(0);
        }

    }

  

    public void initAudioPlayer() {

        try {
            audioPlayer = Manager.createPlayer(audioCapDevLoc);
        } catch (NoPlayerException ex) {
        } catch (IOException ex) {
        }        
        //����һ��������������player״̬�ĸı�
        audioPlayer.addControllerListener(this);
        audioPlayer.realize();
    }


    public void stopAndClosePlayer(){
        audioPlayer.stop();
        audioPlayer.close();
    }

    public synchronized void controllerUpdate(ControllerEvent ce) {  //�����ڵĶ���
        Player p = (Player) ce.getSourceController();  //�õ��¼���Ӧ�Ĳ�����

        JFrame jFrame = new JFrame();
        Component com;                                //��ʱͼ�����

        if (p == null) {
            return;
        }

        //���player��״̬��ΪRealized
        if (ce instanceof RealizeCompleteEvent) {

            if ((com = p.getControlPanelComponent()) != null) {
                jFrame.add(com, BorderLayout.SOUTH);
            }

            if ((com = p.getVisualComponent()) != null) {
                jFrame.add(com, BorderLayout.NORTH);
            }
            jFrame.setVisible(true);
            jFrame.pack();
            jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            p.start();
        }
    }

    public static void main(String args[]) {
        CaptureAndPlay cap = new CaptureAndPlay();
        cap.initAudioCapDevLoc();
        cap.initAudioPlayer();

        try {
            Thread.sleep(20000);//20���ֹͣ���رղ�����
        } catch (InterruptedException ex) {
        }
        cap.stopAndClosePlayer();
    }
}