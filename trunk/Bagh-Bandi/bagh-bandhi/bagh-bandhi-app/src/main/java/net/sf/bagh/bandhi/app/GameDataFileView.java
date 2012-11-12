/**
 * 
 */
package net.sf.bagh.bandhi.app;

import java.io.File;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.filechooser.FileView;


/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public class GameDataFileView extends FileView {

	private static final ImageIcon ICON_MDAT =
			new ImageIcon(GameDataFileView.class.getResource("/images/hint_bulb.png"));

	/* (non-Javadoc)
	 * @see javax.swing.filechooser.FileView#getIcon(java.io.File)
	 */
	@Override
	public Icon getIcon(File f) {
		if(f.getName().toLowerCase().endsWith("bbk".toLowerCase())){
			return ICON_MDAT;
		}
		return super.getIcon(f);
	}
	
	
}
