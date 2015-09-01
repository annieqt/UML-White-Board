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
        //这里可以填写其它的音频编码格式，具体请看AudioFormat类,为了适用大部分录音设备，只限定格式为linear
        audioCapDevList = CaptureDeviceManager.getDeviceList(new AudioFormat(AudioFormat.LINEAR));

        if ((audioCapDevList.size() > 0)) {
            //或许有几个CaptureDevice,这里取第一个
            audioCapDevInfo = (CaptureDeviceInfo) audioCapDevList.elementAt(0);
            audioCapDevLoc = audioCapDevInfo.getLocator();
        } else {
            System.out.println("找不到音频采集设备");
            System.exit(0);
        }

    }

  

    public void initAudioPlayer() {

        try {
            audioPlayer = Manager.createPlayer(audioCapDevLoc);
        } catch (NoPlayerException ex) {
        } catch (IOException ex) {
        }        
        //增加一个侦听器，侦听player状态的改变
        audioPlayer.addControllerListener(this);
        audioPlayer.realize();
    }


    public void stopAndClosePlayer(){
        audioPlayer.stop();
        audioPlayer.close();
    }

    public synchronized void controllerUpdate(ControllerEvent ce) {  //做窗口的东西
        Player p = (Player) ce.getSourceController();  //拿到事件对应的播放器

        JFrame jFrame = new JFrame();
        Component com;                                //临时图形组件

        if (p == null) {
            return;
        }

        //如果player的状态变为Realized
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
            Thread.sleep(20000);//20秒后停止并关闭播放器
        } catch (InterruptedException ex) {
        }
        cap.stopAndClosePlayer();
    }
}