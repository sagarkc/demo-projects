/**
 * 
 */
package net.sf.utils.gui;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public class ResourceBundleManager {
	private static ResourceBundleManager bundleManager;
    
    private Locale defaultLocale;
    private Locale currentLocale;
    
	
    private ResourceBundleManager() {
        defaultLocale = Locale.ENGLISH;
        currentLocale = defaultLocale;
    }

    public static ResourceBundleManager getBundleManager() {
        if(null != bundleManager)
            return bundleManager;
        
        synchronized (ResourceBundleManager.class) {
            if (null == bundleManager) {
                bundleManager = new ResourceBundleManager();
            }
        }
        return bundleManager;
    }

    public Locale getDefaultLocale() {
        return defaultLocale;
    }

    public void setDefaultLocale(Locale defaultLocale) {
        this.defaultLocale = defaultLocale;
    }

    public Locale getCurrentLocale() {
        return currentLocale;
    }

    public void setCurrentLocale(Locale currentLocale) {
        this.currentLocale = currentLocale;
    }
    
    public ResourceBundle getResourceBundle(){
		return ResourceBundle.getBundle("i18n/message", getCurrentLocale());
	}
}
