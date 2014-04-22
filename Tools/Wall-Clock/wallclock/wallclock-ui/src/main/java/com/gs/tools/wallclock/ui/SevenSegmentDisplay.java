/**
 * 
 */
package com.gs.tools.wallclock.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.JPanel;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public class SevenSegmentDisplay extends JPanel{

	protected static final int MIN_WIDTH 		= 28;
	protected static final int MIN_HEIGHT 		= 32;
	protected static final int MIN_VALUE 		= 0;
	protected static final int MAX_VALUE 		= 9;
	protected static final int SEGMENT_COUNT 	= 7;
	
	private enum Alignment{ H, V };
	
	private final String displayId;

	private int value = MIN_VALUE;
	
	private Color foregroundColor = Color.decode("0x64E320");
	private Color backgroundColor = Color.decode("0x181913");
	private boolean dotVisible 	= false;
	
	private int displayWidth 	= 80;
	private int displayHeight 	= 100;
	
	
	
	private final Map<Integer, Boolean[]> DISPLAY_SEGMENT_MAP 
		= new LinkedHashMap<Integer, Boolean[]>();
	
	/**
	 * @param displayId
	 */
	public SevenSegmentDisplay(String displayId) {
		super();
		this.displayId = displayId;
		setDoubleBuffered(true);
		setMinimumSize(new Dimension(MIN_WIDTH, MIN_HEIGHT));
		setPreferredSize(new Dimension(displayWidth, displayHeight));
		for (int i = MIN_VALUE; i < MAX_VALUE; i++) {
			DISPLAY_SEGMENT_MAP.put(new Integer(i), decode(i));
		}
	}
	
	private Boolean[] decode(int x){
		final Boolean[] segments = new Boolean[]
				{true, true, true, true, true, true, true};
		
		switch(x){
		case 0:
			segments[6] = false;
			break;
		case 1:
			segments[0] = false;
			segments[3] = false;
			segments[4] = false;
			segments[5] = false;
			segments[6] = false;
			break;
		case 2:
			segments[2] = false;
			segments[5] = false;
			break;
		case 3:
			segments[4] = false;
			segments[5] = false;
			break;
		case 4:
			segments[0] = false;
			segments[3] = false;
			segments[4] = false;
			break;
		case 5:
			segments[1] = false;
			segments[4] = false;
			break;
		case 6:
			segments[1] = false;
			break;
		case 7:
			segments[3] = false;
			segments[4] = false;
			segments[5] = false;
			segments[6] = false;
			break;
		case 8:
			break;
		case 9:
			segments[6] = false;
			break;
		default:
			break;
		}
		
		return segments;
	}
	
	/* (non-Javadoc)
	 * @see java.awt.Component#setSize(int, int)
	 */
	@Override
	public void setSize(int width, int height) {
		if(width >= MIN_WIDTH && height >= MIN_HEIGHT){
			super.setSize(width, height);
		}
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.JComponent#setPreferredSize(java.awt.Dimension)
	 
	@Override
	public void setPreferredSize(Dimension d) {
		if(d.width >= MIN_WIDTH && d.height >= MIN_HEIGHT)
			super.setSize(d);
	}*/
	
	/* (non-Javadoc)
	 * @see java.awt.Component#setSize(java.awt.Dimension)
	 */
	@Override
	public void setSize(Dimension d) {
		if(d.width >= MIN_WIDTH && d.height >= MIN_HEIGHT)
			super.setSize(d);
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paint(java.awt.Graphics)
	 */
	@Override
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		g2.setColor(backgroundColor);
		g2.fillRect(0, 0, getWidth(), getHeight());
		
		paintSegments(g2);
	}
	
	
	private void paintSegments(Graphics2D g2){
		
	}
	
	/**
	 * @return the value
	 */
	public int getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(int value) {
		this.value = value;
	}

	/**
	 * @return the foregroundColor
	 */
	public Color getForegroundColor() {
		return foregroundColor;
	}

	/**
	 * @param foregroundColor the foregroundColor to set
	 */
	public void setForegroundColor(Color foregroundColor) {
		this.foregroundColor = foregroundColor;
	}

	/**
	 * @return the backgroundColor
	 */
	public Color getBackgroundColor() {
		return backgroundColor;
	}

	/**
	 * @param backgroundColor the backgroundColor to set
	 */
	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	/**
	 * @return the dotVisible
	 */
	public boolean isDotVisible() {
		return dotVisible;
	}

	/**
	 * @param dotVisible the dotVisible to set
	 */
	public void setDotVisible(boolean dotVisible) {
		this.dotVisible = dotVisible;
	}

	/**
	 * @return the displayWidth
	 */
	public int getDisplayWidth() {
		return displayWidth;
	}

	/**
	 * @param displayWidth the displayWidth to set
	 */
	public void setDisplayWidth(int displayWidth) {
		this.displayWidth = displayWidth;
	}

	/**
	 * @return the displayHeight
	 */
	public int getDisplayHeight() {
		return displayHeight;
	}

	/**
	 * @param displayHeight the displayHeight to set
	 */
	public void setDisplayHeight(int displayHeight) {
		this.displayHeight = displayHeight;
	}

	/**
	 * @return the displayId
	 */
	public String getDisplayId() {
		return displayId;
	}
	
	
	
	private final class Segment{
		
		private Alignment alignment = Alignment.H;
		
		private final String segmentCode;
		private final Graphics2D graphics;
		private boolean visible;
		
		/**
		 * @param segmentCode
		 */
		public Segment(String segmentCode, Graphics2D g) {
			this.segmentCode = segmentCode;
			this.graphics = g;
		}

		/**
		 * @return the alignment
		 */
		public Alignment getAlignment() {
			return alignment;
		}

		/**
		 * @param alignment the alignment to set
		 */
		public void setAlignment(Alignment alignment) {
			this.alignment = alignment;
		}

		/**
		 * @return the visible
		 */
		public boolean isVisible() {
			return visible;
		}

		/**
		 * @param visible the visible to set
		 */
		public void setVisible(boolean visible) {
			this.visible = visible;
		}

		/**
		 * @return the segmentCode
		 */
		public String getSegmentCode() {
			return segmentCode;
		}

		/**
		 * @return the graphics
		 */
		public Graphics2D getGraphics() {
			return graphics;
		}
		
		void paint(){
			
		}
	}
	
}
