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
import com.lowagie.text.pdf.PdfIndirectReference;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.rtf.RtfWriter2;
import de.muntjak.tinylookandfeel.TinyLookAndFeel;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Point;
import java.awt.ScrollPane;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.print.PrinterException;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
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
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;
import javax.swing.undo.UndoableEdit;
import org.fife.plaf.Office2003.Office2003LookAndFeel;
import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.skin.SubstanceBusinessBlueSteelLookAndFeel;
import org.jvnet.substance.skin.SubstanceBusinessLookAndFeel;
import org.jvnet.substance.skin.SubstanceChallengerDeepLookAndFeel;
import org.jvnet.substance.skin.SubstanceEmeraldDuskLookAndFeel;
import org.jvnet.substance.skin.SubstanceMistAquaLookAndFeel;
import org.jvnet.substance.skin.SubstanceNebulaLookAndFeel;
import org.jvnet.substance.skin.SubstanceOfficeBlue2007LookAndFeel;



/**
 *
 * @author Green Moon
 */
public class JNPMainFrame extends javax.swing.JFrame implements ChangeListener,
        PropertyChangeListener, ActionListener
{

    private static NotePadContext context = NotePadContext.getInstance();
    private static final java.awt.Font DEFAULT_TEXT_FONT
            = new java.awt.Font(java.awt.Font.MONOSPACED,
                java.awt.Font.PLAIN, 12);
    /** Creates new form JNPMainFrame */
    public JNPMainFrame() {
        loadSavedContext();
        initComponents();
        setFrameProperty();
        addNewFile();
    }



    private void loadSavedContext() {
        context = FileRWUtil.readContext();
        if(context == null){
            context = NotePadContext.getInstance();
        }
    }

    private void saveCurrentContext() {
        context.frameLocation = getLocation();
        context.frameSize = getSize();
        
        FileRWUtil.writeContext(context);
    }

    private void setFrameProperty(){
        setTitle("Notepad");
        setIconImage(
                new javax.swing.ImageIcon(
                getClass().getResource("/icons/Notepadicon.png")).getImage());
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        Dimension fd = getSize();
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension resolution = tk.getScreenSize();
        if(context.frameLocation != null){
            setLocation(context.frameLocation);
        }else{
            Point cd = new Point();
            cd.x = resolution.width/2 - fd.width/2;
            cd.y = resolution.height/2 - fd.height/2;
            setLocation(cd);
        }
        if(context.frameSize != null){
            setSize(context.frameSize);
        }
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
        jPanel1 = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JToolBar.Separator();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jSeparator5 = new javax.swing.JToolBar.Separator();
        jButton5 = new javax.swing.JButton();
        jSeparator14 = new javax.swing.JToolBar.Separator();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jSeparator9 = new javax.swing.JToolBar.Separator();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jSeparator10 = new javax.swing.JToolBar.Separator();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jSeparator16 = new javax.swing.JToolBar.Separator();
        jToggleButton1 = new javax.swing.JToggleButton();
        notesTabbedPane = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jSeparator11 = new javax.swing.JSeparator();
        jMenuItem22 = new javax.swing.JMenuItem();
        jMenuItem23 = new javax.swing.JMenuItem();
        jSeparator12 = new javax.swing.JSeparator();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem21 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JSeparator();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JSeparator();
        jMenuItem8 = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JSeparator();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        jSeparator6 = new javax.swing.JSeparator();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenuItem14 = new javax.swing.JMenuItem();
        jMenuItem15 = new javax.swing.JMenuItem();
        jSeparator7 = new javax.swing.JSeparator();
        jMenuItem16 = new javax.swing.JMenuItem();
        jMenuItem17 = new javax.swing.JMenuItem();
        jMenuItem18 = new javax.swing.JMenuItem();
        jMenuItem19 = new javax.swing.JMenuItem();
        jSeparator8 = new javax.swing.JSeparator();
        jMenuItem20 = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        jMenu7 = new javax.swing.JMenu();
        jMenuItem24 = new javax.swing.JMenuItem();
        jMenuItem26 = new javax.swing.JMenuItem();
        jMenuItem27 = new javax.swing.JMenuItem();
        jMenuItem28 = new javax.swing.JMenuItem();
        jSeparator13 = new javax.swing.JSeparator();
        jMenuItem29 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jCheckBoxMenuItem1 = new javax.swing.JCheckBoxMenuItem();
        jMenuItem25 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenu8 = new javax.swing.JMenu();
        jRadioButtonMenuItem1 = new javax.swing.JRadioButtonMenuItem();
        jSeparator15 = new javax.swing.JSeparator();
        jRadioButtonMenuItem2 = new javax.swing.JRadioButtonMenuItem();
        jRadioButtonMenuItem3 = new javax.swing.JRadioButtonMenuItem();
        jRadioButtonMenuItem4 = new javax.swing.JRadioButtonMenuItem();
        jRadioButtonMenuItem5 = new javax.swing.JRadioButtonMenuItem();
        jRadioButtonMenuItem6 = new javax.swing.JRadioButtonMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem30 = new javax.swing.JMenuItem();
        jMenuItem31 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/new.gif"))); // NOI18N
        jButton1.setToolTipText("New File");
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton1);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/open.gif"))); // NOI18N
        jButton2.setToolTipText("Open File");
        jButton2.setFocusable(false);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton2);
        jToolBar1.add(jSeparator4);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/save.gif"))); // NOI18N
        jButton3.setToolTipText("Save");
        jButton3.setFocusable(false);
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton3);

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/saveas_edit.gif"))); // NOI18N
        jButton4.setToolTipText("Save As...");
        jButton4.setFocusable(false);
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton4);
        jToolBar1.add(jSeparator5);

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/print.gif"))); // NOI18N
        jButton5.setToolTipText("Print");
        jButton5.setFocusable(false);
        jButton5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton5);
        jToolBar1.add(jSeparator14);

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/undo.gif"))); // NOI18N
        jButton6.setToolTipText("Undo");
        jButton6.setFocusable(false);
        jButton6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton6);

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/redo.gif"))); // NOI18N
        jButton7.setToolTipText("Redo");
        jButton7.setFocusable(false);
        jButton7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton7.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButton7);
        jToolBar1.add(jSeparator9);

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/cut.gif"))); // NOI18N
        jButton8.setToolTipText("Cut");
        jButton8.setFocusable(false);
        jButton8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton8.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton8);

        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/copy.gif"))); // NOI18N
        jButton9.setToolTipText("Copy");
        jButton9.setFocusable(false);
        jButton9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton9.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton9);

        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/paste.gif"))); // NOI18N
        jButton10.setToolTipText("Paste");
        jButton10.setFocusable(false);
        jButton10.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton10.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton10);
        jToolBar1.add(jSeparator10);

        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/find.gif"))); // NOI18N
        jButton11.setToolTipText("Find");
        jButton11.setFocusable(false);
        jButton11.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton11.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton11);

        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/findnext.gif"))); // NOI18N
        jButton12.setToolTipText("Find Next");
        jButton12.setActionCommand("jButton12");
        jButton12.setFocusable(false);
        jButton12.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton12.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton12);

        jButton13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/replacenext.gif"))); // NOI18N
        jButton13.setToolTipText("Find / Replace");
        jButton13.setFocusable(false);
        jButton13.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton13.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton13);
        jToolBar1.add(jSeparator16);

        jToggleButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/button-word-wrap.gif"))); // NOI18N
        jToggleButton1.setToolTipText("Word Wrap");
        jToggleButton1.setFocusable(false);
        jToggleButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });
        jToolBar1.add(jToggleButton1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel1.add(jToolBar1, gridBagConstraints);

        notesTabbedPane.setBackground(new java.awt.Color(204, 204, 204));
        notesTabbedPane.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        notesTabbedPane.setFocusTraversalPolicyProvider(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(notesTabbedPane, gridBagConstraints);

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setPreferredSize(new java.awt.Dimension(729, 25));

        jLabel1.setText("jLabel1");

        jLabel2.setText("jLabel2");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 509, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 19, Short.MAX_VALUE)
                .addComponent(jLabel2))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        jPanel1.add(jPanel2, gridBagConstraints);

        jMenu1.setText("File");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/new.gif"))); // NOI18N
        jMenuItem1.setText("New");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/open.gif"))); // NOI18N
        jMenuItem2.setText("Open");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);
        jMenu1.add(jSeparator11);

        jMenuItem22.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/close.gif"))); // NOI18N
        jMenuItem22.setText("Close");
        jMenu1.add(jMenuItem22);

        jMenuItem23.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.SHIFT_MASK));
        jMenuItem23.setText("Close All");
        jMenu1.add(jMenuItem23);
        jMenu1.add(jSeparator12);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/save.gif"))); // NOI18N
        jMenuItem3.setText("Save");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/saveas_edit.gif"))); // NOI18N
        jMenuItem4.setText("Save As...");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);

        jMenuItem21.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/saveall.gif"))); // NOI18N
        jMenuItem21.setText("Save All");
        jMenu1.add(jMenuItem21);
        jMenu1.add(jSeparator1);

        jMenuItem5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/pagesetup.png"))); // NOI18N
        jMenuItem5.setText("Page Setup");
        jMenu1.add(jMenuItem5);

        jMenuItem6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/printpreview.gif"))); // NOI18N
        jMenuItem6.setText("Print Preview");
        jMenu1.add(jMenuItem6);

        jMenuItem7.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/print.gif"))); // NOI18N
        jMenuItem7.setText("Print");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem7);
        jMenu1.add(jSeparator2);

        jMenuItem8.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/refresh_nav.gif"))); // NOI18N
        jMenuItem8.setText("Reload");
        jMenu1.add(jMenuItem8);
        jMenu1.add(jSeparator3);

        jMenuItem9.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/exit.gif"))); // NOI18N
        jMenuItem9.setText("Exit");
        jMenu1.add(jMenuItem9);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");

        jMenuItem10.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/undo.gif"))); // NOI18N
        jMenuItem10.setText("Undo");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem10);

        jMenuItem11.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Y, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/redo.gif"))); // NOI18N
        jMenuItem11.setText("Redo");
        jMenu2.add(jMenuItem11);
        jMenu2.add(jSeparator6);

        jMenuItem12.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/cut.gif"))); // NOI18N
        jMenuItem12.setText("Cut");
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem12);

        jMenuItem13.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/copy.gif"))); // NOI18N
        jMenuItem13.setText("Copy");
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem13ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem13);

        jMenuItem14.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/paste.gif"))); // NOI18N
        jMenuItem14.setText("Paste");
        jMenuItem14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem14ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem14);

        jMenuItem15.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_DELETE, 0));
        jMenuItem15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/delete.gif"))); // NOI18N
        jMenuItem15.setText("Delete");
        jMenu2.add(jMenuItem15);
        jMenu2.add(jSeparator7);

        jMenuItem16.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/find.gif"))); // NOI18N
        jMenuItem16.setText("Find...");
        jMenuItem16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem16ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem16);

        jMenuItem17.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F3, 0));
        jMenuItem17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/findnext.gif"))); // NOI18N
        jMenuItem17.setText("Find Next");
        jMenuItem17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem17ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem17);

        jMenuItem18.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/replace.gif"))); // NOI18N
        jMenuItem18.setText("Replace...");
        jMenuItem18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem18ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem18);

        jMenuItem19.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem19.setText("Go to...");
        jMenu2.add(jMenuItem19);
        jMenu2.add(jSeparator8);

        jMenuItem20.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/selectall.gif"))); // NOI18N
        jMenuItem20.setText("Select All");
        jMenuItem20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem20ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem20);

        jMenuBar1.add(jMenu2);

        jMenu6.setText("Tools");

        jMenu7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/export.gif"))); // NOI18N
        jMenu7.setText("Export As...");

        jMenuItem24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/text-html.png"))); // NOI18N
        jMenuItem24.setText("HTML");
        jMenuItem24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem24ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem24);

        jMenuItem26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/x-pdf.png"))); // NOI18N
        jMenuItem26.setText("PDF");
        jMenuItem26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem26ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem26);

        jMenuItem27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/x-office-document.png"))); // NOI18N
        jMenuItem27.setText("RTF");
        jMenuItem27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem27ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem27);

        jMenuItem28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Filetype-XML-16x16.png"))); // NOI18N
        jMenuItem28.setText("XML");
        jMenuItem28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem28ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem28);

        jMenu6.add(jMenu7);
        jMenu6.add(jSeparator13);

        jMenuItem29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/wm_settings.gif"))); // NOI18N
        jMenuItem29.setText("Settings");
        jMenu6.add(jMenuItem29);

        jMenuBar1.add(jMenu6);

        jMenu3.setText("Format");
        jMenu3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu3ActionPerformed(evt);
            }
        });

        jCheckBoxMenuItem1.setText("Word Wrap");
        jCheckBoxMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/button-word-wrap.gif"))); // NOI18N
        jCheckBoxMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuItem1ActionPerformed(evt);
            }
        });
        jMenu3.add(jCheckBoxMenuItem1);

        jMenuItem25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/fonts.gif"))); // NOI18N
        jMenuItem25.setText("Font");
        jMenuItem25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem25ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem25);

        jMenuBar1.add(jMenu3);

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

        jMenuBar1.add(jMenu4);

        jMenu5.setText("Help");

        jMenuItem30.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        jMenuItem30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/help.gif"))); // NOI18N
        jMenuItem30.setText("Help Index");
        jMenu5.add(jMenuItem30);

        jMenuItem31.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/about.gif"))); // NOI18N
        jMenuItem31.setText("About Notepad");
        jMenu5.add(jMenuItem31);

        jMenuBar1.add(jMenu5);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 729, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed

        addNewFile();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        addNewFile();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        openFile();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        openFile();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        saveAs();
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        saveFile();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        saveFile();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        saveAs();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed

    }//GEN-LAST:event_formWindowClosed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        checkAndSaveFiles();
        saveCurrentContext();
        System.exit(0);
    }//GEN-LAST:event_formWindowClosing

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        printText();
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        printText();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        int s = notesTabbedPane.getSelectedIndex();
        if(s != -1){
            TextEditor ed = textEditorList.get(s);
            JTextArea ta = ed.getTextArea();
            String fileName = ed.getFileName();
            if(fileName == null || fileName.equals("")){
               fileName = "New File.txt";
            }
            if(ta != null){
                cut(ta);
            }
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        int s = notesTabbedPane.getSelectedIndex();
        if(s != -1){
            TextEditor ed = textEditorList.get(s);
            JTextArea ta = ed.getTextArea();
            String fileName = ed.getFileName();
            if(fileName == null || fileName.equals("")){
               fileName = "New File.txt";
            }
            if(ta != null){
                copy(ta);
            }
        }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        int s = notesTabbedPane.getSelectedIndex();
        if(s != -1){
            TextEditor ed = textEditorList.get(s);
            JTextArea ta = ed.getTextArea();
            String fileName = ed.getFileName();
            if(fileName == null || fileName.equals("")){
               fileName = "New File.txt";
            }
            if(ta != null){
                paste(ta);
            }
        }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
        int s = notesTabbedPane.getSelectedIndex();
        if(s != -1){
            TextEditor ed = textEditorList.get(s);
            JTextArea ta = ed.getTextArea();
            String fileName = ed.getFileName();
            if(fileName == null || fileName.equals("")){
               fileName = "New File.txt";
            }
            if(ta != null){
                cut(ta);
            }
        }
    }//GEN-LAST:event_jMenuItem12ActionPerformed

    private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem13ActionPerformed
        int s = notesTabbedPane.getSelectedIndex();
        if(s != -1){
            TextEditor ed = textEditorList.get(s);
            JTextArea ta = ed.getTextArea();
            String fileName = ed.getFileName();
            if(fileName == null || fileName.equals("")){
               fileName = "New File.txt";
            }
            if(ta != null){
                copy(ta);
            }
        }
    }//GEN-LAST:event_jMenuItem13ActionPerformed

    private void jMenuItem14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem14ActionPerformed
        int s = notesTabbedPane.getSelectedIndex();
        if(s != -1){
            TextEditor ed = textEditorList.get(s);
            JTextArea ta = ed.getTextArea();
            String fileName = ed.getFileName();
            if(fileName == null || fileName.equals("")){
               fileName = "New File.txt";
            }
            if(ta != null){
                paste(ta);
            }
        }
    }//GEN-LAST:event_jMenuItem14ActionPerformed

    private void jMenuItem26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem26ActionPerformed
        int s = notesTabbedPane.getSelectedIndex();
        if(s != -1){
            TextEditor ed = textEditorList.get(s);
            JTextArea ta = ed.getTextArea();
            String fileName = ed.getFileName();
            if(fileName == null || fileName.equals("")){
               fileName = "New File.txt";
            }
            if(ta != null){
                convertToPDF(ta);
            }
        }
    }//GEN-LAST:event_jMenuItem26ActionPerformed

    private void jMenuItem24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem24ActionPerformed
        int s = notesTabbedPane.getSelectedIndex();
        if(s != -1){
            TextEditor ed = textEditorList.get(s);
            JTextArea ta = ed.getTextArea();
            String fileName = ed.getFileName();
            if(fileName == null || fileName.equals("")){
               fileName = "New File.txt";
            }
            if(ta != null){
                convertToHTML(ta);
            }
        }
    }//GEN-LAST:event_jMenuItem24ActionPerformed

    private void jMenuItem27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem27ActionPerformed
        int s = notesTabbedPane.getSelectedIndex();
        if(s != -1){
            TextEditor ed = textEditorList.get(s);
            JTextArea ta = ed.getTextArea();
            String fileName = ed.getFileName();
            if(fileName == null || fileName.equals("")){
               fileName = "New File.txt";
            }
            if(ta != null){
                convertToRTF(ta);
            }
        }
    }//GEN-LAST:event_jMenuItem27ActionPerformed

    private void jMenuItem28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem28ActionPerformed
        int s = notesTabbedPane.getSelectedIndex();
        if(s != -1){
            TextEditor ed = textEditorList.get(s);
            JTextArea ta = ed.getTextArea();
            String fileName = ed.getFileName();
            if(fileName == null || fileName.equals("")){
               fileName = "New File.txt";
            }
            if(ta != null){
                convertToXML(ta);
            }
        }
    }//GEN-LAST:event_jMenuItem28ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        int s = notesTabbedPane.getSelectedIndex();
        if(s != -1){
            TextEditor ed = textEditorList.get(s);
            JTextArea ta = ed.getTextArea();
            String fileName = ed.getFileName();
            if(fileName == null || fileName.equals("")){
               fileName = "New File.txt";
            }
            if(ta != null){
                
            }
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jMenuItem16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem16ActionPerformed
        
        JTextArea ta = getSelectedTextArea();
        if(ta != null){
            FindDialog fd = new FindDialog(this, false, ta);
            fd.setVisible(true);
        }
    }//GEN-LAST:event_jMenuItem16ActionPerformed

    private void jMenuItem18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem18ActionPerformed
        JTextArea ta = getSelectedTextArea();
        if(ta != null){
            FindReplaceDialog fd = new FindReplaceDialog(this, false, ta);
            fd.setVisible(true);
        }
    }//GEN-LAST:event_jMenuItem18ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        jMenuItem16ActionPerformed(evt);
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jRadioButtonMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonMenuItem1ActionPerformed
        if(jRadioButtonMenuItem1.isSelected()){
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
        if(jRadioButtonMenuItem2.isSelected()){
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
        if(jRadioButtonMenuItem3.isSelected()){
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
        if(jRadioButtonMenuItem4.isSelected()){
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
        if(jRadioButtonMenuItem5.isSelected()){
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
        if(jRadioButtonMenuItem6.isSelected()){
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

    private void jMenuItem17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem17ActionPerformed
        selectedTextArea = getSelectedTextArea();
        if(selectedTextArea != null){
            String key = selectedTextArea.getSelectedText();
            if(key!= null && key.length() > 0){
                find(
                    key,
                    true,
                    true
                 );
            }
        }
    }//GEN-LAST:event_jMenuItem17ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        selectedTextArea = getSelectedTextArea();
        if(selectedTextArea != null){
            String key = selectedTextArea.getSelectedText();
            if(key!= null && key.length() > 0){
                find(
                    key,
                    true,
                    true
                 );
            }
        }
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        JTextArea ta = getSelectedTextArea();
        if(ta != null){
            FindReplaceDialog fd = new FindReplaceDialog(this, false, ta);
            fd.setVisible(true);
        }
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jMenuItem20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem20ActionPerformed
       selectedTextArea = getSelectedTextArea();
        if(selectedTextArea != null){
            selectedTextArea.setSelectionStart(0);
            selectedTextArea.setSelectionEnd(selectedTextArea.getText().length());
        }
    }//GEN-LAST:event_jMenuItem20ActionPerformed

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        if(jToggleButton1.isSelected()){
            getSelectedTextArea().setLineWrap(true);
            jCheckBoxMenuItem1.setSelected(true);
            return;
        }
        if(!jToggleButton1.isSelected()){
            getSelectedTextArea().setLineWrap(false);
            jCheckBoxMenuItem1.setSelected(false);
        }
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void jMenu3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu3ActionPerformed
        
    }//GEN-LAST:event_jMenu3ActionPerformed

    private void jCheckBoxMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItem1ActionPerformed
        if(jCheckBoxMenuItem1.isSelected()){
            getSelectedTextArea().setLineWrap(true);
            jToggleButton1.setSelected(true);
            return;
        }
        if(!jCheckBoxMenuItem1.isSelected()){
            getSelectedTextArea().setLineWrap(false);
            jToggleButton1.setSelected(false);
        }
    }//GEN-LAST:event_jCheckBoxMenuItem1ActionPerformed
public static String[] fontNames;
    public static String[] fontSizes;
    private void jMenuItem25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem25ActionPerformed
        selectedTextArea = getSelectedTextArea();
        if(selectedTextArea != null){
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            fontNames = ge.getAvailableFontFamilyNames();
            fontSizes = new String[]{"8", "9", "10", "11", "12", "14", "16",
                        "18", "20", "22", "24", "26", "28", "36", "48", "72"};

            AdvancedFontChooser dlg = new AdvancedFontChooser(this);
            SimpleAttributeSet a = new SimpleAttributeSet();
            StyleConstants.setFontFamily(a, "Monospaced");
            StyleConstants.setFontSize(a, 12);
            dlg.setAttributes(a);
            dlg.show();
        }
    }//GEN-LAST:event_jMenuItem25ActionPerformed

    int pos;
    /**      */
    int count;
    /**      *
     */
    int a;
    /**      */
    int newcounter;

    public void find(String key, boolean up, boolean match){
        int position = 0;
        int count = 0, a = 0;
        String text = selectedTextArea.getText();
        if(!match){
            text = text.toLowerCase();
            key = key.toLowerCase();
        }
        position = text.indexOf(key, selectedTextArea.getSelectionEnd() + newcounter);
        if(position >= 0){
            selectedTextArea.select( position , position + key.length());
        }
    }

    public JTextArea getSelectedTextArea(){
        JTextArea ta = null;
        int s = notesTabbedPane.getSelectedIndex();
        if(s != -1){
            TextEditor ed = textEditorList.get(s);
            ta = ed.getTextArea();
        }
        return ta;
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
        }finally{
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
        }finally{
            document.close();
        }
    }

    private String browseForPDF(){
        JFileChooser chooser = new JFileChooser(".");
        chooser.setMultiSelectionEnabled(false);
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int opt = chooser.showSaveDialog(this);
        String fileName = "";
        if(opt == JFileChooser.APPROVE_OPTION){
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
        }finally{
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
        }finally{
            document.close();
        }
    }



    private void printText(){
        int s = notesTabbedPane.getSelectedIndex();
        if(s != -1){
            TextEditor ed = textEditorList.get(s);
            JTextArea ta = ed.getTextArea();
            String fileName = ed.getFileName();
            if(fileName == null || fileName.equals("")){
               fileName = "New File.txt";
            }
            if(ta != null){
                MessageFormat header = createFormat("FILE: "+fileName);
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

    private void checkAndSaveFiles(){
        int i =0, count = notesTabbedPane.getTabCount();
        for(; i<count; i++){
            TextEditor ed = textEditorList.get(i);
            JTextArea ta = ed.getTextArea();
            if(ed.isIsNewFile() || ed.isIsEdited()){
                int opt = JOptionPane.showConfirmDialog(this,
                        "Do you want to save the file?",
                        "Save",
                        JOptionPane.OK_CANCEL_OPTION
                      );
                if(opt == JOptionPane.OK_OPTION){
                    notesTabbedPane.setSelectedIndex(i);
                    saveFile();
                }
                notesTabbedPane.remove(i);
                textEditorList.remove(i);
            }
        }
    }

    private void saveFile(){
        int s = notesTabbedPane.getSelectedIndex();
        if(s != -1){
            TextEditor ed = textEditorList.get(s);
            JTextArea ta = ed.getTextArea();
            String fileName = ed.getFileName();
            if(fileName != null && !fileName.equals("")){
                if(ta != null){
                    writeFile(fileName, ta.getText());
                    ed.setIsEdited(false);
                    ed.setIsNewFile(false);
                }
            }else{
                saveAs();
            }
            
        }
    }

    private void saveAs(){
        JFileChooser chooser = new JFileChooser(".");
        chooser.setMultiSelectionEnabled(false);
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int opt = chooser.showSaveDialog(this);
        if(opt == JFileChooser.APPROVE_OPTION){
            String fileName = chooser.getSelectedFile().getAbsolutePath();
            int s = notesTabbedPane.getSelectedIndex();
            if(s != -1){
                TextEditor ed = textEditorList.get(s);
                JTextArea ta = ed.getTextArea();
                ed.setFileName(fileName);
                if(ta != null){
                    writeFile(fileName, ta.getText());
                    ed.setIsEdited(false);
                    ed.setIsNewFile(false);
                    File f = new File(fileName);
                    notesTabbedPane.setTitleAt(s, f.getName());
                    notesTabbedPane.updateUI();
                }
            }
            
        }
    }

    private void openFile(){
        JFileChooser chooser = new JFileChooser(".");
        chooser.setMultiSelectionEnabled(true);

        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int opt = chooser.showOpenDialog(this);
        if(opt == JFileChooser.APPROVE_OPTION){
            File[] files = chooser.getSelectedFiles();
            for (File file : files) {
                JTextArea ta = readFile(file);
                ta.setMargin(new java.awt.Insets(2, 2, 2, 2));
                final UndoManager undo = new UndoManager();
                javax.swing.text.Document doc = ta.getDocument();

                // Listen for undo and redo events
                doc.addUndoableEditListener(new UndoableEditListener() {
                    public void undoableEditHappened(UndoableEditEvent evt) {
                        undo.addEdit(evt.getEdit());
                    }
                });

                // Create an undo action and add it to the text component
                ta.getActionMap().put("Undo",
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
                ta.getInputMap().put(KeyStroke.getKeyStroke("control Z"), "Undo");

                // Create a redo action and add it to the text component
                ta.getActionMap().put("Redo",
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
                ta.getInputMap().put(KeyStroke.getKeyStroke("control Y"), "Redo");
                JScrollPane sp = new JScrollPane(ta);
                sp.setViewportView(ta);
                notesTabbedPane.addTab(file.getName(), sp);
                int n = notesTabbedPane.getTabCount();
                notesTabbedPane.setTabComponentAt(n-1,
                        new ButtonTabComponent(notesTabbedPane, textEditorList));
                notesTabbedPane.setSelectedIndex(n-1);
                TextEditor ed = new TextEditor();
                ed.setTextArea(ta);
                ed.setIsNewFile(true);
                ed.setFileName(file.getAbsolutePath());
                textEditorList.add(ed);
            }
        }

    }

    private void writeFile(String fileName, String date){
        BufferedWriter bw = null;
        try{
            bw = new BufferedWriter(new FileWriter(new File(fileName)));
            bw.write(date);
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, e);
        }finally{
            if(bw != null){
                    try {
                        bw.close();
                    } catch (IOException ex) {
                        Logger.getLogger(JNPMainFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
        }
    }

    private JTextArea readFile(File f){
        JTextArea ta = new JTextArea(10,8);
        ta.setFont(DEFAULT_TEXT_FONT);
        if(f.exists()){
            BufferedReader br = null;
            try{
                StringBuffer line = new StringBuffer();
                int count = 0;
                char[] buffer = new char[1024];
                br = new BufferedReader(
                        new InputStreamReader(new FileInputStream(f)));
                while((count = br.read(buffer, 0, buffer.length)) >= 0){
                    line.append(buffer, 0, count);
                    ta.append(line.toString());
                    line = new StringBuffer();
                }
            }catch(Exception e){
                JOptionPane.showMessageDialog(this, e);
            }finally{
                if(br != null){
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

    private void addNewFile(){
        JScrollPane sp = new JScrollPane();
        JTextArea textcomp = new JTextArea(5,8);
        textcomp.setMargin(new java.awt.Insets(2, 2, 2, 2));
        textcomp.setFont(DEFAULT_TEXT_FONT);
        final UndoManager undo = new UndoManager();
        javax.swing.text.Document doc = textcomp.getDocument();

        // Listen for undo and redo events
        doc.addUndoableEditListener(new UndoableEditListener() {
            public void undoableEditHappened(UndoableEditEvent evt) {
                undo.addEdit(evt.getEdit());
            }
        });

        // Create an undo action and add it to the text component
        textcomp.getActionMap().put("Undo",
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
        textcomp.getInputMap().put(KeyStroke.getKeyStroke("control Z"), "Undo");

        // Create a redo action and add it to the text component
        textcomp.getActionMap().put("Redo",
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
        textcomp.getInputMap().put(KeyStroke.getKeyStroke("control Y"), "Redo");
        //sta.addPropertyChangeListener("TEXT", list);
        sp.add(textcomp);
        sp.setViewportView(textcomp);
        
        notesTabbedPane.addTab("New File *", sp);
        int n = notesTabbedPane.getTabCount();
        notesTabbedPane.setTabComponentAt(n-1, 
                new ButtonTabComponent(notesTabbedPane, textEditorList));
        notesTabbedPane.setSelectedIndex(n-1);
        TextEditor ed = new TextEditor();
        ed.setTextArea(textcomp);
        ed.setIsNewFile(true);
        textEditorList.add(n-1, ed);
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
                    if(TinyLookAndFeel.class.getCanonicalName().equals(lnfClass)){
                        frame.jRadioButtonMenuItem2.setSelected(true);
                    }
                    if(UIManager.getSystemLookAndFeelClassName().equals(lnfClass)){
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem15;
    private javax.swing.JMenuItem jMenuItem16;
    private javax.swing.JMenuItem jMenuItem17;
    private javax.swing.JMenuItem jMenuItem18;
    private javax.swing.JMenuItem jMenuItem19;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem20;
    private javax.swing.JMenuItem jMenuItem21;
    private javax.swing.JMenuItem jMenuItem22;
    private javax.swing.JMenuItem jMenuItem23;
    private javax.swing.JMenuItem jMenuItem24;
    private javax.swing.JMenuItem jMenuItem25;
    private javax.swing.JMenuItem jMenuItem26;
    private javax.swing.JMenuItem jMenuItem27;
    private javax.swing.JMenuItem jMenuItem28;
    private javax.swing.JMenuItem jMenuItem29;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem30;
    private javax.swing.JMenuItem jMenuItem31;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
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
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JToolBar.Separator jSeparator4;
    private javax.swing.JToolBar.Separator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JToolBar.Separator jSeparator9;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.ButtonGroup lnfButtonGroup;
    private javax.swing.JTabbedPane notesTabbedPane;
    // End of variables declaration//GEN-END:variables

    public void stateChanged(ChangeEvent e) {
        
    }

    public void propertyChange(PropertyChangeEvent evt) {
        
    }

    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        JTextArea ta = null;
        if(e.getSource() instanceof JTextArea){
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
        int type = (error ? JOptionPane.ERROR_MESSAGE :
                            JOptionPane.INFORMATION_MESSAGE);
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
