/**
 * 
 */
package net.sf.bagh.bandhi.app;

import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public enum TigerImage {

	EXTRS_SHORT(BoardSize.EXTRS_SHORT, "");
	
	private final BoardSize sizeFactor;
	private final String imageFileName;
	private Image image;
	
	/**
	 * @param sizeFactor
	 * @param imageFileName
	 */
	private TigerImage(BoardSize sizeFactor, String imageFileName) {
		this.sizeFactor = sizeFactor;
		this.imageFileName = imageFileName;
		this.image = new ImageIcon(
				getClass().getResource("/images/" + imageFileName)
			).getImage();
	}

	/**
	 * @return the sizeFactor
	 */
	public BoardSize getSizeFactor() {
		return sizeFactor;
	}

	/**
	 * @return the imageFileName
	 */
	public String getImageFileName() {
		return imageFileName;
	}
	
	public Image getImage(){
		return image;
	}
}
