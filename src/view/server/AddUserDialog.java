package view.server;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
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
public class AddUserDialog extends javax.swing.JDialog {
	private static final long serialVersionUID = 1L;
	
	private ServerHoster server;
	private JLabel idLabel;
	private JTextField idTextField;
	private JTextField nameTextField;
	private JButton cancelButton;
	private JButton ensureButton;
	private JTextField psTextField;
	private JLabel psLabel;
	private JLabel nameLabel;
	private ServerView clientManagePanel;
	private JPanel bgPanel;


	public AddUserDialog(ServerHoster server,ServerView clientManagePanel) {
		super();
		this.clientManagePanel = clientManagePanel;
		this.server = server;
		initGUI();
	}
	
	private void initGUI() {
		try {
			this.setTitle(ApplicationResources.getInstance().getString("server.addUser"));
			
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			getContentPane().setLayout(null);
			this.setPreferredSize(new java.awt.Dimension(409, 329));
			{
				bgPanel = new JPanel(){
					private static final long serialVersionUID = 1L;
				};
				getContentPane().add(bgPanel);
				bgPanel.setBounds(0, 0, 393, 297);
				bgPanel.setLayout(null);
				{
					idLabel = new JLabel();
					bgPanel.add(idLabel);
					idLabel.setText(ApplicationResources.getInstance().getString("util.userId"));
					idLabel.setFont(new java.awt.Font("Î¢ÈíÑÅºÚ",0,16));
					idLabel.setBounds(63, 48, 89, 29);
					idLabel.setOpaque(false);
				}
				{
					idTextField = new JTextField();
					bgPanel.add(idTextField);
					idTextField.setFont(new java.awt.Font("Î¢ÈíÑÅºÚ",0,16));
					idTextField.setBounds(164, 47, 156, 30);
					idTextField.addKeyListener(new KeyAdapter() {
						public void keyPressed(KeyEvent evt) {
							idKeyPressed(evt);
						}
					});
				}
				{
					nameLabel = new JLabel();
					bgPanel.add(nameLabel);
					nameLabel.setFont(new java.awt.Font("Î¢ÈíÑÅºÚ",0,16));
					nameLabel.setText(ApplicationResources.getInstance().getString("util.userName"));
					nameLabel.setBounds(63, 105, 89, 26);
					nameLabel.setOpaque(false);
				}
				{
					nameTextField = new JTextField();
					bgPanel.add(nameTextField);
					nameTextField.setFont(new java.awt.Font("Î¢ÈíÑÅºÚ",0,16));
					nameTextField.setBounds(164, 103, 156, 30);
					nameTextField.addKeyListener(new KeyAdapter() {
						public void keyPressed(KeyEvent evt) {
							nameKeyPressed(evt);
						}
					});
				}
				{
					psLabel = new JLabel();
					bgPanel.add(psLabel);
					psLabel.setFont(new java.awt.Font("Î¢ÈíÑÅºÚ",0,16));
					psLabel.setText(ApplicationResources.getInstance().getString("util.password"));
					psLabel.setBounds(63, 165, 90, 23);
					psLabel.setOpaque(false);
				}
				{
					psTextField = new JTextField();
					bgPanel.add(psTextField);
					psTextField.setFont(new java.awt.Font("Î¢ÈíÑÅºÚ",0,16));
					psTextField.setBounds(165, 161, 156, 30);
					psTextField.addKeyListener(new KeyAdapter() {
						public void keyPressed(KeyEvent evt) {
							psKeyPressed(evt);
						}
					});
				}
				{
					ensureButton = new JButton();
					bgPanel.add(ensureButton);
					ensureButton.setText(ApplicationResources.getInstance().getString("util.confirm"));
					ensureButton.setFont(new java.awt.Font("Î¢ÈíÑÅºÚ",0,16));
					ensureButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							ensureButtonPressed();
						}
					});
					ensureButton.setBounds(75, 229, 107, 39);
				}
				{
					cancelButton = new JButton();
					bgPanel.add(cancelButton);
					cancelButton.setText(ApplicationResources.getInstance().getString("util.cancel"));
					cancelButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							cancelButtonPressed();
						}
					});
					cancelButton.setFont(new java.awt.Font("Î¢ÈíÑÅºÚ",0,16));
					cancelButton.setBounds(217, 229, 104, 39);
				}
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
			server.addUser(userMsg);
			clientManagePanel.refreshTable();
			this.dispose();
		}
	}
	
	
	private void idKeyPressed(KeyEvent evt){
		if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
			nameTextField.grabFocus();
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
