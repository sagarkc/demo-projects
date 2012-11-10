/**
 * 
 */
package net.sf.bagh.bandhi;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import net.sf.bagh.bandhi.app.GameFrame;

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
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GameFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GameFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GameFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GameFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GameFrame().setVisible(true);
            }
        });
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