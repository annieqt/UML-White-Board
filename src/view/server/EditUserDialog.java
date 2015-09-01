package view.server;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import javax.swing.WindowConstants;
import control.server.ServerHoster;

import util.ApplicationResources;
import util.message.UserMsg;


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
public class EditUserDialog extends javax.swing.JDialog {
	private static final long serialVersionUID = 1L;
	private ServerHoster server;
	private JLabel idLabel;
	private JLabel idTextField;
	private JTextField nameTextField;
	private JButton cancelButton;
	private JButton ensureButton;
	private JTextField psTextField;
	private JLabel psLabel;
	private JLabel nameLabel;
	private ServerView serverView;

	/**
	* Auto-generated main method to display this JFrame
	*/
	
	public EditUserDialog(ServerHoster server,ServerView serverView,String items[]) {
		super();
		this.serverView = serverView;
		this.server = server;
		initGUI();
		idTextField.setText(items[0]);
		nameTextField.setText(items[1]);
		psTextField.setText(items[2]);
	}
	
	private void initGUI() {
		try {
			this.setTitle(ApplicationResources.getInstance().getString("server.editUser"));
			
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			getContentPane().setLayout(null);
			this.setPreferredSize(new java.awt.Dimension(409, 329));
			{
				idLabel = new JLabel();
				getContentPane().add(idLabel);
				idLabel.setText(ApplicationResources.getInstance().getString("util.userId"));
				idLabel.setFont(new java.awt.Font("Î¢ÈíÑÅºÚ",0,16));
				idLabel.setBounds(61, 34, 84, 29);
			}
			{
				idTextField = new JLabel();
				getContentPane().add(idTextField);
				idTextField.setFont(new java.awt.Font("Î¢ÈíÑÅºÚ",0,16));
				idTextField.setBounds(162, 33, 156, 30);
			}
			{
				nameLabel = new JLabel();
				getContentPane().add(nameLabel);
				nameLabel.setFont(new java.awt.Font("Î¢ÈíÑÅºÚ",0,16));
				nameLabel.setText(ApplicationResources.getInstance().getString("util.userName"));
				nameLabel.setBounds(61, 94, 84, 26);
			}
			{
				nameTextField = new JTextField();
				getContentPane().add(nameTextField);
				nameTextField.setFont(new java.awt.Font("Î¢ÈíÑÅºÚ",0,16));
				nameTextField.setBounds(162, 90, 156, 30);
				nameTextField.addKeyListener(new KeyAdapter() {
					public void keyPressed(KeyEvent evt) {
						nameKeyPressed(evt);
					}
				});
			}
			{
				psLabel = new JLabel();
				getContentPane().add(psLabel);
				psLabel.setFont(new java.awt.Font("Î¢ÈíÑÅºÚ",0,16));
				psLabel.setText(ApplicationResources.getInstance().getString("util.password"));
				psLabel.setBounds(61, 154, 84, 23);
			}
			{
				psTextField = new JTextField();
				getContentPane().add(psTextField);
				psTextField.setFont(new java.awt.Font("Î¢ÈíÑÅºÚ",0,16));
				psTextField.setBounds(162, 152, 156, 30);
				psTextField.addKeyListener(new KeyAdapter() {
					public void keyPressed(KeyEvent evt) {
						psKeyPressed(evt);
					}
				});
			}
			{
				ensureButton = new JButton();
				getContentPane().add(ensureButton);
				ensureButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						ensureButtonPressed();
					}
				});
				ensureButton.setText(ApplicationResources.getInstance().getString("util.confirm"));
				ensureButton.setFont(new java.awt.Font("Î¢ÈíÑÅºÚ",0,16));
				ensureButton.setBounds(61, 217, 89, 39);
			}
			{
				cancelButton = new JButton();
				getContentPane().add(cancelButton);
				cancelButton.setText(ApplicationResources.getInstance().getString("util.cancel"));
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						cancelButtonPressed();
					}
				});
				cancelButton.setFont(new java.awt.Font("Î¢ÈíÑÅºÚ",0,16));
				cancelButton.setBounds(223, 217, 95, 39);
			}
			pack();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void ensureButtonPressed(){
		String id = idTextField.getText();
		String name = nameTextField.getText();
		String password = psTextField.getText();
		if(id.equals("")){
			JOptionPane.showMessageDialog(null, ApplicationResources.getInstance().getString("server.err.input"), ApplicationResources.getInstance().getString("server.err.input.title"),
					 JOptionPane.INFORMATION_MESSAGE);
		}else if(name.equals("")){
			JOptionPane.showMessageDialog(null, ApplicationResources.getInstance().getString("server.err.input"), ApplicationResources.getInstance().getString("server.err.input.title"),
					 JOptionPane.INFORMATION_MESSAGE);
		}else if(password.equals("")){
			JOptionPane.showMessageDialog(null, ApplicationResources.getInstance().getString("server.err.input"), ApplicationResources.getInstance().getString("server.err.input.title"),
					 JOptionPane.INFORMATION_MESSAGE);
		}else{
			UserMsg userMsg = new UserMsg(id, name, password);
			server.editUser(userMsg);
			serverView.refreshTable();
			this.dispose();
		}
	}
	
	
	private void nameKeyPressed(KeyEvent evt){
		if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
			psTextField.grabFocus();
		}
	}
	
	private void psKeyPressed(KeyEvent evt){
		if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
			ensureButtonPressed();
		}
	}
	
	public void cancelButtonPressed(){
		this.dispose();
	}

}
