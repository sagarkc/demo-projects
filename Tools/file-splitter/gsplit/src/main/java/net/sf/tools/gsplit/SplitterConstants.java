/**
 * 
 */
package net.sf.tools.gsplit;

import java.awt.Color;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public interface SplitterConstants {

	int KB = 1024;
	int MB = 1024 * 1024;
	int GB = 1024 * 1024 * 1024;
	String BYTE_TEXT = "BYTE";
	String KB_TEXT = "KB"; 
	String MB_TEXT = "MB"; 
	String GB_TEXT = "GB"; 
	
	
	String PART_EXT = ".part";
	String METADATA_EXT = ".mdat";
	
        Color LINK_BG_COLOR = new Color(153, 204, 255);
        Color LINK_FG_COLOR = Color.BLUE;
}
