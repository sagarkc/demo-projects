/**
 * 
 */
package net.sf.bagh.bandhi;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

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
	
	/**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
    	
    	loadStartupData();
    	
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
        	try {
				UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
			} catch (Exception e) {
				
			}
        } 
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GameFrame().setVisible(true);
            }
        });
    }

	private static void loadStartupData() {
		
	}

}

class TestFrame extends JFrame{
	 
	/**
	 * 
	 */
	public TestFrame() {
		setTitle("Catch the tiger");
		setResizable(false);
		setMaximumSize(new Dimension(BaghBandhiKhela.FRAME_WIDTH, BaghBandhiKhela.FRAME_HEIGHT));
		setMinimumSize(new Dimension(BaghBandhiKhela.FRAME_WIDTH, BaghBandhiKhela.FRAME_HEIGHT));
		setPreferredSize(new Dimension(BaghBandhiKhela.FRAME_WIDTH, BaghBandhiKhela.FRAME_HEIGHT));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(new Point(200,200));
		setSize(BaghBandhiKhela.FRAME_WIDTH, BaghBandhiKhela.FRAME_HEIGHT);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(new BoardBasePanel(), BorderLayout.CENTER);
		pack();
	}
}