package view.client;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import javax.swing.WindowConstants;

import util.ApplicationResources;
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
public class RoomDialog extends javax.swing.JDialog {
	private static final long serialVersionUID = 1L;
	
	private JLabel nameLabel;
	private JTextField nameTextField;
	private JButton cancelButton;
	private JButton ensureButton;
	private JTextField maxMenTextField;
	private JLabel maxMenLabel;
	private RoomChooser roomChooser;

	
	public RoomDialog(RoomChooser roomChooser) {
		super();
		this.roomChooser = roomChooser;
		initGUI();
	}
	
	private void initGUI() {
		try {			
			this.setTitle(ApplicationResources.getInstance().getString("client.room.create"));
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			getContentPane().setLayout(null);
			this.setPreferredSize(new java.awt.Dimension(400, 300));
			{
				nameLabel = new JLabel();
				getContentPane().add(nameLabel);
				nameLabel.setText(ApplicationResources.getInstance().getString("util.roomName"));
				nameLabel.setFont(new java.awt.Font("Î¢ÈíÑÅºÚ",0,18));
				nameLabel.setBounds(84, 39, 94, 38);
			}
			{
				nameTextField = new JTextField();
				getContentPane().add(nameTextField);
				nameTextField.setBounds(184, 39, 160, 38);
				nameTextField.setFont(new java.awt.Font("Î¢ÈíÑÅºÚ",0,18));
			}
			{
				maxMenLabel = new JLabel();
				getContentPane().add(maxMenLabel);
				maxMenLabel.setText(ApplicationResources.getInstance().getString("util.maxMem"));
				maxMenLabel.setFont(new java.awt.Font("Î¢ÈíÑÅºÚ",0,18));
				maxMenLabel.setBounds(39, 106, 139, 35);
			}
			{
				maxMenTextField = new JTextField();
				getContentPane().add(maxMenTextField);
				maxMenTextField.setBounds(184, 107, 160, 38);
				maxMenTextField.setFont(new java.awt.Font("Î¢ÈíÑÅºÚ",0,18));
			}
			{
				ensureButton = new JButton();
				getContentPane().add(ensureButton);
				ensureButton.setText("\u786e\u8ba4");
				ensureButton.setBounds(97, 181, 81, 36);
				ensureButton.setFont(new java.awt.Font("Î¢ÈíÑÅºÚ",0,18));
				ensureButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						createRoom();
					}
				});
			}
			{
				cancelButton = new JButton();
				getContentPane().add(cancelButton);
				cancelButton.setText("\u53d6\u6d88");
				cancelButton.setBounds(221, 181, 90, 36);
				cancelButton.setFont(new java.awt.Font("Î¢ÈíÑÅºÚ",0,18));
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						cancel();
					}
				});
			}
			pack();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void createRoom(){
		String roomName = nameTextField.getText();
		int maxMen = Integer.parseInt(maxMenTextField.getText());
		if(maxMen<=0||maxMen>8){
			JOptionPane.showMessageDialog(null,ApplicationResources.getInstance().getString("client.room.err.mem"));
		}else{
			boolean success = roomChooser.createRoom(roomName, maxMen);
			if (success) {
				ClientGuiManager.getInstance().toMainView();
			}else {
				JOptionPane.showMessageDialog(null, ApplicationResources.getInstance().getString("client.room.err.create"));
			}
			this.dispose();
		}
	}
	
	private void cancel() {
		this.dispose();
	}

}
