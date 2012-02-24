package com.gs.jprompt.app.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

import org.apache.log4j.Logger;

import com.gs.jprompt.common.CmdHistoryList;
import com.gs.jprompt.common.ConsoleAppearance;
import com.gs.jprompt.common.JPromptConstants;
import com.gs.jprompt.common.JPromptContext;
import com.gs.jprompt.common.SystemInfo;
import com.gs.utils.text.StringUtil;

public class ConsoleTextArea extends JTextArea implements CaretListener,
		MouseListener, KeyListener, FocusListener {

	/**
	 * @author sabuj.das
	 * @MailTo sabuj.das@gmail.com
	 * 
	 */
	private final class ConsoleDocumentFilter extends DocumentFilter {
		@Override
		public void remove(FilterBypass fb, int offset, int length)
				throws BadLocationException {
			if (offset >= getEndPromptOffset()) {
				super.remove(fb, offset, length);
			} else if(offset == 0 && length > 0){
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
	private char promptCharacter = SystemInfo.PROMPT_CHAR;

	private int promptLength;
	private int endPromptOffset;
	private int promptLine;
	private String promptValue;
	private CmdHistoryList<String> commandHistory = new CmdHistoryList<String>();

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
		//addFocusListener(this);
		AbstractDocument abstractDocument = (AbstractDocument) getDocument();
		abstractDocument.setDocumentFilter(
				new ConsoleDocumentFilter()
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
		rePositionCaret();
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.JTextArea#replaceRange(java.lang.String, int, int)
	 */
	@Override
	public void replaceRange(String str, int start, int end) {
		if(start == 0 && end == getEndPromptOffset()-getPromptLength()){
			try {
				getDocument().remove(start, end - start);
		        getDocument().insertString(start, str, null);
			} catch (Exception e) {
				e.printStackTrace();
			}
			updateEndPromptOffset();
		}
		else 
			super.replaceRange(str, start, end);
	}
	
	

	public void clearScreen(){
		try {
			//setSelectionStart(0);
		//	setSelectionEnd(getEndPromptOffset()-getPromptLength());
			//replaceSelection(getSelectedText());
			//this.replaceRange("", 0, getEndPromptOffset() - getPromptLength());
			//getDocument().remove(0, getEndPromptOffset()-getPromptLength());
			
			this.replaceRange("", 0, getEndPromptOffset() - getPromptLength());
			this.replaceRange("", getEndPromptOffset(), getEndPromptOffset() + getCommandLine().length());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//updateUI();
		//setPrompt(getWorkingDirectory());
		setPrompt(getPrompt());
		rePositionCaret();
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
	
	@Override
	public void focusGained(FocusEvent e) {
		
	}

	@Override
	public void focusLost(FocusEvent e) {
		
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
			System.err.println(e.getMessage());
		}
		return "";
	}
	
	/*----------- Test Method ------------------*/
	public static void main(String args[]) {
		try {
			JFrame frame = new JFrame("Navigation Filter Example");

			ConsoleTextArea cep = new ConsoleTextArea(frame, "dfgsdfgsdfgsdfg");
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
	
	public boolean isSelectionEditable(){
		int start = getSelectionStart();
		int end = getSelectionEnd();
		if((start >=0 && end >=start)
				&& (start <= getEndPromptOffset())
				&& (end <= getEndPromptOffset())){
			return false;
		}
		
		return true;
	}

	/**
	 * 
	 */
	public void updateSelection() {
		int start = getSelectionStart();
		int end = getSelectionEnd();
		if((start >=0 && end >=start)
				&& (start <= getEndPromptOffset())
				&& (end <= getEndPromptOffset())){
			setSelectionStart(-1);
			setSelectionEnd(-1);
			return ;
		}
		setSelectionStart(getEndPromptOffset());
		setSelectionEnd(end);
	}

	/**
	 * @param consoleAppearance
	 */
	public void setAppearance(ConsoleAppearance consoleAppearance) {
		if(null == consoleAppearance){
			setFont(context.getDefaultEditorFont());
			setForeground(Color.BLACK);
			setBackground(Color.WHITE);
			return;
		}
		setFont(consoleAppearance.getFont());
		setForeground(consoleAppearance.getFgColor());
		setCaretColor(consoleAppearance.getFgColor());
		setBackground(consoleAppearance.getBgColor());
	}

	/**
	 * @param fileName
	 */
	public void replaceLastWord(String fileName) {
		if(fileName.contains(" ")){
			fileName = "\"" + fileName + "\"";
		}
		String cmd = getCommandLine();
		
		if(fileName.endsWith("\"")){
			replaceRange(fileName, getEndPromptOffset(), getEndPromptOffset() + cmd.length());
			return;
		}
		
		if(cmd.contains("\"")){
			replaceRange(fileName, getEndPromptOffset(), getEndPromptOffset() + cmd.length());
			return;
		}
		
		if(StringUtil.hasValidContent(cmd)){
			int spaceLoc = cmd.lastIndexOf(' ');
			if(spaceLoc > -1){
				replaceRange(fileName, getEndPromptOffset()+spaceLoc+1, getEndPromptOffset() + cmd.length());
			} else {
				replaceRange(fileName, getEndPromptOffset(), getEndPromptOffset() + cmd.length());
			}
		} else {
			replaceRange(fileName, getEndPromptOffset(), getEndPromptOffset() + cmd.length());
		}
	}
	
	

	public CmdHistoryList<String> getCommandHistory() {
		return commandHistory;
	}
	
	
}
