package view.server;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;

import util.ApplicationResources;
import view.client.ClientGuiManager;



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
public class ServerSignView extends javax.swing.JPanel {
	private static final long serialVersionUID = 1L;
	private Image bg;
	private JButton cancelButton;
	private JButton loginButton;
	private JPasswordField psField;
	private JLabel psLabel;
	private JTextField nameField;
	private JLabel nameLabel;
	private JPanel signPanel;

	public ServerSignView() {
		super();
		try {
			bg= ImageIO.read(new File("img/serverSignBg.png"));
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
					signPanel.setBounds(344, 303, 397, 191);
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
						nameField.setText("root");
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
						.addContainerGap(36, 36)
						.addGroup(signPanelLayout.createParallelGroup()
						    .addComponent(psLabel, GroupLayout.Alignment.LEADING, 0, 129, Short.MAX_VALUE)
						    .addComponent(nameLabel, GroupLayout.Alignment.LEADING, 0, 129, Short.MAX_VALUE)
						    .addGroup(signPanelLayout.createSequentialGroup()
						        .addComponent(loginButton, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
						        .addGap(0, 0, Short.MAX_VALUE)))
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(signPanelLayout.createParallelGroup()
						    .addComponent(nameField, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 187, GroupLayout.PREFERRED_SIZE)
						    .addComponent(psField, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 187, GroupLayout.PREFERRED_SIZE)
						    .addGroup(GroupLayout.Alignment.LEADING, signPanelLayout.createSequentialGroup()
						        .addGap(61)
						        .addComponent(cancelButton, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap(33, 33));
					signPanelLayout.setVerticalGroup(signPanelLayout.createSequentialGroup()
						.addGroup(signPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						    .addComponent(nameLabel, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
						    .addComponent(nameField, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
						.addGap(20)
						.addGroup(signPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						    .addComponent(psField, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
						    .addComponent(psLabel, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
						.addGap(19)
						.addGroup(signPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						    .addComponent(loginButton, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
						    .addComponent(cancelButton, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE))
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
		String sname = nameField.getText();
		String password = String.valueOf(psField.getPassword());
		ServerGuiManager.getInstance().toServerView(sname, password);
	}
	private void cancel() {
		System.exit(0);
	}
	
	private void nameKeyPressed(KeyEvent evt){
		if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
			psField.grabFocus();
		}
	}
	
	private void psKeyPressed(KeyEvent evt){
		if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
			sign();
		}
	}
	
}