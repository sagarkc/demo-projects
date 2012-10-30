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
import java.io.File;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
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
import net.sf.tools.gsplit.SplitterConstants;
import net.sf.tools.gsplit.SplitterContext;
import net.sf.tools.gsplit.WorkerTaskConstants;
import net.sf.tools.gsplit.core.FileAutoJoiner;
import net.sf.tools.gsplit.core.FileSplitter;
import net.sf.tools.gsplit.util.ExtensionFileFilter;
import net.sf.tools.gsplit.util.FileBrowserUtil;
import net.sf.tools.gsplit.util.MetaDataFileView;
import net.sf.tools.gsplit.util.StringUtil;
import net.sf.tools.gsplit.util.WindowUtil;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public class GSplitFrame extends JFrame implements PropertyChangeListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3832358587043248983L;
	
	private static final SplitterContext context = SplitterContext.getContext();
	private ResourceBundle bundle = ResourceBundleManager.getBundleManager().getResourceBundle();
	
	private FileSplitter fileSplitter;
	private FileAutoJoiner fileAutoJoiner;
	
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
		
		setIconImage((new ImageIcon(getClass().getResource("/images/file-splitter_16x16.png"))).getImage());
		
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
        splitterProgressBar = new JProgressBar(0, 100);
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
        
        splitterSourceTextField.getDocument().addDocumentListener(new DocumentListener() {
			public void removeUpdate(DocumentEvent e) {
				updateSplitterStartButton();
			}
			public void insertUpdate(DocumentEvent e) {
				updateSplitterStartButton();
			}
			public void changedUpdate(DocumentEvent e) {
				updateSplitterStartButton();
			}
		});
        splitterTargetTextField.getDocument().addDocumentListener(new DocumentListener() {
			public void removeUpdate(DocumentEvent e) {
				updateSplitterStartButton();
			}
			public void insertUpdate(DocumentEvent e) {
				updateSplitterStartButton();
			}
			public void changedUpdate(DocumentEvent e) {
				updateSplitterStartButton();
			}
		});
        
        
        autoJoinProgressBar.setVisible(false);
        autoJoinProgressBar.setMinimum(0);
        autoJoinProgressBar.setMaximum(100);
        autoJoinProgressBar.setValue(0);
        autoJoinProgressBar.setStringPainted(true);
        
        autoJoinStartButton.setEnabled(false);
        autoJoinStartButton.addActionListener(formActionListener);
        autoJoinStopButton.setVisible(false);
        autoJoinStopButton.addActionListener(formActionListener);
        browseAutoJoinSourceButton.addActionListener(formActionListener);
        browseAutoJoinTargetButton.addActionListener(formActionListener);
        
        autoJoinSourceTextField.getDocument().addDocumentListener(new DocumentListener() {
			public void removeUpdate(DocumentEvent e) {
				updateAutoJoinStartButton();
			}
			public void insertUpdate(DocumentEvent e) {
				updateAutoJoinStartButton();
			}
			public void changedUpdate(DocumentEvent e) {
				updateAutoJoinStartButton();
			}
		});
        autoJoinTargetTextField.getDocument().addDocumentListener(new DocumentListener() {
			public void removeUpdate(DocumentEvent e) {
				updateAutoJoinStartButton();
			}
			public void insertUpdate(DocumentEvent e) {
				updateAutoJoinStartButton();
			}
			public void changedUpdate(DocumentEvent e) {
				updateAutoJoinStartButton();
			}
		});
        
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
        splitterProgressBar.setValue(0);
        splitterProgressBar.setStringPainted(true);
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
        byteComboBox.setSelectedIndex(1);
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
	
	private void updateSplitterStartButton(){
		if(StringUtil.hasValidContent(splitterSourceTextField.getText())
				&& StringUtil.hasValidContent(splitterTargetTextField.getText())){
			splitterStartButton.setEnabled(true);
		} else {
			splitterStartButton.setEnabled(false);
		}
	}
	
	private void updateAutoJoinStartButton(){
		if(StringUtil.hasValidContent(autoJoinSourceTextField.getText())
				&& StringUtil.hasValidContent(autoJoinTargetTextField.getText())){
			autoJoinStartButton.setEnabled(true);
		} else {
			autoJoinStartButton.setEnabled(false);
		}
	}
	
	private void startSplitter() {
		synchronized (this) {
			fileSplitter = new FileSplitter(splitterSourceTextField.getText(), 
					splitterTargetTextField.getText());
			fileSplitter.addPropertyChangeListener(this);
			long blockSize = 0;
			try{
				int multiplier = 1;
				String mul = byteComboBox.getSelectedItem().toString();
				if(SplitterConstants.KB_TEXT.equals(mul)){
					multiplier = SplitterConstants.KB;
				} else if(SplitterConstants.MB_TEXT.equals(mul)){
					multiplier = SplitterConstants.MB;
				} else if(SplitterConstants.GB_TEXT.equals(mul)){
					multiplier = SplitterConstants.GB;
				} 
				blockSize = Integer.parseInt(splitterPartSizeTextField.getText()) * multiplier;
			} catch(Exception ex){
				ex.printStackTrace();
			}
			if(blockSize <= 0){
				JOptionPane.showMessageDialog(this, "Block size is incorrect.");
				return;
			}
			fileSplitter.setByteCount(blockSize);
			fileSplitter.execute();
		}
	}
	
	private void startAutoJoin(){
		synchronized (this) {
			fileAutoJoiner = new FileAutoJoiner(
					autoJoinSourceTextField.getText(), 
					autoJoinTargetTextField.getText());
			fileAutoJoiner.addPropertyChangeListener(this);
			fileAutoJoiner.execute();
		}
	}
	
	private void stopSplitter() {
		if(null != fileSplitter){
			fileSplitter.cancel(true);
		}
	}

	private void stopAutoJoin() {
		if(null != fileAutoJoiner){
			fileAutoJoiner.cancel(true);
		}
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		String propertyName = evt.getPropertyName();
		if(evt.getSource().equals(fileSplitter)){
			if(WorkerTaskConstants.TASK_STATUS_DONE.equals(propertyName)){
				Object newValue = evt.getNewValue();
				JOptionPane.showMessageDialog(GSplitFrame.this, 
						String.format("Completed in [ %10.2f ] seconds.",
								((Long)newValue)/1000.0));
				splitterStartButton.setEnabled(true);
				splitterStopButton.setVisible(false);
				splitterProgressBar.setVisible(false);
				splitterSourceTextField.setEditable(true);
				splitterTargetTextField.setEditable(true);
				browseSplitterSourceButton.setEnabled(true);
				browseSplitterTargetButton.setEnabled(true);
			}
			if(WorkerTaskConstants.TASK_STATUS_ABORT.equals(propertyName)){
				Object newValue = evt.getNewValue();
				JOptionPane.showMessageDialog(GSplitFrame.this, "Split aborted by user");
				splitterStartButton.setEnabled(true);
				splitterStopButton.setVisible(false);
				splitterProgressBar.setVisible(false);
				splitterSourceTextField.setEditable(true);
				splitterTargetTextField.setEditable(true);
				browseSplitterSourceButton.setEnabled(true);
				browseSplitterTargetButton.setEnabled(true);
			}
			if(WorkerTaskConstants.PROPERTY_PROGRESS.equals(propertyName)){
				String type = evt.getNewValue().toString();
				Object newProgress = evt.getNewValue();
				if(null != newProgress && newProgress instanceof Integer){
					splitterProgressBar.setValue((Integer)newProgress);
					splitterProgressBar.updateUI();
				} else if(WorkerTaskConstants.TASK_STATUS_START.equals(newProgress)){
					splitterStartButton.setEnabled(false);
					splitterStopButton.setVisible(true);
					splitterProgressBar.setVisible(true);
					splitterSourceTextField.setEditable(false);
					splitterTargetTextField.setEditable(false);
					browseSplitterSourceButton.setEnabled(false);
					browseSplitterTargetButton.setEnabled(false);
				}
			}
			
			if(WorkerTaskConstants.TASK_STATUS_FAILED.equals(propertyName)){
				Object newValue = evt.getNewValue();
				JOptionPane.showMessageDialog(GSplitFrame.this, "Split failed for : " + newValue);
				splitterStartButton.setEnabled(true);
				splitterStopButton.setVisible(false);
				splitterProgressBar.setVisible(false);
				splitterSourceTextField.setEditable(true);
				splitterTargetTextField.setEditable(true);
				browseSplitterSourceButton.setEnabled(true);
				browseSplitterTargetButton.setEnabled(true);
			}
		}
		else if(evt.getSource().equals(fileAutoJoiner)){
			if(WorkerTaskConstants.TASK_STATUS_DONE.equals(propertyName)){
				Object newValue = evt.getNewValue();
				JOptionPane.showMessageDialog(GSplitFrame.this, 
						String.format("Completed in [ %10.2f ] seconds.",
								((Long)newValue)/1000.0));
				autoJoinStartButton.setEnabled(true);
				autoJoinStopButton.setVisible(false);
				autoJoinProgressBar.setVisible(false);
				autoJoinSourceTextField.setEditable(true);
				autoJoinTargetTextField.setEditable(true);
				browseAutoJoinSourceButton.setEnabled(true);
				browseAutoJoinTargetButton.setEnabled(true);
			}
			if(WorkerTaskConstants.TASK_STATUS_ABORT.equals(propertyName)){
				Object newValue = evt.getNewValue();
				JOptionPane.showMessageDialog(GSplitFrame.this, "Join aborted by user");
				autoJoinStartButton.setEnabled(true);
				autoJoinStopButton.setVisible(false);
				autoJoinProgressBar.setVisible(false);
				autoJoinSourceTextField.setEditable(true);
				autoJoinTargetTextField.setEditable(true);
				browseAutoJoinSourceButton.setEnabled(true);
				browseAutoJoinTargetButton.setEnabled(true);
			}
			if(WorkerTaskConstants.PROPERTY_PROGRESS.equals(propertyName)){
				String type = evt.getNewValue().toString();
				Object newProgress = evt.getNewValue();
				if(null != newProgress && newProgress instanceof Integer){
					autoJoinProgressBar.setValue((Integer)newProgress);
					autoJoinProgressBar.updateUI();
				} else if(WorkerTaskConstants.TASK_STATUS_START.equals(newProgress)){
					autoJoinStartButton.setEnabled(false);
					autoJoinStopButton.setVisible(true);
					autoJoinProgressBar.setVisible(true);
					autoJoinSourceTextField.setEditable(false);
					autoJoinTargetTextField.setEditable(false);
					browseAutoJoinSourceButton.setEnabled(false);
					browseAutoJoinTargetButton.setEnabled(false);
				}
			}
			
			if(WorkerTaskConstants.TASK_STATUS_FAILED.equals(propertyName)){
				Object newValue = evt.getNewValue();
				JOptionPane.showMessageDialog(GSplitFrame.this, "Join failed for : " + newValue);
				autoJoinStartButton.setEnabled(true);
				autoJoinStopButton.setVisible(false);
				autoJoinProgressBar.setVisible(false);
				autoJoinSourceTextField.setEditable(true);
				autoJoinTargetTextField.setEditable(true);
				browseAutoJoinSourceButton.setEnabled(true);
				browseAutoJoinTargetButton.setEnabled(true);
			}
		}
	}

	private class FormActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent evt) {
			if(evt.getSource().equals(closeMenuItem)){
				System.exit(0);
			}
			if(evt.getSource().equals(browseSplitterSourceButton)){
				File file = FileBrowserUtil.openSingleFile(GSplitFrame.this, null, false, context.lastAccessedPathName);
				if(null != file){
					splitterSourceTextField.setText(file.getAbsolutePath());
					
					long size = file.length();
					if(size < SplitterConstants.KB){
						fileSizeLabel.setText("Size: "+file.length() + SplitterConstants.BYTE_TEXT); 
					} else if(size >= SplitterConstants.KB && size < SplitterConstants.MB){
						fileSizeLabel.setText("Size: "+file.length()/SplitterConstants.KB + SplitterConstants.KB_TEXT); 
					} else if(size >= SplitterConstants.MB && size < SplitterConstants.GB){
						fileSizeLabel.setText("Size: "+file.length()/SplitterConstants.MB + SplitterConstants.MB_TEXT); 
					} else if(size >= SplitterConstants.GB){
						fileSizeLabel.setText("Size: "+file.length()/SplitterConstants.GB + SplitterConstants.GB_TEXT); 
					}
					context.lastAccessedPathName = file.getAbsolutePath();
				}
			}
			if(evt.getSource().equals(browseSplitterTargetButton)){
				File dir = FileBrowserUtil.openSingleFile(GSplitFrame.this, null, true, context.lastAccessedPathName);
				if(null != dir){
					splitterTargetTextField.setText(dir.getAbsolutePath());
					context.lastAccessedPathName = dir.getAbsolutePath();
				}
			}
			if(evt.getSource().equals(splitterStartButton)){
				startSplitter();
			}
			if(evt.getSource().equals(splitterStopButton)){
				stopSplitter();
			}
			
			if(evt.getSource().equals(browseAutoJoinSourceButton)){
				File file = FileBrowserUtil.openSingleFile(GSplitFrame.this, 
						new ExtensionFileFilter(new String[]{".mdat"}, "Splitter Metadata file")
						, false, context.lastAccessedPathName, new MetaDataFileView());
				if(null != file){
					autoJoinSourceTextField.setText(file.getAbsolutePath());
					context.lastAccessedPathName = file.getAbsolutePath();
				}
			}
			if(evt.getSource().equals(browseAutoJoinTargetButton)){
				File file = FileBrowserUtil.openSingleFile(GSplitFrame.this, null, false, context.lastAccessedPathName);
				if(null != file){
					autoJoinTargetTextField.setText(file.getAbsolutePath());
					context.lastAccessedPathName = file.getAbsolutePath();
				}
			}
			if(evt.getSource().equals(autoJoinStartButton)){
				startAutoJoin();
			}
			if(evt.getSource().equals(autoJoinStopButton)){
				stopAutoJoin();
			}
		}

		
		
	}
}
