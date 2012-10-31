/**
 *
 */
package net.sf.tools.gsplit;

import java.io.File;
import javax.swing.UIManager;
import net.sf.tools.gsplit.core.SecureFileJoiner;
import net.sf.tools.gsplit.core.SecureFileSplitter;
import net.sf.tools.gsplit.ui.GsplitBaseFrame;
import org.apache.log4j.Logger;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public class GreenSplitter {

    /**
     * @param args
     */
    public static void main(String[] args) {
        SplitterContext.getContext().initContext();
        Logger logger = Logger.getLogger(GreenSplitter.class);
        
        try {
            logger.info("Set LaF to " + SplitterContext.getContext().getAppSettings().getLafClassName());
            UIManager.setLookAndFeel(SplitterContext.getContext().getAppSettings().getLafClassName());
        } catch (Exception e) {
            logger.error(e);
            try {
                logger.info("Set LaF to " + UIManager.getCrossPlatformLookAndFeelClassName());
                UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            } catch (Exception ignore) {
                
            }
        }
        logger.info("Start App");
        //new GSplitFrame().setVisible(true);
        new GsplitBaseFrame().setVisible(true);
    }

    public static void test() {
        SecureFileSplitter fileSplitter = new SecureFileSplitter(
                "D:\\temp\\eclipse.zip",
                "d:\\temp\\eclipseparts");
        SecureFileJoiner fileJoiner = new SecureFileJoiner(
                new File("d:\\temp\\eclipseparts\\eclipse.zip.mdat"),
                new File("d:\\temp\\eclipse-join\\eclipse.zip"));
        try {
            //fileSplitter.splitBySize(50 * SplitterConstants.MB);
            fileJoiner.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
