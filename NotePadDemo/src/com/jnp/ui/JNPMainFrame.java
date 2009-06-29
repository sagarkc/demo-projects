/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * JNPMainFrame.java
 *
 * Created on Jun 6, 2009, 3:04:56 PM
 */
package com.jnp.ui;

import com.jnp.JnpConstants;
import com.jnp.utils.FileRWUtil;
import com.jnp.utils.NotePadContext;
import com.lowagie.text.Chapter;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.html.HtmlWriter;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.DefaultFontMapper;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.rtf.RtfWriter2;
import de.muntjak.tinylookandfeel.TinyLookAndFeel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;
import javax.swing.undo.UndoableEdit;
import org.fife.plaf.Office2003.Office2003LookAndFeel;
import org.jvnet.substance.skin.SubstanceBusinessBlueSteelLookAndFeel;
import org.jvnet.substance.skin.SubstanceMistAquaLookAndFeel;
import org.jvnet.substance.skin.SubstanceOfficeBlue2007LookAndFeel;

/**
 *
 * @author Green Moon
 */
public class JNPMainFrame extends javax.swing.JFrame implements ChangeListener,
        PropertyChangeListener, ActionListener, KeyListener {
    
    public static int NEW_FILE_COUNT = 1;
    public static java.util.List<String> OPEN_FILE_NAMES = new ArrayList<String>();
    private static NotePadContext context = NotePadContext.getInstance();
    public static final java.awt.Font DEFAULT_TEXT_FONT =
            new java.awt.Font(java.awt.Font.MONOSPACED,
                java.awt.Font.PLAIN, 12);

    /** Creates new form JNPMainFrame */
    public JNPMainFrame() {
        loadSavedContext();
        initComponents();
        //mainSplitPane.getLeftComponent().setVisible(false);
        setFrameProperty();
        addNewFile();
        setKeySetup();
        // ********************************

        MiniExplorerPanel mep = new MiniExplorerPanel(this);
        leftPanelTabbedPane.addTab("Explorer", mep);
        //mainSplitPane.getLeftComponent().setVisible(true);
        ListExplorerPanel lep = new ListExplorerPanel();
        leftPanelTabbedPane.addTab("List Explorer", lep);

        // ********************************
    }

    public void setKeySetup(){
        Toolkit tool = Toolkit.getDefaultToolkit();
        try{
            // number lock
            if(tool.getLockingKeyState(KeyEvent.VK_NUM_LOCK)){
                numLockLabel.setText("NUM");
            }else{
                numLockLabel.setText("");
            }
            // caps lock
            if(tool.getLockingKeyState(KeyEvent.VK_CAPS_LOCK)){
                capsLockLabel.setText("CAPS");
            }else{
                capsLockLabel.setText("");
            }
            // scroll lock
            if(tool.getLockingKeyState(KeyEvent.VK_SCROLL_LOCK)){
                scrollLockLabel.setText("SCROLL");
            }else{
                scrollLockLabel.setText("");
            }
        }catch(Exception e){

        }
    }

    private void loadSavedContext() {
        context = FileRWUtil.readContext();
        if (context == null) {
            context = NotePadContext.getInstance();
        }
    }

    private void saveCurrentContext() {
        context.frameLocation = getLocation();
        context.frameSize = getSize();

        FileRWUtil.writeContext(context);
    }

    private void setFrameProperty() {
        setTitle("Notepad");
        setIconImage(
                new javax.swing.ImageIcon(
                getClass().getResource("/icons/Notepadicon.png")).getImage());
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        Dimension fd = getSize();
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension resolution = tk.getScreenSize();
        if (context.frameLocation != null) {
            setLocation(context.frameLocation);
        } else {
            Point cd = new Point();
            cd.x = resolution.width / 2 - fd.width / 2;
            cd.y = resolution.height / 2 - fd.height / 2;
            setLocation(cd);
        }
        if (context.frameSize != null) {
            setSize(context.frameSize);
        }
        addKeyListener(this);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        lnfButtonGroup = new javax.swing.ButtonGroup();
        mainPanel = new javax.swing.JPanel();
        mainToolBar = new javax.swing.JToolBar();
        newFileButton = new javax.swing.JButton();
        openButton = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JToolBar.Separator();
        saveButton = new javax.swing.JButton();
        saveAsButton = new javax.swing.JButton();
        saveAllButton = new javax.swing.JButton();
        jSeparator5 = new javax.swing.JToolBar.Separator();
        printButton = new javax.swing.JButton();
        jSeparator14 = new javax.swing.JToolBar.Separator();
        undoButton = new javax.swing.JButton();
        redoButton = new javax.swing.JButton();
        jSeparator9 = new javax.swing.JToolBar.Separator();
        cutButton = new javax.swing.JButton();
        copyButton = new javax.swing.JButton();
        pasteButton = new javax.swing.JButton();
        jSeparator10 = new javax.swing.JToolBar.Separator();
        findButton = new javax.swing.JButton();
        findNextButton = new javax.swing.JButton();
        replaceButton = new javax.swing.JButton();
        jSeparator16 = new javax.swing.JToolBar.Separator();
        wrapToggleButton = new javax.swing.JToggleButton();
        mainSplitPane = new javax.swing.JSplitPane();
        notesTabbedPane = new javax.swing.JTabbedPane();
        lentComponentPanel = new javax.swing.JPanel();
        leftPanelTabbedPane = new javax.swing.JTabbedPane();
        statusPanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        currentStatusLabel = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        charCountLabel = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        lineNumberLabel = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        insertLabel = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        editorTextPropertyLabel = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        capsLockLabel = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        numLockLabel = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        scrollLockLabel = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        newMenuItem = new javax.swing.JMenuItem();
        openMenuItem = new javax.swing.JMenuItem();
        openNlinesMenuItem = new javax.swing.JMenuItem();
        jSeparator11 = new javax.swing.JSeparator();
        closeMenuItem = new javax.swing.JMenuItem();
        closeAllMenuItem = new javax.swing.JMenuItem();
        jSeparator12 = new javax.swing.JSeparator();
        saveMenuItem = new javax.swing.JMenuItem();
        saveAsMenuItem = new javax.swing.JMenuItem();
        saveAllMenuItem = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JSeparator();
        pageSetupMenuItem = new javax.swing.JMenuItem();
        printPreviewMenuItem = new javax.swing.JMenuItem();
        printMenuItem = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JSeparator();
        reloadMenuItem = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JSeparator();
        exitMenuItem = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        undoMenuItem = new javax.swing.JMenuItem();
        redoMenuItem = new javax.swing.JMenuItem();
        jSeparator6 = new javax.swing.JSeparator();
        cutMenuItem = new javax.swing.JMenuItem();
        copyMenuItem = new javax.swing.JMenuItem();
        pasteMenuItem = new javax.swing.JMenuItem();
        deleteMenuItem = new javax.swing.JMenuItem();
        jSeparator7 = new javax.swing.JSeparator();
        findMenuItem = new javax.swing.JMenuItem();
        findNextMenuItem = new javax.swing.JMenuItem();
        replaceMenuItem = new javax.swing.JMenuItem();
        gotoMenuItem = new javax.swing.JMenuItem();
        jSeparator8 = new javax.swing.JSeparator();
        selectAllMenuItem = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        exportAsMenu = new javax.swing.JMenu();
        exphtmlMenuItem = new javax.swing.JMenuItem();
        exppdfMenuItem = new javax.swing.JMenuItem();
        exprtfMenuItem = new javax.swing.JMenuItem();
        expxmlMenuItem = new javax.swing.JMenuItem();
        converterMenu = new javax.swing.JMenu();
        xml2csvMenuItem = new javax.swing.JMenuItem();
        xml2excelMenuItem = new javax.swing.JMenuItem();
        jSeparator17 = new javax.swing.JSeparator();
        csv2xmlMenuItem = new javax.swing.JMenuItem();
        excel2xmlMenuItem = new javax.swing.JMenuItem();
        jSeparator18 = new javax.swing.JSeparator();
        excel2csvMenuItem = new javax.swing.JMenuItem();
        splitterMenuItem = new javax.swing.JMenuItem();
        jSeparator13 = new javax.swing.JSeparator();
        settingsMenuItem = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        wrapCheckBoxMenuItem = new javax.swing.JCheckBoxMenuItem();
        choseFontMenuItem = new javax.swing.JMenuItem();
        jMenu7 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenu8 = new javax.swing.JMenu();
        jRadioButtonMenuItem1 = new javax.swing.JRadioButtonMenuItem();
        jSeparator15 = new javax.swing.JSeparator();
        jRadioButtonMenuItem2 = new javax.swing.JRadioButtonMenuItem();
        jRadioButtonMenuItem3 = new javax.swing.JRadioButtonMenuItem();
        jRadioButtonMenuItem4 = new javax.swing.JRadioButtonMenuItem();
        jRadioButtonMenuItem5 = new javax.swing.JRadioButtonMenuItem();
        jRadioButtonMenuItem6 = new javax.swing.JRadioButtonMenuItem();
        jSeparator20 = new javax.swing.JSeparator();
        showToolbarCBMenuItem = new javax.swing.JCheckBoxMenuItem();
        showStatusBarCBMenuItem = new javax.swing.JCheckBoxMenuItem();
        showExplorerCheckBoxMenuItem = new javax.swing.JCheckBoxMenuItem();
        jSeparator21 = new javax.swing.JSeparator();
        showListExplorerCheckBoxMenuItem = new javax.swing.JCheckBoxMenuItem();
        jMenu5 = new javax.swing.JMenu();
        helpIndexMenuItem = new javax.swing.JMenuItem();
        aboutMenuItem = new javax.swing.JMenuItem();
        jSeparator19 = new javax.swing.JSeparator();
        updateMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                formKeyTyped(evt);
            }
        });

        mainPanel.setLayout(new java.awt.GridBagLayout());

        mainToolBar.setFloatable(false);
        mainToolBar.setRollover(true);

        newFileButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/new.gif"))); // NOI18N
        newFileButton.setToolTipText("New File");
        newFileButton.setFocusable(false);
        newFileButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        newFileButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        newFileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newFileButtonActionPerformed(evt);
            }
        });
        mainToolBar.add(newFileButton);

        openButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/open.gif"))); // NOI18N
        openButton.setToolTipText("Open File");
        openButton.setFocusable(false);
        openButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        openButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        openButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openButtonActionPerformed(evt);
            }
        });
        mainToolBar.add(openButton);
        mainToolBar.add(jSeparator4);

        saveButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/save.gif"))); // NOI18N
        saveButton.setToolTipText("Save");
        saveButton.setFocusable(false);
        saveButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        saveButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });
        mainToolBar.add(saveButton);

        saveAsButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/saveas_edit.gif"))); // NOI18N
        saveAsButton.setToolTipText("Save As...");
        saveAsButton.setFocusable(false);
        saveAsButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        saveAsButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        saveAsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveAsButtonActionPerformed(evt);
            }
        });
        mainToolBar.add(saveAsButton);

        saveAllButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/saveall.gif"))); // NOI18N
        saveAllButton.setToolTipText("Save All");
        saveAllButton.setFocusable(false);
        saveAllButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        saveAllButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        saveAllButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveAllButtonActionPerformed(evt);
            }
        });
        mainToolBar.add(saveAllButton);
        mainToolBar.add(jSeparator5);

        printButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/print.gif"))); // NOI18N
        printButton.setToolTipText("Print");
        printButton.setFocusable(false);
        printButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        printButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        printButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printButtonActionPerformed(evt);
            }
        });
        mainToolBar.add(printButton);
        mainToolBar.add(jSeparator14);

        undoButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/undo.gif"))); // NOI18N
        undoButton.setToolTipText("Undo");
        undoButton.setFocusable(false);
        undoButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        undoButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        undoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                undoButtonActionPerformed(evt);
            }
        });
        mainToolBar.add(undoButton);

        redoButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/redo.gif"))); // NOI18N
        redoButton.setToolTipText("Redo");
        redoButton.setFocusable(false);
        redoButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        redoButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        mainToolBar.add(redoButton);
        mainToolBar.add(jSeparator9);

        cutButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/cut.gif"))); // NOI18N
        cutButton.setToolTipText("Cut");
        cutButton.setFocusable(false);
        cutButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        cutButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        cutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cutButtonActionPerformed(evt);
            }
        });
        mainToolBar.add(cutButton);

        copyButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/copy.gif"))); // NOI18N
        copyButton.setToolTipText("Copy");
        copyButton.setFocusable(false);
        copyButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        copyButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        copyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                copyButtonActionPerformed(evt);
            }
        });
        mainToolBar.add(copyButton);

        pasteButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/paste.gif"))); // NOI18N
        pasteButton.setToolTipText("Paste");
        pasteButton.setFocusable(false);
        pasteButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        pasteButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        pasteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pasteButtonActionPerformed(evt);
            }
        });
        mainToolBar.add(pasteButton);
        mainToolBar.add(jSeparator10);

        findButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/find.gif"))); // NOI18N
        findButton.setToolTipText("Find");
        findButton.setFocusable(false);
        findButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        findButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        findButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                findButtonActionPerformed(evt);
            }
        });
        mainToolBar.add(findButton);

        findNextButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/findnext.gif"))); // NOI18N
        findNextButton.setToolTipText("Find Next");
        findNextButton.setActionCommand("jButton12");
        findNextButton.setFocusable(false);
        findNextButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        findNextButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        findNextButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                findNextButtonActionPerformed(evt);
            }
        });
        mainToolBar.add(findNextButton);

        replaceButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/replacenext.gif"))); // NOI18N
        replaceButton.setToolTipText("Find / Replace");
        replaceButton.setFocusable(false);
        replaceButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        replaceButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        replaceButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                replaceButtonActionPerformed(evt);
            }
        });
        mainToolBar.add(replaceButton);
        mainToolBar.add(jSeparator16);

        wrapToggleButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/button-word-wrap.gif"))); // NOI18N
        wrapToggleButton.setToolTipText("Word Wrap");
        wrapToggleButton.setFocusable(false);
        wrapToggleButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        wrapToggleButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        wrapToggleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wrapToggleButtonActionPerformed(evt);
            }
        });
        mainToolBar.add(wrapToggleButton);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        mainPanel.add(mainToolBar, gridBagConstraints);

        mainSplitPane.setContinuousLayout(true);
        mainSplitPane.setOneTouchExpandable(true);

        notesTabbedPane.setBackground(new java.awt.Color(204, 204, 204));
        notesTabbedPane.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        notesTabbedPane.setFocusTraversalPolicyProvider(true);
        notesTabbedPane.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                notesTabbedPaneStateChanged(evt);
            }
        });
        mainSplitPane.setRightComponent(notesTabbedPane);

        lentComponentPanel.setLayout(new java.awt.BorderLayout());

        leftPanelTabbedPane.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);
        lentComponentPanel.add(leftPanelTabbedPane, java.awt.BorderLayout.CENTER);

        mainSplitPane.setLeftComponent(lentComponentPanel);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        mainPanel.add(mainSplitPane, gridBagConstraints);

        statusPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 255)));
        statusPanel.setPreferredSize(new java.awt.Dimension(729, 25));
        statusPanel.setLayout(new java.awt.GridBagLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 255)));
        jPanel1.setMinimumSize(new java.awt.Dimension(55, 25));
        jPanel1.setPreferredSize(new java.awt.Dimension(102, 240));
        jPanel1.setLayout(new java.awt.BorderLayout());

        currentStatusLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jPanel1.add(currentStatusLabel, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        statusPanel.add(jPanel1, gridBagConstraints);

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 255)));
        jPanel2.setMinimumSize(new java.awt.Dimension(50, 25));
        jPanel2.setPreferredSize(new java.awt.Dimension(102, 240));
        jPanel2.setLayout(new java.awt.BorderLayout());

        charCountLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel2.add(charCountLabel, java.awt.BorderLayout.CENTER);

        statusPanel.add(jPanel2, new java.awt.GridBagConstraints());

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 255)));
        jPanel3.setMinimumSize(new java.awt.Dimension(50, 25));
        jPanel3.setPreferredSize(new java.awt.Dimension(102, 240));
        jPanel3.setLayout(new java.awt.BorderLayout());

        lineNumberLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel3.add(lineNumberLabel, java.awt.BorderLayout.CENTER);

        statusPanel.add(jPanel3, new java.awt.GridBagConstraints());

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 255)));
        jPanel4.setMinimumSize(new java.awt.Dimension(50, 25));
        jPanel4.setPreferredSize(new java.awt.Dimension(102, 240));
        jPanel4.setLayout(new java.awt.BorderLayout());

        insertLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        insertLabel.setText("INS");
        jPanel4.add(insertLabel, java.awt.BorderLayout.CENTER);

        statusPanel.add(jPanel4, new java.awt.GridBagConstraints());

        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 255)));
        jPanel5.setMinimumSize(new java.awt.Dimension(200, 25));
        jPanel5.setPreferredSize(new java.awt.Dimension(102, 240));
        jPanel5.setLayout(new java.awt.BorderLayout());

        editorTextPropertyLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel5.add(editorTextPropertyLabel, java.awt.BorderLayout.CENTER);

        statusPanel.add(jPanel5, new java.awt.GridBagConstraints());

        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 255)));
        jPanel6.setMinimumSize(new java.awt.Dimension(50, 25));
        jPanel6.setPreferredSize(new java.awt.Dimension(102, 240));
        jPanel6.setLayout(new java.awt.BorderLayout());

        capsLockLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel6.add(capsLockLabel, java.awt.BorderLayout.CENTER);

        statusPanel.add(jPanel6, new java.awt.GridBagConstraints());

        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 255)));
        jPanel7.setMinimumSize(new java.awt.Dimension(50, 25));
        jPanel7.setPreferredSize(new java.awt.Dimension(102, 240));
        jPanel7.setLayout(new java.awt.BorderLayout());

        numLockLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel7.add(numLockLabel, java.awt.BorderLayout.CENTER);

        statusPanel.add(jPanel7, new java.awt.GridBagConstraints());

        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 255)));
        jPanel8.setMinimumSize(new java.awt.Dimension(50, 25));
        jPanel8.setPreferredSize(new java.awt.Dimension(102, 240));
        jPanel8.setLayout(new java.awt.BorderLayout());

        scrollLockLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel8.add(scrollLockLabel, java.awt.BorderLayout.CENTER);

        statusPanel.add(jPanel8, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        mainPanel.add(statusPanel, gridBagConstraints);

        jMenu1.setText("File");

        newMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        newMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/new.gif"))); // NOI18N
        newMenuItem.setText("New");
        newMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newMenuItemActionPerformed(evt);
            }
        });
        jMenu1.add(newMenuItem);

        openMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        openMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/open.gif"))); // NOI18N
        openMenuItem.setText("Open");
        openMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openMenuItemActionPerformed(evt);
            }
        });
        jMenu1.add(openMenuItem);

        openNlinesMenuItem.setText("Open N Lines");
        openNlinesMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openNlinesMenuItemActionPerformed(evt);
            }
        });
        jMenu1.add(openNlinesMenuItem);
        jMenu1.add(jSeparator11);

        closeMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.ALT_MASK));
        closeMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/close.gif"))); // NOI18N
        closeMenuItem.setText("Close");
        closeMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeMenuItemActionPerformed(evt);
            }
        });
        jMenu1.add(closeMenuItem);

        closeAllMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.SHIFT_MASK));
        closeAllMenuItem.setText("Close All");
        closeAllMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeAllMenuItemActionPerformed(evt);
            }
        });
        jMenu1.add(closeAllMenuItem);
        jMenu1.add(jSeparator12);

        saveMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        saveMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/save.gif"))); // NOI18N
        saveMenuItem.setText("Save");
        saveMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveMenuItemActionPerformed(evt);
            }
        });
        jMenu1.add(saveMenuItem);

        saveAsMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        saveAsMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/saveas_edit.gif"))); // NOI18N
        saveAsMenuItem.setText("Save As...");
        saveAsMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveAsMenuItemActionPerformed(evt);
            }
        });
        jMenu1.add(saveAsMenuItem);

        saveAllMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        saveAllMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/saveall.gif"))); // NOI18N
        saveAllMenuItem.setText("Save All");
        saveAllMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveAllMenuItemActionPerformed(evt);
            }
        });
        jMenu1.add(saveAllMenuItem);
        jMenu1.add(jSeparator1);

        pageSetupMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/pagesetup.png"))); // NOI18N
        pageSetupMenuItem.setText("Page Setup");
        jMenu1.add(pageSetupMenuItem);

        printPreviewMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/printpreview.gif"))); // NOI18N
        printPreviewMenuItem.setText("Print Preview");
        printPreviewMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printPreviewMenuItemActionPerformed(evt);
            }
        });
        jMenu1.add(printPreviewMenuItem);

        printMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        printMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/print.gif"))); // NOI18N
        printMenuItem.setText("Print");
        printMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printMenuItemActionPerformed(evt);
            }
        });
        jMenu1.add(printMenuItem);
        jMenu1.add(jSeparator2);

        reloadMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        reloadMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/refresh_nav.gif"))); // NOI18N
        reloadMenuItem.setText("Reload");
        reloadMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reloadMenuItemActionPerformed(evt);
            }
        });
        jMenu1.add(reloadMenuItem);
        jMenu1.add(jSeparator3);

        exitMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
        exitMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/exit.gif"))); // NOI18N
        exitMenuItem.setText("Exit");
        jMenu1.add(exitMenuItem);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");

        undoMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z, java.awt.event.InputEvent.CTRL_MASK));
        undoMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/undo.gif"))); // NOI18N
        undoMenuItem.setText("Undo");
        undoMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                undoMenuItemActionPerformed(evt);
            }
        });
        jMenu2.add(undoMenuItem);

        redoMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Y, java.awt.event.InputEvent.CTRL_MASK));
        redoMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/redo.gif"))); // NOI18N
        redoMenuItem.setText("Redo");
        jMenu2.add(redoMenuItem);
        jMenu2.add(jSeparator6);

        cutMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
        cutMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/cut.gif"))); // NOI18N
        cutMenuItem.setText("Cut");
        cutMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cutMenuItemActionPerformed(evt);
            }
        });
        jMenu2.add(cutMenuItem);

        copyMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        copyMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/copy.gif"))); // NOI18N
        copyMenuItem.setText("Copy");
        copyMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                copyMenuItemActionPerformed(evt);
            }
        });
        jMenu2.add(copyMenuItem);

        pasteMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.CTRL_MASK));
        pasteMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/paste.gif"))); // NOI18N
        pasteMenuItem.setText("Paste");
        pasteMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pasteMenuItemActionPerformed(evt);
            }
        });
        jMenu2.add(pasteMenuItem);

        deleteMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_DELETE, 0));
        deleteMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/delete.gif"))); // NOI18N
        deleteMenuItem.setText("Delete");
        jMenu2.add(deleteMenuItem);
        jMenu2.add(jSeparator7);

        findMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.CTRL_MASK));
        findMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/find.gif"))); // NOI18N
        findMenuItem.setText("Find...");
        findMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                findMenuItemActionPerformed(evt);
            }
        });
        jMenu2.add(findMenuItem);

        findNextMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F3, 0));
        findNextMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/findnext.gif"))); // NOI18N
        findNextMenuItem.setText("Find Next");
        findNextMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                findNextMenuItemActionPerformed(evt);
            }
        });
        jMenu2.add(findNextMenuItem);

        replaceMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.CTRL_MASK));
        replaceMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/replace.gif"))); // NOI18N
        replaceMenuItem.setText("Replace...");
        replaceMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                replaceMenuItemActionPerformed(evt);
            }
        });
        jMenu2.add(replaceMenuItem);

        gotoMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.CTRL_MASK));
        gotoMenuItem.setText("Go to...");
        jMenu2.add(gotoMenuItem);
        jMenu2.add(jSeparator8);

        selectAllMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        selectAllMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/selectall.gif"))); // NOI18N
        selectAllMenuItem.setText("Select All");
        selectAllMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectAllMenuItemActionPerformed(evt);
            }
        });
        jMenu2.add(selectAllMenuItem);

        jMenuBar1.add(jMenu2);

        jMenu6.setText("Tools");

        exportAsMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/export.gif"))); // NOI18N
        exportAsMenu.setText("Export As...");

        exphtmlMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/text-html.png"))); // NOI18N
        exphtmlMenuItem.setText("HTML");
        exphtmlMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exphtmlMenuItemActionPerformed(evt);
            }
        });
        exportAsMenu.add(exphtmlMenuItem);

        exppdfMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/x-pdf.png"))); // NOI18N
        exppdfMenuItem.setText("PDF");
        exppdfMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exppdfMenuItemActionPerformed(evt);
            }
        });
        exportAsMenu.add(exppdfMenuItem);

        exprtfMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/x-office-document.png"))); // NOI18N
        exprtfMenuItem.setText("RTF");
        exprtfMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exprtfMenuItemActionPerformed(evt);
            }
        });
        exportAsMenu.add(exprtfMenuItem);

        expxmlMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Filetype-XML-16x16.png"))); // NOI18N
        expxmlMenuItem.setText("XML");
        expxmlMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                expxmlMenuItemActionPerformed(evt);
            }
        });
        exportAsMenu.add(expxmlMenuItem);

        jMenu6.add(exportAsMenu);

        converterMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/ico_converter.png"))); // NOI18N
        converterMenu.setText("Convertre");

        xml2csvMenuItem.setText("XML to CSV");
        xml2csvMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xml2csvMenuItemActionPerformed(evt);
            }
        });
        converterMenu.add(xml2csvMenuItem);

        xml2excelMenuItem.setText("XML to Excel");
        converterMenu.add(xml2excelMenuItem);
        converterMenu.add(jSeparator17);

        csv2xmlMenuItem.setText("CSV to XML");
        converterMenu.add(csv2xmlMenuItem);

        excel2xmlMenuItem.setText("Excel to XML");
        converterMenu.add(excel2xmlMenuItem);
        converterMenu.add(jSeparator18);

        excel2csvMenuItem.setText("Excel to CSV");
        converterMenu.add(excel2csvMenuItem);

        jMenu6.add(converterMenu);

        splitterMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/file_splitter_16x16.gif"))); // NOI18N
        splitterMenuItem.setText("Splitter");
        splitterMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                splitterMenuItemActionPerformed(evt);
            }
        });
        jMenu6.add(splitterMenuItem);
        jMenu6.add(jSeparator13);

        settingsMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/wm_settings.gif"))); // NOI18N
        settingsMenuItem.setText("Settings");
        jMenu6.add(settingsMenuItem);

        jMenuBar1.add(jMenu6);

        jMenu3.setText("Format");
        jMenu3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu3ActionPerformed(evt);
            }
        });

        wrapCheckBoxMenuItem.setText("Word Wrap");
        wrapCheckBoxMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/button-word-wrap.gif"))); // NOI18N
        wrapCheckBoxMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wrapCheckBoxMenuItemActionPerformed(evt);
            }
        });
        jMenu3.add(wrapCheckBoxMenuItem);

        choseFontMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/fonts.gif"))); // NOI18N
        choseFontMenuItem.setText("Font");
        choseFontMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                choseFontMenuItemActionPerformed(evt);
            }
        });
        jMenu3.add(choseFontMenuItem);

        jMenuBar1.add(jMenu3);

        jMenu7.setText("Insert");
        jMenuBar1.add(jMenu7);

        jMenu4.setText("View");

        jMenu8.setText("Look N Feel");

        lnfButtonGroup.add(jRadioButtonMenuItem1);
        jRadioButtonMenuItem1.setSelected(true);
        jRadioButtonMenuItem1.setText("System");
        jRadioButtonMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonMenuItem1ActionPerformed(evt);
            }
        });
        jMenu8.add(jRadioButtonMenuItem1);
        jMenu8.add(jSeparator15);

        lnfButtonGroup.add(jRadioButtonMenuItem2);
        jRadioButtonMenuItem2.setText("Forest");
        jRadioButtonMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonMenuItem2ActionPerformed(evt);
            }
        });
        jMenu8.add(jRadioButtonMenuItem2);

        lnfButtonGroup.add(jRadioButtonMenuItem3);
        jRadioButtonMenuItem3.setText("Office 2003");
        jRadioButtonMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonMenuItem3ActionPerformed(evt);
            }
        });
        jMenu8.add(jRadioButtonMenuItem3);

        lnfButtonGroup.add(jRadioButtonMenuItem4);
        jRadioButtonMenuItem4.setText("Office 2007 Blue");
        jRadioButtonMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonMenuItem4ActionPerformed(evt);
            }
        });
        jMenu8.add(jRadioButtonMenuItem4);

        lnfButtonGroup.add(jRadioButtonMenuItem5);
        jRadioButtonMenuItem5.setText("Business");
        jRadioButtonMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonMenuItem5ActionPerformed(evt);
            }
        });
        jMenu8.add(jRadioButtonMenuItem5);

        lnfButtonGroup.add(jRadioButtonMenuItem6);
        jRadioButtonMenuItem6.setText("Mist Aqua");
        jRadioButtonMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonMenuItem6ActionPerformed(evt);
            }
        });
        jMenu8.add(jRadioButtonMenuItem6);

        jMenu4.add(jMenu8);
        jMenu4.add(jSeparator20);

        showToolbarCBMenuItem.setSelected(true);
        showToolbarCBMenuItem.setText("Toolbar");
        showToolbarCBMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showToolbarCBMenuItemActionPerformed(evt);
            }
        });
        jMenu4.add(showToolbarCBMenuItem);

        showStatusBarCBMenuItem.setSelected(true);
        showStatusBarCBMenuItem.setText("Status Bar");
        showStatusBarCBMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showStatusBarCBMenuItemActionPerformed(evt);
            }
        });
        jMenu4.add(showStatusBarCBMenuItem);

        showExplorerCheckBoxMenuItem.setText("Explorer");
        jMenu4.add(showExplorerCheckBoxMenuItem);
        jMenu4.add(jSeparator21);

        showListExplorerCheckBoxMenuItem.setText("ListExplorer");
        jMenu4.add(showListExplorerCheckBoxMenuItem);

        jMenuBar1.add(jMenu4);

        jMenu5.setText("Help");

        helpIndexMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        helpIndexMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/help.gif"))); // NOI18N
        helpIndexMenuItem.setText("Help Index");
        jMenu5.add(helpIndexMenuItem);

        aboutMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.CTRL_MASK));
        aboutMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/about.gif"))); // NOI18N
        aboutMenuItem.setText("About Notepad");
        jMenu5.add(aboutMenuItem);
        jMenu5.add(jSeparator19);

        updateMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/wn_updates48.gif"))); // NOI18N
        updateMenuItem.setText("Upate");
        updateMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateMenuItemActionPerformed(evt);
            }
        });
        jMenu5.add(updateMenuItem);

        jMenuBar1.add(jMenu5);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 757, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void newMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newMenuItemActionPerformed

        addNewFile();
}//GEN-LAST:event_newMenuItemActionPerformed

    private void newFileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newFileButtonActionPerformed
        addNewFile();
}//GEN-LAST:event_newFileButtonActionPerformed

    private void openMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openMenuItemActionPerformed
        openFile();
}//GEN-LAST:event_openMenuItemActionPerformed

    private void openButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openButtonActionPerformed
        openFile();
}//GEN-LAST:event_openButtonActionPerformed

    private void saveAsMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveAsMenuItemActionPerformed
        saveAs();
}//GEN-LAST:event_saveAsMenuItemActionPerformed

    private void saveMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveMenuItemActionPerformed
        saveFile(notesTabbedPane.getSelectedIndex());
}//GEN-LAST:event_saveMenuItemActionPerformed

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        saveFile(notesTabbedPane.getSelectedIndex());
}//GEN-LAST:event_saveButtonActionPerformed

    private void saveAsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveAsButtonActionPerformed
        saveAs();
}//GEN-LAST:event_saveAsButtonActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
    }//GEN-LAST:event_formWindowClosed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        checkAndSaveFiles();
        saveCurrentContext();
        System.exit(0);
    }//GEN-LAST:event_formWindowClosing

    private void printMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printMenuItemActionPerformed
        printText();
}//GEN-LAST:event_printMenuItemActionPerformed

    private void printButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printButtonActionPerformed
        printText();
}//GEN-LAST:event_printButtonActionPerformed

    private void cutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cutButtonActionPerformed
        int s = notesTabbedPane.getSelectedIndex();
        if (s != -1) {
            TextEditor ed = textEditorList.get(s);
            JTextArea ta = ed.getTextArea();
            String fileName = ed.getFileName();
            if (fileName == null || fileName.equals("")) {
                fileName = "New File.txt";
            }
            if (ta != null) {
                cut(ta);
            }
        }
}//GEN-LAST:event_cutButtonActionPerformed

    private void copyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_copyButtonActionPerformed
        int s = notesTabbedPane.getSelectedIndex();
        if (s != -1) {
            TextEditor ed = textEditorList.get(s);
            JTextArea ta = ed.getTextArea();
            String fileName = ed.getFileName();
            if (fileName == null || fileName.equals("")) {
                fileName = "New File.txt";
            }
            if (ta != null) {
                copy(ta);
            }
        }
}//GEN-LAST:event_copyButtonActionPerformed

    private void pasteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pasteButtonActionPerformed
        int s = notesTabbedPane.getSelectedIndex();
        if (s != -1) {
            TextEditor ed = textEditorList.get(s);
            JTextArea ta = ed.getTextArea();
            String fileName = ed.getFileName();
            if (fileName == null || fileName.equals("")) {
                fileName = "New File.txt";
            }
            if (ta != null) {
                paste(ta);
            }
        }
}//GEN-LAST:event_pasteButtonActionPerformed

    private void cutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cutMenuItemActionPerformed
        int s = notesTabbedPane.getSelectedIndex();
        if (s != -1) {
            TextEditor ed = textEditorList.get(s);
            JTextArea ta = ed.getTextArea();
            String fileName = ed.getFileName();
            if (fileName == null || fileName.equals("")) {
                fileName = "New File.txt";
            }
            if (ta != null) {
                cut(ta);
            }
        }
}//GEN-LAST:event_cutMenuItemActionPerformed

    private void copyMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_copyMenuItemActionPerformed
        int s = notesTabbedPane.getSelectedIndex();
        if (s != -1) {
            TextEditor ed = textEditorList.get(s);
            JTextArea ta = ed.getTextArea();
            String fileName = ed.getFileName();
            if (fileName == null || fileName.equals("")) {
                fileName = "New File.txt";
            }
            if (ta != null) {
                copy(ta);
            }
        }
}//GEN-LAST:event_copyMenuItemActionPerformed

    private void pasteMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pasteMenuItemActionPerformed
        int s = notesTabbedPane.getSelectedIndex();
        if (s != -1) {
            TextEditor ed = textEditorList.get(s);
            JTextArea ta = ed.getTextArea();
            String fileName = ed.getFileName();
            if (fileName == null || fileName.equals("")) {
                fileName = "New File.txt";
            }
            if (ta != null) {
                paste(ta);
            }
        }
}//GEN-LAST:event_pasteMenuItemActionPerformed

    private void exppdfMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exppdfMenuItemActionPerformed
        int s = notesTabbedPane.getSelectedIndex();
        if (s != -1) {
            TextEditor ed = textEditorList.get(s);
            JTextArea ta = ed.getTextArea();
            String fileName = ed.getFileName();
            if (fileName == null || fileName.equals("")) {
                fileName = "New File.txt";
            }
            if (ta != null) {
                convertToPDF(ta);
            }
        }
}//GEN-LAST:event_exppdfMenuItemActionPerformed

    private void exphtmlMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exphtmlMenuItemActionPerformed
        int s = notesTabbedPane.getSelectedIndex();
        if (s != -1) {
            TextEditor ed = textEditorList.get(s);
            JTextArea ta = ed.getTextArea();
            String fileName = ed.getFileName();
            if (fileName == null || fileName.equals("")) {
                fileName = "New File.txt";
            }
            if (ta != null) {
                convertToHTML(ta);
            }
        }
}//GEN-LAST:event_exphtmlMenuItemActionPerformed

    private void exprtfMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exprtfMenuItemActionPerformed
        int s = notesTabbedPane.getSelectedIndex();
        if (s != -1) {
            TextEditor ed = textEditorList.get(s);
            JTextArea ta = ed.getTextArea();
            String fileName = ed.getFileName();
            if (fileName == null || fileName.equals("")) {
                fileName = "New File.txt";
            }
            if (ta != null) {
                convertToRTF(ta);
            }
        }
}//GEN-LAST:event_exprtfMenuItemActionPerformed

    private void expxmlMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_expxmlMenuItemActionPerformed
        int s = notesTabbedPane.getSelectedIndex();
        if (s != -1) {
            TextEditor ed = textEditorList.get(s);
            JTextArea ta = ed.getTextArea();
            String fileName = ed.getFileName();
            if (fileName == null || fileName.equals("")) {
                fileName = "New File.txt";
            }
            if (ta != null) {
                convertToXML(ta);
            }
        }
}//GEN-LAST:event_expxmlMenuItemActionPerformed

    private void undoMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_undoMenuItemActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_undoMenuItemActionPerformed

    private void undoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_undoButtonActionPerformed
        int s = notesTabbedPane.getSelectedIndex();
        if (s != -1) {
            TextEditor ed = textEditorList.get(s);
            JTextArea ta = ed.getTextArea();
            String fileName = ed.getFileName();
            if (fileName == null || fileName.equals("")) {
                fileName = "New File.txt";
            }
            if (ta != null) {
            }
        }
}//GEN-LAST:event_undoButtonActionPerformed

    private void findMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_findMenuItemActionPerformed

        JTextArea ta = getSelectedTextArea();
        if (ta != null) {
            FindDialog fd = new FindDialog(this, false, ta);
            fd.setVisible(true);
        }
}//GEN-LAST:event_findMenuItemActionPerformed

    private void replaceMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_replaceMenuItemActionPerformed
        JTextArea ta = getSelectedTextArea();
        if (ta != null) {
            FindReplaceDialog fd = new FindReplaceDialog(this, false, ta);
            fd.setVisible(true);
        }
}//GEN-LAST:event_replaceMenuItemActionPerformed

    private void findButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_findButtonActionPerformed
        findMenuItemActionPerformed(evt);
}//GEN-LAST:event_findButtonActionPerformed

    private void jRadioButtonMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonMenuItem1ActionPerformed
        if (jRadioButtonMenuItem1.isSelected()) {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                SwingUtilities.updateComponentTreeUI(this);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(JNPMainFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                Logger.getLogger(JNPMainFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(JNPMainFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedLookAndFeelException ex) {
                Logger.getLogger(JNPMainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jRadioButtonMenuItem1ActionPerformed

    private void jRadioButtonMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonMenuItem2ActionPerformed
        if (jRadioButtonMenuItem2.isSelected()) {
            try {
                UIManager.setLookAndFeel(TinyLookAndFeel.class.getCanonicalName());
                SwingUtilities.updateComponentTreeUI(this);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(JNPMainFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                Logger.getLogger(JNPMainFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(JNPMainFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedLookAndFeelException ex) {
                Logger.getLogger(JNPMainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jRadioButtonMenuItem2ActionPerformed

    private void jRadioButtonMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonMenuItem3ActionPerformed
        if (jRadioButtonMenuItem3.isSelected()) {
            try {
                UIManager.setLookAndFeel(Office2003LookAndFeel.class.getCanonicalName());
                SwingUtilities.updateComponentTreeUI(this);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(JNPMainFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                Logger.getLogger(JNPMainFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(JNPMainFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedLookAndFeelException ex) {
                Logger.getLogger(JNPMainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jRadioButtonMenuItem3ActionPerformed

    private void jRadioButtonMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonMenuItem4ActionPerformed
        if (jRadioButtonMenuItem4.isSelected()) {
            try {
                UIManager.setLookAndFeel(SubstanceOfficeBlue2007LookAndFeel.class.getCanonicalName());
                SwingUtilities.updateComponentTreeUI(this);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(JNPMainFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                Logger.getLogger(JNPMainFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(JNPMainFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedLookAndFeelException ex) {
                Logger.getLogger(JNPMainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jRadioButtonMenuItem4ActionPerformed

    private void jRadioButtonMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonMenuItem5ActionPerformed
        if (jRadioButtonMenuItem5.isSelected()) {
            try {
                UIManager.setLookAndFeel(SubstanceBusinessBlueSteelLookAndFeel.class.getCanonicalName());
                SwingUtilities.updateComponentTreeUI(this);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(JNPMainFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                Logger.getLogger(JNPMainFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(JNPMainFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedLookAndFeelException ex) {
                Logger.getLogger(JNPMainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jRadioButtonMenuItem5ActionPerformed

    private void jRadioButtonMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonMenuItem6ActionPerformed
        if (jRadioButtonMenuItem6.isSelected()) {
            try {
                UIManager.setLookAndFeel(SubstanceMistAquaLookAndFeel.class.getCanonicalName());
                SwingUtilities.updateComponentTreeUI(this);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(JNPMainFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                Logger.getLogger(JNPMainFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(JNPMainFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedLookAndFeelException ex) {
                Logger.getLogger(JNPMainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jRadioButtonMenuItem6ActionPerformed

    private void findNextMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_findNextMenuItemActionPerformed
        selectedTextArea = getSelectedTextArea();
        if (selectedTextArea != null) {
            String key = selectedTextArea.getSelectedText();
            if (key != null && key.length() > 0) {
                find(
                        key,
                        true,
                        true);
            }
        }
}//GEN-LAST:event_findNextMenuItemActionPerformed

    private void findNextButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_findNextButtonActionPerformed
        selectedTextArea = getSelectedTextArea();
        if (selectedTextArea != null) {
            String key = selectedTextArea.getSelectedText();
            if (key != null && key.length() > 0) {
                find(
                        key,
                        true,
                        true);
            }
        }
}//GEN-LAST:event_findNextButtonActionPerformed

    private void replaceButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_replaceButtonActionPerformed
        JTextArea ta = getSelectedTextArea();
        if (ta != null) {
            FindReplaceDialog fd = new FindReplaceDialog(this, false, ta);
            fd.setVisible(true);
        }
}//GEN-LAST:event_replaceButtonActionPerformed

    private void selectAllMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectAllMenuItemActionPerformed
        selectedTextArea = getSelectedTextArea();
        if (selectedTextArea != null) {
            selectedTextArea.setSelectionStart(0);
            selectedTextArea.setSelectionEnd(selectedTextArea.getText().length());
        }
}//GEN-LAST:event_selectAllMenuItemActionPerformed

    private void wrapToggleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wrapToggleButtonActionPerformed
        if (wrapToggleButton.isSelected()) {
            getSelectedTextArea().setLineWrap(true);
            wrapCheckBoxMenuItem.setSelected(true);
            return;
        }
        if (!wrapToggleButton.isSelected()) {
            getSelectedTextArea().setLineWrap(false);
            wrapCheckBoxMenuItem.setSelected(false);
        }
}//GEN-LAST:event_wrapToggleButtonActionPerformed

    private void jMenu3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu3ActionPerformed
    }//GEN-LAST:event_jMenu3ActionPerformed

    private void wrapCheckBoxMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wrapCheckBoxMenuItemActionPerformed
        if (wrapCheckBoxMenuItem.isSelected()) {
            getSelectedTextArea().setLineWrap(true);
            wrapToggleButton.setSelected(true);
            return;
        }
        if (!wrapCheckBoxMenuItem.isSelected()) {
            getSelectedTextArea().setLineWrap(false);
            wrapToggleButton.setSelected(false);
        }
}//GEN-LAST:event_wrapCheckBoxMenuItemActionPerformed
    public static String[] fontNames;
    public static String[] fontSizes;
    private void choseFontMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_choseFontMenuItemActionPerformed
        selectedTextArea = getSelectedTextArea();
        if (selectedTextArea != null) {
            FontChooserDialog dlg = new FontChooserDialog(this, true,
                    selectedTextArea.getFont());
            int opt = dlg.showFontChooserDialog();
            if(opt == FontChooserDialog.OK_OPTION){
                java.awt.Font f = dlg.getSelectedFont();
                selectedTextArea.setFont(f);
                selectedTextArea.updateUI();
            }
        }
}//GEN-LAST:event_choseFontMenuItemActionPerformed

    private void printPreviewMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printPreviewMenuItemActionPerformed
        Printable p = getSelectedTextArea().getPrintable(createFormat(""),
                createFormat(""));
        
}//GEN-LAST:event_printPreviewMenuItemActionPerformed

    private void openNlinesMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openNlinesMenuItemActionPerformed
        NumberOfLineDialog lineDialog = new NumberOfLineDialog(this, true);
        int opt = lineDialog.showOpenDialog();
        if(NumberOfLineDialog.OK_OPTION == opt){
            JTextArea ta = lineDialog.getTextArea();
            File file = lineDialog.getSelectedFile();
            if(ta != null){
                JScrollPane sp = new JScrollPane(ta);
                sp.setViewportView(ta);
                notesTabbedPane.addTab(file.getName(), sp);
                int n = notesTabbedPane.getTabCount();
                notesTabbedPane.setTabComponentAt(n - 1,
                        new ButtonTabComponent(notesTabbedPane, textEditorList));
                notesTabbedPane.setSelectedIndex(n - 1);
                TextEditor ed = new TextEditor();
                ed.setTextArea(ta);
                ed.setIsNewFile(true);
                ed.setFileName(file.getAbsolutePath());
                textEditorList.add(ed);
            }
        }
}//GEN-LAST:event_openNlinesMenuItemActionPerformed

    private void notesTabbedPaneStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_notesTabbedPaneStateChanged
        int index = notesTabbedPane.getSelectedIndex();
        if(index > -1){
            if(evt.getSource().equals(notesTabbedPane))
                updateOthrComponentsByTabChange(index);
        }
    }//GEN-LAST:event_notesTabbedPaneStateChanged

    public void updateOthrComponentsByTabChange(int index){
        if(textEditorList.size() < index + 1){
            return;
        }
        TextEditor editor = textEditorList.get(index);
        if(editor != null){
            setTitle("NotePad - [" + editor.getFileTitle() + " * ]");
            if(editor.isInsertEnabled()){
                insertLabel.setText("INS");
            }else{
                insertLabel.setText("OVR");
            }
        }
    }

    private void closeMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeMenuItemActionPerformed
        int index = notesTabbedPane.getSelectedIndex();
        closeFile(index);
}//GEN-LAST:event_closeMenuItemActionPerformed

    private void closeAllMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeAllMenuItemActionPerformed
        closeAllFiles();
}//GEN-LAST:event_closeAllMenuItemActionPerformed

    private void reloadMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reloadMenuItemActionPerformed
        int index = notesTabbedPane.getSelectedIndex();
        reload(index);
}//GEN-LAST:event_reloadMenuItemActionPerformed

    private void saveAllButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveAllButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_saveAllButtonActionPerformed

    private void updateMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateMenuItemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_updateMenuItemActionPerformed

    private void showToolbarCBMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showToolbarCBMenuItemActionPerformed
        if(showToolbarCBMenuItem.isSelected()){
            mainToolBar.setVisible(true);
        }
        if(!showToolbarCBMenuItem.isSelected()){
            mainToolBar.setVisible(false);
        }
        mainPanel.updateUI();
    }//GEN-LAST:event_showToolbarCBMenuItemActionPerformed

    private void showStatusBarCBMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showStatusBarCBMenuItemActionPerformed
        if(showStatusBarCBMenuItem.isSelected()){
            statusPanel.setVisible(true);
        }
        if(!showStatusBarCBMenuItem.isSelected()){
            statusPanel.setVisible(false);
        }
        mainPanel.updateUI();
    }//GEN-LAST:event_showStatusBarCBMenuItemActionPerformed

    private void splitterMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_splitterMenuItemActionPerformed
        FileSplitterDialog dialog = new FileSplitterDialog(this, false);
        dialog.setVisible(true);
    }//GEN-LAST:event_splitterMenuItemActionPerformed

    private void xml2csvMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xml2csvMenuItemActionPerformed
        
    }//GEN-LAST:event_xml2csvMenuItemActionPerformed

    private void saveAllMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveAllMenuItemActionPerformed
        saveAll();
    }//GEN-LAST:event_saveAllMenuItemActionPerformed

    private void formKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyTyped
        currentStatusLabel.setText("C: "+evt.getKeyChar());
    }//GEN-LAST:event_formKeyTyped

    public void saveAll(){
        while(notesTabbedPane.getTabCount() > 0 ){
            boolean saved = saveFile(notesTabbedPane.getTabCount() - 1);
            if(saved){
                return;
            }
        }
    }

    public boolean reload(int index){
        boolean reloaded = false;

        if (index > -1) {
            TextEditor ed = textEditorList.get(index);
            if(ed != null){
                if(ed.isIsNewFile()){
                    return false;
                }
                if(ed.isIsEdited()){
                    int opt = JOptionPane.showConfirmDialog(this, "This file is modified.\nDo you want to reload ?",
                            "Reload ...", JOptionPane.OK_CANCEL_OPTION);
                    if(opt == JOptionPane.OK_OPTION){
                        ed.getTextArea().setText(readFileAsText(new File(ed.getFileName())));
                        reloaded = true;
                        ed.setIsEdited(false);
                        notesTabbedPane.setTitleAt(index, new File(ed.getFileName()).getName());
                    }
                    else if(opt == JOptionPane.CANCEL_OPTION){
                        reloaded = false;
                    }
                }
            }
        }

        return reloaded;
    }


    public boolean closeFile(int index){
        boolean isCanceled = false;
        if (index > -1) {
            TextEditor ed = textEditorList.get(index);
            
            if(ed != null){
                if(ed.isIsEdited()){
                    int opt = JOptionPane.showConfirmDialog(this, "This file is modified.\nDo you want to save the changes ?",
                            "Save ...", JOptionPane.YES_NO_CANCEL_OPTION);
                    if(opt == JOptionPane.YES_OPTION)
                        saveFile(index);
                    else if(opt == JOptionPane.CANCEL_OPTION){
                        isCanceled = true;
                        return isCanceled;
                    }
                }
            }
            if(!isCanceled){
                notesTabbedPane.removeTabAt(index);
                textEditorList.remove(index);
            }
            
        }
        return isCanceled;
    }

    public void closeAllFiles(){
        while(notesTabbedPane.getTabCount() > 0 ){
            boolean canceled = closeFile(notesTabbedPane.getTabCount() - 1);
            if(canceled){
                return;
            }
        }
    }

    int pos;
    /**      */
    int count;
    /**      *
     */
    int a;
    /**      */
    int newcounter;

    public void find(String key, boolean up, boolean match) {
        int position = 0;
        int count = 0, a = 0;
        String text = selectedTextArea.getText();
        if (!match) {
            text = text.toLowerCase();
            key = key.toLowerCase();
        }
        position = text.indexOf(key, selectedTextArea.getSelectionEnd() + newcounter);
        if (position >= 0) {
            selectedTextArea.select(position, position + key.length());
        }
    }

    public JTextArea getSelectedTextArea() {
        JTextArea ta = null;
        int s = notesTabbedPane.getSelectedIndex();
        if (s != -1) {
            TextEditor ed = textEditorList.get(s);
            ta = ed.getTextArea();
        }
        return ta;
    }

    public TextEditor getSelectedTE(){
        int s = notesTabbedPane.getSelectedIndex();
        if (s != -1) {
            return textEditorList.get(s);
        }
        return null;
    }

    private void convertToXML(JTextArea textArea) {
        String fn = browseForPDF();
        if (fn.equals("")) {
            return;
        }
        Document document = new Document();
        try {
            RtfWriter2.getInstance(document, new FileOutputStream(fn));
            document.open();

            java.awt.Font jFont = textArea.getFont();
            DefaultFontMapper fontMapper = new DefaultFontMapper();

            BaseFont bf = fontMapper.awtToPdf(jFont);

            Font font = new Font(bf);
//            font.setFamily(jFont.getFamily());
//            font.setColor(textArea.getForeground());
//            font.setSize(jFont.getSize());
//            font.setStyle(jFont.getStyle());
            Paragraph paragraph = new Paragraph(textArea.getText());
            paragraph.setFont(font);
            Chapter chapter = new Chapter(paragraph, 1);
            document.add(chapter);

        } catch (DocumentException de) {
            System.err.println(de.getMessage());
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        } finally {
            document.close();
        }
    }

    private void convertToRTF(JTextArea textArea) {
        String fn = browseForPDF();
        if (fn.equals("")) {
            return;
        }
        Document document = new Document();
        try {
            RtfWriter2.getInstance(document, new FileOutputStream(fn));
            document.open();

            java.awt.Font jFont = textArea.getFont();
            DefaultFontMapper fontMapper = new DefaultFontMapper();

            BaseFont bf = fontMapper.awtToPdf(jFont);

            Font font = new Font(bf);
//            font.setFamily(jFont.getFamily());
//            font.setColor(textArea.getForeground());
//            font.setSize(jFont.getSize());
//            font.setStyle(jFont.getStyle());
            Paragraph paragraph = new Paragraph(textArea.getText());
            paragraph.setFont(font);
            Chapter chapter = new Chapter(paragraph, 1);
            document.add(chapter);

        } catch (DocumentException de) {
            System.err.println(de.getMessage());
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        } finally {
            document.close();
        }
    }

    private String browseForPDF() {
        JFileChooser chooser = new JFileChooser(".");
        chooser.setMultiSelectionEnabled(false);
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int opt = chooser.showSaveDialog(this);
        String fileName = "";
        if (opt == JFileChooser.APPROVE_OPTION) {
            fileName = chooser.getSelectedFile().getAbsolutePath();

        }
        return fileName;
    }

    private void convertToPDF(JTextArea textArea) {
        String fn = browseForPDF();
        if (fn.equals("")) {
            return;
        }
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(fn));
            document.open();

            java.awt.Font jFont = textArea.getFont();
            Font font = new Font();
            font.setFamily(jFont.getFamily());
            font.setColor(textArea.getForeground());
            font.setSize(jFont.getSize());
            font.setStyle(jFont.getStyle());
            Paragraph paragraph = new Paragraph(textArea.getText());
            paragraph.setFont(font);
            Chapter chapter = new Chapter(paragraph, 1);
            document.add(chapter);

        } catch (DocumentException de) {
            System.err.println(de.getMessage());
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        } finally {
            document.close();
        }
    }

    private void convertToHTML(JTextArea textArea) {
        String fn = browseForPDF();
        if (fn.equals("")) {
            return;
        }
        Document document = new Document();
        try {
            HtmlWriter.getInstance(document, new FileOutputStream(fn));
            document.open();

            java.awt.Font jFont = textArea.getFont();
            DefaultFontMapper fontMapper = new DefaultFontMapper();

            BaseFont bf = fontMapper.awtToPdf(jFont);

            Font font = new Font(bf);
//            font.setFamily(jFont.getFamily());
//            font.setColor(textArea.getForeground());
//            font.setSize(jFont.getSize());
//            font.setStyle(jFont.getStyle());
            Paragraph paragraph = new Paragraph(textArea.getText());
            paragraph.setFont(font);
            Chapter chapter = new Chapter(paragraph, 1);
            document.add(chapter);

        } catch (DocumentException de) {
            System.err.println(de.getMessage());
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        } finally {
            document.close();
        }
    }

    private void printText() {
        int s = notesTabbedPane.getSelectedIndex();
        if (s != -1) {
            TextEditor ed = textEditorList.get(s);
            JTextArea ta = ed.getTextArea();
            String fileName = ed.getFileName();
            if (fileName == null || fileName.equals("")) {
                fileName = "New File.txt";
            }
            if (ta != null) {
                MessageFormat header = createFormat("FILE: " + fileName);
                MessageFormat footer = createFormat("Green Moon --- Page  {0}");
                boolean interactive = true;
                boolean background = false;

                PrintingTask task = new PrintingTask(header, footer, interactive, ta);
                if (background) {
                    task.execute();
                } else {
                    task.run();
                }
            }
        }

    }

    private void checkAndSaveFiles() {
        while(notesTabbedPane.getTabCount() > 0 ){
            TextEditor ed = textEditorList.get(notesTabbedPane.getTabCount()-1);
            JTextArea ta = ed.getTextArea();
            if (ed.isIsNewFile() || ed.isIsEdited()) {
                int opt = JOptionPane.showConfirmDialog(this,
                        "Do you want to save the file?",
                        "Save",
                        JOptionPane.YES_NO_OPTION);
                if (opt == JOptionPane.YES_OPTION) {
                    notesTabbedPane.setSelectedIndex(notesTabbedPane.getTabCount()-1);
                    saveFile(notesTabbedPane.getSelectedIndex());
                }
                textEditorList.remove(notesTabbedPane.getTabCount()-1);
                notesTabbedPane.remove(notesTabbedPane.getTabCount()-1);
            }
        }
    }

    private boolean saveFile(int index) {
        boolean saved = false;
        if (index != -1) {
            TextEditor ed = textEditorList.get(index);
            JTextArea ta = ed.getTextArea();
            String fileName = ed.getFileName();
            if (fileName != null && !fileName.equals("")) {
                if (ta != null) {
                    writeFile(fileName, ta.getText());
                    ed.setIsEdited(false);
                    ed.setIsNewFile(false);
                    saved = true;
                }
            } else {
                saved = saveAs();
            }

        }
        return saved;
    }

    private boolean saveAs() {
        boolean saved = false;
        JFileChooser chooser = new JFileChooser(".");
        chooser.setMultiSelectionEnabled(false);
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int opt = chooser.showSaveDialog(this);
        if (opt == JFileChooser.APPROVE_OPTION) {
            String fileName = chooser.getSelectedFile().getAbsolutePath();
            int s = notesTabbedPane.getSelectedIndex();
            if (s != -1) {
                TextEditor ed = textEditorList.get(s);
                JTextArea ta = ed.getTextArea();
                ed.setFileName(fileName);
                if (ta != null) {
                    writeFile(fileName, ta.getText());
                    ed.setIsEdited(false);
                    ed.setIsNewFile(false);
                    File f = new File(fileName);
                    notesTabbedPane.setTitleAt(s, f.getName());
                    notesTabbedPane.updateUI();
                    saved = true;
                }
            }

        }else{
            saved = false;
        }
        return saved;
    }

    public void openFile(String fileName) {
        File file = new File(fileName);
        OPEN_FILE_NAMES.add(file.getAbsolutePath());
        JTextArea ta = readFile(file);
        JScrollPane sp = new JScrollPane(ta);
        sp.setViewportView(ta);
        notesTabbedPane.addTab(file.getName(), sp);
        int n = notesTabbedPane.getTabCount();
        notesTabbedPane.setTabComponentAt(n - 1,
                new ButtonTabComponent(notesTabbedPane, textEditorList));
        notesTabbedPane.setSelectedIndex(n - 1);
        TextEditor ed = new TextEditor();
        ed.setTextArea(ta);
        ed.setIsNewFile(false);
        ed.setFileName(file.getAbsolutePath());
        ed.setIsEdited(false);
        textEditorList.add(ed);
    }

    public void openFile() {
        JFileChooser chooser = new JFileChooser(".");
        chooser.setMultiSelectionEnabled(true);

        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int opt = chooser.showOpenDialog(this);
        if (opt == JFileChooser.APPROVE_OPTION) {
            File[] files = chooser.getSelectedFiles();
            for (File file : files) {
                OPEN_FILE_NAMES.add(file.getAbsolutePath());
                JTextArea ta = readFile(file);
                JScrollPane sp = new JScrollPane(ta);
                sp.setViewportView(ta);
                notesTabbedPane.addTab(file.getName(), sp);
                int n = notesTabbedPane.getTabCount();
                notesTabbedPane.setTabComponentAt(n - 1,
                        new ButtonTabComponent(notesTabbedPane, textEditorList));
                notesTabbedPane.setSelectedIndex(n - 1);
                TextEditor ed = new TextEditor();
                ed.setTextArea(ta);
                ed.setIsNewFile(false);
                ed.setFileName(file.getAbsolutePath());
                ed.setIsEdited(false);
                textEditorList.add(ed);
            }
        }

    }

    private void writeFile(String fileName, String date) {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(new File(fileName)));
            bw.write(date);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException ex) {
                    Logger.getLogger(JNPMainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    private JTextArea readFile(File f) {
        JTextArea ta = createNewTexArea();
        
        if (f.exists()) {
            BufferedReader br = null;
            try {
                StringBuffer line = new StringBuffer();
                int count = 0;
                char[] buffer = new char[1024];
                br = new BufferedReader(
                        new InputStreamReader(new FileInputStream(f)));
                while ((count = br.read(buffer, 0, buffer.length)) >= 0) {
                    line.append(buffer, 0, count);
                    ta.append(line.toString());
                    line = new StringBuffer();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e);
            } finally {
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException ex) {
                        Logger.getLogger(JNPMainFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        return ta;
    }

    private String readFileAsText(File f) {
        StringBuffer strBuffer = new StringBuffer();
        if (f.exists()) {
            BufferedReader br = null;
            try {
                StringBuffer line = new StringBuffer();
                int count = 0;
                char[] buffer = new char[1024];
                br = new BufferedReader(
                        new InputStreamReader(new FileInputStream(f)));
                while ((count = br.read(buffer, 0, buffer.length)) >= 0) {
                    line.append(buffer, 0, count);
                    strBuffer.append(line.toString());
                    line = new StringBuffer();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e);
            } finally {
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException ex) {
                        Logger.getLogger(JNPMainFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        return strBuffer.toString();
    }

    private void addNewFile() {
        JScrollPane sp = new JScrollPane();
        JTextArea textcomp = createNewTexArea();
        //sta.addPropertyChangeListener("TEXT", list);
        sp.add(textcomp);
        sp.setViewportView(textcomp);

        notesTabbedPane.addTab( JnpConstants.NEW_FILE_NAME + NEW_FILE_COUNT, sp);
        int n = notesTabbedPane.getTabCount();
        notesTabbedPane.setTabComponentAt(n - 1,
                new ButtonTabComponent(notesTabbedPane, textEditorList));
        notesTabbedPane.setSelectedIndex(n - 1);
        TextEditor ed = new TextEditor();
        ed.setTextArea(textcomp);
        ed.setIsNewFile(true);
        ed.setNewFileCount(NEW_FILE_COUNT);
        ed.setFileName(null);
        ed.setInsertEnabled(true);
        textEditorList.add(n - 1, ed);
        ed.setIsEdited(false);
        NEW_FILE_COUNT ++;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                try {

                    String osName = System.getProperty("os.name");
                    String lnfClass = TinyLookAndFeel.class.getCanonicalName();

                    if (osName.toLowerCase().contains("win")) {
                        lnfClass = UIManager.getSystemLookAndFeelClassName();

                    }
                    UIManager.setLookAndFeel(lnfClass);

                    JNPMainFrame frame = new JNPMainFrame();
                    if (TinyLookAndFeel.class.getCanonicalName().equals(lnfClass)) {
                        frame.jRadioButtonMenuItem2.setSelected(true);
                    }
                    if (UIManager.getSystemLookAndFeelClassName().equals(lnfClass)) {
                        frame.jRadioButtonMenuItem1.setSelected(true);
                    }
                    frame.setVisible(true);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(JNPMainFrame.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InstantiationException ex) {
                    Logger.getLogger(JNPMainFrame.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(JNPMainFrame.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnsupportedLookAndFeelException ex) {
                    Logger.getLogger(JNPMainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
    }
    private List<TextEditor> textEditorList = new ArrayList<TextEditor>();
    protected EditUndoAction undoAction = new EditUndoAction(); // an Action for undo
    protected EditRedoAction redoAction = new EditRedoAction(); // an Action for redo
    private JTextArea selectedTextArea;

    public void setSelectedTextArea(JTextArea selectedTextArea) {
        this.selectedTextArea = selectedTextArea;
    }

    private JTextArea createNewTexArea(){
        JTextArea textArea = new JTextArea(5, 8);
        LineNumberedBorder lineNumberedBorder = new LineNumberedBorder(LineNumberedBorder.LEFT_SIDE,
					LineNumberedBorder.RIGHT_JUSTIFY);
        textArea.setFont(DEFAULT_TEXT_FONT);
        textArea.setBorder(
                BorderFactory.createCompoundBorder(
                  BorderFactory.createEmptyBorder(1, 1, 1, 1),
                  BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(Color.GRAY, 1),
                    BorderFactory.createCompoundBorder(
                        lineNumberedBorder, BorderFactory.createCompoundBorder(
                            BorderFactory.createLineBorder(Color.GRAY, 1),
                            BorderFactory.createEmptyBorder(0, 8, 0, 2)
                  ))
                  )
                  )
                  );


        final UndoManager undo = new UndoManager();
        javax.swing.text.Document doc = textArea.getDocument();
        textArea.addKeyListener(this);
        // Listen for undo and redo events
        doc.addUndoableEditListener(new UndoableEditListener() {

            public void undoableEditHappened(UndoableEditEvent evt) {
                undo.addEdit(evt.getEdit());
            }
        });

        // Create an undo action and add it to the text component
        textArea.getActionMap().put("Undo",
                new AbstractAction("Undo") {

                    public void actionPerformed(ActionEvent evt) {
                        try {
                            if (undo.canUndo()) {
                                undo.undo();
                            }
                        } catch (CannotUndoException e) {
                        }
                    }
                });

        // Bind the undo action to ctl-Z
        textArea.getInputMap().put(KeyStroke.getKeyStroke("control Z"), "Undo");

        // Create a redo action and add it to the text component
        textArea.getActionMap().put("Redo",
                new AbstractAction("Redo") {

                    public void actionPerformed(ActionEvent evt) {
                        try {
                            if (undo.canRedo()) {
                                undo.redo();
                            }
                        } catch (CannotRedoException e) {
                        }
                    }
                });

        // Bind the redo action to ctl-Y
        textArea.getInputMap().put(KeyStroke.getKeyStroke("control Y"), "Redo");
        return textArea;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JLabel capsLockLabel;
    private javax.swing.JLabel charCountLabel;
    private javax.swing.JMenuItem choseFontMenuItem;
    private javax.swing.JMenuItem closeAllMenuItem;
    private javax.swing.JMenuItem closeMenuItem;
    private javax.swing.JMenu converterMenu;
    private javax.swing.JButton copyButton;
    private javax.swing.JMenuItem copyMenuItem;
    private javax.swing.JMenuItem csv2xmlMenuItem;
    private javax.swing.JLabel currentStatusLabel;
    private javax.swing.JButton cutButton;
    private javax.swing.JMenuItem cutMenuItem;
    private javax.swing.JMenuItem deleteMenuItem;
    private javax.swing.JLabel editorTextPropertyLabel;
    private javax.swing.JMenuItem excel2csvMenuItem;
    private javax.swing.JMenuItem excel2xmlMenuItem;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenuItem exphtmlMenuItem;
    private javax.swing.JMenu exportAsMenu;
    private javax.swing.JMenuItem exppdfMenuItem;
    private javax.swing.JMenuItem exprtfMenuItem;
    private javax.swing.JMenuItem expxmlMenuItem;
    private javax.swing.JButton findButton;
    private javax.swing.JMenuItem findMenuItem;
    private javax.swing.JButton findNextButton;
    private javax.swing.JMenuItem findNextMenuItem;
    private javax.swing.JMenuItem gotoMenuItem;
    private javax.swing.JMenuItem helpIndexMenuItem;
    private javax.swing.JLabel insertLabel;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem1;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem2;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem3;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem4;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem5;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JToolBar.Separator jSeparator14;
    private javax.swing.JSeparator jSeparator15;
    private javax.swing.JToolBar.Separator jSeparator16;
    private javax.swing.JSeparator jSeparator17;
    private javax.swing.JSeparator jSeparator18;
    private javax.swing.JSeparator jSeparator19;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator20;
    private javax.swing.JSeparator jSeparator21;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JToolBar.Separator jSeparator4;
    private javax.swing.JToolBar.Separator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JToolBar.Separator jSeparator9;
    private javax.swing.JTabbedPane leftPanelTabbedPane;
    private javax.swing.JPanel lentComponentPanel;
    private javax.swing.JLabel lineNumberLabel;
    private javax.swing.ButtonGroup lnfButtonGroup;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JSplitPane mainSplitPane;
    private javax.swing.JToolBar mainToolBar;
    private javax.swing.JButton newFileButton;
    private javax.swing.JMenuItem newMenuItem;
    private javax.swing.JTabbedPane notesTabbedPane;
    private javax.swing.JLabel numLockLabel;
    private javax.swing.JButton openButton;
    private javax.swing.JMenuItem openMenuItem;
    private javax.swing.JMenuItem openNlinesMenuItem;
    private javax.swing.JMenuItem pageSetupMenuItem;
    private javax.swing.JButton pasteButton;
    private javax.swing.JMenuItem pasteMenuItem;
    private javax.swing.JButton printButton;
    private javax.swing.JMenuItem printMenuItem;
    private javax.swing.JMenuItem printPreviewMenuItem;
    private javax.swing.JButton redoButton;
    private javax.swing.JMenuItem redoMenuItem;
    private javax.swing.JMenuItem reloadMenuItem;
    private javax.swing.JButton replaceButton;
    private javax.swing.JMenuItem replaceMenuItem;
    private javax.swing.JButton saveAllButton;
    private javax.swing.JMenuItem saveAllMenuItem;
    private javax.swing.JButton saveAsButton;
    private javax.swing.JMenuItem saveAsMenuItem;
    private javax.swing.JButton saveButton;
    private javax.swing.JMenuItem saveMenuItem;
    private javax.swing.JLabel scrollLockLabel;
    private javax.swing.JMenuItem selectAllMenuItem;
    private javax.swing.JMenuItem settingsMenuItem;
    private javax.swing.JCheckBoxMenuItem showExplorerCheckBoxMenuItem;
    private javax.swing.JCheckBoxMenuItem showListExplorerCheckBoxMenuItem;
    private javax.swing.JCheckBoxMenuItem showStatusBarCBMenuItem;
    private javax.swing.JCheckBoxMenuItem showToolbarCBMenuItem;
    private javax.swing.JMenuItem splitterMenuItem;
    private javax.swing.JPanel statusPanel;
    private javax.swing.JButton undoButton;
    private javax.swing.JMenuItem undoMenuItem;
    private javax.swing.JMenuItem updateMenuItem;
    private javax.swing.JCheckBoxMenuItem wrapCheckBoxMenuItem;
    private javax.swing.JToggleButton wrapToggleButton;
    private javax.swing.JMenuItem xml2csvMenuItem;
    private javax.swing.JMenuItem xml2excelMenuItem;
    // End of variables declaration//GEN-END:variables

    public void stateChanged(ChangeEvent e) {
    }

    public void propertyChange(PropertyChangeEvent evt) {
    }

    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        JTextArea ta = null;
        if (e.getSource() instanceof JTextArea) {
            ta = (JTextArea) e.getSource();
        }
        if (cmd.equals("COPY")) {
            copy(ta);
        } else if (cmd.equals("Cut")) {
            cut(ta);
        } else if (cmd.equals("Paste")) {
            paste(ta);
        }
    }

    private void copy(JTextArea ta) {
        String s = ta.getSelectedText();
        int start = ta.getSelectionStart();
        int end = ta.getSelectionEnd();
        StringSelection ss = new StringSelection(s);
        // Set the content to the clipboard.
        this.getToolkit().getSystemClipboard().
                setContents(ss, ss);
    }

    private void cut(JTextArea field) {
        String s = field.getSelectedText();
        int start = field.getSelectionStart();
        int end = field.getSelectionEnd();
        field.replaceRange("", start, end);
        StringSelection ss = new StringSelection(s);
        // Set the content to the clipboard.
        this.getToolkit().getSystemClipboard().setContents(ss,
                ss);
    }

    private void paste(JTextArea field) {
        Clipboard cb = this.getToolkit().getSystemClipboard();
        Transferable tr = cb.getContents(this);
        try {
            // Get the content from the clipboard.
            String s = (String) tr.getTransferData(DataFlavor.stringFlavor);
            int start = field.getSelectionStart();
            int end = field.getSelectionEnd();
            field.replaceRange(s, start, end);
        } catch (Exception e) {
            return;
        }
    }

    public void keyTyped(KeyEvent e) {
        try{
            JTextArea ta = getSelectedTextArea();
            if(e.getSource().equals(ta)){
                int index = notesTabbedPane.getSelectedIndex();
                TextEditor te = textEditorList.get(index);
                if(!e.isActionKey()){
                    te.setIsEdited(true);
                    notesTabbedPane.setTitleAt(index,
                            te.getFileTitle() + " * ");

                    te.getTextArea().getCaretPosition();
                }
                lineNumberLabel.setText("L: "+te.getTextArea().getLineCount());
            }
        }catch(UnsupportedOperationException use){

        }
    }


    public void keyPressed(KeyEvent e) {
        try{
            setKeySetup();            
            JTextArea ta = getSelectedTextArea();
            if(e.getSource().equals(ta)){
                int index = notesTabbedPane.getSelectedIndex();
                TextEditor te = textEditorList.get(index);
                if(!e.isActionKey() && KeyEvent.VK_INSERT != e.getKeyCode()
                        && (KeyEvent.VK_CONTROL + KeyEvent.VK_N) != e.getKeyCode()){
                    te.setIsEdited(true);
                    notesTabbedPane.setTitleAt(index,
                            te.getFileTitle() + " * ");
                    setTitle("NotePad - [" + te.getFileTitle() + " * ]");
                }
                // Insert
                if(KeyEvent.VK_INSERT == e.getKeyCode()){
                    if(te.isInsertEnabled()){
                        insertLabel.setText("OVR");
                        te.setInsertEnabled(false);
                    }else{
                        insertLabel.setText("INS");
                        te.setInsertEnabled(true);
                    }
                }
                //lineNumberLabel.setText("L: "+te.getTextArea().getLineCount());
                //columnNumberLabel.setText("C: " + te.getTextArea().getDocument().getLength());
                //editorTextPropertyLabel.setText("C: " + te.getTextArea().getCaretPosition());
            }
        }catch(UnsupportedOperationException use){

        }
    }

    public void keyReleased(KeyEvent e) {
        
    }

    private class PrintingTask extends SwingWorker<Object, Object> {

        private final MessageFormat headerFormat;
        private final MessageFormat footerFormat;
        private final boolean interactive;
        private volatile boolean complete = false;
        private volatile String message;
        private final JTextArea textArea;

        public PrintingTask(MessageFormat header, MessageFormat footer,
                boolean interactive, JTextArea ta) {
            this.headerFormat = header;
            this.footerFormat = footer;
            this.interactive = interactive;
            this.textArea = ta;
        }

        @Override
        protected Object doInBackground() {
            try {
                complete = textArea.print(headerFormat, footerFormat,
                        true, null, null, interactive);
                message = "Printing " + (complete ? "complete" : "canceled");
            } catch (PrinterException ex) {
                message = "Sorry, a printer error occurred";
            } catch (SecurityException ex) {
                message =
                        "Sorry, cannot access the printer due to security reasons";
            }
            return null;
        }

        @Override
        protected void done() {
            message(!complete, message);
        }
    }

    private MessageFormat createFormat(String text) {
        if (text != null && text.length() > 0) {
            try {
                return new MessageFormat(text);
            } catch (IllegalArgumentException e) {
                error("Sorry, this format is invalid.");
            }
        }
        return null;
    }

    private MessageFormat createFormat(JTextField source) {
        return createFormat(source.getText());
    }

    private void message(boolean error, String msg) {
        int type = (error ? JOptionPane.ERROR_MESSAGE : JOptionPane.INFORMATION_MESSAGE);
        JOptionPane.showMessageDialog(this, msg, "Printing", type);
    }

    private void error(String msg) {
        message(true, msg);
    }

    public class EditUndoAction extends AbstractAction
            implements UndoableEditListener {

        private UndoableEdit edit;

        public EditUndoAction() {
            super("Undo");
            //jMenuItem10.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z, java.awt.event.InputEvent.CTRL_MASK));
            setIconImage(new javax.swing.ImageIcon(
                    getClass().getResource("/icons/undo.gif")).getImage());
            //addKeyListener(l);
            setEnabled(false);
        }

        public void updateEnabled() {
            setEnabled(edit.canUndo());
        }

        public void undoableEditHappened(UndoableEditEvent event) {
            edit = event.getEdit();
            putValue(NAME, edit.getUndoPresentationName());
            updateEnabled();
        }

        public void actionPerformed(ActionEvent ae) {
            edit.undo();
            updateEnabled(); // disable undo
            redoAction.updateEnabled(); // enable redo
        }
    }

    public class EditRedoAction extends AbstractAction
            implements UndoableEditListener {

        private UndoableEdit edit;

        public EditRedoAction() {
            super("Redo");
            setEnabled(false);
        }

        public boolean updateEnabled() {
            return edit.canRedo();
        }

        public void undoableEditHappened(UndoableEditEvent event) {
            edit = event.getEdit();
            putValue(NAME, edit.getRedoPresentationName());
            updateEnabled();
        }

        public void actionPerformed(ActionEvent ae) {
            edit.redo();
            updateEnabled(); // disable redo
            undoAction.updateEnabled(); // enable undo
        }
    }
}
