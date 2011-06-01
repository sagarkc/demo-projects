package com.gs.jprompt.app.components;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Caret;
import javax.swing.text.DocumentFilter;
import javax.swing.text.DocumentFilter.FilterBypass;

import org.apache.log4j.Logger;

import com.gs.jprompt.common.JPromptConstants;
import com.gs.jprompt.common.JPromptContext;
import com.gs.utils.text.StringUtil;

/**
 * The requirement to mark parts of your text area content as read-only can be
 * done using DocumentFilter. In short: - get the Document from your JTextArea
 * -cast it to AbstractDocument (this cast is safe unless the JTextArea has a
 * custom document) - create a custom DocumentFilter subclass and instantiate it
 * (perhaps an anonymous inner class will do) - call setDocumentFilter on the
 * document
 * 
 * The custom DocumentFilter is where the hard work is done. Override
 * insertString to make sure you can't insert anything before your current
 * prompt position. Override remove to make sure you can't remove anything
 * before your current prompt position. Override replace to make sure you can't
 * replace anything before your current prompt position.
 * 
 * That leaves one issue: the caret position. You don't want to have your cursor
 * to be positioned before your current prompt position. Perhaps you can use a
 * CaretListener for that. But resetting the caret position to the end (or at
 * least after your current prompt position) may cause text highlighting (for
 * copying data) to not work. These are things you need to consider.
 * 
 * @author Sabuj Das
 * 
 */

public class ConsoleEditorPane extends JEditorPane implements KeyListener,
		MouseListener, CaretListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7905965721433569980L;
	private static final Logger logger = Logger
			.getLogger(ConsoleEditorPane.class);

	private static final JPromptContext context = JPromptContext.getContext();

	private JFrame parentFrame;

	private String prompt;
	private File workingDirectory;
	private char promptCharacter = JPromptConstants.DEFAULT_PROMPT_CHAR;

	private int promptLength;
	private int endPromptOffset;
	private int promptLine;

	public ConsoleEditorPane(JFrame parentFrame) {
		this(parentFrame, new File("."));
	}

	public ConsoleEditorPane(JFrame parentFrame, File workingDirectory) {
		this(parentFrame, workingDirectory, null);
	}

	public ConsoleEditorPane(JFrame parentFrame, File workingDirectory,
			String prompt) {
		this.parentFrame = parentFrame;
		if (!StringUtil.hasValidContent(prompt)) {
			prompt = JPromptConstants.DEFAULT_PROMPT;
			if (workingDirectory.exists()) {
				try {
					prompt = workingDirectory.getCanonicalPath();
				} catch (IOException e) {
					logger.error("The working directory not found. Setting it to the user.home directory.");
					workingDirectory = new File(JPromptConstants.USER_DIR);
					prompt = workingDirectory.getAbsolutePath();
				}
			} else {
				logger.error("The working directory not found. Setting it to the user.home directory.");
				workingDirectory = new File(JPromptConstants.USER_DIR);
				prompt = workingDirectory.getAbsolutePath();
			}
		}
		this.workingDirectory = workingDirectory;
		this.prompt = prompt;
		initComponents();
	}

	private void initComponents() {
		setFont(context.getDefaultEditorFont());
		setText(getPrompt() + getPromptCharacter());
		promptLength = prompt.length() + 1;
		endPromptOffset = promptLength - 1;
		addKeyListener(this);
		AbstractDocument abstractDocument = (AbstractDocument) getDocument();
		PromptDocumentFilter documentFilter =  new PromptDocumentFilter();
		documentFilter.setPromptLength(getPromptLength());
		abstractDocument.setDocumentFilter(documentFilter);
	}

	/**
	 * @return the prompt
	 */
	public String getPrompt() {
		return prompt;
	}

	/**
	 * @param prompt
	 *            the prompt to set
	 */
	public void setPrompt(String prompt) {
		this.prompt = prompt;
	}

	/**
	 * @return the workingDirectory
	 */
	public File getWorkingDirectory() {
		return workingDirectory;
	}

	/**
	 * @param workingDirectory
	 *            the workingDirectory to set
	 */
	public void setWorkingDirectory(File workingDirectory) {
		this.workingDirectory = workingDirectory;
	}

	/**
	 * @return the promptCharacter
	 */
	public char getPromptCharacter() {
		return promptCharacter;
	}

	/**
	 * @param promptCharacter
	 *            the promptCharacter to set
	 */
	public void setPromptCharacter(char promptCharacter) {
		this.promptCharacter = promptCharacter;
	}

	/**
	 * @return the parentFrame
	 */
	public JFrame getParentFrame() {
		return parentFrame;
	}

	/**
	 * @param parentFrame
	 *            the parentFrame to set
	 */
	public void setParentFrame(JFrame parentFrame) {
		this.parentFrame = parentFrame;
	}

	public int getPromptLength() {
		return promptLength;
	}

	public int getEndPromptOffset() {
		return endPromptOffset;
	}

	public int getPromptLine() {
		return promptLine;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		int keyCode = e.getKeyCode();
		if(KeyEvent.VK_ENTER == keyCode){
			System.out.println("Command: ");
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void caretUpdate(CaretEvent e) {
		
	}

	public boolean isPromptLine(){
		int caretPos = getCaretPosition();
		return false;
	}
	
	
	/*----------- Document Filter ------------------*/

	/*----------- Test Method ------------------*/
	public static void main(String args[]) {
		try {
			JFrame frame = new JFrame("Navigation Filter Example");

			ConsoleEditorPane cep = new ConsoleEditorPane(frame, new File(""));
			cep.setPreferredSize(new Dimension(280, 150));
			cep.setMinimumSize(cep.getPreferredSize());
			JScrollPane sp = new JScrollPane();
			sp.setViewportView(cep);
			// frame.setSize(200, 120);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.getContentPane().add(sp);
			frame.pack();
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	

}
