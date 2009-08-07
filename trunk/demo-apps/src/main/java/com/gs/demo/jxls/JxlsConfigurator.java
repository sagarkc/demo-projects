/**
 * 
 */
package com.gs.demo.jxls;

import com.gs.demo.jxls.gui.JxlsConfiguratorFrame;

/**
 * @author sabuj.das
 *
 */
public class JxlsConfigurator {

	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JxlsConfiguratorFrame().setVisible(true);
            }
        });
	}

}
