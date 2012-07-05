package com.gs.javadocwriter.editors;

import org.eclipse.ui.editors.text.TextEditor;

public class JavaDocWriter extends TextEditor {

	private ColorManager colorManager;

	public JavaDocWriter() {
		super();
		colorManager = new ColorManager();
		setSourceViewerConfiguration(new XMLConfiguration(colorManager));
		setDocumentProvider(new XMLDocumentProvider());
	}
	public void dispose() {
		colorManager.dispose();
		super.dispose();
	}

}
