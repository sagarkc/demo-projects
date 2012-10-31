/**
 *
 */
package net.sf.tools.gsplit;

import com.sun.java.swing.plaf.motif.MotifLookAndFeel;
import com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel;
import com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel;
import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.plaf.metal.MetalLookAndFeel;

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
    String LOG_FILE_NAME = "./logs/gsplit.log";
    String SETTINGS_FILE_NAME = "./.settings/gsplit-settings.conf";
    
    String LAF_SYSTEM = UIManager.getSystemLookAndFeelClassName();
    String LAF_METAL = MetalLookAndFeel.class.getCanonicalName();
    String LAF_MOTIF = MotifLookAndFeel.class.getCanonicalName();
    String LAF_NIMBUS = NimbusLookAndFeel.class.getCanonicalName();
    String LAF_WINDOWS_CLASSIC = WindowsClassicLookAndFeel.class.getCanonicalName();
    String LAF_WINDOWS = WindowsLookAndFeel.class.getCanonicalName();
}
