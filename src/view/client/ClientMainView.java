package view.client;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.LayoutStyle;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import module.client.ClientInfoManager;
import util.AppCommandListener;
import util.ApplicationResources;
import util.MethodCall;
import util.message.UserMsg;
import view.EditorCommandDispatcher;
import view.MainToolbarManager;
import view.MenuManager;
import view.StaticStructureEditorToolbarManager;
import view.inter.MainClientRefresher;
import whiteboard.umldraw.structure.StructureDiagram;
import whiteboard.util.draw.Label;
import whiteboard.util.draw.LabelChangeListener;
import whiteboard.util.model.UmlModel;
import whiteboard.util.model.UmlModelImpl;
import whiteboard.view.commands.ModelReader;
import whiteboard.view.commands.ModelWriter;
import whiteboard.view.commands.PngExporter;
import whiteboard.view.commands.SvgExporter;
import whiteboard.view.diagram.DiagramEditor;
import whiteboard.view.diagram.EditorMouseEvent;
import whiteboard.view.diagram.EditorStateListener;
import whiteboard.view.diagram.SelectionListener;
import control.inter.ClientSender;



/**
 * This code was edited or generated using CloudGarden's Jigloo SWT/Swing GUI
 * Builder, which is free for non-commercial use. If Jigloo is being used
 * commercially (ie, by a corporation, company or business for any purpose
 * whatever) then you should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details. Use of Jigloo implies
 * acceptance of these licensing terms. A COMMERCIAL LICENSE HAS NOT BEEN
 * PURCHASED FOR THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED LEGALLY FOR
 * ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
public class ClientMainView extends javax.swing.JPanel implements
		MainClientRefresher, EditorStateListener, AppCommandListener,
		SelectionListener {
	private static final long serialVersionUID = 1L;
	private ClientSender clientSender;
	private JPanel comPanel;
	private JLabel chatLabel;
	private JTabbedPane tabbedPane;

	private JButton send;
	private JCheckBox voiceCheckBox;
	private JPanel chatPanel;
	private JSlider voiceSpinner;
	private JLabel voice;
	private JPanel voiceManagePanel;
	private JTextField chatting;
	private JTextArea chatField;
	private Image panelBg;
	private FilePanel filePanel1;
	private ClientPanel clientPanel;
	private JScrollBar jScrollBar;

	// °×°åËùÓÃ
	private JTabbedPane tabbedPane1;

	public ClientMainView() {
		super();
		try {
			panelBg = ImageIO.read(new File("img/board.png"));
		} catch (IOException e) {
			ClientGuiManager.showMessage("ÕÒ²»µ½Í¼Æ¬¡£");
		}
		initGUI();
	}

	private JFrame frame;

	public ClientMainView(JFrame frame1) {
		super();
		frame = frame1;
		try {
			panelBg = ImageIO.read(new File("img/board.png"));
		} catch (IOException e) {
			ClientGuiManager.showMessage("ÕÒ²»µ½Í¼Æ¬¡£");
		}
		initGUI();
		initializeWhiteBoasrd();
		setClientSender(ClientInfoManager.getInstance().getClient());
	}

	public void setClientSender(ClientSender clientSender) {
		this.clientSender = clientSender;
	}

	public void refresh() {
		try {
			clientSender.refreshAll();
		} catch (RemoteException e) {
			disconnect();
		}
	}

	protected ClientSender getClientSender() {
		return clientSender;
	}

	private void initGUI() {
		try {
			GroupLayout thisLayout = new GroupLayout((JComponent)this);
			this.setLayout(thisLayout);
			this.setPreferredSize(new java.awt.Dimension(1200, 618));
			this.setSize(1200, 620);
			{
				voiceManagePanel = new JPanel();
				GroupLayout voiceManagePanelLayout = new GroupLayout(
						(JComponent) voiceManagePanel);
				voiceManagePanel.setLayout(voiceManagePanelLayout);
				voiceManagePanel.setOpaque(false);
				voiceManagePanelLayout.setVerticalGroup(voiceManagePanelLayout
						.createSequentialGroup());
				voiceManagePanelLayout
						.setHorizontalGroup(voiceManagePanelLayout
								.createSequentialGroup());
			}
			{
				chatPanel = new JPanel() {
					private static final long serialVersionUID = 1L;

					protected void paintComponent(Graphics g) {
						super.paintComponent(g);
						g.drawImage(panelBg, 0, 0, getWidth(), getHeight(),
								null);
					}
				};
				GroupLayout chatPanelLayout = new GroupLayout(
						(JComponent) chatPanel);
				chatPanel.setLayout(chatPanelLayout);
				chatPanel.setOpaque(false);
				{
					chatLabel = new JLabel();
					chatLabel.setFont(new java.awt.Font("Î¢ÈíÑÅºÚ", 0, 14));
					chatLabel.setText(ApplicationResources.getInstance().getString("client.chat"));
				}
				{
					chatting = new JTextField();
					chatting.setFont(new java.awt.Font("Î¢ÈíÑÅºÚ", 0, 14));
					chatting.addKeyListener(new KeyAdapter() {
						@Override
						public void keyPressed(KeyEvent e) {
							if (e.getKeyCode() == KeyEvent.VK_ENTER) {
								sendText();
							}
						}
					});
				}
				{
					send = new JButton();
					send.setFont(new java.awt.Font("Î¢ÈíÑÅºÚ", 0, 14));
					send.setText(ApplicationResources.getInstance().getString("client.send"));
					send.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							sendText();
						}
					});
				}
				{
					chatScrollPane = new JScrollPane();
					{
						jScrollBar = chatScrollPane.getVerticalScrollBar();
						chatField = new JTextArea();
						chatScrollPane.setViewportView(chatField);
						JScrollBar sBar = chatScrollPane.getVerticalScrollBar();
						sBar.setValue(sBar.getMaximum());
						chatField.setFont(new java.awt.Font("Î¢ÈíÑÅºÚ", 0, 14));
						chatField.setEditable(false);
					}
				}
				chatPanelLayout.setHorizontalGroup(chatPanelLayout.createSequentialGroup()
					.addContainerGap(18, 18)
					.addGroup(chatPanelLayout.createParallelGroup()
					    .addGroup(GroupLayout.Alignment.LEADING, chatPanelLayout.createSequentialGroup()
					        .addComponent(chatScrollPane, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
					        .addGap(0, 9, Short.MAX_VALUE))
					    .addGroup(GroupLayout.Alignment.LEADING, chatPanelLayout.createSequentialGroup()
					        .addComponent(chatting, 0, 130, Short.MAX_VALUE)
					        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
					        .addComponent(send, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE))
					    .addGroup(GroupLayout.Alignment.LEADING, chatPanelLayout.createSequentialGroup()
					        .addGap(42)
					        .addComponent(chatLabel, 0, 160, Short.MAX_VALUE)
					        .addGap(7)))
					.addContainerGap());
				chatPanelLayout.setVerticalGroup(chatPanelLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(chatLabel, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
					.addComponent(chatScrollPane, GroupLayout.PREFERRED_SIZE, 196, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
					.addGroup(chatPanelLayout.createParallelGroup()
					    .addGroup(chatPanelLayout.createSequentialGroup()
					        .addComponent(chatting, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
					    .addGroup(chatPanelLayout.createSequentialGroup()
					        .addComponent(send, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(43, Short.MAX_VALUE));
			}
			{
				mainPanel = new JPanel();
				GroupLayout jPanel1Layout = new GroupLayout((JComponent)mainPanel);
				mainPanel.setLayout(jPanel1Layout);
				{
					tabbedPane1 = createEditorArea();
					// tabbedPane1.setBorder( SoftBevelBorder(BevelBorder.LOWERED,
							// null, null, null, null));
				}
				{
					exitButton = new JButton();
					exitButton.setFont(new java.awt.Font("Î¢ÈíÑÅºÚ", 0, 14));
					exitButton.setText(ApplicationResources.getInstance().getString("client.back"));
					exitButton.setOpaque(false);
					exitButton.setIcon(new ImageIcon(getClass().getClassLoader()
							.getResource("org/icon/³ö¿â.png")));
					exitButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							exit();
						}
					});
				}
				{
					voiceCheckBox = new JCheckBox();
					voiceCheckBox.setFont(new java.awt.Font("Î¢ÈíÑÅºÚ", 0, 12));
					voiceCheckBox.setText(ApplicationResources.getInstance().getString("client.mute"));
					voiceCheckBox.addChangeListener(new ChangeListener() {
						public void stateChanged(ChangeEvent evt) {
							setMute();
						}
					});
				}
				{
					
					voiceSpinner = new JSlider();
					
					voiceSpinner.addChangeListener(new ChangeListener() {
						public void stateChanged(ChangeEvent evt) {
							setVolume();
						}
					});
				}
				{
					voice = new JLabel();
					voice.setFont(new java.awt.Font("Î¢ÈíÑÅºÚ", 0, 14));
					voice.setText(ApplicationResources.getInstance().getString("client.volume"));
				}
				jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup()
					.addComponent(tabbedPane1, GroupLayout.Alignment.LEADING, 0, 939, Short.MAX_VALUE)
					.addGroup(GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
					    .addGap(19)
					    .addComponent(voice, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
					    .addComponent(voiceSpinner, 0, 100, Short.MAX_VALUE)
					    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
					    .addComponent(voiceCheckBox, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
					    .addGap(474)
					    .addComponent(exitButton, GroupLayout.PREFERRED_SIZE, 212, GroupLayout.PREFERRED_SIZE)));
				jPanel1Layout.setVerticalGroup(jPanel1Layout.createSequentialGroup()
					.addComponent(tabbedPane1, GroupLayout.PREFERRED_SIZE, 552, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
					.addGroup(jPanel1Layout.createParallelGroup()
					    .addGroup(jPanel1Layout.createSequentialGroup()
					        .addGap(0, 0, Short.MAX_VALUE)
					        .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					            .addComponent(exitButton, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
					            .addComponent(voice, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
					            .addComponent(voiceCheckBox, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)))
					    .addGroup(GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
					        .addGap(7)
					        .addComponent(voiceSpinner, 0, 30, Short.MAX_VALUE)
					        .addGap(6))));
			}
			{
				comPanel = new JPanel() {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					protected void paintComponent(Graphics g) {
						super.paintComponent(g);
						g.drawImage(panelBg, 0, 0, getWidth(), getHeight(),
								null);
					}
				};
				comPanel.setLayout(null);
				{
					tabbedPane = new JTabbedPane(JTabbedPane.TOP);
					comPanel.add(tabbedPane);
					tabbedPane.setBounds(11, 23, 215, 228);
					filePanel1 = new FilePanel(this);
					clientPanel = new ClientPanel();
					tabbedPane.addTab(ApplicationResources.getInstance().getString("client.file"), null, filePanel1, null);
					filePanel1.setSize(197, 210);
					tabbedPane.addTab(ApplicationResources.getInstance().getString("client.users"), null, clientPanel, null);

				}
			}
			thisLayout.setVerticalGroup(thisLayout.createSequentialGroup()
				.addContainerGap()
				.addGroup(thisLayout.createParallelGroup()
				    .addComponent(mainPanel, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 601, GroupLayout.PREFERRED_SIZE)
				    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				        .addComponent(comPanel, GroupLayout.PREFERRED_SIZE, 269, GroupLayout.PREFERRED_SIZE)
				        .addComponent(chatPanel, GroupLayout.PREFERRED_SIZE, 332, GroupLayout.PREFERRED_SIZE)))
				.addGap(301)
				.addComponent(voiceManagePanel, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE));
			thisLayout.setHorizontalGroup(thisLayout.createSequentialGroup()
				.addContainerGap()
				.addGroup(thisLayout.createParallelGroup()
				    .addComponent(comPanel, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 238, GroupLayout.PREFERRED_SIZE)
				    .addComponent(chatPanel, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 238, GroupLayout.PREFERRED_SIZE))
				.addGroup(thisLayout.createParallelGroup()
				    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				        .addComponent(mainPanel, GroupLayout.PREFERRED_SIZE, 939, GroupLayout.PREFERRED_SIZE)
				        .addGap(0, 17, Short.MAX_VALUE))
				    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				        .addGap(328)
				        .addComponent(voiceManagePanel, GroupLayout.PREFERRED_SIZE, 628, GroupLayout.PREFERRED_SIZE))));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		//g.drawImage(bg, 0, 0, getWidth(), getHeight(), null);
	}

	private void sendText() {
		try {
			String msg = chatting.getText();
			if (msg.equals("")) {
				JOptionPane.showMessageDialog(null, getResourceString("client.text.err.null"));
				return;
			}
			clientSender.sendText(msg);
			chatting.setText("");
			jScrollBar.setValue(jScrollBar.getMaximum());
		} catch (RemoteException e) {
			disconnect();
		}
	}

	@Override
	public void addText(String text) {
		chatField.append(text + "\n");
		chatField.setCaretPosition(chatField.getText().length());
	}

	private void sendWBUpdateInfo() {
		try {
			clientSender.sendWBUpdateInfo(diagram);
		} catch (RemoteException e) {
			disconnect();
		}
	}

	@Override
	public void updateWBInfo(StructureDiagram diagram1) {
		diagram = diagram1;
		tabbedPane1.removeAll();
		System.out.println("package received!");
		// currentEditor.setDiagrm(diagram1);
		umlModel = diagram1.getUmlmodel();
		// umlModel.addDiagram(diagram);
		createEditor(diagram1);
	}

	// whiteboard
	private JLabel coordLabel = new JLabel("    ");
	private JLabel memLabel = new JLabel("    ");
	private UmlModel umlModel;
	private DiagramEditor currentEditor;
	private transient Timer timer = new Timer();
	private transient StaticStructureEditorToolbarManager staticToolbarManager;
	private JPanel mainPanel;
	private JButton exitButton;
	private JScrollPane chatScrollPane;
	private transient EditorCommandDispatcher editorDispatcher;
	private transient MainToolbarManager toolbarmanager;
	private transient MenuManager menumanager;
	private transient File currentFile;
	private transient Map<String, MethodCall> selectorMap = new HashMap<String, MethodCall>();

	StructureDiagram diagram;

	/**
	 * Reset the transient values for serialization.
	 * 
	 * @param stream
	 *            an ObjectInputStream
	 * @throws IOException
	 *             if I/O error occured
	 * @throws ClassNotFoundException
	 *             if class was not found
	 */
	private void readObject(ObjectInputStream stream) throws IOException,
			ClassNotFoundException {
		timer = new Timer();
		staticToolbarManager = null;
		editorDispatcher = null;
		toolbarmanager = null;
		menumanager = null;
		currentFile = null;
		initSelectorMap();
	}

	/**
	 * Creates a new instance of AppFrame.
	 * 
	 * @return
	 */
	public void initializeWhiteBoasrd() {
		// setTitle(getResourceString("application.title"));
		// setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		editorDispatcher = new EditorCommandDispatcher(this);

		// frame.getContentPane().add(createEditorArea(), BorderLayout.CENTER);
		installMainToolbar();
		installMenubar();
		installStatusbar();

		/*
		 * addWindowListener(new WindowAdapter() {
		 *//**
		 * {@inheritDoc}
		 */
		/*
		 * public void windowClosing(WindowEvent e) { quitApplication(); } });
		 */
		newModel();
		// pack();
		scheduleMemTimer();
		initSelectorMap();
		// setExtendedState(JFrame.MAXIMIZED_BOTH);
	}

	/**
	 * Returns the currently displayed editor.
	 * 
	 * @return the current editor
	 */
	public DiagramEditor getCurrentEditor() {
		return currentEditor;
	}

	/**
	 * Returns the menu manager.
	 * 
	 * @return the menu manager
	 */
	public MenuManager getMenuManager() {
		return menumanager;
	}

	/**
	 * Creates the tabbed pane for the editor area.
	 * 
	 * @return the tabbed pane
	 */
	private JTabbedPane createEditorArea() {
		tabbedPane1 = new JTabbedPane();
		return tabbedPane1;
	}

	/**
	 * Creates an editor for the specified diagram and adds it to the tabbed
	 * pane.
	 * 
	 * @param diagram
	 *            the diagram
	 */
	private void createEditor(StructureDiagram diagram) {
		currentEditor = new DiagramEditor(frame, diagram);
		currentEditor.addEditorStateListener(this);
		currentEditor.addSelectionListener(this);
		currentEditor.addAppCommandListener(editorDispatcher);
		currentEditor.addAppCommandListener(this);
		JScrollPane spane = new JScrollPane(currentEditor);
		JPanel editorPanel = new JPanel(new BorderLayout());
		spane.getVerticalScrollBar().setUnitIncrement(10);
		spane.getHorizontalScrollBar().setUnitIncrement(10);
		staticToolbarManager = new StaticStructureEditorToolbarManager();
		JToolBar toolbar = staticToolbarManager.getToolbar();
		staticToolbarManager.addCommandListener(editorDispatcher);
		editorPanel.add(spane, BorderLayout.CENTER);
		editorPanel.add(toolbar, BorderLayout.NORTH);
		final Component comp = tabbedPane1.add(diagram.getLabelText(),
				editorPanel);
		diagram.addNameLabelChangeListener(new LabelChangeListener() {
			private static final long serialVersionUID = 1L;

			/** {@inheritDoc} */
			public void labelTextChanged(Label label) {
				tabbedPane1.setTitleAt(tabbedPane1.indexOfComponent(comp),
						label.getText());
			}
		});
	}

	/**
	 * Adds the tool bar.
	 */
	private void installMainToolbar() {
		toolbarmanager = new MainToolbarManager();
		toolbarmanager.addCommandListener(this);
		toolbarmanager.addCommandListener(editorDispatcher);
		frame.getContentPane().add(toolbarmanager.getToolbar(),
				BorderLayout.NORTH);
	}

	/**
	 * Adds the menubar.
	 */
	private void installMenubar() {
		menumanager = new MenuManager();
		menumanager.addCommandListener(this);
		menumanager.addCommandListener(editorDispatcher);
		frame.setJMenuBar(menumanager.getMenuBar());
	}

	/**
	 * Adds a status bar.
	 */
	private void installStatusbar() {
		JPanel statusbar = new JPanel(new BorderLayout());
		statusbar.add(coordLabel, BorderLayout.WEST);
		statusbar.add(memLabel, BorderLayout.EAST);
		frame.getContentPane().add(statusbar, BorderLayout.SOUTH);
	}

	/**
	 * Returns the specified resource as a String object.
	 * 
	 * @param property
	 *            the property name
	 * @return the property value
	 */
	private String getResourceString(String property) {
		return ApplicationResources.getInstance().getString(property);
	}

	// ************************************************************************
	// **** Event listeners
	// *****************************************

	// ************************************************************************
	// **** EditorStateListener
	// *****************************************

	/**
	 * {@inheritDoc}
	 */
	public void mouseMoved(EditorMouseEvent event) {
		coordLabel.setText(String.format("(%.1f, %.1f)", event.getX(),
				event.getY()));
	}

	/**
	 * {@inheritDoc}
	 */
	public void stateChanged(DiagramEditor editor) {
		updateMenuAndToolbars(editor);
		System.out.println("state change");
		sendWBUpdateInfo();
	}

	/**
	 * {@inheritDoc}
	 */
	public void elementAdded(DiagramEditor editor) {
		// spring loading is implemented here
		staticToolbarManager.doClick("SELECT_MODE");
		updateMenuAndToolbars(editor);
		System.out.println("elementAdded");
		sendWBUpdateInfo();
		try {
			clientSender.sendText(getResourceString("client.wb.add"));
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void elementRemoved(DiagramEditor editor) {
		updateMenuAndToolbars(editor);
		System.out.println("elementRemoved");
		sendWBUpdateInfo();
		try {
			clientSender.sendText(getResourceString("client.wb.remove"));
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Query the specified editor state and set the menu and the toolbars
	 * accordingly.
	 * 
	 * @param editor
	 *            the editor
	 */
	private void updateMenuAndToolbars(DiagramEditor editor) {
		menumanager.enableMenuItem("UNDO", editor.canUndo());
		menumanager.enableMenuItem("REDO", editor.canRedo());
		toolbarmanager.enableButton("UNDO", editor.canUndo());
		toolbarmanager.enableButton("REDO", editor.canRedo());
	}

	// ************************************************************************
	// **** SelectionListener
	// *****************************************

	/**
	 * {@inheritDoc}
	 */
	public void selectionStateChanged() {
		boolean hasSelection = getCurrentEditor().getSelectedElements().size() > 0;
		/*
		 * menumanager.enableMenuItem("CUT", hasSelection);
		 * menumanager.enableMenuItem("COPY", hasSelection);
		 */
		menumanager.enableMenuItem("DELETE", hasSelection);
		/*
		 * toolbarmanager.enableButton("CUT", hasSelection);
		 * toolbarmanager.enableButton("COPY", hasSelection);
		 */
		toolbarmanager.enableButton("DELETE", hasSelection);
	}

	// ************************************************************************
	// **** CommandListener
	// *****************************************

	/**
	 * Initializes the selector map.
	 */
	private void initSelectorMap() {
		try {
			selectorMap.put("NEW_MODEL",
					new MethodCall(getClass().getMethod("newModel")));
			selectorMap.put("OPEN_MODEL",
					new MethodCall(getClass().getMethod("openModel")));
			selectorMap.put("SAVE_AS",
					new MethodCall(getClass().getMethod("saveAs")));
			selectorMap.put("SAVE",
					new MethodCall(getClass().getMethod("save")));
			selectorMap.put("EXPORT_GFX",
					new MethodCall(getClass().getMethod("exportGfx")));
			selectorMap.put("DELETE",
					new MethodCall(getClass().getMethod("delete")));
			selectorMap.put("EDIT_SETTINGS", new MethodCall(getClass()
					.getMethod("editSettings")));
			selectorMap.put("QUIT",
					new MethodCall(getClass().getMethod("quitApplication")));
			selectorMap.put("ABOUT",
					new MethodCall(getClass().getMethod("about")));
			selectorMap.put("HELP_CONTENTS", new MethodCall(getClass()
					.getMethod("displayHelpContents")));
		} catch (NoSuchMethodException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void handleCommand(String command) {
		System.out.println("command:" + command);
		MethodCall methodcall = selectorMap.get(command);
		if (methodcall != null) {
			methodcall.call(this);
			System.out.println(methodcall.toString());
		} else {
			System.out.println("not handled: " + command);
		}
	}

	/**
	 * Call this method to exit this application in a clean way.
	 */
	public void quitApplication() {
		if (canQuit()) {
			timer.cancel();
			timer.purge();
			// dispose();
			Thread.currentThread().interrupt();
		}
	}

	/**
	 * Checks if application can be quit safely.
	 * 
	 * @return true if can quit safely, false otherwise
	 */
	private boolean canQuit() {
		if (currentEditor.canUndo()) {
			return JOptionPane.showConfirmDialog(
					this,
					ApplicationResources.getInstance().getString(
							"confirm.quit.message"),
					ApplicationResources.getInstance().getString(
							"confirm.quit.title"), JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
		}
		return true;
	}

	/**
	 * Opens the settings editor.
	 */
	public void editSettings() {
		System.out.println("EDIT SETTINGS");
	}

	/**
	 * Creates a new model.
	 */
	public void newModel() {
		if (canCreateNewModel()) {
			umlModel = new UmlModelImpl();
			diagram = new StructureDiagram(umlModel);
			umlModel.addDiagram(diagram);
			diagram.setLabelText("Class diagram 1");
			tabbedPane1.removeAll();
			createEditor(diagram);
		}
	}

	/**
	 * Determines if a new model can be created.
	 * 
	 * @return true the model can be created, false otherwise
	 */
	private boolean canCreateNewModel() {
		if (currentEditor != null && currentEditor.canUndo()) {
			return JOptionPane.showConfirmDialog(
					this,
					ApplicationResources.getInstance().getString(
							"confirm.new.message"),
					ApplicationResources.getInstance().getString(
							"confirm.new.title"), JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
		}
		return true;
	}

	/**
	 * Sets up and starts the timer task.
	 */
	private void scheduleMemTimer() {
		TimerTask task = new TimerTask() {
			public void run() {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						memLabel.setText(getMemString());
					}
				});
			}
		};
		// every 5 seconds
		timer.schedule(task, 2000, 5000);
	}

	/**
	 * Creates the memory information string.
	 * 
	 * @return the memory status string
	 */
	private String getMemString() {
		long free = Runtime.getRuntime().freeMemory();
		long total = Runtime.getRuntime().totalMemory();
		long used = total - free;
		used /= (1024 * 1024);
		total /= (1024 * 1024);
		return String.format("used: %dM total: %dM   ", used, total);
	}

	/**
	 * Exports graphics as SVG.
	 */
	public void exportGfx() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle(getResourceString("dialog.exportgfx.title"));
		FileNameExtensionFilter svgFilter = new FileNameExtensionFilter(
				"Scalable Vector Graphics file (*.svg)", "svg");
		FileNameExtensionFilter pngFilter = new FileNameExtensionFilter(
				"Portable Network Graphics file (*.png)", "png");
		fileChooser.addChoosableFileFilter(svgFilter);
		fileChooser.addChoosableFileFilter(pngFilter);
		fileChooser.setAcceptAllFileFilterUsed(false);
		if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
			if (fileChooser.getFileFilter() == svgFilter) {
				try {
					SvgExporter exporter = new SvgExporter();
					exporter.writeSVG(getCurrentEditor(),
							fileChooser.getSelectedFile());
				} catch (IOException ex) {
					JOptionPane.showMessageDialog(this, ex.getMessage(),
							getResourceString("error.exportgfx.title"),
							JOptionPane.ERROR_MESSAGE);
				}
			} else if (fileChooser.getFileFilter() == pngFilter) {
				try {
					PngExporter exporter = new PngExporter();
					exporter.writePNG(getCurrentEditor(),
							fileChooser.getSelectedFile());
				} catch (IOException ex) {
					JOptionPane.showMessageDialog(this, ex.getMessage(),
							getResourceString("error.exportgfx.title"),
							JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}

	/**
	 * Returns the FileFilter for the TinyUML serialized model files.
	 * 
	 * @return the FileFilter
	 */
	private FileNameExtensionFilter createModelFileFilter() {
		return new FileNameExtensionFilter(
				"TinyUML serialized model file (*.tsm)", "tsm");
	}

	/**
	 * Opens a TinyUML model.
	 */
	public void openModel() {
		if (canOpen()) {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser
					.setDialogTitle(getResourceString("dialog.openmodel.title"));
			fileChooser.addChoosableFileFilter(createModelFileFilter());
			if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
				try {
					currentFile = fileChooser.getSelectedFile();
					umlModel = ModelReader.getInstance().readModel(currentFile);
					tabbedPane1.removeAll();
					createEditor((StructureDiagram) umlModel.getDiagrams().get(
							0));
					updateFrameTitle();
				} catch (IOException ex) {
					JOptionPane.showMessageDialog(this, ex.getMessage(),
							getResourceString("error.readfile.title"),
							JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}

	/**
	 * Checks if application can be quit safely.
	 * 
	 * @return true if can quit safely, false otherwise
	 */
	private boolean canOpen() {
		if (currentEditor.canUndo()) {
			return JOptionPane.showConfirmDialog(
					this,
					ApplicationResources.getInstance().getString(
							"confirm.open.message"),
					ApplicationResources.getInstance().getString(
							"confirm.open.title"), JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
		}
		return true;
	}

	/**
	 * Saves the model with a file chooser.
	 */
	public void saveAs() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle(getResourceString("dialog.saveas.title"));
		fileChooser.addChoosableFileFilter(createModelFileFilter());
		if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
			currentFile = saveModelFile(fileChooser.getSelectedFile());
			updateFrameTitle();
		}
	}

	/**
	 * Saves immediately if possible.
	 */
	public void save() {
		if (currentFile == null) {
			saveAs();
		} else {
			saveModelFile(currentFile);
		}
	}

	/**
	 * Writes the current model file. The returned file is different if the
	 * input file does not have the tsm extension.
	 * 
	 * @param file
	 *            the file to write
	 * @return the file that was written
	 */
	private File saveModelFile(File file) {
		File result = null;
		try {
			result = ModelWriter.getInstance().writeModel(this, file, umlModel);
			currentEditor.clearUndoManager();
			updateMenuAndToolbars(currentEditor);
		} catch (IOException ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(this, ex.getMessage(),
					getResourceString("error.savefile.title"),
					JOptionPane.ERROR_MESSAGE);
		}
		return result;
	}

	/**
	 * Sets the frame title according to the current working file.
	 */
	private void updateFrameTitle() {
		if (currentFile != null) {
			frame.setTitle(ApplicationResources.getInstance().getString(
					"application.title")
					+ " [" + currentFile.getName() + "]");
		} else {
			frame.setTitle(ApplicationResources.getInstance().getString(
					"application.title"));
		}
	}

	/**
	 * Deletes the current selection.
	 */
	public void delete() {
		getCurrentEditor().deleteSelection();
	}

	/**
	 * Shows the about dialog.
	 */
	public void about() {
		JOptionPane.showMessageDialog(this,
				getResourceString("dialog.about.text"),
				getResourceString("dialog.about.title"),
				JOptionPane.INFORMATION_MESSAGE);
		// TODO Êó±²¸Ï½ô°ÑÕâ¸ö»»µô,»¹ÓÐÓ¢ÎÄ±äÖÐÎÄ
	}

	/**
	 * Displays the help contents.
	 */
	public void displayHelpContents() {
		try {
			URI helpUri = new URI("http://www.tinyuml.org/Wikka/UserDocs");
			// TODO Êó±²¸Ï½ô°ÑÕâ¸öÆúµô
			Desktop.getDesktop().browse(helpUri);
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(this,
					getResourceString("error.nohelp.message"),
					getResourceString("error.nohelp.title"),
					JOptionPane.ERROR_MESSAGE);
		} catch (URISyntaxException ignore) {
			ignore.printStackTrace();
		}
	}

	public void refreshFileList(String[] fileNames) {
		filePanel1.refreshFileList(fileNames);
	}

	public void refreshMemberList(UserMsg[] members) {
		clientPanel.refreshMemberList(members);
	}

	private void exit() {
		try {
			clientSender.disconnect();
			ClientGuiManager.getInstance().toRoomView();
		} catch (RemoteException e) {
			disconnect();
		}
	}

	private void setVolume() {
		clientSender.setSoundVolume(voiceSpinner.getValue());
	}

	private void setMute() {
		boolean mute = voiceCheckBox.isSelected();
		int pVolumn=voiceSpinner.getValue();
		clientSender.setSoundMute(mute,pVolumn);
	}

	@Override
	public StructureDiagram getDiagram() {
		return diagram;
	}

	private void disconnect() {
		JOptionPane.showMessageDialog(null, getResourceString("client.net.err"));
		try {
			ClientInfoManager.getInstance().close();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		ClientGuiManager.getInstance().toSignView();
	}
}
