package view.server;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import control.server.ServerHoster;
import util.ApplicationResources;
import util.message.UserMsg;
import view.TableOperator;

/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class ServerView extends javax.swing.JPanel {
	private static final long serialVersionUID = 1L;
	
	private ServerHoster server;
	private JScrollPane clientScrollPane;
	private JLabel clientLabel;
	private JButton jButton1;
	private JLabel ipLabel;
	private JButton deleteButton;
	private JButton changeButton;
	private JButton addButton;
	private JTable clientTable;

	/**
	* Auto-generated main method to display this 
	* JPanel inside a new JFrame.
	*/
	
	public ServerView(ServerHoster server) {
		super();
		this.server = server;
		initGUI();
		refreshTable();
	}
	
	private void initGUI() {
		try {
			
			setPreferredSize(new Dimension(1000, 700));
			this.setLayout(null);
			{
				clientScrollPane = new JScrollPane();
				this.add(clientScrollPane);
				clientScrollPane.setBounds(44, 95, 688, 568);
			}
			{
				clientLabel = new JLabel();
				this.add(clientLabel);
				clientLabel.setFont(new java.awt.Font("Î¢ÈíÑÅºÚ",0,24));
				clientLabel.setText(ApplicationResources.getInstance().getString("server.title"));
				clientLabel.setBounds(44, 26, 370, 45);
			}
			{
				addButton = new JButton(new ImageIcon("res/icon/addUser.png"));
				this.add(addButton);
				addButton.setFont(new java.awt.Font("Î¢ÈíÑÅºÚ",0,20));
				addButton.setBounds(787, 123, 179, 63);
				addButton.setText(ApplicationResources.getInstance().getString("server.addUser"));
				addButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("org/icon/addUser.png")));
				addButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						add();
					}
				});
			}
			{
				changeButton = new JButton(new ImageIcon("res/icon/edit.png"));
				this.add(changeButton);
				changeButton.setText(ApplicationResources.getInstance().getString("server.editUser"));
				changeButton.setFont(new java.awt.Font("Î¢ÈíÑÅºÚ",0,20));
				changeButton.setBounds(787, 228, 179, 63);
				changeButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("org/icon/edit.png")));
				changeButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						edit();
					}
				});
			}
			{
				deleteButton = new JButton(new ImageIcon("res/icon/delete.png"));
				this.add(deleteButton);
				deleteButton.setFont(new java.awt.Font("Î¢ÈíÑÅºÚ",0,20));
				deleteButton.setText(ApplicationResources.getInstance().getString("server.deleteUser"));
				deleteButton.setBounds(787, 330, 179, 62);
				deleteButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("org/icon/delete.png")));
				deleteButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						delete();
					}
				});
			}
			{
				jButton1 = new JButton(new ImageIcon("res/icon/ÍË³ö.png"));
				this.add(jButton1);
				jButton1.setText(ApplicationResources.getInstance().getString("util.signView.exit"));
				jButton1.setFont(new java.awt.Font("Î¢ÈíÑÅºÚ",0,18));
				jButton1.setBounds(819, 601, 147, 62);
				jButton1.setIcon(new ImageIcon(getClass().getClassLoader().getResource("org/icon/ÍË³ö.png")));
				jButton1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						close();
					}
				});
			}
			{
				ipLabel = new JLabel();
				this.add(ipLabel);
				String ip = InetAddress.getLocalHost().getHostAddress().toString();
				ipLabel.setText("Candidate IP: "+ip);
				ipLabel.setFont(new java.awt.Font("Î¢ÈíÑÅºÚ",0,24));
				ipLabel.setBounds(420, 26, 546, 45);
			}

			{
				clientTable = new JTable();
				clientScrollPane.setViewportView(clientTable);
				clientTable.setPreferredSize(new java.awt.Dimension(731, 561));
				TableOperator.initTable(clientTable, new String[][] { {}, {} ,{},{},{},{}},
						new String[] {ApplicationResources.getInstance().getString("util.userId")
									,ApplicationResources.getInstance().getString("util.userName")
									,ApplicationResources.getInstance().getString("util.password")});
			
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void refreshMemberList(UserMsg[] members) {
		ArrayList<Object[]> tableArrayList = new ArrayList<Object[]>();
		for(int i=0;i<members.length;i++){
			Object[] newRow = new Object[3];
			//"»áÔ±ID"£¬"»áÔ±Ãû³Æ"£¬ "ÃÜÂë"
			newRow[0] = members[i].id;
			newRow[1] = members[i].name;
			newRow[2] = members[i].password;
			tableArrayList.add(newRow);
		}
		TableOperator.refreshTable(clientTable, tableArrayList);
	}
	public void refreshTable() {
		try {
			refreshMemberList(server.getAllUsers());
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, ApplicationResources.getInstance().getString("server.err.opf"));
		}
	}
	
	private void add() {
		AddUserDialog addClient = new AddUserDialog(server,this);
		ServerGuiManager.getInstance().showDialog(addClient);
	}
	private void edit() {
		int selected=clientTable.getSelectedRow();
		if(selected>=0){
			String[] newRow = new String[clientTable.getColumnCount()];
			for (int i = 0; i < clientTable.getColumnCount(); i++) {
				newRow[i] = (String) clientTable.getValueAt(selected, i);
			}
			EditUserDialog changeClient = new EditUserDialog(server,this,newRow);
			ServerGuiManager.getInstance().showDialog(changeClient);
		}else {
			JOptionPane.showMessageDialog(null, ApplicationResources.getInstance().getString("server.err.pleasechoose"));
		}
		
	}
	private void delete() {
		int answer = JOptionPane.showConfirmDialog(null, ApplicationResources.getInstance().getString("server.confirm.delete"), ApplicationResources.getInstance().getString("server.confirm.delete.title"), JOptionPane.YES_NO_OPTION);
		if (answer == JOptionPane.YES_OPTION) {
			int selected=clientTable.getSelectedRow();
			if(selected>=0){
				String userId=(String)clientTable.getValueAt(selected, 0);
				server.deleteUser(userId);
			}
			else{
				JOptionPane.showMessageDialog(null, ApplicationResources.getInstance().getString("server.err.pleasechoose"));
			}
			refreshTable();
		}
	}
	private void close() {
		server.closeServer();
		System.exit(0);
	}
}
