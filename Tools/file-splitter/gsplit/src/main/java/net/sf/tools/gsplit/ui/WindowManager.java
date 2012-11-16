package net.sf.tools.gsplit.ui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public final class WindowManager implements InternalFrameListener {

    protected static final String SECURE_SPLIT_WINDOW_TITLE = "Secure Split";
    protected static final String SECURE_JOIN_WINDOW_TITLE = "Secure Join";
    protected static final String FLAT_SPLIT_WINDOW_TITLE = "Flat Split";
    protected static final String FLAT_JOIN_WINDOW_TITLE = "Flat Join";
    protected static final String TEXT_SPLIT_WINDOW_TITLE = "Text Split";
    protected static final String TEXT_JOIN_WINDOW_TITLE = "Text Join";
    protected static final String PDF_SPLIT_WINDOW_TITLE = "PDF Split";
    protected static final String PDF_JOIN_WINDOW_TITLE = "PDF Join";
    protected static final String XML_SPLIT_WINDOW_TITLE = "XML Split";
    protected static final String XML_JOIN_WINDOW_TITLE = "XML Join";
    
    protected static final String SHOW_LOG_WINDOW_TITLE = "Show Log";
    protected static final String APP_SETTINGS_WINDOW_TITLE = "Settings";
    
    protected static final String SHOW_HELP_WINDOW_TITLE = "Help";
    
    protected static final int FRAME_DISTANCE = 10;
    protected static final int FRAME_X = 10;
    protected static final int FRAME_Y = 10;
    private static int frameCount = 0;
    private static WindowManager manager;


    private WindowManager() {
        internalFrameMap = new HashMap<String, JInternalFrame>();
        hiddenFrameMap = new LinkedHashMap<String, JInternalFrame>();
    }

    public static WindowManager getManager() {
        if (null != manager) {
            return manager;
        }
        synchronized (WindowManager.class) {
            manager = new WindowManager();
        }
        return manager;
    }
    private Map<String, JInternalFrame> internalFrameMap;
    private Map<String, JInternalFrame> hiddenFrameMap;
    public JLabel windowManagerLabel;
    public JDesktopPane baseDesktopPane;
    public JPopupMenu windowManagerPopupMenu;

    protected void addIFrame(JInternalFrame internalFrame) {
        if (internalFrameMap.containsKey(internalFrame.getTitle())) {
            showFrame(internalFrame);
            return;
        }
        internalFrame.setLocation(FRAME_X + (FRAME_DISTANCE * frameCount),
                FRAME_Y + (FRAME_DISTANCE * frameCount));
        frameCount++;
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
        if (null != iFrame) {
            internalFrameMap.remove(iFrame.getTitle());
            hiddenFrameMap.remove(iFrame.getTitle());
            removePopupMenu(iFrame.getTitle());
        }
        frameCount--;
    }

    @Override
    public void internalFrameIconified(InternalFrameEvent e) {
        JInternalFrame iFrame = e.getInternalFrame();
        if (null != iFrame) {
            iFrame.hide();
            hiddenFrameMap.put(iFrame.getTitle(), iFrame);
            addPopupMenus(iFrame);
        }
    }

    @Override
    public void internalFrameDeiconified(InternalFrameEvent e) {
        JInternalFrame iFrame = e.getInternalFrame();
        if (null != iFrame) {
            iFrame.setVisible(true);
            iFrame.setEnabled(true);
            try {
                iFrame.setSelected(true);
            } catch (PropertyVetoException ex) {
                
            }
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
        if (null != iFrame) {
            JMenuItem menuItem = new JMenuItem(iFrame.getTitle());
            menuItem.setIcon(iFrame.getFrameIcon());
            menuItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (iFrame.isIcon()) {
                        iFrame.show();
                        baseDesktopPane.getDesktopManager().deiconifyFrame(iFrame);
                        baseDesktopPane.getDesktopManager().activateFrame(iFrame);
                        try {
                            iFrame.setSelected(true);
                        } catch (PropertyVetoException ex) {
                            
                        }
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
        if (hiddenFrameMap.size() > 0) {
            windowManagerLabel.setEnabled(true);
        } else {
            windowManagerLabel.setEnabled(false);
        }
    }

    private void removePopupMenu(String title) {
        Component[] components = windowManagerPopupMenu.getComponents();
        if (null != components && components.length > 0) {
            for (int i = 0; i < components.length; i++) {
                Component component = components[i];
                if (component instanceof JMenuItem) {
                    JMenuItem menuItem = (JMenuItem) component;
                    if (title.equals(menuItem.getText())) {
                        windowManagerPopupMenu.remove(component);
                    }
                }
            }
        }
        windowManagerPopupMenu.updateUI();
        if (hiddenFrameMap.size() > 0) {
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
        if (null == internalFrame) {
            return;
        }
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

    public void cascadeWindows(JDesktopPane desktopPane) {
        JInternalFrame[] frames = desktopPane.getAllFrames();
        if (frames.length == 0) {
            return;
        }

        cascadeWindows(frames, desktopPane.getBounds(), 24);
    }

    public void cascadeWindows(JDesktopPane desktopPane, int layer) {
        JInternalFrame[] frames = desktopPane.getAllFramesInLayer(layer);
        if (frames.length == 0) {
            return;
        }

        cascadeWindows(frames, desktopPane.getBounds(), 24);
    }

    private void cascadeWindows(JInternalFrame[] frames, Rectangle dBounds, int separation) {
        int margin = frames.length * separation + separation;
        int width = dBounds.width - margin;
        int height = dBounds.height - margin;
        for (int i = 0; i < frames.length; i++) {
            frames[i].setBounds(separation + dBounds.x + i * separation,
                    separation + dBounds.y + i * separation,
                    width, height);
        }
    }

    public void tileWindows(JDesktopPane desktopPane) {

        // How many frames do we have?
        JInternalFrame[] allframes = desktopPane.getAllFrames();
        int count = allframes.length;
        if (count == 0) {
            return;
        }

        // Determine the necessary grid size
        int sqrt = (int) Math.sqrt(count);
        int rows = sqrt;
        int cols = sqrt;
        if (rows * cols < count) {
            cols++;
            if (rows * cols < count) {
                rows++;
            }
        }

        // Define some initial values for size & location.
        Dimension size = desktopPane.getSize();

        int w = size.width / cols;
        int h = size.height / rows;
        int x = 0;
        int y = 0;

        // Iterate over the frames, deiconifying any iconified frames and then
        // relocating & resizing each.
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols && ((i * cols) + j < count); j++) {
                JInternalFrame f = allframes[(i * cols) + j];

                if (!f.isClosed() && f.isIcon()) {
                    try {
                        f.setIcon(false);
                    } catch (PropertyVetoException ignored) {
                    }
                }

                desktopPane.getDesktopManager().resizeFrame(f, x, y, w, h);
                x += w;
            }
            y += h; // start the next row
            x = 0;
        }
    }

    public void showAllFrames() {
        JInternalFrame[] frames = baseDesktopPane.getAllFrames();
        if (null != frames && frames.length > 0) {
            for (int i = 0; i < frames.length; i++) {
                JInternalFrame iFrame = frames[i];
                if (iFrame.isIcon()) {
                    iFrame.show();
                    baseDesktopPane.getDesktopManager().deiconifyFrame(iFrame);
                    baseDesktopPane.getDesktopManager().activateFrame(iFrame);
                    baseDesktopPane.moveToFront(iFrame);
                    iFrame.updateUI();
                    hiddenFrameMap.remove(iFrame.getTitle());
                    removePopupMenu(iFrame.getTitle());
                    try {
                        iFrame.setSelected(true);
                    } catch (PropertyVetoException ex) {

                    }
                }
            }
        }
    }
}
