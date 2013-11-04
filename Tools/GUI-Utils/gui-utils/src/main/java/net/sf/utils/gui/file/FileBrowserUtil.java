/**
 * 
 */
package net.sf.utils.gui.file;

import java.awt.Component;
import java.io.File;

import javax.swing.JFileChooser;
import net.sf.utils.gui.StringUtil;


/**
 * @author sabuj.das
 * 
 */
public class FileBrowserUtil {

	public static File openSingleFile(Component parent,
			ExtensionFileFilter fileFilter, Boolean isDir) {
		return openSingleFile(parent, fileFilter, isDir, ".");
	}

	public static File openSingleFile(Component parent,
			ExtensionFileFilter fileFilter, Boolean isDir, String initialDir) {
		File file = null;
		
		if(!StringUtil.hasValidContent(initialDir)){
			initialDir = ".";
		}
		
		JFileChooser chooser = new JFileChooser(initialDir);
		if (fileFilter != null)
			chooser.setFileFilter(fileFilter);
		chooser.setMultiSelectionEnabled(false);
		if (isDir != null) {
			if (Boolean.TRUE == isDir)
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			else
				chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		} else {
			chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		}

		int option = chooser.showOpenDialog(parent);
		if (JFileChooser.APPROVE_OPTION == option) {
			file = chooser.getSelectedFile();
		}

		return file;
	}

	public static File[] openMultipleFile(Component parent,
			ExtensionFileFilter fileFilter, Boolean isDir){
		return openMultipleFile(parent, fileFilter, isDir, ".");
	}
	
	public static File[] openMultipleFile(Component parent,
			ExtensionFileFilter fileFilter, Boolean isDir, String initialDir) {
		File[] files = null;

		if(!StringUtil.hasValidContent(initialDir)){
			initialDir = ".";
		}
		
		JFileChooser chooser = new JFileChooser(initialDir);
		if (fileFilter != null)
			chooser.setFileFilter(fileFilter);
		chooser.setMultiSelectionEnabled(true);
		if (isDir != null) {
			if (Boolean.TRUE == isDir)
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			else
				chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		} else {
			chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		}

		int option = chooser.showOpenDialog(parent);
		if (JFileChooser.APPROVE_OPTION == option) {
			files = chooser.getSelectedFiles();
		}

		return files;
	}

	public static File browseToSaveFile(Component parent, String path,
			ExtensionFileFilter fileFilter, Boolean isDir){
		return browseToSaveFile(parent, path, fileFilter, isDir, ".");
	}
	
	public static File browseToSaveFile(Component parent, String path,
			ExtensionFileFilter fileFilter, Boolean isDir, String initialDir) {
		File file = null;
		if(!StringUtil.hasValidContent(initialDir)){
			initialDir = ".";
		}
		JFileChooser chooser = null;
		if (StringUtil.hasValidContent(path)) {
			chooser = new JFileChooser(path);
		} else {
			chooser = new JFileChooser(".");
		}
		if (fileFilter != null)
			chooser.setFileFilter(fileFilter);
		chooser.setMultiSelectionEnabled(false);
		if (isDir != null) {
			if (Boolean.TRUE == isDir)
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			else
				chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		} else {
			chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		}

		int option = chooser.showSaveDialog(parent);
		if (JFileChooser.APPROVE_OPTION == option) {
			file = chooser.getSelectedFile();
		}

		return file;
	}
}
