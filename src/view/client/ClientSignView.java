package view.client;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.ServerNotActiveException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;

import util.ApplicationResources;
import module.client.ClientInfoManager;

import control.inter.UserIdentifier;



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
public class ClientSignView extends javax.swing.JPanel {
	private static final long serialVersionUID = 1L;
	private UserIdentifier userIdentifier;
	private Image bg;
	private JLabel ipLabel;
	private JTextField ipTextField;
	private JButton cancelButton;
	private JButton loginButton;
	private JPasswordField psField;
	private JLabel psLabel;
	private JTextField nameField;
	private JLabel nameLabel;
	private JPanel signPanel;

	public ClientSignView(UserIdentifier userIdentifier) {
		super();
		this.userIdentifier=userIdentifier;	
		try {
			bg= ImageIO.read(new File("img/clientSignBg.png"));
		} catch (IOException e) {
			ClientGuiManager.showMessage("ÕÒ²»µ½Í¼Æ¬¡£");
		}
		initGUI();
	}
	
	private void initGUI() {
		try {
				this.setLayout(null);
				{
					signPanel = new JPanel();
					GroupLayout signPanelLayout = new GroupLayout((JComponent)signPanel);
					signPanel.setLayout(signPanelLayout);
					this.add(signPanel);
					signPanel.setBounds(343, 268, 397, 242);
					signPanel.setBackground(new java.awt.Color(255,255,255));
					{
						nameLabel = new JLabel();
						nameLabel.setText(ApplicationResources.getInstance().getString("util.userId")+":");
						nameLabel.setBackground(new java.awt.Color(255,255,255));
						nameLabel.setForeground(new java.awt.Color(0,0,0));
						nameLabel.setBounds(1077, 184, 106, 48);
						nameLabel.setFont(new java.awt.Font("Î¢ÈíÑÅºÚ",0,24));
					}
					{
						nameField = new JTextField();
						nameField.setFont(new java.awt.Font("Î¢ÈíÑÅºÚ",0,24));
						nameField.addKeyListener(new KeyAdapter() {
							public void keyPressed(KeyEvent evt) {
								nameKeyPressed(evt);
							}
						});
					}
					{
						psLabel = new JLabel();
						psLabel.setText(ApplicationResources.getInstance().getString("util.password")+":");
						psLabel.setFont(new java.awt.Font("Î¢ÈíÑÅºÚ",0,24));
					}
					{
						ipLabel = new JLabel();
						ipLabel.setText(ApplicationResources.getInstance().getString("util.serverip")+":");
						ipLabel.setFont(new java.awt.Font("Î¢ÈíÑÅºÚ",0,24));
					}
					{
						ipTextField = new JTextField();
						ipTextField.setFont(new java.awt.Font("Î¢ÈíÑÅºÚ",0,24));
						ipTextField.setText("localhost");
						ipTextField.addKeyListener(new KeyAdapter() {
							public void keyPressed(KeyEvent evt) {
								ipKeyPressed(evt);
							}
						});
					}
					{
						cancelButton = new JButton();
						cancelButton.setText(ApplicationResources.getInstance().getString("util.signView.exit"));
						cancelButton.setFont(new java.awt.Font("Î¢ÈíÑÅºÚ",0,20));
						cancelButton.setSize(90, 40);
						cancelButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("org/icon/ÍË³ö.png")));
						cancelButton.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								cancel();
							}
						});
					}
					{
						loginButton = new JButton();
						loginButton.setText(ApplicationResources.getInstance().getString("util.sign"));
						loginButton.setFont(new java.awt.Font("Î¢ÈíÑÅºÚ",0,20));
						loginButton.setSize(90, 40);
						loginButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("org/icon/µÇÂ¼.png")));
						loginButton.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								sign();
							}
						});
					}
					{
						psField = new JPasswordField();
						psField.setFont(new java.awt.Font("Î¢ÈíÑÅºÚ",0,24));
						psField.addKeyListener(new KeyAdapter() {
							public void keyPressed(KeyEvent evt) {
								psKeyPressed(evt);
							}
						});
					}
					signPanelLayout.setHorizontalGroup(signPanelLayout.createSequentialGroup()
						.addContainerGap()
						.addGroup(signPanelLayout.createParallelGroup()
						    .addGroup(GroupLayout.Alignment.LEADING, signPanelLayout.createSequentialGroup()
						        .addComponent(nameLabel, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
						        .addGap(0, 12, Short.MAX_VALUE))
						    .addGroup(GroupLayout.Alignment.LEADING, signPanelLayout.createSequentialGroup()
						        .addComponent(psLabel, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
						        .addGap(0, 12, Short.MAX_VALUE))
						    .addGroup(GroupLayout.Alignment.LEADING, signPanelLayout.createSequentialGroup()
						        .addComponent(loginButton, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
						        .addGap(0, 26, Short.MAX_VALUE))
						    .addComponent(ipLabel, GroupLayout.Alignment.LEADING, 0, 156, Short.MAX_VALUE))
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(signPanelLayout.createParallelGroup()
						    .addComponent(ipTextField, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 187, GroupLayout.PREFERRED_SIZE)
						    .addComponent(psField, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 187, GroupLayout.PREFERRED_SIZE)
						    .addComponent(nameField, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 187, GroupLayout.PREFERRED_SIZE)
						    .addGroup(GroupLayout.Alignment.LEADING, signPanelLayout.createSequentialGroup()
						        .addGap(56)
						        .addComponent(cancelButton, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap(30, 30));
					signPanelLayout.setVerticalGroup(signPanelLayout.createSequentialGroup()
						.addGroup(signPanelLayout.createParallelGroup()
						    .addGroup(GroupLayout.Alignment.LEADING, signPanelLayout.createSequentialGroup()
						        .addComponent(nameField, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
						        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED))
						    .addComponent(nameLabel, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
						.addGroup(signPanelLayout.createParallelGroup()
						    .addComponent(psField, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
						    .addGroup(GroupLayout.Alignment.LEADING, signPanelLayout.createSequentialGroup()
						        .addComponent(psLabel, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
						        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)))
						.addGap(16)
						.addGroup(signPanelLayout.createParallelGroup()
						    .addComponent(ipTextField, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						    .addComponent(ipLabel, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))
						.addGap(0, 29, Short.MAX_VALUE)
						.addGroup(signPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						    .addComponent(cancelButton, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
						    .addComponent(loginButton, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE))
						.addContainerGap());
				}

			this.setPreferredSize(new java.awt.Dimension(1000, 700));
			this.setBackground(new java.awt.Color(255,255,255));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(bg, 0, 0, getWidth(), getHeight(), null);
	}

	private void sign(){
		try {
			ClientInfoManager.getInstance().connect(ipTextField.getText());
			boolean success=userIdentifier.sign(nameField.getText(), String.valueOf(psField.getPassword()),ipTextField.getText());
			if (success) {
				ClientGuiManager.getInstance().toRoomView();
			}else {
				JOptionPane.showMessageDialog(null, "ÓÃ»§Ãû»òÃÜÂë´íÎó¡£");
			}
		} catch (MalformedURLException e) {
			ClientGuiManager.showMessage("·þÎñÆ÷IPµØÖ·ÎÞ·¨½âÎö¡£");
		} catch (RemoteException e) {
			ClientGuiManager.showMessage("ÎÞ·¨Á¬½Óµ½·þÎñÆ÷¡£");
		} catch (NotBoundException e) {
			ClientGuiManager.showMessage("ÕÒ²»µ½·þÎñÆ÷¡£");
		} catch (ServerNotActiveException e) {
			ClientGuiManager.showMessage("·þÎñÆ÷Î´Æô¶¯¡£");
		}
	}
	
	private void nameKeyPressed(KeyEvent evt){
		if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
			psField.grabFocus();
		}
	}
	
	private void psKeyPressed(KeyEvent evt){
		if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
			ipTextField.selectAll();
			ipTextField.grabFocus();
		}
	}
	
	private void ipKeyPressed(KeyEvent evt){
		if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
			sign();
		}
	}
	
	private void cancel() {
		System.exit(0);
	}
	
}