/**
 * 
 */
package net.sf.bagh.bandhi;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Point;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.UIManager;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;

import net.sf.bagh.bandhi.app.GameFrame;
import net.sf.bagh.bandhi.app.board.BoardBasePanel;
import net.sf.bagh.bandhi.core.GameEngine;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 * 
 */
public class BaghBandhiKhela {

	public static final int FRAME_WIDTH = 820;
	public static final int FRAME_HEIGHT = 730;
	private static GameEngine gameEngine = GameEngine.getEngine();
	private static Logger logger = Logger.getLogger(BaghBandhiKhela.class);
	
	public static Font bitstreamFont;
	static {
		try {
			DOMConfigurator.configure("log4j.xml");
			logger = Logger.getLogger(BaghBandhiKhela.class);
			bitstreamFont = Font.createFont(Font.TRUETYPE_FONT,
					BaghBandhiKhela.class
							.getResourceAsStream("/fonts/VeraMoBd.ttf"));
			bitstreamFont = new Font(bitstreamFont.getFontName(),
					java.awt.Font.BOLD, 13);
			if(logger.isDebugEnabled()){
				logger.debug("Font initialization complete ...");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {

		loadStartupData();

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			if(logger.isDebugEnabled()){
				logger.debug("LaF initialization complete ...");
			}
		} catch (Exception ex) {
			logger.error("Could not apply the LaF... ", ex);
			try {
				UIManager.setLookAndFeel(UIManager
						.getCrossPlatformLookAndFeelClassName());
				if(logger.isDebugEnabled()){
					logger.debug("CrossPlatformLookAndFeel applied ...");
				}
			} catch (Exception e) {

			}
		}
		

		GameFrame frame = new GameFrame();
		frame.setVisible(true);
	}

	private static void loadStartupData() {

	}

}

class TestFrame extends JFrame {

	/**
	 * 
	 */
	public TestFrame() {
		setTitle("Catch the tiger");
		setResizable(false);
		setMaximumSize(new Dimension(BaghBandhiKhela.FRAME_WIDTH,
				BaghBandhiKhela.FRAME_HEIGHT));
		setMinimumSize(new Dimension(BaghBandhiKhela.FRAME_WIDTH,
				BaghBandhiKhela.FRAME_HEIGHT));
		setPreferredSize(new Dimension(BaghBandhiKhela.FRAME_WIDTH,
				BaghBandhiKhela.FRAME_HEIGHT));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(new Point(200, 200));
		setSize(BaghBandhiKhela.FRAME_WIDTH, BaghBandhiKhela.FRAME_HEIGHT);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(new BoardBasePanel(), BorderLayout.CENTER);
		pack();
	}
}