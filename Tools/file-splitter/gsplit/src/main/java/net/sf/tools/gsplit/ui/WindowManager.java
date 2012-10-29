
package net.sf.tools.gsplit.ui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

/**
 *
 * @author Sabuj Das | sabuj.das@gmail.com
 */
public final class WindowManager implements InternalFrameListener{
    
    protected static final String SECURE_SPLIT_WINDOW_TITLE = "Secure Split";
    
    private static WindowManager manager;
    private WindowManager(){
        internalFrameMap = new HashMap<String, JInternalFrame>();
        hiddenFrameMap = new LinkedHashMap<String, JInternalFrame> ();
    }

    public static WindowManager getManager() {
        if(null != manager){
            return manager;
        }
        synchronized(WindowManager.class){
            manager = new WindowManager();
        }
        return manager;
    }
    
    private Map<String, JInternalFrame> internalFrameMap;
    private Map<String, JInternalFrame> hiddenFrameMap;
    public JLabel windowManagerLabel;
    public JDesktopPane baseDesktopPane;
    public JPopupMenu windowManagerPopupMenu;
    
    protected void addIFrame(JInternalFrame internalFrame){
        if(internalFrameMap.containsKey(internalFrame.getTitle())){
            showFrame(internalFrame);
            return;
        }
        
        baseDesktopPane.add(internalFrame);
        internalFrameMap.put(internalFrame.getTitle(), internalFrame);
        
        
        internalFrame.setVisible(true);
        try {
            internalFrame.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {
        }
    }

    @Override
    public void internalFrameOpened(InternalFrameEvent e) {
        
    }

    @Override
    public void internalFrameClosing(InternalFrameEvent e) {
        
    }

    @Override
    public void internalFrameClosed(InternalFrameEvent e) {
        JInternalFrame iFrame = e.getInternalFrame();
        if(null != iFrame){
            internalFrameMap.remove(iFrame.getTitle());
            hiddenFrameMap.remove(iFrame.getTitle());
            removePopupMenu(iFrame.getTitle());
        }
    }

    @Override
    public void internalFrameIconified(InternalFrameEvent e) {
        JInternalFrame iFrame = e.getInternalFrame();
        if(null != iFrame){
            iFrame.hide();
            hiddenFrameMap.put(iFrame.getTitle(), iFrame);
            addPopupMenus(iFrame);
        }
    }

    @Override
    public void internalFrameDeiconified(InternalFrameEvent e) {
        JInternalFrame iFrame = e.getInternalFrame();
        if(null != iFrame){
            iFrame.setVisible(true);
            hiddenFrameMap.remove(iFrame.getTitle());
            removePopupMenu(iFrame.getTitle());
        }
    }

    @Override
    public void internalFrameActivated(InternalFrameEvent e) {
        
    }

    @Override
    public void internalFrameDeactivated(InternalFrameEvent e) {
        
    }

    private void addPopupMenus(final JInternalFrame iFrame) {
        for (Map.Entry<String, JInternalFrame> entry : hiddenFrameMap.entrySet()) {
            JInternalFrame internalFrame = entry.getValue();
            if(null != internalFrame){
                JMenuItem menuItem = new JMenuItem(internalFrame.getTitle());
                menuItem.setIcon(internalFrame.getFrameIcon());
                menuItem.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(iFrame.isIcon()){
                            iFrame.show();
                            baseDesktopPane.getDesktopManager().deiconifyFrame(iFrame);
                            iFrame.updateUI();
                        }
                        baseDesktopPane.moveToFront(iFrame);
                        hiddenFrameMap.remove(iFrame.getTitle());
                        removePopupMenu(iFrame.getTitle());
                    }
                });
                windowManagerPopupMenu.add(menuItem);
                windowManagerPopupMenu.updateUI();
            }
        }
        if(hiddenFrameMap.size() > 0){
            windowManagerLabel.setEnabled(true);
        } else {
            windowManagerLabel.setEnabled(false);
        }
    }

    private void removePopupMenu(String title) {
        Component[] components = windowManagerPopupMenu.getComponents();
        if(null != components && components.length > 0){
            for (int i = 0; i < components.length; i++) {
                Component component = components[i];
                if(component instanceof JMenuItem){
                    JMenuItem menuItem = (JMenuItem) component;
                    if(title.equals(menuItem.getText())){
                        windowManagerPopupMenu.remove(component);
                    }
                }
            }
        }
        windowManagerPopupMenu.updateUI();
        if(hiddenFrameMap.size() > 0){
            windowManagerLabel.setEnabled(true);
        } else {
            windowManagerLabel.setEnabled(false);
        }
    }

    protected boolean containsFrame(String frameTitle) {
        return internalFrameMap.containsKey(frameTitle);
    }

    protected void showFrame(String frameTitle) {
        showFrame(internalFrameMap.get(frameTitle));
    }

    private void showFrame(JInternalFrame internalFrame) {
        if(null == internalFrame)
            return;
        if (internalFrame.isIcon()) {
            internalFrame.show();
            baseDesktopPane.getDesktopManager().deiconifyFrame(internalFrame);
            
            baseDesktopPane.moveToFront(internalFrame);
            hiddenFrameMap.remove(internalFrame.getTitle());
            removePopupMenu(internalFrame.getTitle());
        } else {
            baseDesktopPane.moveToFront(internalFrame);
            try {
                internalFrame.setSelected(true);
            } catch (PropertyVetoException ex) {
                
            }
        }
        
    }
    
}
