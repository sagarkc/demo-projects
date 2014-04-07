/**
 * 
 */
package com.gs.games.mysticsquare.core;

import java.util.Arrays;
import java.util.Random;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 * 
 */
public class IntegerProvider implements ValueProvider<Integer> {

	private Integer[] items;
	private final int length;

	/**
	 * 
	 */
	public IntegerProvider(int length) {
		this.length = length;
		items = new Integer[length];
		for (int i = 0; i < length; i++) {
			items[i] = i+1;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gs.games.mysticsquare.core.ValueProvider#getValues()
	 */
	public Integer[] getValues() {
		return items;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gs.games.mysticsquare.core.ValueProvider#shuffle(java.lang.Object[])
	 */
	public void shuffle() {
		Random random = new Random(length-1);
		for (int i = 0; i < length; i++) { // Exchange a[i] with random element
											// in a[i..N-1]
			int r = i + Math.abs(random.nextInt());
			if(r >= length)
				r = length - 1;
			Integer temp = items[i];
			items[i] = items[r];
			items[r] = temp;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gs.games.mysticsquare.core.ValueProvider#sort(java.lang.Object[])
	 */
	public void sort() {
		Arrays.sort(items);
	}

	/* (non-Javadoc)
	 * @see com.gs.games.mysticsquare.core.ValueProvider#getValueAt(int)
	 */
	public Integer getValueAt(int index) {
		if(index >= 0 && index < length)
			return items[index];
		return null;
	}

	
}
