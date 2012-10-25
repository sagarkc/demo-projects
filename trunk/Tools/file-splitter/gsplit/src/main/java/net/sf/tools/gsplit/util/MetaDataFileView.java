/**
 * 
 */
package net.sf.tools.gsplit.util;

import java.io.File;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.filechooser.FileView;

import net.sf.tools.gsplit.SplitterConstants;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public class MetaDataFileView extends FileView {

	private static final ImageIcon ICON_MDAT =
			new ImageIcon(MetaDataFileView.class.getResource("/images/file-splitter_16x16.png"));

	/* (non-Javadoc)
	 * @see javax.swing.filechooser.FileView#getIcon(java.io.File)
	 */
	@Override
	public Icon getIcon(File f) {
		if(f.getName().toLowerCase().endsWith(SplitterConstants.METADATA_EXT.toLowerCase())){
			return ICON_MDAT;
		}
		return super.getIcon(f);
	}
	
	
}
