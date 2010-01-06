/** ***************************************************************************
 *		MetalOfficeLnF :: Office Look and Feel from Metal	
 *	
 *	File	: HSBReference.java
 *	Type	: com.gs.plaf.common.HSBReference.java
 *	Date	: Jan 3, 2010	10:51:42 PM
 *
 *	Author	: Sabuj Das
 *
 *	
 *****************************************************************************/
package com.gs.plaf.common;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 *
 */
public class HSBReference extends ColorReference {
	protected int hue;
	protected boolean preserveGrey;

	public HSBReference(int hue, int sat, int bri, int ref) {
		this.hue = hue;
		this.sat = sat;
		this.bri = bri;
		this.ref = ref;
		this.preserveGrey = true;
	}

	public int getHue() {
		return this.hue;
	}

	public void setHue(int newHue) {
		this.hue = newHue;
	}

	public void load(DataInputStream in) throws IOException {
		try {
			this.hue = in.readInt();
			this.sat = in.readInt();
			this.bri = in.readInt();
			this.ref = in.readInt();
			this.preserveGrey = in.readBoolean();
		} catch (Exception ex) {
			throw new IOException("HSBReference.load() : " + ex.getMessage());
		}
	}

	public void save(DataOutputStream out) throws IOException {
		out.writeInt(this.hue);
		out.writeInt(this.sat);
		out.writeInt(this.bri);
		out.writeInt(this.ref);
		out.writeBoolean(this.preserveGrey);
	}

	public boolean isPreserveGrey() {
		return this.preserveGrey;
	}

	public void setPreserveGrey(boolean b) {
		this.preserveGrey = b;
	}
}
