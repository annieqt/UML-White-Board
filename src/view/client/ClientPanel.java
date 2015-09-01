package view.client;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.JComponent;

import javax.swing.JScrollPane;
import javax.swing.JTable;

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
public class ClientPanel extends javax.swing.JPanel {
	private static final long serialVersionUID = 1L;
	private JTable clientTable;
	private JScrollPane scrollPane;

	public ClientPanel() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			GroupLayout thisLayout = new GroupLayout((JComponent)this);
			this.setLayout(thisLayout);
			setPreferredSize(new Dimension(209, 197));
			{
				scrollPane = new JScrollPane();
				{
					clientTable = new JTable();
					scrollPane.setViewportView(clientTable);
					clientTable.setPreferredSize(new java.awt.Dimension(203, 177));
					TableOperator.initTable(clientTable, new String[][] { {}, {} ,{},{},{},{}},
							new String[] {ApplicationResources.getInstance().getString("util.userId"),
										ApplicationResources.getInstance().getString("util.userName"),});
				}
			}
			thisLayout.setVerticalGroup(thisLayout.createSequentialGroup()
				.addContainerGap()
				.addComponent(scrollPane, 0, 185, Short.MAX_VALUE)
				.addContainerGap());
			thisLayout.setHorizontalGroup(thisLayout.createSequentialGroup()
				.addComponent(scrollPane, 0, 209, Short.MAX_VALUE));
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.setOpaque(false);
	}
	
	
	public void refreshMemberList(UserMsg[] members) {
			ArrayList<Object[]> tableArrayList = new ArrayList<Object[]>();
			for(int i=0;i<members.length;i++){
				Object[] newRow = new Object[2];
				//"»áÔ±Ãû³Æ"
				newRow[0] = members[i].id;
				newRow[1] = members[i].name;
				tableArrayList.add(newRow);
			}
			TableOperator.refreshTable(clientTable, tableArrayList);
				
	}

}
