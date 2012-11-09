/**
 * 
 */
package net.sf.bagh.bandhi;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import net.sf.bagh.bandhi.app.board.BoardBasePanel;
import net.sf.bagh.bandhi.app.board.UiBox;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public class BaghBandhiKhela {

	public static final int FRAME_WIDTH = 820;
	public static final int FRAME_HEIGHT = 730;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new TestFrame().setVisible(true);

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