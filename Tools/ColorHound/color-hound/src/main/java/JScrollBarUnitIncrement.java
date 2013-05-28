import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

import com.gs.tools.colorhound.ui.ImagePanel;

public class JScrollBarUnitIncrement {

	public static void main(String[] args) {
		final JFrame f = new JFrame("");
		f.getContentPane().setLayout(new BorderLayout());

		ImagePanel imagePanel = new ImagePanel("D:/SabujDas/SVN_HOME/demo-projects/Tools/ColorHound/sample-img/Water lilies.jpg");
		
		final JScrollPane sPane = new JScrollPane(imagePanel);
		final int increment = 50;
		sPane.getVerticalScrollBar().setUnitIncrement(increment);
		KeyStroke kUp = KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0);
		KeyStroke kDown = KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0);
		sPane.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(
				kUp, "actionWhenKeyUp");
		sPane.getActionMap().put("actionWhenKeyUp",
				new AbstractAction("keyUpAction") {

					private static final long serialVersionUID = 1L;

					public void actionPerformed(ActionEvent e) {
						final JScrollBar bar = sPane.getVerticalScrollBar();
						int currentValue = bar.getValue();
						bar.setValue(currentValue - increment);
					}
				});
		sPane.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(
				kDown, "actionWhenKeyDown");
		sPane.getActionMap().put("actionWhenKeyDown",
				new AbstractAction("keyDownAction") {

					private static final long serialVersionUID = 1L;

					public void actionPerformed(ActionEvent e) {
						final JScrollBar bar = sPane.getVerticalScrollBar();
						int currentValue = bar.getValue();
						bar.setValue(currentValue + increment);
					}
				});
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.getContentPane().add(sPane, BorderLayout.CENTER);
		f.pack();
		SwingUtilities.invokeLater(new Runnable() {

			public void run() {
				f.setVisible(true);
			}
		});
	}

	private JScrollBarUnitIncrement() {
	}
}
