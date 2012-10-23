/**
 * 
 */
package net.sf.tools.gsplit;

import java.io.File;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel;

import net.sf.tools.gsplit.core.FileAutoJoiner;
import net.sf.tools.gsplit.core.FileSplitter;
import net.sf.tools.gsplit.ui.GSplitFrame;


/**
 * @author Sabuj Das | sabuj.das@gmail.com
 * 
 */
public class GreenSplitter {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			//UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
			//UIManager.setLookAndFeel(NimbusLookAndFeel.class.getCanonicalName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new GSplitFrame().setVisible(true);
	}
	
	public static void test() {
		FileSplitter fileSplitter = new FileSplitter(
				"D:\\temp\\eclipse.zip", 
				"d:\\temp\\eclipseparts");
		FileAutoJoiner fileJoiner = new FileAutoJoiner(
				new File("d:\\temp\\eclipseparts\\eclipse.zip.mdat"),
				new File("d:\\temp\\eclipse-join\\eclipse.zip")
				);
		try {
			//fileSplitter.splitBySize(50 * SplitterConstants.MB);
			fileJoiner.join();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
