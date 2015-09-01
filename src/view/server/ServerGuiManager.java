package view.server;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;

import util.ApplicationResources;

import control.server.ServerController;
import control.server.ServerHoster;

public class ServerGuiManager {
	private JFrame frame;
	private JPanel nowPanel;	
	
	private static ServerGuiManager instance=new ServerGuiManager();
	
	private  ServerGuiManager() {
		setLookAndFeel();
		frame=new JFrame();
		frame.setTitle(ApplicationResources.getInstance().getString("server.title"));
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
	
	public static ServerGuiManager getInstance() {
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
		ServerSignView signView=new ServerSignView();
		toView(signView);
	}
	public void toServerView(String sname,String password) {
		ServerHoster controller = new ServerController();
		try {
			controller.createServer(sname,password);
			ServerView view=new ServerView(controller);		
			toView(view);
		}catch (SQLException e) {
			JOptionPane.showMessageDialog(null,ApplicationResources.getInstance().getString("server.err.wrongpass"));
		} catch (RemoteException e) {
			JOptionPane.showMessageDialog(null,ApplicationResources.getInstance().getString("server.err.servefailed"));
		} catch (MalformedURLException e) {
			JOptionPane.showMessageDialog(null,ApplicationResources.getInstance().getString("server.err.url"));
		} catch (AlreadyBoundException e) {
			JOptionPane.showMessageDialog(null,ApplicationResources.getInstance().getString("server.err.port"));
		}		
	}
}
