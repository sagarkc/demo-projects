package com.gs.captuarix.app;

import com.gs.captuarix.common.CaptureContext;
import com.gs.captuarix.model.Screen;
import com.sun.awt.AWTUtilities;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 *
 * @author Sabuj Das | sabuj.das@gmail.com
 */
public class CaptureWindow
		extends ResizableWindow{

	private Frame parentFrame;
	private Screen screen = Screen.defaultScreen;
	
	public CaptureWindow(Frame parentFrame) {
		this(parentFrame, Screen.defaultScreen);
	}
	
	public CaptureWindow(Frame parentFrame, Screen screen) {
		this.parentFrame = parentFrame;
		parentFrame.setEnabled(false);
		if(null == screen){
			setSize(Screen.defaultScreen.getSize());
			this.screen = Screen.defaultScreen;
		}
		else{ 
			this.screen = screen;
			setSize(screen.getSize());
		}
		setPreferredSize(getSize());
		setLocation(screen.getLocation());
		initComponents();
	}



	private void initComponents() {
		setAlwaysOnTop(true);
		getContentPane().setLayout(new BorderLayout());
		setBackground(Color.BLUE);

		if (AWTUtilities.isTranslucencySupported(AWTUtilities.Translucency.TRANSLUCENT)) {
			AWTUtilities.setWindowOpacity(this, 0.25F);
		}
		
		JPanel innerPanel = new JPanel();
		innerPanel.setBackground(Color.LIGHT_GRAY);
		innerPanel.setBorder(new LineBorder(Color.BLACK, 2));

		innerPanel.setLayout(new FlowLayout(FlowLayout.TRAILING, 4, 4));
		
		JButton selectButton = new JButton("Select");
		selectButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Screen s = new Screen(getLocation(), getSize());
				CaptureContext.getContext().currentScreen =  s;
				CaptureWindow.this.dispose();
				parentFrame.setEnabled(true);
			}
		});
		innerPanel.add(selectButton);
		
		getContentPane().add(innerPanel, BorderLayout.CENTER);
	}
	
	

	
}
