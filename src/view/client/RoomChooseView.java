package view.client;

import view.TableOperator;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import module.client.ClientInfoManager;

import util.ApplicationResources;
import util.message.RoomState;
import view.inter.RoomChooseRefresher;
import control.inter.RoomChooser;



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
public class RoomChooseView extends javax.swing.JPanel implements RoomChooseRefresher{
	private static final long serialVersionUID = 1L;
	private RoomChooser roomChooser;
	private JTable roomTable;
	private JLabel welcomeLable;
	private JPanel welcomePanel;
	private JScrollPane jScrollPane1;
	private JButton createButton;
	private JButton refreshButton;
	private JButton cancel;
	private JButton join;

	public RoomChooseView(RoomChooser roomChooser) {
		super();
		this.roomChooser=roomChooser;		
		initGUI();
		ClientInfoManager.getInstance().setRoomView(this);
		requestRefresh();
	}
	
	private void initGUI() {
		try {
			GroupLayout thisLayout = new GroupLayout((JComponent)this);
			this.setLayout(thisLayout);
			setPreferredSize(new Dimension(1000, 700));
			{
				welcomePanel = new JPanel();
				{
					welcomeLable = new JLabel();
					welcomePanel.add(welcomeLable);
					welcomeLable.setFont(new java.awt.Font("微软雅黑",0,24));
					welcomeLable.setText(ApplicationResources.getInstance().getString("client.room.welcome")
							+" "+ClientInfoManager.getInstance().getNowLog().name+"!");
				}
			}
			{
				jScrollPane1 = new JScrollPane();
				{
					roomTable = new JTable();
					jScrollPane1.setViewportView(roomTable);
					roomTable.getTableHeader().setFont(new java.awt.Font("微软雅黑", 0, 16));
					roomTable.setFont(new java.awt.Font("微软雅黑",0,16));
					
					DefaultTableCellRenderer render = new DefaultTableCellRenderer();
					render.setHorizontalAlignment(SwingConstants.CENTER);
					for(int i=0;i<roomTable.getColumnCount();i++) {
						roomTable.getColumn(roomTable.getColumnName(i)).setCellRenderer(render);
					}		
					roomTable.setColumnSelectionAllowed(false);
					roomTable.setCellSelectionEnabled(false);
					roomTable.setRowSelectionAllowed(true);
					roomTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					roomTable.setEnabled(true);
					
					noEditTableModel tableModel=this.new noEditTableModel(new String[][]{}, new String[]{
							ApplicationResources.getInstance().getString("util.roomId"),
							ApplicationResources.getInstance().getString("util.roomName"),
							ApplicationResources.getInstance().getString("util.creator"),
							ApplicationResources.getInstance().getString("util.maxMem")});
					roomTable.setModel(tableModel);

				}
			}
			{
				createButton = new JButton();
				createButton.setFont(new java.awt.Font("微软雅黑",0,20));
				createButton.setText(ApplicationResources.getInstance().getString("client.room.create"));
				createButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("org/icon/add.png")));
				createButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						create();
					}
				});
			}
			{
				refreshButton = new JButton();
				refreshButton.setText(ApplicationResources.getInstance().getString("client.room.refresh"));
				refreshButton.setFont(new java.awt.Font("微软雅黑",0,20));
				refreshButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("org/icon/分析.png")));
				refreshButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						requestRefresh();
					}
				});
			}
			{
				join = new JButton();
				join.setFont(new java.awt.Font("微软雅黑",0,20));
				join.setText(ApplicationResources.getInstance().getString("client.room.join"));
				join.setIcon(new ImageIcon(getClass().getClassLoader().getResource("org/icon/入库.png")));
				join.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						join();
					}
				});
			}
			{
				cancel = new JButton();
				cancel.setFont(new java.awt.Font("微软雅黑",0,20));
				cancel.setText(ApplicationResources.getInstance().getString("util.signView.exit"));
				cancel.setIcon(new ImageIcon(getClass().getClassLoader().getResource("org/icon/退出.png")));
				cancel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						exit();
					}
				});
			}
			thisLayout.setVerticalGroup(thisLayout.createSequentialGroup()
				.addComponent(welcomePanel, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
				.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 551, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
				.addGroup(thisLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				    .addComponent(cancel, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
				    .addComponent(join, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
				    .addComponent(refreshButton, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
				    .addComponent(createButton, GroupLayout.Alignment.BASELINE, 0, 57, Short.MAX_VALUE))
				.addContainerGap(19, 19));
			thisLayout.setHorizontalGroup(thisLayout.createSequentialGroup()
				.addContainerGap(31, 31)
				.addGroup(thisLayout.createParallelGroup()
				    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				        .addComponent(createButton, GroupLayout.PREFERRED_SIZE, 230, GroupLayout.PREFERRED_SIZE)
				        .addGap(76)
				        .addComponent(join, GroupLayout.PREFERRED_SIZE, 168, GroupLayout.PREFERRED_SIZE)
				        .addGap(0, 72, Short.MAX_VALUE)
				        .addComponent(refreshButton, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE)
				        .addGap(0, 60, GroupLayout.PREFERRED_SIZE)
				        .addComponent(cancel, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE))
				    .addComponent(welcomePanel, GroupLayout.Alignment.LEADING, 0, 938, Short.MAX_VALUE)
				    .addComponent(jScrollPane1, GroupLayout.Alignment.LEADING, 0, 938, Short.MAX_VALUE))
				.addContainerGap(31, 31));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	class noEditTableModel extends DefaultTableModel{
		private static final long serialVersionUID = 1L;
		public noEditTableModel(Object[][] data, Object[] columnNames)	{
	    	super(data, columnNames);
	    }
	    public boolean isCellEditable(int row, int column)	{
	    	return false;//不可以编辑
	    }
	}

	
	
	@Override
	public void refreshRoomlist(RoomState[] rooms) {
		ArrayList<Object[]> tableArrayList = new ArrayList<Object[]>();
		for(int i=0;i<rooms.length;i++){
			Object[] newRow = new Object[4];
			//"房间id","房间名","创建者","人数"
			newRow[0] = rooms[i].id;
			newRow[1] = rooms[i].name;
			newRow[2] = rooms[i].creator.name;
			newRow[3] = rooms[i].nowMem +"/"+ rooms[i].maxMem;
			tableArrayList.add(newRow);
		}
		TableOperator.refreshTable(roomTable, tableArrayList);
		
	}
	private void create() {
		RoomDialog  roomDialog = new RoomDialog(roomChooser);
		ClientGuiManager.getInstance().showDialog(roomDialog);
	}
	private void join() {
		int choosedRow=roomTable.getSelectedRow();
		if (choosedRow<0) {
			JOptionPane.showMessageDialog(null, "请先选择一个房间。");
			return;
		}
		int roomId=(Integer) roomTable.getValueAt(choosedRow, 0);	//获得用户选择的房间号
		boolean success = roomChooser.joinRoom(roomId);
		if (success) {
			ClientGuiManager.getInstance().toMainView();
		}else {
			JOptionPane.showMessageDialog(null, "房间不存在。");
		}
	}
	private void exit() {
		try {
			ClientInfoManager.getInstance().close();
		} catch (RemoteException e) {
			JOptionPane.showMessageDialog(null, ApplicationResources.getInstance().getString("client.net.err"));
		}
		ClientGuiManager.getInstance().toSignView();
	}
	private void requestRefresh() {
		try {
			roomChooser.refreshRoomList();
		} catch (RemoteException e) {
			JOptionPane.showMessageDialog(null, ApplicationResources.getInstance().getString("client.net.err"));
			ClientGuiManager.getInstance().toSignView();
		}
	}

}
