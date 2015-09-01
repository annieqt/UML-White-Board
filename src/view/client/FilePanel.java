package view.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.ArrayList;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComponent;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle;

import util.ApplicationResources;
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
public class FilePanel extends javax.swing.JPanel {
	private static final long serialVersionUID = 1L;
	private JButton download;
	private JTable fileTable;
	private JScrollPane scrollPane;
	private JButton upload;
	private ClientMainView clientMainView;

	/**
	* Auto-generated main method to display this 
	* JPanel inside a new JFrame.
	*/
	
	public FilePanel(ClientMainView clientMainView) {
		super();
		this.clientMainView =clientMainView;
		initGUI();
	}
	
	private void initGUI() {
		try {
			
				GroupLayout thisLayout = new GroupLayout((JComponent)this);
			this.setLayout(thisLayout);
			this.setPreferredSize(new java.awt.Dimension(197, 209));
			this.setOpaque(false);
			{
				scrollPane = new JScrollPane();
				scrollPane.setSize(209, 156);
				scrollPane.setWheelScrollingEnabled(false);
				{
					fileTable = new JTable();
					scrollPane.setViewportView(fileTable);
					TableOperator.initTable(fileTable,new String[][] {}, 
							new String[] {ApplicationResources.getInstance().getString("client.fileName")});
					fileTable.getTableHeader().setFont(new java.awt.Font("Î¢ÈíÑÅºÚ", 0, 14));
					fileTable.setFont(new java.awt.Font("Î¢ÈíÑÅºÚ", 0, 14));
					fileTable.setPreferredSize(new java.awt.Dimension(194, 140));
					fileTable.setDragEnabled(true);
				}
				{
					
				}
			}
			{
				upload = new JButton();
				upload.setFont(new java.awt.Font("Î¢ÈíÑÅºÚ",0,12));
				upload.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						upload();
					}
				});
				upload.setText(ApplicationResources.getInstance().getString("client.up"));
			}
			{
				download = new JButton();
				download.setFont(new java.awt.Font("Î¢ÈíÑÅºÚ",0,12));
				download.setText(ApplicationResources.getInstance().getString("client.down"));
				download.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						download();
					}
				});
			}
			thisLayout.setVerticalGroup(thisLayout.createSequentialGroup()
				.addContainerGap()
				.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				.addGroup(thisLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				    .addComponent(upload, GroupLayout.Alignment.BASELINE, 0, 29, Short.MAX_VALUE)
				    .addComponent(download, GroupLayout.Alignment.BASELINE, 0, 29, Short.MAX_VALUE))
				.addContainerGap(17, 17));
			thisLayout.setHorizontalGroup(thisLayout.createParallelGroup()
				.addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				    .addComponent(upload, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
				    .addGap(28)
				    .addComponent(download, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE))
				.addComponent(scrollPane, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void refreshFileList(String[] fileNames) {
		ArrayList<Object[]> tableArrayList = new ArrayList<Object[]>();
		for(int i=0;i<fileNames.length;i++){
			Object[] newRow = new Object[1];
			//"»áÔ±Ãû³Æ"
			newRow[0] = fileNames[i];
			tableArrayList.add(newRow);
		}
		TableOperator.refreshTable(fileTable, tableArrayList);
	}
	
	public void upload() {
		try {
			FileChooserView fileChooserView = new FileChooserView();
			String path = fileChooserView.getSelectedFilePath();
			String name = fileChooserView.getSelectedFileName();
			clientMainView.getClientSender().upload(path, name);
		} catch (RemoteException e) {
			JOptionPane.showMessageDialog(null, "ÍøÂçÁ¬½Ó³ö´í¡£");
		}
	}
	
	public void download() {
		try {
			String name = (String) fileTable.getValueAt(fileTable.getSelectedRow(), 0);
			FileSaveView fileSaveView = new FileSaveView();
			String path = fileSaveView.getPresentPath();
			clientMainView.getClientSender().download(path, name);
		} catch (RemoteException e) {
			JOptionPane.showMessageDialog(null, "ÍøÂçÁ¬½Ó³ö´í¡£");
		}
	}

}
