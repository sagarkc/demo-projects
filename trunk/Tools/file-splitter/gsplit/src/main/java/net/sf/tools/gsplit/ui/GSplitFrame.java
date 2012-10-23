/**
 * 
 */
package net.sf.tools.gsplit.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.io.File;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import net.sf.tools.gsplit.ResourceBundleManager;
import net.sf.tools.gsplit.SplitterContext;
import net.sf.tools.gsplit.util.FileBrowserUtil;
import net.sf.tools.gsplit.util.StringUtil;
import net.sf.tools.gsplit.util.WindowUtil;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public class GSplitFrame extends JFrame {

	private static final SplitterContext context = SplitterContext.getContext();
	private ResourceBundle bundle = ResourceBundleManager.getBundleManager().getResourceBundle();
	
	
	private FormActionListener formActionListener;
	
	private JMenuItem aboutMenuItem;
    private JButton addSourceFileButton;
    private JPanel autoJoinPanel;
    private JProgressBar autoJoinProgressBar;
    private JTextField autoJoinSourceTextField;
    private JButton autoJoinStartButton;
    private JTextField autoJoinTargetTextField;
    private JButton browseAutoJoinSourceButton;
    private JButton browseAutoJoinTargetButton;
    private JButton browseManualTargetButton;
    private JButton browseSplitterSourceButton;
    private JButton browseSplitterTargetButton;
    private JMenuItem closeMenuItem;
    private JMenu fileMenu;
    private JLabel fileSizeLabel;
    private JMenu helpMenu;
    private JMenuItem helpMenuItem;
    private JLabel jLabel1;
    private JLabel jLabel10;
    private JLabel jLabel11;
    private JLabel jLabel12;
    private JLabel jLabel13;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel jLabel6;
    private JLabel jLabel7;
    private JLabel jLabel8;
    private JLabel jLabel9;
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JScrollPane jScrollPane1;
    private JPopupMenu.Separator jSeparator1;
    private JPopupMenu.Separator jSeparator2;
    private JPanel manualJoinPanel;
    private JProgressBar manualJoinProgressBar;
    private JButton manualJoinStartButton;
    private JTextField manualJoinTargetTextField;
    private JMenuBar menuBar;
    private JButton moveDownButton;
    private JButton moveUpButton;
    private JMenuItem openMdatMenuItem;
    private JButton removeSourceButton;
    private JList sourceFileList;
    private JPanel splitterPanel;
    private JProgressBar splitterProgressBar;
    private JTextField splitterSourceTextField;
    private JButton splitterStartButton;
    private JButton splitterStopButton;
    private JButton autoJoinStopButton;
    private JTextField splitterTargetTextField;
    private JButton stopManualButton;
    private JTabbedPane tabbedPane;
    private JTextField splitterPartSizeTextField;
    private JComboBox byteComboBox;
	
	/**
	 * 
	 */
	public GSplitFrame() {
		setTitle(bundle.getString("app.title")
				+ " " + bundle.getString("app.version"));
		setSize(540, 350);
		setMinimumSize(getSize());
		WindowUtil.bringToCenter(this);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		formActionListener = new FormActionListener();
		initConponents();
	}

	/**
	 * 
	 */
	private void initConponents() {
		GridBagConstraints gridBagConstraints;

        tabbedPane = new JTabbedPane();
        splitterPanel = new JPanel();
        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        splitterSourceTextField = new JTextField();
        browseSplitterSourceButton = new JButton();
        fileSizeLabel = new JLabel();
        jLabel4 = new JLabel();
        splitterTargetTextField = new JTextField();
        browseSplitterTargetButton = new JButton();
        splitterProgressBar = new JProgressBar();
        splitterStopButton = new JButton();
        jLabel5 = new JLabel();
        splitterStartButton = new JButton();
        jLabel13 = new JLabel();
        splitterPartSizeTextField = new JTextField();
        byteComboBox = new JComboBox();
        autoJoinPanel = new JPanel();
        jLabel3 = new JLabel();
        jLabel6 = new JLabel();
        autoJoinSourceTextField = new JTextField();
        browseAutoJoinSourceButton = new JButton();
        jLabel7 = new JLabel();
        autoJoinTargetTextField = new JTextField();
        browseAutoJoinTargetButton = new JButton();
        autoJoinProgressBar = new JProgressBar();
        autoJoinStopButton = new JButton();
        jLabel8 = new JLabel();
        autoJoinStartButton = new JButton();
        manualJoinPanel = new JPanel();
        jLabel9 = new JLabel();
        jPanel1 = new JPanel();
        jScrollPane1 = new JScrollPane();
        sourceFileList = new JList();
        moveUpButton = new JButton();
        moveDownButton = new JButton();
        addSourceFileButton = new JButton();
        removeSourceButton = new JButton();
        jPanel2 = new JPanel();
        jLabel10 = new JLabel();
        manualJoinTargetTextField = new JTextField();
        browseManualTargetButton = new JButton();
        jLabel11 = new JLabel();
        manualJoinProgressBar = new JProgressBar();
        stopManualButton = new JButton();
        jLabel12 = new JLabel();
        manualJoinStartButton = new JButton();
        menuBar = new JMenuBar();
        fileMenu = new JMenu();
        openMdatMenuItem = new JMenuItem();
        jSeparator1 = new JPopupMenu.Separator();
        closeMenuItem = new JMenuItem();
        helpMenu = new JMenu();
        helpMenuItem = new JMenuItem();
        jSeparator2 = new JPopupMenu.Separator();
        aboutMenuItem = new JMenuItem();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        
        splitterStartButton.setEnabled(false);
        splitterStopButton.setVisible(false);
        splitterProgressBar.setVisible(false);
        
        browseSplitterSourceButton.addActionListener(formActionListener);
        browseSplitterTargetButton.addActionListener(formActionListener);
        splitterStartButton.addActionListener(formActionListener);
        splitterStopButton.addActionListener(formActionListener);
        
        splitterSourceTextField.getDocument().addDocumentListener(formActionListener);
        splitterTargetTextField.getDocument().addDocumentListener(formActionListener);
        
        
        splitterPanel.setLayout(new GridBagLayout());

        jLabel1.setFont(new Font("Tahoma", 1, 15)); // NOI18N
        jLabel1.setForeground(new Color(39, 0, 255));
        jLabel1.setText("File Spliter");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(7, 4, 7, 4);
        splitterPanel.add(jLabel1, gridBagConstraints);

        jLabel2.setText("Source File");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new Insets(4, 4, 4, 4);
        splitterPanel.add(jLabel2, gridBagConstraints);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(4, 4, 4, 4);
        splitterPanel.add(splitterSourceTextField, gridBagConstraints);

        browseSplitterSourceButton.setText("Browse");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new Insets(4, 4, 4, 4);
        splitterPanel.add(browseSplitterSourceButton, gridBagConstraints);

        fileSizeLabel.setFont(new Font("Tahoma", 1, 12)); // NOI18N
        fileSizeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(4, 4, 4, 4);
        splitterPanel.add(fileSizeLabel, gridBagConstraints);

        jLabel4.setText("Target Folder");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new Insets(4, 4, 4, 4);
        splitterPanel.add(jLabel4, gridBagConstraints);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(4, 4, 4, 4);
        splitterPanel.add(splitterTargetTextField, gridBagConstraints);

        browseSplitterTargetButton.setText("Browse");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new Insets(4, 4, 4, 4);
        splitterPanel.add(browseSplitterTargetButton, gridBagConstraints);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(4, 4, 4, 4);
        splitterPanel.add(splitterProgressBar, gridBagConstraints);

        splitterStopButton.setText("Stop");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new Insets(4, 4, 4, 4);
        splitterPanel.add(splitterStopButton, gridBagConstraints);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(4, 4, 4, 4);
        splitterPanel.add(jLabel5, gridBagConstraints);

        splitterStartButton.setText("Start");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new Insets(4, 4, 4, 4);
        splitterPanel.add(splitterStartButton, gridBagConstraints);

        jLabel13.setText("Max Part File Size");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new Insets(4, 4, 4, 4);
        splitterPanel.add(jLabel13, gridBagConstraints);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(4, 4, 4, 4);
        splitterPanel.add(splitterPartSizeTextField, gridBagConstraints);

        byteComboBox.setModel(new DefaultComboBoxModel(new String[] { "BYTE", "KB", "MB", "GB" }));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(4, 4, 4, 4);
        splitterPanel.add(byteComboBox, gridBagConstraints);

        tabbedPane.addTab("Split", splitterPanel);

        autoJoinPanel.setLayout(new GridBagLayout());

        jLabel3.setFont(new Font("Tahoma", 1, 15)); // NOI18N
        jLabel3.setForeground(new Color(39, 0, 255));
        jLabel3.setText("Parts Auto Joiner");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(7, 4, 7, 4);
        autoJoinPanel.add(jLabel3, gridBagConstraints);

        jLabel6.setText("Source Metadata File");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new Insets(4, 4, 4, 4);
        autoJoinPanel.add(jLabel6, gridBagConstraints);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(4, 4, 4, 4);
        autoJoinPanel.add(autoJoinSourceTextField, gridBagConstraints);

        browseAutoJoinSourceButton.setText("Browse");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new Insets(4, 4, 4, 4);
        autoJoinPanel.add(browseAutoJoinSourceButton, gridBagConstraints);

        jLabel7.setText("Target File");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new Insets(4, 4, 4, 4);
        autoJoinPanel.add(jLabel7, gridBagConstraints);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(4, 4, 4, 4);
        autoJoinPanel.add(autoJoinTargetTextField, gridBagConstraints);

        browseAutoJoinTargetButton.setText("Browse");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new Insets(4, 4, 4, 4);
        autoJoinPanel.add(browseAutoJoinTargetButton, gridBagConstraints);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(4, 4, 4, 4);
        autoJoinPanel.add(autoJoinProgressBar, gridBagConstraints);

        autoJoinStopButton.setText("Stop");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new Insets(4, 4, 4, 4);
        autoJoinPanel.add(autoJoinStopButton, gridBagConstraints);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(4, 4, 4, 4);
        autoJoinPanel.add(jLabel8, gridBagConstraints);

        autoJoinStartButton.setText("Start");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new Insets(4, 4, 4, 4);
        autoJoinPanel.add(autoJoinStartButton, gridBagConstraints);

        tabbedPane.addTab("Auto Join", autoJoinPanel);

        manualJoinPanel.setLayout(new GridBagLayout());

        jLabel9.setFont(new Font("Tahoma", 1, 15)); // NOI18N
        jLabel9.setForeground(new Color(39, 0, 255));
        jLabel9.setText("Manual Joiner");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(7, 4, 7, 4);
        manualJoinPanel.add(jLabel9, gridBagConstraints);

        jPanel1.setBorder(BorderFactory.createTitledBorder(" Source Files "));
        jPanel1.setLayout(new GridBagLayout());

        jScrollPane1.setViewportView(sourceFileList);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(4, 4, 4, 4);
        jPanel1.add(jScrollPane1, gridBagConstraints);

        moveUpButton.setText("UP");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = GridBagConstraints.PAGE_START;
        gridBagConstraints.insets = new Insets(4, 4, 4, 4);
        jPanel1.add(moveUpButton, gridBagConstraints);

        moveDownButton.setText("Down");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = GridBagConstraints.PAGE_END;
        gridBagConstraints.insets = new Insets(4, 4, 4, 4);
        jPanel1.add(moveDownButton, gridBagConstraints);

        addSourceFileButton.setText("Add");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new Insets(4, 4, 4, 4);
        jPanel1.add(addSourceFileButton, gridBagConstraints);

        removeSourceButton.setText("Remove");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new Insets(4, 4, 4, 4);
        jPanel1.add(removeSourceButton, gridBagConstraints);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 1.0;
        manualJoinPanel.add(jPanel1, gridBagConstraints);

        jPanel2.setLayout(new GridBagLayout());

        jLabel10.setText("Target File");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(4, 4, 4, 4);
        jPanel2.add(jLabel10, gridBagConstraints);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(4, 4, 4, 4);
        jPanel2.add(manualJoinTargetTextField, gridBagConstraints);

        browseManualTargetButton.setText("Browse");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(4, 4, 4, 4);
        jPanel2.add(browseManualTargetButton, gridBagConstraints);

        jLabel11.setFont(new Font("Tahoma", 1, 12)); // NOI18N
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(4, 4, 4, 4);
        jPanel2.add(jLabel11, gridBagConstraints);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(4, 4, 4, 4);
        jPanel2.add(manualJoinProgressBar, gridBagConstraints);

        stopManualButton.setText("Stop");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new Insets(4, 4, 4, 4);
        jPanel2.add(stopManualButton, gridBagConstraints);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(4, 4, 4, 4);
        jPanel2.add(jLabel12, gridBagConstraints);

        manualJoinStartButton.setText("Start");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.insets = new Insets(4, 4, 4, 4);
        jPanel2.add(manualJoinStartButton, gridBagConstraints);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 1.0;
        manualJoinPanel.add(jPanel2, gridBagConstraints);

        tabbedPane.addTab("Manual Join", manualJoinPanel);

        getContentPane().add(tabbedPane, BorderLayout.CENTER);

        fileMenu.setText("File");

        openMdatMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
        openMdatMenuItem.setText("Open Metadata");
        fileMenu.add(openMdatMenuItem);
        fileMenu.add(jSeparator1);

        closeMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.ALT_MASK));
        closeMenuItem.setText("Close");
        fileMenu.add(closeMenuItem);

        menuBar.add(fileMenu);

        helpMenu.setText("Help");

        helpMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
        helpMenuItem.setText("Help");
        helpMenu.add(helpMenuItem);
        helpMenu.add(jSeparator2);

        aboutMenuItem.setText("About");
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        setJMenuBar(menuBar);

        pack();
	}
	
	

	private class FormActionListener implements ActionListener, 
		PropertyChangeListener,
		VetoableChangeListener,
		DocumentListener{

		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent evt) {
			if(evt.getSource().equals(closeMenuItem)){
				System.exit(0);
			}
			if(evt.getSource().equals(browseSplitterSourceButton)){
				File file = FileBrowserUtil.openSingleFile(GSplitFrame.this, null, false);
				if(null != file){
					splitterSourceTextField.setText(file.getAbsolutePath());
					fileSizeLabel.setText("Size: "+file.length());
				}
			}
			if(evt.getSource().equals(browseSplitterTargetButton)){
				File dir = FileBrowserUtil.openSingleFile(GSplitFrame.this, null, true);
				if(null != dir){
					splitterTargetTextField.setText(dir.getAbsolutePath());
				}
			}
			if(evt.getSource().equals(splitterStartButton)){
				JOptionPane.showMessageDialog(GSplitFrame.this, "dsfasdfasdf");
			}
		}
		
		/* (non-Javadoc)
		 * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
		 */
		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			
		}
		
		/* (non-Javadoc)
		 * @see java.beans.VetoableChangeListener#vetoableChange(java.beans.PropertyChangeEvent)
		 */
		@Override
		public void vetoableChange(PropertyChangeEvent evt)
				throws PropertyVetoException {
			
		}

		/* (non-Javadoc)
		 * @see event.DocumentListener#insertUpdate(event.DocumentEvent)
		 */
		@Override
		public void insertUpdate(DocumentEvent evt) {
			if(StringUtil.hasValidContent(splitterSourceTextField.getText())
					&& StringUtil.hasValidContent(splitterTargetTextField.getText())){
				splitterStartButton.setEnabled(true);
			} else {
				splitterStartButton.setEnabled(false);
			}
		}

		/* (non-Javadoc)
		 * @see event.DocumentListener#removeUpdate(event.DocumentEvent)
		 */
		@Override
		public void removeUpdate(DocumentEvent evt) {
			if(StringUtil.hasValidContent(splitterSourceTextField.getText())
					&& StringUtil.hasValidContent(splitterTargetTextField.getText())){
				splitterStartButton.setEnabled(true);
			} else {
				splitterStartButton.setEnabled(false);
			}
		}

		/* (non-Javadoc)
		 * @see event.DocumentListener#changedUpdate(event.DocumentEvent)
		 */
		@Override
		public void changedUpdate(DocumentEvent evt) {
			if(StringUtil.hasValidContent(splitterSourceTextField.getText())
					&& StringUtil.hasValidContent(splitterTargetTextField.getText())){
				splitterStartButton.setEnabled(true);
			} else {
				splitterStartButton.setEnabled(false);
			}
		}
		
		
	}
}
