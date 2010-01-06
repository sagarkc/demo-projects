/** ***************************************************************************
 *		MetalOfficeLnF :: Office Look and Feel from Metal	
 *	
 *	File	: ColorReference.java
 *	Type	: com.gs.plaf.common.ColorReference.java
 *	Date	: Jan 3, 2010	10:50:41 PM
 *
 *	Author	: Sabuj Das
 *
 *	
 *****************************************************************************/
package com.gs.plaf.common;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import javax.swing.AbstractButton;
import javax.swing.Icon;
import javax.swing.plaf.ColorUIResource;

/**
 *
 */
public class ColorReference {
	public static final int ABS_COLOR = 1;
	public static final int MAIN_COLOR = 2;
	public static final int BACK_COLOR = 3;
	public static final int DIS_COLOR = 4;
	public static final int FRAME_COLOR = 5;
	public static final int SUB1_COLOR = 6;
	public static final int SUB2_COLOR = 7;
	public static final int SUB3_COLOR = 8;
	public static final int SUB4_COLOR = 9;
	public static final int SUB5_COLOR = 10;
	public static final int SUB6_COLOR = 11;
	public static final int SUB7_COLOR = 12;
	public static final int SUB8_COLOR = 13;
	protected ColorUIResource c;
	protected int sat;
	protected int bri;
	protected int ref;
	protected boolean locked;
	protected ColorIcon icon;
	protected static ColorIcon absolueIcon;

	public ColorReference() {
	}

	public ColorReference(Color c) {
		this.c = new ColorUIResource(c);
		this.sat = 0;
		this.bri = 0;
		this.ref = 1;
	}

	public ColorReference(Color c, int sat, int bri, int ref) {
		this.c = new ColorUIResource(c);
		this.sat = sat;
		this.bri = bri;
		this.ref = ref;
	}

	public ColorReference(Color c, int sat, int bri, int ref, boolean locked) {
		this.c = new ColorUIResource(c);
		this.sat = sat;
		this.bri = bri;
		this.ref = ref;
		this.locked = locked;
	}

	public ColorReference(ColorReference other) {
		this.c = other.c;
		this.sat = other.sat;
		this.bri = other.bri;
		this.ref = other.ref;
	}

	public ColorReference(ColorReference other, int ref) {
		this.c = other.c;
		this.sat = 0;
		this.bri = 0;
		this.ref = ref;
	}

	public void reset() {
		this.sat = 0;
		this.bri = 0;
	}

	public ColorUIResource getColor() {
		return this.c;
	}

	public int getSaturation() {
		return this.sat;
	}

	public int getBrightness() {
		return this.bri;
	}

	public int getReference() {
		return this.ref;
	}

	public ColorUIResource getReferenceColor() {
		switch (this.ref) {
		case 2:
			return Theme.mainColor[Theme.style].getColor();
		case 3:
			return Theme.backColor[Theme.style].getColor();
		case 4:
			return Theme.disColor[Theme.style].getColor();
		case 5:
			return Theme.frameColor[Theme.style].getColor();
		case 6:
			return Theme.sub1Color[Theme.style].getColor();
		case 7:
			return Theme.sub2Color[Theme.style].getColor();
		case 8:
			return Theme.sub3Color[Theme.style].getColor();
		case 9:
			return Theme.sub4Color[Theme.style].getColor();
		case 10:
			return Theme.sub5Color[Theme.style].getColor();
		case 11:
			return Theme.sub6Color[Theme.style].getColor();
		case 12:
			return Theme.sub7Color[Theme.style].getColor();
		case 13:
			return Theme.sub8Color[Theme.style].getColor();
		}
		return this.c;
	}

	public static ColorUIResource getReferenceColor(int ref) {
		switch (ref) {
		case 2:
			return Theme.mainColor[Theme.style].getColor();
		case 3:
			return Theme.backColor[Theme.style].getColor();
		case 4:
			return Theme.disColor[Theme.style].getColor();
		case 5:
			return Theme.frameColor[Theme.style].getColor();
		case 6:
			return Theme.sub1Color[Theme.style].getColor();
		case 7:
			return Theme.sub2Color[Theme.style].getColor();
		case 8:
			return Theme.sub3Color[Theme.style].getColor();
		case 9:
			return Theme.sub4Color[Theme.style].getColor();
		case 10:
			return Theme.sub5Color[Theme.style].getColor();
		case 11:
			return Theme.sub6Color[Theme.style].getColor();
		case 12:
			return Theme.sub7Color[Theme.style].getColor();
		case 13:
			return Theme.sub8Color[Theme.style].getColor();
		}
		return null;
	}

	public String getReferenceString() {
		switch (this.ref) {
		case 2:
			return "Main Color";
		case 3:
			return "Back Color";
		case 4:
			return "Disabled Color";
		case 5:
			return "Frame Color";
		case 6:
			return "Sub1 Color";
		case 7:
			return "Sub2 Color";
		case 8:
			return "Sub3 Color";
		case 9:
			return "Sub4 Color";
		case 10:
			return "Sub5 Color";
		case 11:
			return "Sub6 Color";
		case 12:
			return "Sub7 Color";
		case 13:
			return "Sub8 Color";
		}
		return "";
	}

	public void setColor(Color newColor) {
		if (!(isAbsoluteColor()))
			return;

		this.c = new ColorUIResource(newColor);
	}

	public void setSaturation(int newSat) {
		this.sat = newSat;
	}

	public void setBrightness(int newBri) {
		this.bri = newBri;
	}

	public void setReference(int newRef) {
		this.ref = newRef;
	}

	public void setColor(int sat, int bri) {
		if (isAbsoluteColor())
			return;

		this.sat = sat;
		this.bri = bri;

		switch (this.ref) {
		case 2:
			this.c = new ColorUIResource(SBChooser.getAdjustedColor(
					Theme.mainColor[Theme.style].getColor(), sat, bri));
			break;
		case 3:
			this.c = new ColorUIResource(SBChooser.getAdjustedColor(
					Theme.backColor[Theme.style].getColor(), sat, bri));
			break;
		case 4:
			this.c = new ColorUIResource(SBChooser.getAdjustedColor(
					Theme.disColor[Theme.style].getColor(), sat, bri));
			break;
		case 5:
			this.c = new ColorUIResource(SBChooser.getAdjustedColor(
					Theme.frameColor[Theme.style].getColor(), sat, bri));
			break;
		case 6:
			this.c = new ColorUIResource(SBChooser.getAdjustedColor(
					Theme.sub1Color[Theme.style].getColor(), sat, bri));
			break;
		case 7:
			this.c = new ColorUIResource(SBChooser.getAdjustedColor(
					Theme.sub2Color[Theme.style].getColor(), sat, bri));
			break;
		case 8:
			this.c = new ColorUIResource(SBChooser.getAdjustedColor(
					Theme.sub3Color[Theme.style].getColor(), sat, bri));
			break;
		case 9:
			this.c = new ColorUIResource(SBChooser.getAdjustedColor(
					Theme.sub4Color[Theme.style].getColor(), sat, bri));
			break;
		case 10:
			this.c = new ColorUIResource(SBChooser.getAdjustedColor(
					Theme.sub5Color[Theme.style].getColor(), sat, bri));
			break;
		case 11:
			this.c = new ColorUIResource(SBChooser.getAdjustedColor(
					Theme.sub6Color[Theme.style].getColor(), sat, bri));
			break;
		case 12:
			this.c = new ColorUIResource(SBChooser.getAdjustedColor(
					Theme.sub7Color[Theme.style].getColor(), sat, bri));
			break;
		case 13:
			this.c = new ColorUIResource(SBChooser.getAdjustedColor(
					Theme.sub8Color[Theme.style].getColor(), sat, bri));
		}
	}

	public ColorUIResource update() {
		if (isAbsoluteColor())
			return this.c;

		switch (this.ref) {
		case 2:
			this.c = new ColorUIResource(SBChooser
					.getAdjustedColor(Theme.mainColor[Theme.style].getColor(),
							this.sat, this.bri));
			break;
		case 3:
			this.c = new ColorUIResource(SBChooser
					.getAdjustedColor(Theme.backColor[Theme.style].getColor(),
							this.sat, this.bri));
			break;
		case 4:
			this.c = new ColorUIResource(SBChooser.getAdjustedColor(
					Theme.disColor[Theme.style].getColor(), this.sat, this.bri));
			break;
		case 5:
			this.c = new ColorUIResource(SBChooser.getAdjustedColor(
					Theme.frameColor[Theme.style].getColor(), this.sat,
					this.bri));
			break;
		case 6:
			this.c = new ColorUIResource(SBChooser
					.getAdjustedColor(Theme.sub1Color[Theme.style].getColor(),
							this.sat, this.bri));
			break;
		case 7:
			this.c = new ColorUIResource(SBChooser
					.getAdjustedColor(Theme.sub2Color[Theme.style].getColor(),
							this.sat, this.bri));
			break;
		case 8:
			this.c = new ColorUIResource(SBChooser
					.getAdjustedColor(Theme.sub3Color[Theme.style].getColor(),
							this.sat, this.bri));
			break;
		case 9:
			this.c = new ColorUIResource(SBChooser
					.getAdjustedColor(Theme.sub4Color[Theme.style].getColor(),
							this.sat, this.bri));
			break;
		case 10:
			this.c = new ColorUIResource(SBChooser
					.getAdjustedColor(Theme.sub5Color[Theme.style].getColor(),
							this.sat, this.bri));
			break;
		case 11:
			this.c = new ColorUIResource(SBChooser
					.getAdjustedColor(Theme.sub6Color[Theme.style].getColor(),
							this.sat, this.bri));
			break;
		case 12:
			this.c = new ColorUIResource(SBChooser
					.getAdjustedColor(Theme.sub7Color[Theme.style].getColor(),
							this.sat, this.bri));
			break;
		case 13:
			this.c = new ColorUIResource(SBChooser
					.getAdjustedColor(Theme.sub8Color[Theme.style].getColor(),
							this.sat, this.bri));
		}

		return this.c;
	}

	public boolean isAbsoluteColor() {
		return (this.ref == 1);
	}

	public void setLocked(boolean newLocked) {
		this.locked = newLocked;
	}

	public boolean isLocked() {
		return this.locked;
	}

	public String toString() {
		return this.c.toString();
	}

	public Icon getIcon() {
		if (this.icon == null) {
			this.icon = new ColorIcon(false);
		}

		return this.icon;
	}

	public static Icon getAbsoluteIcon() {
		if (absolueIcon == null) {
			tmp20_13 = new ColorReference(Color.BLACK);
			tmp20_13.getClass();
			absolueIcon = new ColorIcon(true);
		}

		return absolueIcon;
	}

	public void save(DataOutputStream out) throws IOException {
		out.writeInt(this.c.getRGB());
		out.writeInt(this.sat);
		out.writeInt(this.bri);
		out.writeInt(this.ref);
		out.writeBoolean(this.locked);
	}

	public void load(DataInputStream in) throws IOException {
		try {
			if (Theme.fileID >= 12852) {
				this.c = new ColorUIResource(in.readInt());
			} else {
				this.c = new ColorUIResource(in.readInt(), in.readInt(), in
						.readInt());
			}

			this.sat = in.readInt();
			this.bri = in.readInt();
			this.ref = in.readInt();
			this.locked = in.readBoolean();
		} catch (Exception ex) {
			throw new IOException("ColorReference.load() : " + ex.getMessage());
		}
	}

	class ColorIcon implements Icon {
		private boolean paintGradients;

		ColorIcon(boolean paramBoolean) {
			this.paintGradients = paramBoolean;
		}

		public int getIconHeight() {
			return 16;
		}

		public int getIconWidth() {
			return 16;
		}

		public void paintIcon(Component comp, Graphics g, int x, int y) {
			Color tempCol = g.getColor();

			g.setColor(Color.GRAY);
			g.drawRect(x, y, getIconWidth(), getIconHeight());

			if (this.paintGradients) {
				float hue = 0.0F;

				for (int i = 0; i < 15; ++i) {
					g.setColor(Color.getHSBColor(hue, 0.5F, 1.0F));
					g.drawLine(x + 1 + i, y + 1, x + 1 + i, y + getIconHeight()
							- 1);
					hue = (float) (hue + 0.0625D);
				}
			} else {
				g.setColor(ColorReference.this.c);
				g.fillRect(x + 1, y + 1, getIconWidth() - 1,
						getIconHeight() - 1);
			}

			if ((comp instanceof AbstractButton)
					&& (((AbstractButton) comp).isSelected())) {
				g.setColor(Color.WHITE);
				drawArrow(g, x + 1, y + 1);

				g.setColor(Color.BLACK);
				drawArrow(g, x, y);
			}

			g.setColor(tempCol);
		}

		private void drawArrow(Graphics g, int x, int y) {
			g.drawLine(x + 3, y + 5, x + 3, y + 7);
			g.drawLine(x + 4, y + 6, x + 4, y + 8);
			g.drawLine(x + 5, y + 7, x + 5, y + 9);
			g.drawLine(x + 6, y + 6, x + 6, y + 8);
			g.drawLine(x + 7, y + 5, x + 7, y + 7);
			g.drawLine(x + 8, y + 4, x + 8, y + 6);
			g.drawLine(x + 9, y + 3, x + 9, y + 5);
		}
	}
}
