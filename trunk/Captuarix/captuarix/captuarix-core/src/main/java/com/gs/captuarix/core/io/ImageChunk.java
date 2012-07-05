/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gs.captuarix.core.io;

import java.io.Serializable;

/**
 *
 * @author Sabuj Das | sabuj.das@gmail.com
 */
public class ImageChunk implements Serializable{

	private byte[] data;
	private int number;
	private int length;

	public ImageChunk() {
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
	
	
	
}
