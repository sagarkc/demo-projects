/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sf.tools4csv.app;

import java.util.Locale;
import org.apache.log4j.Logger;


/**
 *
 * @author sabuj.das
 */
public class ResourceBundleManager {
    
    private static ResourceBundleManager bundleManager;
    private static Logger logger = Logger.getLogger(ResourceBundleManager.class);
    
    private Locale defaultLocale;
    private Locale currentLocale;
    
	
    private ResourceBundleManager() {
        defaultLocale = Locale.getDefault();
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

    

}
