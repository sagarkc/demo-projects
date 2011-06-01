package com.gs.jprompt.app.components;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.DocumentFilter.FilterBypass;

import org.apache.log4j.Logger;

import com.gs.jprompt.common.JPromptConstants;
import com.gs.jprompt.common.JPromptContext;
import com.gs.utils.text.StringUtil;

public class ConsoleTextArea extends JTextArea implements CaretListener,
		MouseListener, KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7905965721433569980L;
	private static final Logger logger = Logger
			.getLogger(ConsoleEditorPane.class);

	private static final JPromptContext context = JPromptContext.getContext();

	private JFrame parentFrame;

	private String prompt;
	private String workingDirectory;
	private char promptCharacter = JPromptConstants.DEFAULT_PROMPT_CHAR;

	private int promptLength;
	private int endPromptOffset;
	private int promptLine;
	private String promptValue;

	public ConsoleTextArea(JFrame parentFrame) {
		this(parentFrame, ".");
	}

	public ConsoleTextArea(JFrame parentFrame, String workingDirectory) {
		this(parentFrame, workingDirectory, null);
	}

	public ConsoleTextArea(JFrame parentFrame, String workingDirectory,
			String prompt) {
		
		this.parentFrame = parentFrame;
		
		if (!StringUtil.hasValidContent(prompt)) {
			File dorkDir = new File(workingDirectory);
			prompt = JPromptConstants.DEFAULT_PROMPT;
			if (dorkDir.exists()) {
				try {
					prompt = dorkDir.getCanonicalPath();
				} catch (IOException e) {
					logger.error("The working directory not found. Setting it to the user.home directory.");
					workingDirectory = JPromptConstants.USER_DIR;
					prompt = JPromptConstants.USER_DIR;
				}
			} else {
				logger.error("The working directory not found. Setting it to the user.home directory.");
				workingDirectory = JPromptConstants.USER_DIR;
				prompt = JPromptConstants.USER_DIR;
			}
		} else {
			this.promptValue = prompt;
		}
		this.workingDirectory = workingDirectory;
		
		this.prompt = prompt;
		endPromptOffset = promptLength;
		initComponents();
	}
	
	

	private void initComponents() {
		setFont(context.getDefaultEditorFont());
		setText(getPrompt() + getPromptCharacter());
		promptLength = prompt.length() + 1;
		endPromptOffset = promptLength;
		//addKeyListener(this);
		//addMouseListener(this);
		//addCaretListener(this);
		AbstractDocument abstractDocument = (AbstractDocument) getDocument();
//		PromptDocumentFilter documentFilter =  new PromptDocumentFilter();
//		documentFilter.setPromptLength(getPromptLength());
		abstractDocument.setDocumentFilter(
				new DocumentFilter(){
					@Override
					public void remove(FilterBypass fb, int offset, int length)
							throws BadLocationException {
						if (offset >= getEndPromptOffset()) {
							super.remove(fb, offset, length);
						}
					}

					@Override
					public void insertString(FilterBypass fb, int offset, String string,
							AttributeSet attr) throws BadLocationException {
						if (offset >= getEndPromptOffset()) {
							super.insertString(fb, offset, string, attr);
						} else {
							super.insertString(fb, getEndPromptOffset(), string, attr);
						}
						
					}

					@Override
					public void replace(FilterBypass fb, int offset, int length, String text,
							AttributeSet attrs) throws BadLocationException {
						if (offset >= getEndPromptOffset()) {
							super.replace(fb, offset, length, text, attrs);
						} else {
							super.replace(fb, getEndPromptOffset(), length, text, attrs);
						}
					}
				}
		);
		isPromptLine();
	}
	
	
	public JFrame getParentFrame() {
		return parentFrame;
	}

	public void setParentFrame(JFrame parentFrame) {
		this.parentFrame = parentFrame;
	}

	public String getPrompt() {
		return prompt;
	}

	public void setPrompt(String prompt) {
		if (!StringUtil.hasValidContent(prompt)) {
			prompt = JPromptConstants.DEFAULT_PROMPT;
			File workDir = new File(workingDirectory);
			prompt = JPromptConstants.DEFAULT_PROMPT;
			if (workDir.exists()) {
				try {
					prompt = workDir.getCanonicalPath();
				} catch (IOException e) {
					logger.error("The working directory not found. Setting it to the user.home directory.");
					workingDirectory = JPromptConstants.USER_DIR;
					prompt = JPromptConstants.USER_DIR;
				}
			} else {
				logger.error("The working directory not found. Setting it to the user.home directory.");
				workingDirectory = JPromptConstants.USER_DIR;
				prompt = JPromptConstants.USER_DIR;
			}
		} 
		this.prompt = prompt;
		this.promptValue = prompt;
		updatePromptLength();
	}

	public String getWorkingDirectory() {
		return workingDirectory;
	}

	public void setWorkingDirectory(String workingDirectory) {
		this.workingDirectory = workingDirectory;
		File workDir = new File(workingDirectory);
		prompt = JPromptConstants.DEFAULT_PROMPT;
		if (workDir.exists()) {
			try {
				prompt = workDir.getCanonicalPath();
			} catch (IOException e) {
				logger.error("The working directory not found. Setting it to the user.home directory.");
				workingDirectory = JPromptConstants.USER_DIR;
				prompt = JPromptConstants.USER_DIR;
			}
			//prompt = workDir.getPath();
		} else {
			logger.error("The working directory not found. Setting it to the user.home directory.");
			workingDirectory = JPromptConstants.USER_DIR;
			prompt = JPromptConstants.USER_DIR;
		}
		updatePromptLength();
	}

	private void updatePromptLength() {
		setPromptLength(prompt.length() +1);
	}

	public char getPromptCharacter() {
		return promptCharacter;
	}

	public void setPromptCharacter(char promptCharacter) {
		this.promptCharacter = promptCharacter;
	}

	public int getPromptLength() {
		return promptLength;
	}

	public void setPromptLength(int promptLength) {
		this.promptLength = promptLength;
	}

	public int getEndPromptOffset() {
		return endPromptOffset;
	}

	public void setEndPromptOffset(int endPromptOffset) {
		this.endPromptOffset = endPromptOffset;
	}

	public int getPromptLine() {
		return promptLine;
	}

	public void setPromptLine(int promptLine) {
		this.promptLine = promptLine;
	}

	public boolean isPromptLine(){
		int caretPos = getCaretPosition();
		int lastLine = getRows();
		return false;
	}
	
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if(KeyEvent.VK_ENTER == keyCode){
			
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

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
	public void caretUpdate(CaretEvent e) {
		int line = getLineCount();
		int lastLine = line-1;
		int charCount = getText().length();
		try {
			int end = getLineEndOffset(lastLine);
			int start = getLineStartOffset(lastLine) + getPromptLength();
		} catch (BadLocationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	
	public void rePositionCaret(){
		int row = 0;
    	int currentLine = 0;
    	int currentCol = 0;
    	try {
    		int caretpos = getCaretPosition();
    		currentLine = getLineOfOffset(caretpos);
    		currentCol = caretpos - getLineStartOffset(currentLine);
    		
			int lastLine = getLineCount();
			row = lastLine-1;
			
			if(currentLine != row){
				setCaretPosition(getText().length());
			} else {
				if(currentCol < getPromptLength()){
					setCaretPosition(getText().length());
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	
	public String getCommandLine(){
		int lastLine = getLineCount() - 1;
		if(lastLine < 0){
			return "";
		}
		try {
			int start = getLineStartOffset(lastLine) + getPromptLength();
			int end = getLineEndOffset(lastLine);
			String text = getText(start, end - start);
			return text;
		} catch (BadLocationException e) {
			e.printStackTrace();
			
		}
		return "";
	}
	
	/*----------- Test Method ------------------*/
	public static void main(String args[]) {
		try {
			JFrame frame = new JFrame("Navigation Filter Example");

			ConsoleTextArea cep = new ConsoleTextArea(frame, "d:\\");
			cep.setRows(20);
			cep.setColumns(133);
			cep.setPreferredSize(new Dimension(280, 150));
			cep.setMinimumSize(cep.getPreferredSize());
			JScrollPane sp = new JScrollPane();
			//sp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			//sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
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

	public void updateEndPromptOffset() {
		int lastLine = getLineCount() - 1;
		if(lastLine < 0){
			return ;
		}
		try {
			int start = getLineStartOffset(lastLine) + getPromptLength();
			endPromptOffset = start;
		} catch (BadLocationException e) {
			e.printStackTrace();
			
		}
	}
}
