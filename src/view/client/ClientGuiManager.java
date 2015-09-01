package view.client;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;

import util.ApplicationResources;
import view.inter.MainClientRefresher;
import control.client.RoomController;
import control.client.SignController;
import control.inter.UserIdentifier;

public class ClientGuiManager {
	private JFrame frame;
	private JPanel nowPanel;
	
	private ClientSignView signView;
	private RoomChooseView roomView;
	private ClientMainView mainView;
	
	
	private static ClientGuiManager instance=new ClientGuiManager();
	
	private  ClientGuiManager() {
		setLookAndFeel();
		frame=new JFrame();
		frame.setTitle(ApplicationResources.getInstance().getString("client.title"));
		frame.setBackground(Color.BLACK);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1200, 750);
		//frame.setUndecorated(true);
		//frame.getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG); 
		frame.getRootPane().setWindowDecorationStyle(JRootPane.QUESTION_DIALOG);
		frame.setLocationRelativeTo(null);
		
	}
	
	private static void setLookAndFeel() {
		// Set Look & Feel
		try {
			javax.swing.UIManager
					.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static ClientGuiManager getInstance() {
		return instance;
	}
	
	public void showDialog(JDialog dialog) {
		 Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();   //获取当前屏幕大小
		 Dimension frameSize = dialog.getPreferredSize();//获取当前窗口大小
		 dialog.setLocation((screenSize.width - frameSize.width)/2, (screenSize.height - frameSize.height)/2);//保持窗口弹出位置居中
		 dialog.setModal(true);
		 dialog.setVisible(true);

	}
	
	private void toView(JPanel newPanel) {
		
		if (nowPanel!=null) {
			frame.remove(nowPanel);
		}
		nowPanel=newPanel;
		if (nowPanel!=null) {
			frame.add(nowPanel);
		}
		frame.pack();
		frame.validate();
		frame.setVisible(true);
	}
	
	public void toSignView() {
		frame.getContentPane().removeAll();
		UserIdentifier signController=new SignController();
		signView=new ClientSignView(signController);
		toView(signView);
		frame.setJMenuBar(null);		
	}
	public void toRoomView() {
		frame.getContentPane().removeAll();
		RoomController roomController=new RoomController();
		roomView = new RoomChooseView(roomController);
		toView(roomView);
		frame.setJMenuBar(null);
	}
	public void toMainView() {
		mainView=new ClientMainView(frame);
		toView(mainView);
		mainView.refresh();
	}

	public static MainClientRefresher getMainClientRefresher() {
		return instance.mainView;
	}

	public static void showMessage(String text) {
		JOptionPane.showMessageDialog(null, text);
	}
}
