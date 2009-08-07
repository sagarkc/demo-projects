/**
 * 
 */
package com.gs.demo.cmd;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * @author sabuj.das
 * 
 */
public class CmdTest extends JFrame implements ActionListener, KeyListener {

	private static final String TITLE = "CMD Test - ";

	private JLabel inputLabel, potputLabel;
	private JTextArea outputArea;
	private JTextField inputField;
	private JButton runButton, clearButton;
	private JToggleButton wrapButton;
	private JPanel parentPanel;

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new CmdTest().setVisible(true);
	}

	public CmdTest() {
		setLocation(100, 100);
		setTitle(TITLE);
		setSize(640, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		GridBagConstraints gridBagConstraints;

		inputLabel = new JLabel("Input CMD: ");
		potputLabel = new JLabel("CMD Output: ");
		outputArea = new JTextArea(5, 6);
		outputArea.setEditable(false);
		inputField = new JTextField();
		inputField.addKeyListener(this);
		inputField.setActionCommand("ENTER_KEY_PRASED");
		runButton = new JButton("Run");
		runButton.addActionListener(this);
		runButton.setActionCommand("RUN_CMD");
		clearButton = new JButton("Clear");
		clearButton.setActionCommand("CLEAR_OP");
		clearButton.addActionListener(this);
		parentPanel = new JPanel();
		wrapButton = new JToggleButton("Wrap");
		wrapButton.addActionListener(this);
		wrapButton.setActionCommand("WRAP_TEXT");

		parentPanel.setLayout(new GridBagLayout());

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridheight = 1;
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.insets = new Insets(5, 5, 5, 5);
		gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
		parentPanel.add(inputLabel, gridBagConstraints);

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridheight = 1;
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.insets = new Insets(5, 5, 5, 5);
		gridBagConstraints.anchor = GridBagConstraints.CENTER;
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.weightx = 1.0;
		parentPanel.add(inputField, gridBagConstraints);

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridheight = 1;
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.insets = new Insets(5, 5, 5, 5);
		gridBagConstraints.anchor = GridBagConstraints.NORTHEAST;
		parentPanel.add(runButton, gridBagConstraints);

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridheight = 1;
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.insets = new Insets(1, 5, 5, 5);
		gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
		parentPanel.add(potputLabel, gridBagConstraints);

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridheight = 1;
		gridBagConstraints.gridwidth = 2;
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.insets = new Insets(5, 5, 5, 5);
		gridBagConstraints.anchor = GridBagConstraints.CENTER;
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		parentPanel.add(new JScrollPane(outputArea), gridBagConstraints);

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridheight = 1;
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.insets = new Insets(5, 5, 5, 5);
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		parentPanel.add(wrapButton, gridBagConstraints);

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridheight = 1;
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.insets = new Insets(5, 5, 5, 5);
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		parentPanel.add(clearButton, gridBagConstraints);

		getContentPane().add(parentPanel, BorderLayout.CENTER);

		// pack();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String actCmd = e.getActionCommand();
		if ("RUN_CMD".equals(actCmd)) {
			String cmd = inputField.getText();
			executeCmd(cmd);
		}
		if ("CLEAR_OP".equals(actCmd)) {
			inputField.setText("");
			outputArea.setText("");
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() != KeyEvent.VK_ENTER) {
			return;
		}
		Object s = e.getSource();
		if (s instanceof JTextField) {
			String cmd = ((JTextField) s).getText();
			executeCmd(cmd);
		}
	}

	private void executeCmd(String cmd) {
		outputArea.append("\nCMD :>\t" + cmd);
		try {
			Process p = Runtime.getRuntime().exec("cmd /c " + cmd);
			BufferedReader reader = new BufferedReader(new InputStreamReader(p
					.getInputStream()));
			String line = reader.readLine();
			while (line != null) {
				outputArea.append("\nCMD :>\t" + line);
				line = reader.readLine();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
