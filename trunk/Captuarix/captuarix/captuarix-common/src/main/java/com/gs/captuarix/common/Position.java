/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gs.captuarix.common;

import java.awt.Point;
import java.awt.Rectangle;

/**
 *
 * @author 50120C1509
 */
public class Position {
	
	public static enum POSITION{N, S, E, W, NW, NE, SW, SE, OUT, IN, UNKNOWN}
	
	public static POSITION findPosition(Point point, Rectangle rectangle, int d){
		if(null == rectangle || null == point)
			return POSITION.UNKNOWN;
		
		int x = point.x;
		int y = point.y;
		
		int x1 = (int)rectangle.getX();
		int y1 = (int)rectangle.getY();
		
		int x2 = x1 + (int)rectangle.getWidth();
		int y2 = y1 + (int)rectangle.getHeight();
		
		//int w = (int)rectangle.getWidth();
		//int h = (int)rectangle.getHeight();
		
		if((x >= x1 && x <= x1+d) 
				&& (y >= y1 && y <= y1+d))
			return POSITION.NW;
		
		if((x <= x2 && x >= x2-d) 
				&& (y <= y2 && y >= y2-d))
			return POSITION.SE;
		
		if((x >= x2-d && x <= x2) 
				&& (y >= y1 && y <= y1+d))
			return POSITION.NE;
		
		if((x >= x1 && x <= x1+d) 
				&& (y <= y2 && y >= y2-d))
			return POSITION.SW;
		
		if((x <= x1 && x >= x2-d) 
				&& (y <= y2 && y >= y2-d))
			return POSITION.SE;
		
		if(x >= x1 && x <= x2
				&& y >= y1 && y <= y2){
			if((x >= x1 && x <= x2) 
				&& (y >= y1 && y <= y1+d))
				return POSITION.N;
			
			if((x <= x2 && x >= x2-d) 
				&& (y >= y1 && y <= y2))
				return POSITION.E;
			
			if((x >= x1 && x <= x1+d) 
				&& (y >= y1 && y <= y2))
				return POSITION.W;
			
			if((x >= x1 && x <= x2) 
				&& (y >= y2-d && y <= y2))
				return POSITION.S;
			
			return POSITION.IN;
		}
		
		return POSITION.OUT;
	}
	
	public static void main(String []args){
		Rectangle r = new Rectangle(100, 100, 200, 100);
		Point p = new Point(120, 120);
		POSITION ps = findPosition(p, r, 2);
		System.out.println(ps.name());
	}
	
}
