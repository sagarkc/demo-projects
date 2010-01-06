/** ***************************************************************************
 *		MetalOfficeLnF :: Office Look and Feel from Metal	
 *	
 *	File	: ColorRoutines.java
 *	Type	: com.gs.plaf.common.ColorRoutines.java
 *	Date	: Jan 3, 2010	10:48:50 PM
 *
 *	Author	: Sabuj Das
 *
 *	
 *****************************************************************************/
package com.gs.plaf.common;

import java.awt.Color;

/**
 *
 */
public class ColorRoutines {
	private static final int RGB = 1;
	private static final int RBG = 2;
	private static final int GBR = 3;
	private static final int GRB = 4;
	private static final int BRG = 5;
	private static final int BGR = 6;
	private boolean preserveGrey;
	private int chue;
	private int csat;
	private int cbri;
	private int fr;
	private int fg;
	private int fb;
	int hi;
	int lo;
	int md;
	boolean hiIsR;
	boolean hiIsG;
	boolean hiIsB;
	boolean mdIsR;
	boolean mdIsG;
	boolean mdIsB;
	boolean loIsR;
	boolean loIsG;
	boolean loIsB;

	public ColorRoutines(Color c) {
		setHSB(c.getRed(), c.getGreen(), c.getBlue());
	}

	public ColorRoutines(int hue, int sat, int bri, boolean preserveGrey) {
		this.chue = hue;
		this.csat = sat;
		this.cbri = bri;
		this.preserveGrey = preserveGrey;

		Color c = Color.getHSBColor((float) (this.chue / 360.0D), 1.0F, 1.0F);
		this.fr = c.getRed();
		this.fg = c.getGreen();
		this.fb = c.getBlue();

		if ((this.fr >= this.fg) && (this.fg >= this.fb)) {
			this.hi = this.fr;
			this.md = this.fg;
			this.lo = this.fb;
			this.hiIsR = true;
			this.mdIsG = true;
			this.loIsB = true;
		} else if ((this.fr >= this.fb) && (this.fb >= this.fg)) {
			this.hi = this.fr;
			this.md = this.fb;
			this.lo = this.fg;
			this.hiIsR = true;
			this.mdIsB = true;
			this.loIsG = true;
		} else if ((this.fg >= this.fr) && (this.fr >= this.fb)) {
			this.hi = this.fg;
			this.md = this.fr;
			this.lo = this.fb;
			this.hiIsG = true;
			this.mdIsR = true;
			this.loIsB = true;
		} else if ((this.fg >= this.fb) && (this.fb >= this.fr)) {
			this.hi = this.fg;
			this.md = this.fb;
			this.lo = this.fr;
			this.hiIsG = true;
			this.mdIsB = true;
			this.loIsR = true;
		} else if ((this.fb >= this.fg) && (this.fg >= this.fr)) {
			this.hi = this.fb;
			this.md = this.fg;
			this.lo = this.fr;
			this.hiIsB = true;
			this.mdIsG = true;
			this.loIsR = true;
		} else if ((this.fb >= this.fr) && (this.fr >= this.fg)) {
			this.hi = this.fb;
			this.md = this.fr;
			this.lo = this.fg;
			this.hiIsB = true;
			this.mdIsR = true;
			this.loIsG = true;
		}
	}

	private void setHSB(int r, int g, int b) {
		this.chue = getHue(r, g, b);
		this.csat = getSaturation(r, g, b);
		this.cbri = getBrightness(r, g, b);
	}

	public static Color getAverage(Color c1, Color c2) {
		int r = (int) Math.round((c1.getRed() + c2.getRed()) / 2.0D);
		int g = (int) Math.round((c1.getGreen() + c2.getGreen()) / 2.0D);
		int b = (int) Math.round((c1.getBlue() + c2.getBlue()) / 2.0D);

		return new Color(r, g, b);
	}

	public static Color getGradient(Color c1, Color c2, int d, int i) {
		if (i == 0)
			return c1;
		if (i == d) {
			return c2;
		}
		double d2 = i * 1.1D / d;
		double d1 = 1.0D - d2;

		int r = (int) Math.round(c1.getRed() * d1 + c2.getRed() * d2);
		int g = (int) Math.round(c1.getGreen() * d1 + c2.getGreen() * d2);
		int b = (int) Math.round(c1.getBlue() * d1 + c2.getBlue() * d2);

		return new Color(r, g, b);
	}

	public static Color getMaxSaturation(Color c, int memH) {
		int r = c.getRed();
		int g = c.getGreen();
		int b = c.getBlue();

		if ((r == g) && (r == b)) {
			return c;
		}
		int ta = 0;
		int tb = 0;
		int tc = 0;
		int mapping = 1;

		if ((r >= g) && (r >= b)) {
			tc = r;

			if (g == b) {
				ta = g;
				tb = b;
				mapping = 1;
			} else if (g > b) {
				ta = g;
				tb = b;
				mapping = 1;
			} else {
				tb = g;
				ta = b;
				mapping = 2;
			}
		} else if ((g >= r) && (g >= b)) {
			tc = g;

			if (r == b) {
				ta = r;
				tb = b;
				mapping = 4;
			} else if (r > b) {
				ta = r;
				tb = b;
				mapping = 4;
			} else {
				tb = r;
				ta = b;
				mapping = 3;
			}
		} else if ((b >= r) && (b >= g)) {
			tc = b;

			if (r == g) {
				ta = r;
				tb = g;
				mapping = 5;
			} else if (r > g) {
				ta = r;
				tb = g;
				mapping = 5;
			} else {
				tb = r;
				ta = g;
				mapping = 6;
			}
		}

		if (tb == 0) {
			return c;
		}

		int nc = Math.min(255, tc + tb);
		int nb = Math.max(0, tc + tb - 255);
		int na = ta;
		int h = 0;
		int mh = 0;
		int ba = 0;
		int delta = 360;
		Color rc = null;

		switch (mapping) {
		case 1:
			h = getHue(nc, na, nb);
			mh = h;
			while ((h != memH) && (na < 256)) {
				h = getHue(nc, ++na, nb);
				if (na == 256) {
					break;
				}
				if (h == memH) {
					return new Color(nc, na, nb);
				}
				if (((mh < memH) && (h > memH)) || ((mh > memH) && (h < memH))) {
					return new Color(nc, na, nb);
				}
				if (Math.abs(h - memH) < delta) {
					delta = Math.abs(h - memH);
					ba = na;
				}
				mh = h;
			}

			if (h != memH) {
				h = getHue(nc, na, nb);
				mh = h;
				na = ta;
				while ((h != memH) && (na >= 0)) {
					h = getHue(nc, --na, nb);
					if (na == -1) {
						break;
					}
					if (h == memH) {
						return new Color(nc, na, nb);
					}
					if (((mh < memH) && (h > memH))
							|| ((mh > memH) && (h < memH))) {
						return new Color(nc, na, nb);
					}
					if (Math.abs(h - memH) < delta) {
						delta = Math.abs(h - memH);
						ba = na;
					}
					mh = h;
				}
			}
			if ((((na == 256) ? 1 : 0) | ((na == -1) ? 1 : 0)) != 0) {
				na = ba;
			}
			rc = new Color(nc, na, nb);
			break;
		case 2:
			h = getHue(nc, nb, na);
			mh = h;
			while ((h != memH) && (na < 256)) {
				h = getHue(nc, nb, ++na);
				if (na == 256) {
					break;
				}
				if (h == memH) {
					return new Color(nc, nb, na);
				}
				if (((mh < memH) && (h > memH)) || ((mh > memH) && (h < memH))) {
					return new Color(nc, nb, na);
				}
				if (Math.abs(h - memH) < delta) {
					delta = Math.abs(h - memH);
					ba = na;
				}
				mh = h;
			}

			if (h != memH) {
				h = getHue(nc, na, nb);
				mh = h;
				na = ta;
				while ((h != memH) && (na >= 0)) {
					h = getHue(nc, nb, --na);
					if (na == -1) {
						break;
					}
					if (h == memH) {
						return new Color(nc, nb, na);
					}
					if (((mh < memH) && (h > memH))
							|| ((mh > memH) && (h < memH))) {
						return new Color(nc, nb, na);
					}
					if (Math.abs(h - memH) < delta) {
						delta = Math.abs(h - memH);
						ba = na;
					}
					mh = h;
				}
			}
			if ((((na == 256) ? 1 : 0) | ((na == -1) ? 1 : 0)) != 0) {
				na = ba;
			}
			rc = new Color(nc, nb, na);
			break;
		case 3:
			h = getHue(nb, nc, na);
			mh = h;
			while ((h != memH) && (na < 256)) {
				h = getHue(nb, nc, ++na);
				if (na == 256) {
					break;
				}
				if (h == memH) {
					return new Color(nb, nc, na);
				}
				if (((mh < memH) && (h > memH)) || ((mh > memH) && (h < memH))) {
					return new Color(nb, nc, na);
				}
				if (Math.abs(h - memH) < delta) {
					delta = Math.abs(h - memH);
					ba = na;
				}
				mh = h;
			}

			if (h != memH) {
				h = getHue(nc, na, nb);
				mh = h;
				na = ta;
				while ((h != memH) && (na >= 0)) {
					h = getHue(nb, nc, --na);
					if (na == -1) {
						break;
					}
					if (h == memH) {
						return new Color(nb, nc, na);
					}
					if (((mh < memH) && (h > memH))
							|| ((mh > memH) && (h < memH))) {
						return new Color(nb, nc, na);
					}
					if (Math.abs(h - memH) < delta) {
						delta = Math.abs(h - memH);
						ba = na;
					}
					mh = h;
				}
			}

			if ((((na == 256) ? 1 : 0) | ((na == -1) ? 1 : 0)) != 0) {
				na = ba;
			}

			rc = new Color(nb, nc, na);
			break;
		case 4:
			h = getHue(na, nc, nb);
			mh = h;
			while ((h != memH) && (na < 256)) {
				h = getHue(++na, nc, nb);
				if (na == 256) {
					break;
				}
				if (h == memH) {
					return new Color(na, nc, nb);
				}
				if (((mh < memH) && (h > memH)) || ((mh > memH) && (h < memH))) {
					return new Color(na, nc, nb);
				}
				if (Math.abs(h - memH) < delta) {
					delta = Math.abs(h - memH);
					ba = na;
				}
				mh = h;
			}

			if (h != memH) {
				h = getHue(nc, na, nb);
				mh = h;
				na = ta;
				while ((h != memH) && (na >= 0)) {
					h = getHue(--na, nc, nb);
					if (na == -1) {
						break;
					}
					if (h == memH) {
						return new Color(na, nc, nb);
					}
					if (((mh < memH) && (h > memH))
							|| ((mh > memH) && (h < memH))) {
						return new Color(na, nc, nb);
					}
					if (Math.abs(h - memH) < delta) {
						delta = Math.abs(h - memH);
						ba = na;
					}
					mh = h;
				}
			}
			if ((((na == 256) ? 1 : 0) | ((na == -1) ? 1 : 0)) != 0) {
				na = ba;
			}
			rc = new Color(na, nc, nb);
			break;
		case 5:
			h = getHue(na, nb, nc);
			mh = h;
			while ((h != memH) && (na < 256)) {
				h = getHue(++na, nb, nc);
				if (na == 256) {
					break;
				}
				if (h == memH) {
					return new Color(na, nb, nc);
				}
				if (((mh < memH) && (h > memH)) || ((mh > memH) && (h < memH))) {
					return new Color(na, nb, nc);
				}
				if (Math.abs(h - memH) < delta) {
					delta = Math.abs(h - memH);
					ba = na;
				}
				mh = h;
			}

			if (h != memH) {
				h = getHue(nc, na, nb);
				mh = h;
				na = ta;
				while ((h != memH) && (na >= 0)) {
					h = getHue(--na, nb, nc);
					if (na == -1) {
						break;
					}
					if (h == memH) {
						return new Color(na, nb, nc);
					}
					if (((mh < memH) && (h > memH))
							|| ((mh > memH) && (h < memH))) {
						return new Color(na, nb, nc);
					}
					if (Math.abs(h - memH) < delta) {
						delta = Math.abs(h - memH);
						ba = na;
					}
					mh = h;
				}
			}
			if ((((na == 256) ? 1 : 0) | ((na == -1) ? 1 : 0)) != 0) {
				na = ba;
			}
			rc = new Color(na, nb, nc);
			break;
		case 6:
			h = getHue(nb, na, nc);
			mh = h;
			while ((h != memH) && (na < 256)) {
				h = getHue(nb, ++na, nc);
				if (na == 256) {
					break;
				}
				if (h == memH) {
					return new Color(nb, na, nc);
				}
				if (((mh < memH) && (h > memH)) || ((mh > memH) && (h < memH))) {
					return new Color(nb, na, nc);
				}
				if (Math.abs(h - memH) < delta) {
					delta = Math.abs(h - memH);
					ba = na;
				}
				mh = h;
			}

			if (h != memH) {
				h = getHue(nc, na, nb);
				mh = h;
				na = ta;
				while ((h != memH) && (na >= 0)) {
					h = getHue(nb, --na, nc);
					if (na == -1) {
						break;
					}
					if (h == memH) {
						return new Color(nb, na, nc);
					}
					if (((mh < memH) && (h > memH))
							|| ((mh > memH) && (h < memH))) {
						return new Color(nb, na, nc);
					}
					if (Math.abs(h - memH) < delta) {
						delta = Math.abs(h - memH);
						ba = na;
					}
					mh = h;
				}
			}
			if ((((na == 256) ? 1 : 0) | ((na == -1) ? 1 : 0)) != 0) {
				na = ba;
			}
			rc = new Color(nb, na, nc);
		}

		return rc;
	}

	public static float getGreyValue(Color c) {
		int r = c.getRed();
		int g = c.getGreen();
		int b = c.getBlue();
		int tb = 0;
		int tc = 0;

		if ((r >= g) && (r >= b)) {
			if (r == 0) {
				return 0.0F;
			}
			tc = r;

			if (g >= b) {
				tb = b;
			} else {
				tb = g;
			}

		} else if ((g >= r) && (g >= b)) {
			tc = g;

			if (r >= b) {
				tb = b;
			} else {
				tb = r;
			}

		} else if ((b >= r) && (b >= g)) {
			tc = b;

			if (r >= g) {
				tb = g;
			} else {
				tb = r;
			}

		}

		return (float) ((tc + tb) / 2.0D);
	}

	public static int getBrightness(Color c) {
		int r = c.getRed();
		int g = c.getGreen();
		int b = c.getBlue();

		return getBrightness(r, g, b);
	}

	public static int getBrightness(int r, int g, int b) {
		if ((r >= g) && (r >= b)) {
			return (int) Math.round(100 * r / 255.0D);
		}
		if ((g >= r) && (g >= b)) {
			return (int) Math.round(100 * g / 255.0D);
		}
		if ((b >= r) && (b >= g)) {
			return (int) Math.round(100 * b / 255.0D);
		}

		return -1;
	}

	public static int getSaturation(Color c) {
		int r = c.getRed();
		int g = c.getGreen();
		int b = c.getBlue();

		return getSaturation(r, g, b);
	}

	public static int getSaturation(int r, int g, int b) {
		int tb = 0;
		int tc = 0;

		if ((r >= g) && (r >= b)) {
			if (r == 0) {
				return 0;
			}
			tc = r;

			if (g >= b) {
				tb = b;
			} else {
				tb = g;
			}

		} else if ((g >= r) && (g >= b)) {
			tc = g;

			if (r >= b) {
				tb = b;
			} else {
				tb = r;
			}

		} else if ((b >= r) && (b >= g)) {
			tc = b;

			if (r >= g) {
				tb = g;
			} else {
				tb = r;
			}

		}

		return (100 - (int) Math.round(100.0D * tb / tc));
	}

	public static int getHue(Color c) {
		int r = c.getRed();
		int g = c.getGreen();
		int b = c.getBlue();

		return getHue(r, g, b);
	}

	public static int calculateHue(Color c) {
		float[] f = Color.RGBtoHSB(c.getRed(), c.getGreen(), c.getBlue(), null);
		return (int) Math.round(360.0D * f[0]);
	}

	public static int getHue(int r, int g, int b) {
		int ta = 0;
		int tb = 0;
		int tc = 0;
		int mapping = 1;

		if ((r >= g) && (r >= b)) {
			tc = r;

			if (g == b) {
				return 0;
			}
			if (g > b) {
				ta = g;
				tb = b;
				mapping = 1;
			} else {
				tb = g;
				ta = b;
				mapping = 2;
			}
		} else if ((g >= r) && (g >= b)) {
			tc = g;

			if (r == b) {
				return 120;
			}
			if (r > b) {
				ta = r;
				tb = b;
				mapping = 4;
			} else {
				tb = r;
				ta = b;
				mapping = 3;
			}
		} else if ((b >= r) && (b >= g)) {
			tc = b;

			if (r == g) {
				return 240;
			}
			if (r > g) {
				ta = r;
				tb = g;
				mapping = 5;
			} else {
				tb = r;
				ta = g;
				mapping = 6;
			}

		}

		double na = ta * 255.0D / tc;
		double nb = tb * 255.0D / tc;

		double val = (na - nb) * 255.0D / (255.0D - nb);

		int w = (int) Math.round(60.0D * val / 255.0D);

		switch (mapping) {
		case 1:
			return w;
		case 2:
			return (360 - w);
		case 3:
			return (120 + w);
		case 4:
			return (120 - w);
		case 5:
			return (240 + w);
		case 6:
			return (240 - w);
		}
		return -1;
	}

	public static Color getHSB(int h, int s, int b) {
		double cr = 0.0D;
		double cg = 0.0D;
		double cb = 0.0D;
		int mapping = 1;

		if (h == 360) {
			h = 0;
		}

		int winkel = h / 60;
		int amount = h % 60;

		switch (winkel) {
		case 0:
			cr = 255.0D;
			cg = 255 * amount / 60.0D;
			mapping = 1;
			break;
		case 1:
			cg = 255.0D;
			cr = 255.0D - (255 * amount / 60.0D);
			mapping = 3;
			break;
		case 2:
			cg = 255.0D;
			cb = 255 * amount / 60.0D;
			mapping = 3;
			break;
		case 3:
			cb = 255.0D;
			cg = 255.0D - (255 * amount / 60.0D);
			mapping = 5;
			break;
		case 4:
			cb = 255.0D;
			cr = 255 * amount / 60.0D;
			mapping = 5;
			break;
		case 5:
			cr = 255.0D;
			cb = 255.0D - (255 * amount / 60.0D);
			mapping = 1;
		}

		cr = cr * b / 100.0D;
		cg = cg * b / 100.0D;
		cb = cb * b / 100.0D;

		int d = 100 - s;

		switch (mapping) {
		case 1:
			cg += (cr - cg) * d / 100.0D;
			cb += (cr - cb) * d / 100.0D;
			break;
		case 3:
			cr += (cg - cr) * d / 100.0D;
			cb += (cg - cb) * d / 100.0D;
			break;
		case 5:
			cr += (cb - cr) * d / 100.0D;
			cg += (cb - cg) * d / 100.0D;
		case 2:
		case 4:
		}
		return new Color((int) Math.round(cr), (int) Math.round(cg), (int) Math
				.round(cb));
	}

	public int colorize(int r, int g, int b, int a) {
		if (this.cbri == 100) {
			return colorToInt(255, 255, 255, a);
		}
		if (this.cbri == -100) {
			return colorToInt(0, 0, 0, a);
		}

		int hi1 = r;
		if ((g >= r) && (g >= b))
			hi1 = g;
		else if ((b >= r) && (b >= g)) {
			hi1 = b;
		}
		int lo1 = r;
		if ((g <= r) && (g <= b))
			lo1 = g;
		else if ((b <= r) && (b <= g)) {
			lo1 = b;
		}
		int grey = (hi1 + lo1) / 2;

		if (this.cbri < 0) {
			grey += grey * this.cbri / 100;
		} else if (this.cbri > 0) {
			grey += (255 - grey) * this.cbri / 100;
		}

		if ((this.preserveGrey) && (r == g) && (r == b)) {
			return colorToInt(grey, grey, grey, a);
		}

		int hr = 0;
		int hg = 0;
		int hb = 0;
		int diff = 0;

		if (grey >= 127) {
			diff = 255 - grey;
		} else {
			diff = grey;
		}

		if (this.hiIsR) {
			hr = grey + diff * this.csat / 100;
		} else if (this.hiIsG) {
			hg = grey + diff * this.csat / 100;
		} else if (this.hiIsB) {
			hb = grey + diff * this.csat / 100;
		}

		if (this.mdIsR) {
			if (grey >= 127) {
				diff = this.fr + (255 - this.fr) * (grey - 127) / 128 - grey;
			} else {
				diff = this.fr * grey / 127 - grey;
			}
			hr = grey + diff * this.csat / 100;
		} else if (this.mdIsG) {
			if (grey >= 127) {
				diff = this.fg + (255 - this.fg) * (grey - 127) / 128 - grey;
			} else {
				diff = this.fg * grey / 127 - grey;
			}
			hg = grey + diff * this.csat / 100;
		} else if (this.mdIsB) {
			if (grey >= 127) {
				diff = this.fb + (255 - this.fb) * (grey - 127) / 128 - grey;
			} else {
				diff = this.fb * grey / 127 - grey;
			}
			hb = grey + diff * this.csat / 100;
		}

		diff = grey - (255 - grey);
		if (diff < 0)
			diff = 0;
		diff = grey - diff;

		if (this.loIsR) {
			hr = grey - (diff * this.csat / 100);
		} else if (this.loIsG) {
			hg = grey - (diff * this.csat / 100);
		} else if (this.loIsB) {
			hb = grey - (diff * this.csat / 100);
		}

		return colorToInt(hr, hg, hb, a);
	}

	public static Color getInverseColor(Color c) {
		int r = 255 - c.getRed();
		int g = 255 - c.getGreen();
		int b = 255 - c.getBlue();

		return new Color(r, g, b);
	}

	public static Color getRandomColor() {
		int r = (int) (Math.random() * 255.0D);
		int g = (int) (Math.random() * 255.0D);
		int b = (int) (Math.random() * 255.0D);

		return new Color(r, g, b);
	}

	public static Color getAlphaColor(Color c, int a) {
		int r = c.getRed();
		int g = c.getGreen();
		int b = c.getBlue();

		return new Color(r, g, b, a);
	}

	protected static int colorToInt(Color c, int a) {
		return (c.getBlue() + c.getGreen() * 256 + c.getRed() * 65536 + a * 16777216);
	}

	protected static int colorToInt(int r, int g, int b, int a) {
		return (b + g * 256 + r * 65536 + a * 16777216);
	}

	public static Color lighten(Color c, int amount) {
		if (amount < 0)
			return c;

		if (amount > 100)
			amount = 100;

		int dr = (int) Math.round((255 - c.getRed()) * amount / 100.0D);
		int dg = (int) Math.round((255 - c.getGreen()) * amount / 100.0D);
		int db = (int) Math.round((255 - c.getBlue()) * amount / 100.0D);

		return new Color(c.getRed() + dr, c.getGreen() + dg, c.getBlue() + db,
				c.getAlpha());
	}

	public static Color darken(Color c, int amount) {
		if ((amount < 0) || (amount > 100))
			return c;

		int r = (int) Math.round(c.getRed() * (100 - amount) / 100.0D);
		int g = (int) Math.round(c.getGreen() * (100 - amount) / 100.0D);
		int b = (int) Math.round(c.getBlue() * (100 - amount) / 100.0D);

		return new Color(r, g, b, c.getAlpha());
	}

	public static Color lighten(int grey, int amount) {
		if ((amount < 0) || (amount > 100))
			return new Color(grey, grey, grey);

		int val = (255 - grey) * amount / 100 + grey;

		return new Color(val, val, val);
	}

	public static Color darken(int grey, int amount) {
		if ((amount < 0) || (amount > 100))
			return new Color(grey, grey, grey);

		int val = grey * (100 - amount) / 100;

		return new Color(val, val, val);
	}
}