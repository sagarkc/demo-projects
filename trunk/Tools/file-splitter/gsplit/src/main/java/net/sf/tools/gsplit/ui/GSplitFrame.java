/**
 * 
 */
package net.sf.tools.gsplit.ui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.io.File;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
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
	
	private JMenuBar menuBar;
	private JMenu fileMenu;
	private JMenuItem openMetadataFileItem;
	private JMenuItem closeMenuItem;
	private JMenu helpMenu;
	private JMenuItem openHelpMenuItem;
	private JMenuItem aboutMenuItem;
	
	private FormActionListener formActionListener;
	
	private JTabbedPane tabbedPane;
	
	private JButton autoJoinButton;
	private JPanel splitterPanel;
	private JButton splitStartButton;
	private JTextField splitSourceField;
	private JButton browseSourceButton;
	private JTextField splitTargetField;
	private JButton browseTargetButton;
	private JProgressBar splitProgressBar;
	private JLabel splitStatusLabel;
	
	private JPanel autoJoinPanel;
	
	/**
	 * 
	 */
	public GSplitFrame() {
		setTitle(bundle.getString("app.title")
				+ " " + bundle.getString("app.version"));
		setSize(540, 350);
		WindowUtil.bringToCenter(this);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		formActionListener = new FormActionListener();
		initConponents();
	}

	/**
	 * 
	 */
	private void initConponents() {
		setLayout(new BorderLayout());
		
		menuBar = new JMenuBar();
		fileMenu = new JMenu(bundle.getString("fileMenu.text"));
		openMetadataFileItem = new JMenuItem(bundle.getString("openMetadataFileItem.text"));
		fileMenu.add(openMetadataFileItem);
		fileMenu.addSeparator();
		closeMenuItem = new JMenuItem(bundle.getString("closeMenuItem.text"));
		closeMenuItem.addActionListener(formActionListener);
		fileMenu.add(closeMenuItem);
		menuBar.add(fileMenu);
		
		helpMenu = new JMenu(bundle.getString("helpMenu.text"));
		openHelpMenuItem = new JMenuItem(bundle.getString("openHelpMenuItem.text"));
		helpMenu.add(openHelpMenuItem);
		helpMenu.addSeparator();
		aboutMenuItem = new JMenuItem(bundle.getString("aboutMenuItem.text"));
		helpMenu.add(aboutMenuItem);
		menuBar.add(helpMenu);
		
		setJMenuBar(menuBar);
		
		tabbedPane = createTabs();
		getContentPane().add(tabbedPane, BorderLayout.CENTER);
	}
	
	/**
	 * @return
	 */
	private JTabbedPane createTabs() {
		tabbedPane = new JTabbedPane();
		
		tabbedPane.addTab("Splitter", createSplitterPanel());
		tabbedPane.addTab("Auto Join", createAutoJoinPanel());
		
		return tabbedPane;
	}
	
	/**
	 * @return
	 */
	private JPanel createAutoJoinPanel() {
		autoJoinPanel = new JPanel(new GridBagLayout());
		
		return autoJoinPanel;
	}

	private JPanel createSplitterPanel(){
		splitterPanel = new JPanel(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.NORTH;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 3;
		c.weightx = 1;
		c.weighty = 0;
		c.insets = new Insets(2, 2, 2, 2);
		c.ipady = 20;
		
		splitterPanel.add(new JLabel(
				bundle.getString("splitterPanel.label.text")), c);
		
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.anchor = GridBagConstraints.NORTH;
		c.fill = GridBagConstraints.NONE;
		c.weightx = 0;
		c.weighty = 0;
		c.insets = new Insets(2, 2, 2, 2);
		
		splitterPanel.add(new JLabel(
				bundle.getString("splitterPanel.label.source.text")), c);
		
		splitSourceField = new JTextField();
		splitSourceField.getDocument().addDocumentListener(formActionListener);
		c.gridx = 1;
		c.gridy = 1;
		c.anchor = GridBagConstraints.NORTH;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		c.weighty = 0;
		c.insets = new Insets(2, 2, 2, 2);
		splitterPanel.add(splitSourceField, c);
		
		browseSourceButton = new JButton(bundle.getString("splitterPanel.browseSourceButton.text"));
		browseSourceButton.addActionListener(formActionListener);
		c.gridx = 2;
		c.gridy = 1;
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.NONE;
		c.weightx = 0;
		c.weighty = 0;
		c.insets = new Insets(2, 2, 2, 2);
		splitterPanel.add(browseSourceButton, c);
		
		
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 2;
		c.anchor = GridBagConstraints.NORTH;
		c.fill = GridBagConstraints.NONE;
		c.weightx = 0;
		c.weighty = 0;
		c.insets = new Insets(2, 2, 2, 2);
		
		splitterPanel.add(new JLabel(
				bundle.getString("splitterPanel.label.target.text")), c);
		
		splitTargetField = new JTextField();
		splitTargetField.getDocument().addDocumentListener(formActionListener);
		c.gridx = 1;
		c.gridy = 2;
		c.anchor = GridBagConstraints.NORTH;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		c.weighty = 0;
		c.insets = new Insets(2, 2, 2, 2);
		splitterPanel.add(splitTargetField, c);
		
		browseTargetButton = new JButton(bundle.getString("splitterPanel.browseTargetButton.text"));
		browseTargetButton.addActionListener(formActionListener);
		c.gridx = 2;
		c.gridy = 2;
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.NONE;
		c.weightx = 0;
		c.weighty = 0;
		c.insets = new Insets(2, 2, 2, 2);
		splitterPanel.add(browseTargetButton, c);
		
		splitProgressBar = new JProgressBar();
		splitProgressBar.setMaximum(100);
		splitProgressBar.setMinimum(0);
		splitProgressBar.setDoubleBuffered(true);
		splitProgressBar.setVisible(false);
		c.gridx = 1;
		c.gridy = 3;
		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 0;
		c.insets = new Insets(2, 2, 2, 2);
		splitterPanel.add(splitProgressBar, c);
		
		splitStatusLabel = new JLabel();
		c.gridx = 0;
		c.gridy = 4;
		c.gridwidth = 3;
		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 1;
		c.insets = new Insets(2, 2, 2, 2);
		splitterPanel.add(splitStatusLabel, c);
		
		splitStartButton = new JButton(bundle.getString("splitterPanel.splitButton.text"));
		splitStartButton.setEnabled(false);
		splitStartButton.addActionListener(formActionListener);
		c.gridx = 2;
		c.gridy = 5;
		c.anchor = GridBagConstraints.EAST;
		c.fill = GridBagConstraints.NONE;
		c.weightx = 0;
		c.weighty = 0;
		c.insets = new Insets(2, 2, 2, 2);
		splitterPanel.add(splitStartButton, c);
		
		return splitterPanel;
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
			if(evt.getSource().equals(browseSourceButton)){
				File file = FileBrowserUtil.openSingleFile(GSplitFrame.this, null, false);
				if(null != file){
					splitSourceField.setText(file.getAbsolutePath());
				}
			}
			if(evt.getSource().equals(browseTargetButton)){
				File dir = FileBrowserUtil.openSingleFile(GSplitFrame.this, null, true);
				if(null != dir){
					splitTargetField.setText(dir.getAbsolutePath());
				}
			}
			if(evt.getSource().equals(splitStartButton)){
				JOptionPane.showMessageDialog(GSplitFrame.this, "dsfasdfasdf");
			}
		}
		
		/* (non-Javadoc)
		 * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
		 */
		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			if(evt.getSource().equals(splitSourceField)){
				if(StringUtil.hasValidContent(splitSourceField.getText())
						&& StringUtil.hasValidContent(splitTargetField.getText())){
					splitStartButton.setEnabled(true);
				} else {
					splitStartButton.setEnabled(false);
				}
			}
			if(evt.getSource().equals(splitTargetField)){
				if(StringUtil.hasValidContent(splitSourceField.getText())
						&& StringUtil.hasValidContent(splitTargetField.getText())){
					splitStartButton.setEnabled(true);
				} else {
					splitStartButton.setEnabled(false);
				}
			}
		}
		
		/* (non-Javadoc)
		 * @see java.beans.VetoableChangeListener#vetoableChange(java.beans.PropertyChangeEvent)
		 */
		@Override
		public void vetoableChange(PropertyChangeEvent evt)
				throws PropertyVetoException {
			if(evt.getSource().equals(splitSourceField)){
				if(StringUtil.hasValidContent(splitSourceField.getText())
						&& StringUtil.hasValidContent(splitTargetField.getText())){
					splitStartButton.setEnabled(true);
				} else {
					splitStartButton.setEnabled(false);
				}
			}
			if(evt.getSource().equals(splitTargetField)){
				if(StringUtil.hasValidContent(splitSourceField.getText())
						&& StringUtil.hasValidContent(splitTargetField.getText())){
					splitStartButton.setEnabled(true);
				} else {
					splitStartButton.setEnabled(false);
				}
			}
		}

		/* (non-Javadoc)
		 * @see javax.swing.event.DocumentListener#insertUpdate(javax.swing.event.DocumentEvent)
		 */
		@Override
		public void insertUpdate(DocumentEvent evt) {
			if(StringUtil.hasValidContent(splitSourceField.getText())
					&& StringUtil.hasValidContent(splitTargetField.getText())){
				splitStartButton.setEnabled(true);
			} else {
				splitStartButton.setEnabled(false);
			}
		}

		/* (non-Javadoc)
		 * @see javax.swing.event.DocumentListener#removeUpdate(javax.swing.event.DocumentEvent)
		 */
		@Override
		public void removeUpdate(DocumentEvent evt) {
			if(StringUtil.hasValidContent(splitSourceField.getText())
					&& StringUtil.hasValidContent(splitTargetField.getText())){
				splitStartButton.setEnabled(true);
			} else {
				splitStartButton.setEnabled(false);
			}
		}

		/* (non-Javadoc)
		 * @see javax.swing.event.DocumentListener#changedUpdate(javax.swing.event.DocumentEvent)
		 */
		@Override
		public void changedUpdate(DocumentEvent evt) {
			if(StringUtil.hasValidContent(splitSourceField.getText())
					&& StringUtil.hasValidContent(splitTargetField.getText())){
				splitStartButton.setEnabled(true);
			} else {
				splitStartButton.setEnabled(false);
			}
		}
		
		
	}
}
