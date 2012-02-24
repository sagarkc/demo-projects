package com.gs.jprompt.app.form;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyVetoException;
import java.text.SimpleDateFormat;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.WindowConstants;


import com.gs.jprompt.JPromptImageConstants;
import com.gs.jprompt.common.JPromptContext;
import com.gs.utils.collection.CollectionUtils;

/**
 * @author Sabuj Das
 *
 */
public class JPromptMainFrame extends JFrame {

	private static final JPromptContext context = JPromptContext.getContext();
	private Timer timer;
	
    /** Creates new form JPromptMainFrame */
    public JPromptMainFrame() {
    	timer = new Timer();
    	
        initComponents();
        timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				java.util.Date date = new java.util.Date();
				SimpleDateFormat f = new SimpleDateFormat("HH:mm:ss");
				String time = f.format(date);
				sysTimeLabel.setText(time);
			}
		}, 1);
        
    }

    
    
    private void initComponents() {
        GridBagConstraints gridBagConstraints;

        actionToolBar = new JToolBar();
        newConsoleButton = new JButton();
        openCommandButton = new JButton();
        jSeparator15 = new JToolBar.Separator();
        copyButton = new JButton();
        pasteButton = new JButton();
        clearScreenButton = new JButton();
        jSeparator16 = new JToolBar.Separator();
        findButton = new JButton();
        promptDesktopPane = new JDesktopPane();
        statusToolBar = new JToolBar();
        jLabel1 = new JLabel();
        quickLaunchButton = new JButton();
        jSeparator9 = new JToolBar.Separator();
        jLabel2 = new JLabel();
        minimizedConcolesButton = new JButton();
        jSeparator10 = new JToolBar.Separator();
        jLabel3 = new JLabel();
        quickRunTextField = new JTextField();
        runInNewConsoleCheckBox = new JCheckBox();
        jSeparator11 = new JToolBar.Separator();
        lineInfoLabel = new JLabel();
        jSeparator12 = new JToolBar.Separator();
        numLockLabel = new JLabel();
        jSeparator13 = new JToolBar.Separator();
        capsLockLabel = new JLabel();
        jSeparator14 = new JToolBar.Separator();
        sysTimeLabel = new JLabel();
        promptFrameMenuBar = new JMenuBar();
        fileMenu = new JMenu();
        newPromptMenuItem = new JMenuItem();
        openCommandFileMenuItem = new JMenuItem();
        jSeparator17 = new JPopupMenu.Separator();
        closeMenuItem = new JMenuItem();
        closeAllMenuItem = new JMenuItem();
        jSeparator2 = new JPopupMenu.Separator();
        saveMenuItem = new JMenuItem();
        saveAsMenuItem = new JMenuItem();
        saveAllMenuItem = new JMenuItem();
        jSeparator3 = new JPopupMenu.Separator();
        openLogFolderMenuItem = new JMenuItem();
        jSeparator4 = new JPopupMenu.Separator();
        exitMenuItem = new JMenuItem();
        editMenu = new JMenu();
        cutMenuItem = new JMenuItem();
        copyMenuItem = new JMenuItem();
        pasteMenuItem = new JMenuItem();
        deleteMenuItem = new JMenuItem();
        jSeparator1 = new JPopupMenu.Separator();
        selectAllMenuItem = new JMenuItem();
        clearScreenMenuItem = new JMenuItem();
        searchMenu = new JMenu();
        findMenuItem = new JMenuItem();
        findReplaceMenuItem = new JMenuItem();
        jSeparator5 = new JPopupMenu.Separator();
        findInAllConsolesMenuItem = new JMenuItem();
        toolsMenu = new JMenu();
        runCommandMenuItem = new JMenuItem();
        jSeparator6 = new JPopupMenu.Separator();
        startPauseMenuItem = new JMenuItem();
        stopMenuItem = new JMenuItem();
        jSeparator7 = new JPopupMenu.Separator();
        startPauseAllMenuItem = new JMenuItem();
        stopAllMenuItem = new JMenuItem();
        jSeparator8 = new JPopupMenu.Separator();
        settingsMenuItem = new JMenuItem();
        viewMenu = new JMenu();
        showToolbarCheckBoxMenuItem = new JCheckBoxMenuItem();
        showStatusbarCheckBoxMenuItem = new JCheckBoxMenuItem();
        jSeparator18 = new JPopupMenu.Separator();
        showEnvVarsMenuItem = new JMenuItem();
        helpMenu = new JMenu();
        helpMenuItem = new JMenuItem();
        aboutMenuItem = new JMenuItem();

        FormListener formListener = new FormListener();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        setTitle(context.getResourceBundle().getString("JPromptMainFrame.title")); // NOI18N
        setIconImage(JPromptImageConstants.JPROMPT_FRAME_ICON.getImage());
        setName("Form"); // NOI18N
        getContentPane().setLayout(new GridBagLayout());

        actionToolBar.setFloatable(false);
        actionToolBar.setRollover(true);
        actionToolBar.setName("actionToolBar"); // NOI18N

        newConsoleButton.setIcon(JPromptImageConstants.NEW_CONSOLE_ICON); // NOI18N
        newConsoleButton.setText(context.getResourceBundle().getString("JPromptMainFrame.newConsoleButton.text")); // NOI18N
        newConsoleButton.setFocusable(false);
        newConsoleButton.setHorizontalTextPosition(SwingConstants.CENTER);
        newConsoleButton.setName("newConsoleButton"); // NOI18N
        newConsoleButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        newConsoleButton.addActionListener(formListener);
        actionToolBar.add(newConsoleButton);

        openCommandButton.setIcon(JPromptImageConstants.FOLDER_OPEN_ICON); // NOI18N
        openCommandButton.setText(context.getResourceBundle().getString("JPromptMainFrame.openCommandButton.text")); // NOI18N
        openCommandButton.setFocusable(false);
        openCommandButton.setHorizontalTextPosition(SwingConstants.CENTER);
        openCommandButton.setName("openCommandButton"); // NOI18N
        openCommandButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        openCommandButton.addActionListener(formListener);
        actionToolBar.add(openCommandButton);

        jSeparator15.setName("jSeparator15"); // NOI18N
        actionToolBar.add(jSeparator15);

        copyButton.setText(context.getResourceBundle().getString("JPromptMainFrame.copyButton.text")); // NOI18N
        copyButton.setIcon(JPromptImageConstants.COPY_ICON);
        copyButton.setFocusable(false);
        copyButton.setHorizontalTextPosition(SwingConstants.CENTER);
        copyButton.setName("copyButton"); // NOI18N
        copyButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        copyButton.addActionListener(formListener);
        actionToolBar.add(copyButton);

        pasteButton.setText(context.getResourceBundle().getString("JPromptMainFrame.pasteButton.text")); // NOI18N
        pasteButton.setIcon(JPromptImageConstants.PASTE_ICON);
        pasteButton.setFocusable(false);
        pasteButton.setHorizontalTextPosition(SwingConstants.CENTER);
        pasteButton.setName("pasteButton"); // NOI18N
        pasteButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        pasteButton.addActionListener(formListener);
        actionToolBar.add(pasteButton);

        clearScreenButton.setText(context.getResourceBundle().getString("JPromptMainFrame.clearScreenButton.text")); // NOI18N
        clearScreenButton.setIcon(JPromptImageConstants.CLEAR_ICON);
        clearScreenButton.setFocusable(false);
        clearScreenButton.setHorizontalTextPosition(SwingConstants.CENTER);
        clearScreenButton.setName("clearScreenButton"); // NOI18N
        clearScreenButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        clearScreenButton.addActionListener(formListener);
        actionToolBar.add(clearScreenButton);

        jSeparator16.setName("jSeparator16"); // NOI18N
        actionToolBar.add(jSeparator16);

        findButton.setText(context.getResourceBundle().getString("JPromptMainFrame.findButton.text")); // NOI18N
        findButton.setIcon(JPromptImageConstants.FIND_ICON);
        findButton.setFocusable(false);
        findButton.setHorizontalTextPosition(SwingConstants.CENTER);
        findButton.setName("findButton"); // NOI18N
        findButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        findButton.addActionListener(formListener);
        actionToolBar.add(findButton);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(0, 0, 2, 0);
        getContentPane().add(actionToolBar, gridBagConstraints);

        promptDesktopPane.setBackground(SystemColor.activeCaption);
        promptDesktopPane.setMinimumSize(new Dimension(640, 380));
        promptDesktopPane.setName("promptDesktopPane"); // NOI18N
        promptDesktopPane.setPreferredSize(new Dimension(640, 380));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(promptDesktopPane, gridBagConstraints);

        statusToolBar.setFloatable(false);
        statusToolBar.setRollover(true);
        statusToolBar.setName("statusToolBar"); // NOI18N

        jLabel1.setText(context.getResourceBundle().getString("JPromptMainFrame.jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N
        statusToolBar.add(jLabel1);

        quickLaunchButton.setIcon(JPromptImageConstants.QUICK_LAUNCH_ICON);
        quickLaunchButton.setText(context.getResourceBundle().getString("JPromptMainFrame.quickLaunchButton.text")); // NOI18N
        quickLaunchButton.setFocusable(false);
        quickLaunchButton.setHorizontalTextPosition(SwingConstants.CENTER);
        quickLaunchButton.setName("quickLaunchButton"); // NOI18N
        quickLaunchButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        quickLaunchButton.addActionListener(formListener);
        statusToolBar.add(quickLaunchButton);

        jSeparator9.setName("jSeparator9"); // NOI18N
        statusToolBar.add(jSeparator9);

        jLabel2.setText(context.getResourceBundle().getString("JPromptMainFrame.jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N
        statusToolBar.add(jLabel2);

        minimizedConcolesButton.setIcon(JPromptImageConstants.MINIMIZED_WINDOWS_ICON);
        minimizedConcolesButton.setText(context.getResourceBundle().getString("JPromptMainFrame.minimizedConcolesButton.text")); // NOI18N
        minimizedConcolesButton.setFocusable(false);
        minimizedConcolesButton.setHorizontalTextPosition(SwingConstants.CENTER);
        minimizedConcolesButton.setName("minimizedConcolesButton"); // NOI18N
        minimizedConcolesButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        minimizedConcolesButton.addActionListener(formListener);
        statusToolBar.add(minimizedConcolesButton);

        jSeparator10.setName("jSeparator10"); // NOI18N
        statusToolBar.add(jSeparator10);

        jLabel3.setText(context.getResourceBundle().getString("JPromptMainFrame.jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N
        statusToolBar.add(jLabel3);

        quickRunTextField.setText(context.getResourceBundle().getString("JPromptMainFrame.quickRunTextField.text")); // NOI18N
        quickRunTextField.setMinimumSize(new Dimension(80, 20));
        quickRunTextField.setName("quickRunTextField"); // NOI18N
        quickRunTextField.setPreferredSize(new Dimension(80, 20));
        quickRunTextField.addActionListener(formListener);
        quickRunTextField.addKeyListener(formListener);
        statusToolBar.add(quickRunTextField);

        runInNewConsoleCheckBox.setSelected(true);
        runInNewConsoleCheckBox.setText(context.getResourceBundle().getString("JPromptMainFrame.runInNewConsoleCheckBox.text")); // NOI18N
        runInNewConsoleCheckBox.setFocusable(false);
        runInNewConsoleCheckBox.setHorizontalTextPosition(SwingConstants.RIGHT);
        runInNewConsoleCheckBox.setName("runInNewConsoleCheckBox"); // NOI18N
        runInNewConsoleCheckBox.setVerticalTextPosition(SwingConstants.BOTTOM);
        runInNewConsoleCheckBox.addActionListener(formListener);
        statusToolBar.add(runInNewConsoleCheckBox);

        jSeparator11.setName("jSeparator11"); // NOI18N
        statusToolBar.add(jSeparator11);

        lineInfoLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        lineInfoLabel.setText(context.getResourceBundle().getString("JPromptMainFrame.lineInfoLabel.text")); // NOI18N
        lineInfoLabel.setMaximumSize(new Dimension(80, 14));
        lineInfoLabel.setMinimumSize(new Dimension(80, 14));
        lineInfoLabel.setName("lineInfoLabel"); // NOI18N
        lineInfoLabel.setPreferredSize(new Dimension(80, 14));
        statusToolBar.add(lineInfoLabel);

        jSeparator12.setName("jSeparator12"); // NOI18N
        statusToolBar.add(jSeparator12);

        numLockLabel.setHorizontalAlignment(SwingConstants.CENTER);
        numLockLabel.setText(context.getResourceBundle().getString("JPromptMainFrame.numLockLabel.text")); // NOI18N
        numLockLabel.setMaximumSize(new Dimension(35, 14));
        numLockLabel.setMinimumSize(new Dimension(35, 14));
        numLockLabel.setName("numLockLabel"); // NOI18N
        numLockLabel.setPreferredSize(new Dimension(35, 14));
        statusToolBar.add(numLockLabel);

        jSeparator13.setName("jSeparator13"); // NOI18N
        statusToolBar.add(jSeparator13);

        capsLockLabel.setHorizontalAlignment(SwingConstants.CENTER);
        capsLockLabel.setText(context.getResourceBundle().getString("JPromptMainFrame.capsLockLabel.text")); // NOI18N
        capsLockLabel.setMaximumSize(new Dimension(35, 14));
        capsLockLabel.setMinimumSize(new Dimension(35, 14));
        capsLockLabel.setName("capsLockLabel"); // NOI18N
        capsLockLabel.setPreferredSize(new Dimension(35, 14));
        statusToolBar.add(capsLockLabel);

        jSeparator14.setName("jSeparator14"); // NOI18N
        statusToolBar.add(jSeparator14);

        sysTimeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        //sysTimeLabel.setText(context.getResourceBundle().getString("JPromptMainFrame.sysTimeLabel.text")); // NOI18N
        //sysTimeLabel.setText(new Date().toString());
        sysTimeLabel.setMaximumSize(new Dimension(80, 14));
        sysTimeLabel.setMinimumSize(new Dimension(80, 14));
        sysTimeLabel.setName("sysTimeLabel"); // NOI18N
        sysTimeLabel.setPreferredSize(new Dimension(80, 14));
        sysTimeLabel.addMouseListener(formListener);
        sysTimeLabel.setFont(context.getDigital_7_mono());
        statusToolBar.add(sysTimeLabel);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.SOUTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(2, 0, 0, 0);
        getContentPane().add(statusToolBar, gridBagConstraints);

        promptFrameMenuBar.setName("promptFrameMenuBar"); // NOI18N

        fileMenu.setText(context.getResourceBundle().getString("JPromptMainFrame.fileMenu.text")); // NOI18N
        fileMenu.setName("fileMenu"); // NOI18N

        newPromptMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
        newPromptMenuItem.setIcon(JPromptImageConstants.NEW_CONSOLE_ICON);
        newPromptMenuItem.setText(context.getResourceBundle().getString("JPromptMainFrame.newPromptMenuItem.text")); // NOI18N
        newPromptMenuItem.setName("newPromptMenuItem"); // NOI18N
        newPromptMenuItem.addActionListener(formListener);
        fileMenu.add(newPromptMenuItem);

        openCommandFileMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
        openCommandFileMenuItem.setIcon(JPromptImageConstants.FOLDER_OPEN_ICON);
        openCommandFileMenuItem.setText(context.getResourceBundle().getString("JPromptMainFrame.openCommandFileMenuItem.text")); // NOI18N
        openCommandFileMenuItem.setName("openCommandFileMenuItem"); // NOI18N
        openCommandFileMenuItem.addActionListener(formListener);
        fileMenu.add(openCommandFileMenuItem);

        jSeparator17.setName("jSeparator17"); // NOI18N
        fileMenu.add(jSeparator17);

        closeMenuItem.setText(context.getResourceBundle().getString("JPromptMainFrame.closeMenuItem.text")); // NOI18N
        closeMenuItem.setIcon(JPromptImageConstants.CLOSE_ICON);
        closeMenuItem.setName("closeMenuItem"); // NOI18N
        closeMenuItem.addActionListener(formListener);
        fileMenu.add(closeMenuItem);

        closeAllMenuItem.setText(context.getResourceBundle().getString("JPromptMainFrame.closeAllMenuItem.text")); // NOI18N
        closeAllMenuItem.setIcon(JPromptImageConstants.CLOSE_ALL_ICON);
        closeAllMenuItem.setName("closeAllMenuItem"); // NOI18N
        closeAllMenuItem.addActionListener(formListener);
        fileMenu.add(closeAllMenuItem);

        jSeparator2.setName("jSeparator2"); // NOI18N
        fileMenu.add(jSeparator2);

        saveMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
        saveMenuItem.setIcon(JPromptImageConstants.SAVE_ICON);
        saveMenuItem.setText(context.getResourceBundle().getString("JPromptMainFrame.saveMenuItem.text")); // NOI18N
        saveMenuItem.setName("saveMenuItem"); // NOI18N
        saveMenuItem.addActionListener(formListener);
        fileMenu.add(saveMenuItem);

        saveAsMenuItem.setIcon(JPromptImageConstants.SAVE_AS_ICON); // NOI18N
        saveAsMenuItem.setText(context.getResourceBundle().getString("JPromptMainFrame.saveAsMenuItem.text")); // NOI18N
        saveAsMenuItem.setName("saveAsMenuItem"); // NOI18N
        saveAsMenuItem.addActionListener(formListener);
        fileMenu.add(saveAsMenuItem);

        saveAllMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.SHIFT_MASK | InputEvent.CTRL_MASK));
        saveAllMenuItem.setIcon(JPromptImageConstants.SAVE_ALL_ICON); // NOI18N
        saveAllMenuItem.setText(context.getResourceBundle().getString("JPromptMainFrame.saveAllMenuItem.text")); // NOI18N
        saveAllMenuItem.setName("saveAllMenuItem"); // NOI18N
        saveAllMenuItem.addActionListener(formListener);
        fileMenu.add(saveAllMenuItem);

        jSeparator3.setName("jSeparator3"); // NOI18N
        fileMenu.add(jSeparator3);

        openLogFolderMenuItem.setText(context.getResourceBundle().getString("JPromptMainFrame.openLogFolderMenuItem.text")); // NOI18N
        openLogFolderMenuItem.setIcon(JPromptImageConstants.OPEN_LOG_FOLDER_ICON);
        openLogFolderMenuItem.setName("openLogFolderMenuItem"); // NOI18N
        openLogFolderMenuItem.addActionListener(formListener);
        fileMenu.add(openLogFolderMenuItem);

        jSeparator4.setName("jSeparator4"); // NOI18N
        fileMenu.add(jSeparator4);

        exitMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.ALT_MASK));
        exitMenuItem.setIcon(new ImageIcon(getClass().getResource("/images/exit.gif"))); // NOI18N
        exitMenuItem.setText(context.getResourceBundle().getString("JPromptMainFrame.exitMenuItem.text")); // NOI18N
        exitMenuItem.setName("exitMenuItem"); // NOI18N
        exitMenuItem.addActionListener(formListener);
        fileMenu.add(exitMenuItem);

        promptFrameMenuBar.add(fileMenu);

        editMenu.setText(context.getResourceBundle().getString("JPromptMainFrame.editMenu.text")); // NOI18N
        editMenu.setName("editMenu"); // NOI18N

        cutMenuItem.setText(context.getResourceBundle().getString("JPromptMainFrame.cutMenuItem.text")); // NOI18N
        cutMenuItem.setName("cutMenuItem"); // NOI18N
        cutMenuItem.addActionListener(formListener);
        editMenu.add(cutMenuItem);

        copyMenuItem.setText(context.getResourceBundle().getString("JPromptMainFrame.copyMenuItem.text")); // NOI18N
        copyMenuItem.setName("copyMenuItem"); // NOI18N
        copyMenuItem.addActionListener(formListener);
        editMenu.add(copyMenuItem);

        pasteMenuItem.setText(context.getResourceBundle().getString("JPromptMainFrame.pasteMenuItem.text")); // NOI18N
        pasteMenuItem.setName("pasteMenuItem"); // NOI18N
        pasteMenuItem.addActionListener(formListener);
        editMenu.add(pasteMenuItem);

        deleteMenuItem.setText(context.getResourceBundle().getString("JPromptMainFrame.deleteMenuItem.text")); // NOI18N
        deleteMenuItem.setName("deleteMenuItem"); // NOI18N
        deleteMenuItem.addActionListener(formListener);
        editMenu.add(deleteMenuItem);

        jSeparator1.setName("jSeparator1"); // NOI18N
        editMenu.add(jSeparator1);

        selectAllMenuItem.setText(context.getResourceBundle().getString("JPromptMainFrame.selectAllMenuItem.text")); // NOI18N
        selectAllMenuItem.setName("selectAllMenuItem"); // NOI18N
        selectAllMenuItem.addActionListener(formListener);
        editMenu.add(selectAllMenuItem);

        clearScreenMenuItem.setText(context.getResourceBundle().getString("JPromptMainFrame.clearScreenMenuItem.text")); // NOI18N
        clearScreenMenuItem.setName("clearScreenMenuItem"); // NOI18N
        clearScreenMenuItem.addActionListener(formListener);
        editMenu.add(clearScreenMenuItem);

        promptFrameMenuBar.add(editMenu);

        searchMenu.setText(context.getResourceBundle().getString("JPromptMainFrame.searchMenu.text")); // NOI18N
        searchMenu.setName("searchMenu"); // NOI18N

        findMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_MASK));
        findMenuItem.setText(context.getResourceBundle().getString("JPromptMainFrame.findMenuItem.text")); // NOI18N
        findMenuItem.setName("findMenuItem"); // NOI18N
        findMenuItem.addActionListener(formListener);
        searchMenu.add(findMenuItem);

        findReplaceMenuItem.setText(context.getResourceBundle().getString("JPromptMainFrame.findReplaceMenuItem.text")); // NOI18N
        findReplaceMenuItem.setName("findReplaceMenuItem"); // NOI18N
        findReplaceMenuItem.addActionListener(formListener);
        searchMenu.add(findReplaceMenuItem);

        jSeparator5.setName("jSeparator5"); // NOI18N
        searchMenu.add(jSeparator5);

        findInAllConsolesMenuItem.setText(context.getResourceBundle().getString("JPromptMainFrame.findInAllConsolesMenuItem.text")); // NOI18N
        findInAllConsolesMenuItem.setName("findInAllConsolesMenuItem"); // NOI18N
        findInAllConsolesMenuItem.addActionListener(formListener);
        searchMenu.add(findInAllConsolesMenuItem);

        promptFrameMenuBar.add(searchMenu);

        toolsMenu.setText(context.getResourceBundle().getString("JPromptMainFrame.toolsMenu.text")); // NOI18N
        toolsMenu.setName("toolsMenu"); // NOI18N

        runCommandMenuItem.setText(context.getResourceBundle().getString("JPromptMainFrame.runCommandMenuItem.text")); // NOI18N
        runCommandMenuItem.setName("runCommandMenuItem"); // NOI18N
        runCommandMenuItem.addActionListener(formListener);
        toolsMenu.add(runCommandMenuItem);

        jSeparator6.setName("jSeparator6"); // NOI18N
        toolsMenu.add(jSeparator6);

        startPauseMenuItem.setText(context.getResourceBundle().getString("JPromptMainFrame.startPauseMenuItem.text")); // NOI18N
        startPauseMenuItem.setName("startPauseMenuItem"); // NOI18N
        startPauseMenuItem.addActionListener(formListener);
        toolsMenu.add(startPauseMenuItem);

        stopMenuItem.setText(context.getResourceBundle().getString("JPromptMainFrame.stopMenuItem.text")); // NOI18N
        stopMenuItem.setName("stopMenuItem"); // NOI18N
        stopMenuItem.addActionListener(formListener);
        toolsMenu.add(stopMenuItem);

        jSeparator7.setName("jSeparator7"); // NOI18N
        toolsMenu.add(jSeparator7);

        startPauseAllMenuItem.setText(context.getResourceBundle().getString("JPromptMainFrame.startPauseAllMenuItem.text")); // NOI18N
        startPauseAllMenuItem.setName("startPauseAllMenuItem"); // NOI18N
        startPauseAllMenuItem.addActionListener(formListener);
        toolsMenu.add(startPauseAllMenuItem);

        stopAllMenuItem.setText(context.getResourceBundle().getString("JPromptMainFrame.stopAllMenuItem.text")); // NOI18N
        stopAllMenuItem.setName("stopAllMenuItem"); // NOI18N
        stopAllMenuItem.addActionListener(formListener);
        toolsMenu.add(stopAllMenuItem);

        jSeparator8.setName("jSeparator8"); // NOI18N
        toolsMenu.add(jSeparator8);

        settingsMenuItem.setText(context.getResourceBundle().getString("JPromptMainFrame.settingsMenuItem.text")); // NOI18N
        settingsMenuItem.setName("settingsMenuItem"); // NOI18N
        settingsMenuItem.addActionListener(formListener);
        toolsMenu.add(settingsMenuItem);

        promptFrameMenuBar.add(toolsMenu);

        viewMenu.setText(context.getResourceBundle().getString("JPromptMainFrame.viewMenu.text")); // NOI18N
        viewMenu.setName("viewMenu"); // NOI18N

        showToolbarCheckBoxMenuItem.setSelected(true);
        showToolbarCheckBoxMenuItem.setText(context.getResourceBundle().getString("JPromptMainFrame.showToolbarCheckBoxMenuItem.text")); // NOI18N
        showToolbarCheckBoxMenuItem.setName("showToolbarCheckBoxMenuItem"); // NOI18N
        showToolbarCheckBoxMenuItem.addActionListener(formListener);
        viewMenu.add(showToolbarCheckBoxMenuItem);

        showStatusbarCheckBoxMenuItem.setSelected(true);
        showStatusbarCheckBoxMenuItem.setText(context.getResourceBundle().getString("JPromptMainFrame.showStatusbarCheckBoxMenuItem.text")); // NOI18N
        showStatusbarCheckBoxMenuItem.setName("showStatusbarCheckBoxMenuItem"); // NOI18N
        showStatusbarCheckBoxMenuItem.addActionListener(formListener);
        viewMenu.add(showStatusbarCheckBoxMenuItem);

        jSeparator18.setName("jSeparator18"); // NOI18N
        viewMenu.add(jSeparator18);

        showEnvVarsMenuItem.setText(context.getResourceBundle().getString("JPromptMainFrame.showEnvVarsMenuItem.text")); // NOI18N
        showEnvVarsMenuItem.setName("showEnvVarsMenuItem"); // NOI18N
        showEnvVarsMenuItem.addActionListener(formListener);
        viewMenu.add(showEnvVarsMenuItem);

        promptFrameMenuBar.add(viewMenu);

        helpMenu.setText(context.getResourceBundle().getString("JPromptMainFrame.helpMenu.text")); // NOI18N
        helpMenu.setName("helpMenu"); // NOI18N

        helpMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
        helpMenuItem.setText(context.getResourceBundle().getString("JPromptMainFrame.helpMenuItem.text")); // NOI18N
        helpMenuItem.setName("helpMenuItem"); // NOI18N
        helpMenuItem.addActionListener(formListener);
        helpMenu.add(helpMenuItem);

        aboutMenuItem.setText(context.getResourceBundle().getString("JPromptMainFrame.aboutMenuItem.text")); // NOI18N
        aboutMenuItem.setName("aboutMenuItem"); // NOI18N
        aboutMenuItem.addActionListener(formListener);
        helpMenu.add(aboutMenuItem);

        promptFrameMenuBar.add(helpMenu);

        setJMenuBar(promptFrameMenuBar);

        pack();
    }

    // Code for dispatching events from components to event handlers.

    private class FormListener implements ActionListener, KeyListener, MouseListener {
        FormListener() {}
        public void actionPerformed(ActionEvent evt) {
            if (evt.getSource() == newPromptMenuItem) {
                JPromptMainFrame.this.newPromptMenuItemActionPerformed(evt);
            }
            else if (evt.getSource() == openCommandFileMenuItem) {
                JPromptMainFrame.this.openCommandFileMenuItemActionPerformed(evt);
            }
            else if (evt.getSource() == closeMenuItem) {
                JPromptMainFrame.this.closeMenuItemActionPerformed(evt);
            }
            else if (evt.getSource() == closeAllMenuItem) {
                JPromptMainFrame.this.closeAllMenuItemActionPerformed(evt);
            }
            else if (evt.getSource() == saveMenuItem) {
                JPromptMainFrame.this.saveMenuItemActionPerformed(evt);
            }
            else if (evt.getSource() == saveAsMenuItem) {
                JPromptMainFrame.this.saveAsMenuItemActionPerformed(evt);
            }
            else if (evt.getSource() == saveAllMenuItem) {
                JPromptMainFrame.this.saveAllMenuItemActionPerformed(evt);
            }
            else if (evt.getSource() == openLogFolderMenuItem) {
                JPromptMainFrame.this.openLogFolderMenuItemActionPerformed(evt);
            }
            else if (evt.getSource() == exitMenuItem) {
                JPromptMainFrame.this.exitMenuItemActionPerformed(evt);
            }
            else if (evt.getSource() == newConsoleButton) {
                JPromptMainFrame.this.newConsoleButtonActionPerformed(evt);
            }
            else if (evt.getSource() == openCommandButton) {
                JPromptMainFrame.this.openCommandButtonActionPerformed(evt);
            }
            else if (evt.getSource() == copyButton) {
                JPromptMainFrame.this.copyButtonActionPerformed(evt);
            }
            else if (evt.getSource() == pasteButton) {
                JPromptMainFrame.this.pasteButtonActionPerformed(evt);
            }
            else if (evt.getSource() == clearScreenButton) {
                JPromptMainFrame.this.clearScreenButtonActionPerformed(evt);
            }
            else if (evt.getSource() == findButton) {
                JPromptMainFrame.this.findButtonActionPerformed(evt);
            }
            else if (evt.getSource() == quickLaunchButton) {
                JPromptMainFrame.this.quickLaunchButtonActionPerformed(evt);
            }
            else if (evt.getSource() == minimizedConcolesButton) {
                JPromptMainFrame.this.minimizedConcolesButtonActionPerformed(evt);
            }
            else if (evt.getSource() == quickRunTextField) {
                JPromptMainFrame.this.quickRunTextFieldActionPerformed(evt);
            }
            else if (evt.getSource() == runInNewConsoleCheckBox) {
                JPromptMainFrame.this.runInNewConsoleCheckBoxActionPerformed(evt);
            }
            else if (evt.getSource() == cutMenuItem) {
                JPromptMainFrame.this.cutMenuItemActionPerformed(evt);
            }
            else if (evt.getSource() == copyMenuItem) {
                JPromptMainFrame.this.copyMenuItemActionPerformed(evt);
            }
            else if (evt.getSource() == pasteMenuItem) {
                JPromptMainFrame.this.pasteMenuItemActionPerformed(evt);
            }
            else if (evt.getSource() == deleteMenuItem) {
                JPromptMainFrame.this.deleteMenuItemActionPerformed(evt);
            }
            else if (evt.getSource() == selectAllMenuItem) {
                JPromptMainFrame.this.selectAllMenuItemActionPerformed(evt);
            }
            else if (evt.getSource() == clearScreenMenuItem) {
                JPromptMainFrame.this.clearScreenMenuItemActionPerformed(evt);
            }
            else if (evt.getSource() == findMenuItem) {
                JPromptMainFrame.this.findMenuItemActionPerformed(evt);
            }
            else if (evt.getSource() == findReplaceMenuItem) {
                JPromptMainFrame.this.findReplaceMenuItemActionPerformed(evt);
            }
            else if (evt.getSource() == findInAllConsolesMenuItem) {
                JPromptMainFrame.this.findInAllConsolesMenuItemActionPerformed(evt);
            }
            else if (evt.getSource() == runCommandMenuItem) {
                JPromptMainFrame.this.runCommandMenuItemActionPerformed(evt);
            }
            else if (evt.getSource() == startPauseMenuItem) {
                JPromptMainFrame.this.startPauseMenuItemActionPerformed(evt);
            }
            else if (evt.getSource() == stopMenuItem) {
                JPromptMainFrame.this.stopMenuItemActionPerformed(evt);
            }
            else if (evt.getSource() == startPauseAllMenuItem) {
                JPromptMainFrame.this.startPauseAllMenuItemActionPerformed(evt);
            }
            else if (evt.getSource() == stopAllMenuItem) {
                JPromptMainFrame.this.stopAllMenuItemActionPerformed(evt);
            }
            else if (evt.getSource() == settingsMenuItem) {
                JPromptMainFrame.this.settingsMenuItemActionPerformed(evt);
            }
            else if (evt.getSource() == helpMenuItem) {
                JPromptMainFrame.this.helpMenuItemActionPerformed(evt);
            }
            else if (evt.getSource() == aboutMenuItem) {
                JPromptMainFrame.this.aboutMenuItemActionPerformed(evt);
            }
            else if (evt.getSource() == showToolbarCheckBoxMenuItem) {
                JPromptMainFrame.this.showToolbarCheckBoxMenuItemActionPerformed(evt);
            }
            else if (evt.getSource() == showStatusbarCheckBoxMenuItem) {
                JPromptMainFrame.this.showStatusbarCheckBoxMenuItemActionPerformed(evt);
            }
            else if (evt.getSource() == showEnvVarsMenuItem) {
                JPromptMainFrame.this.showEnvVarsMenuItemActionPerformed(evt);
            }
        }

        public void keyPressed(KeyEvent evt) {
            if (evt.getSource() == quickRunTextField) {
                JPromptMainFrame.this.quickRunTextFieldKeyPressed(evt);
            }
        }

        public void keyReleased(KeyEvent evt) {
            if (evt.getSource() == quickRunTextField) {
                JPromptMainFrame.this.quickRunTextFieldKeyReleased(evt);
            }
        }

        public void keyTyped(KeyEvent evt) {
            if (evt.getSource() == quickRunTextField) {
                JPromptMainFrame.this.quickRunTextFieldKeyTyped(evt);
            }
        }

        public void mouseClicked(MouseEvent evt) {
        }

        public void mouseEntered(MouseEvent evt) {
            if (evt.getSource() == sysTimeLabel) {
                JPromptMainFrame.this.sysTimeLabelMouseEntered(evt);
            }
        }

        public void mouseExited(MouseEvent evt) {
            if (evt.getSource() == sysTimeLabel) {
                JPromptMainFrame.this.sysTimeLabelMouseExited(evt);
            }
        }

        public void mousePressed(MouseEvent evt) {
        }

        public void mouseReleased(MouseEvent evt) {
        }
    }

    private void newPromptMenuItemActionPerformed(ActionEvent evt) {
        JPromptInternalFrame f = new JPromptInternalFrame(this);
        
        JInternalFrame[] iFrames = promptDesktopPane.getAllFrames();
        if(null != iFrames && iFrames.length > 0){
        	Point location = iFrames[iFrames.length-1].getLocation();
        	f.setLocation((int)location.getX()+20, (int)location.getY()+20);
        	
        }
        try {
			f.setSelected(true);
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
        f.setVisible(true);
        
        
        promptDesktopPane.add(f);
    }

    private void openCommandFileMenuItemActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void closeMenuItemActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void closeAllMenuItemActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void saveMenuItemActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void saveAsMenuItemActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void saveAllMenuItemActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void openLogFolderMenuItemActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void exitMenuItemActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void newConsoleButtonActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void openCommandButtonActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void copyButtonActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void pasteButtonActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void clearScreenButtonActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void findButtonActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void quickLaunchButtonActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void minimizedConcolesButtonActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void quickRunTextFieldActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void quickRunTextFieldKeyPressed(KeyEvent evt) {
        // TODO add your handling code here:
    }

    private void quickRunTextFieldKeyReleased(KeyEvent evt) {
        // TODO add your handling code here:
    }

    private void quickRunTextFieldKeyTyped(KeyEvent evt) {
        // TODO add your handling code here:
    }

    private void runInNewConsoleCheckBoxActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void sysTimeLabelMouseEntered(MouseEvent evt) {
        // TODO add your handling code here:
    }

    private void sysTimeLabelMouseExited(MouseEvent evt) {
        // TODO add your handling code here:
    }

    private void cutMenuItemActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void copyMenuItemActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void pasteMenuItemActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void deleteMenuItemActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void selectAllMenuItemActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void clearScreenMenuItemActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void findMenuItemActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void findReplaceMenuItemActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void findInAllConsolesMenuItemActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void runCommandMenuItemActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void startPauseMenuItemActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void stopMenuItemActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void startPauseAllMenuItemActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void stopAllMenuItemActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void settingsMenuItemActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void helpMenuItemActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void aboutMenuItemActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void showToolbarCheckBoxMenuItemActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void showStatusbarCheckBoxMenuItemActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void showEnvVarsMenuItemActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }
    
    
    public void updateCaretStatus(int row, int column){
    	lineInfoLabel.setText("" + row + " : " + column);
    }

    
    // Variables declaration - do not modify
    private JMenuItem aboutMenuItem;
    private JToolBar actionToolBar;
    private JLabel capsLockLabel;
    private JButton clearScreenButton;
    private JMenuItem clearScreenMenuItem;
    private JMenuItem closeAllMenuItem;
    private JMenuItem closeMenuItem;
    private JButton copyButton;
    private JMenuItem copyMenuItem;
    private JMenuItem cutMenuItem;
    private JMenuItem deleteMenuItem;
    private JMenu editMenu;
    private JMenuItem exitMenuItem;
    private JMenu fileMenu;
    private JButton findButton;
    private JMenuItem findInAllConsolesMenuItem;
    private JMenuItem findMenuItem;
    private JMenuItem findReplaceMenuItem;
    private JMenu helpMenu;
    private JMenuItem helpMenuItem;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JPopupMenu.Separator jSeparator1;
    private JToolBar.Separator jSeparator10;
    private JToolBar.Separator jSeparator11;
    private JToolBar.Separator jSeparator12;
    private JToolBar.Separator jSeparator13;
    private JToolBar.Separator jSeparator14;
    private JToolBar.Separator jSeparator15;
    private JToolBar.Separator jSeparator16;
    private JPopupMenu.Separator jSeparator17;
    private JPopupMenu.Separator jSeparator18;
    private JPopupMenu.Separator jSeparator2;
    private JPopupMenu.Separator jSeparator3;
    private JPopupMenu.Separator jSeparator4;
    private JPopupMenu.Separator jSeparator5;
    private JPopupMenu.Separator jSeparator6;
    private JPopupMenu.Separator jSeparator7;
    private JPopupMenu.Separator jSeparator8;
    private JToolBar.Separator jSeparator9;
    private JLabel lineInfoLabel;
    private JButton minimizedConcolesButton;
    private JButton newConsoleButton;
    private JMenuItem newPromptMenuItem;
    private JLabel numLockLabel;
    private JButton openCommandButton;
    private JMenuItem openCommandFileMenuItem;
    private JMenuItem openLogFolderMenuItem;
    private JButton pasteButton;
    private JMenuItem pasteMenuItem;
    private JDesktopPane promptDesktopPane;
    private JMenuBar promptFrameMenuBar;
    private JButton quickLaunchButton;
    private JTextField quickRunTextField;
    private JMenuItem runCommandMenuItem;
    private JCheckBox runInNewConsoleCheckBox;
    private JMenuItem saveAllMenuItem;
    private JMenuItem saveAsMenuItem;
    private JMenuItem saveMenuItem;
    private JMenu searchMenu;
    private JMenuItem selectAllMenuItem;
    private JMenuItem settingsMenuItem;
    private JMenuItem showEnvVarsMenuItem;
    private JCheckBoxMenuItem showStatusbarCheckBoxMenuItem;
    private JCheckBoxMenuItem showToolbarCheckBoxMenuItem;
    private JMenuItem startPauseAllMenuItem;
    private JMenuItem startPauseMenuItem;
    private JToolBar statusToolBar;
    private JMenuItem stopAllMenuItem;
    private JMenuItem stopMenuItem;
    private JLabel sysTimeLabel;
    private JMenu toolsMenu;
    private JMenu viewMenu;
    // End of variables declaration

}
