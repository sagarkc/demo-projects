package com.gs.jprompt.app.components;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class PromptDocumentFilter extends DocumentFilter {

	private int promptLength;
	private int endPromptOffset;
	private int promptLine;

	public PromptDocumentFilter() {
		// TODO Auto-generated constructor stub
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

	@Override
	public void remove(FilterBypass fb, int offset, int length)
			throws BadLocationException {
		if (offset >= getPromptLength()) {
			super.remove(fb, offset, length);
		}
	}

	@Override
	public void insertString(FilterBypass fb, int offset, String string,
			AttributeSet attr) throws BadLocationException {
		if (offset >= getPromptLength()) {
			super.insertString(fb, offset, string, attr);
		} else {
			super.insertString(fb, getPromptLength(), string, attr);
		}
		
	}

	@Override
	public void replace(FilterBypass fb, int offset, int length, String text,
			AttributeSet attrs) throws BadLocationException {
		if (offset >= getPromptLength()) {
			super.replace(fb, offset, length, text, attrs);
		} else {
			super.replace(fb, getPromptLength(), length, text, attrs);
		}
	}

}
