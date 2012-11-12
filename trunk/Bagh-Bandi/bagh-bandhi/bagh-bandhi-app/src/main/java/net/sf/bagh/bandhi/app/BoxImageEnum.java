/**
 * 
 */
package net.sf.bagh.bandhi.app;

import javax.swing.ImageIcon;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public enum BoxImageEnum {

	GREY("GREY", "box_grey-256x256.png"),
	GREEN("GREEN", "box_green-256x256.png"),
	BLUE("BLUE", "box_blue-256x256.png"),
	ORANGE("ORANGE", "box_orange-256x256.png"),
	RED("RED", "box_red-256x256.png");
	
	private final String boxType;
	private final String imageFileName;
	private ImageIcon imageIcon;
	/**
	 * @param boxType
	 * @param imageFileName
	 */
	private BoxImageEnum(String boxType, String imageFileName) {
		this.boxType = boxType;
		this.imageFileName = imageFileName;
		this.imageIcon = new ImageIcon(getClass().getResource(
				"/images/" + imageFileName));
	}
	/**
	 * @return the boxType
	 */
	public String getBoxType() {
		return boxType;
	}
	/**
	 * @return the imageFileName
	 */
	public String getImageFileName() {
		return imageFileName;
	}
	/**
	 * @return the imageIcon
	 */
	public ImageIcon getImageIcon() {
		return imageIcon;
	}
	
}
