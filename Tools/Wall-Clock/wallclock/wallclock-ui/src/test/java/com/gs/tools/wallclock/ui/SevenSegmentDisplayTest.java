package com.gs.tools.wallclock.ui;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JFrame;


public class SevenSegmentDisplayTest 
{

	public static void main(String[] args) {
		testDisplay();
	}
	
	public static void testDisplay()
    {
        SevenSegmentDisplay display = new SevenSegmentDisplay("D1");
        display.setValue(9);
        display.setPreferredSize(new Dimension(30, 90));
        
        JFrame frame = new JFrame("7-Segment Test");
        frame.getContentPane().setLayout(new FlowLayout());
        
        frame.getContentPane().add(display);
        
        frame.setSize(new Dimension(300, 200));
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(100, 100);
        
        frame.setVisible(true);
    }
}
