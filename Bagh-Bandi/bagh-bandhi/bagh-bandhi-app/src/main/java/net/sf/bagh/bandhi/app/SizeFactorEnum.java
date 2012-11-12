/**
 * 
 */
package net.sf.bagh.bandhi.app;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public enum SizeFactorEnum {
	EXTRA_SHORT, 
	SHORT,
	NORMAL,
	LARGE, 
	EXTRA_LARGE;
	
	public static SizeFactorEnum getValue(int i){
		if(i == 1)
			return EXTRA_SHORT;
		if(i == 2)
			return SHORT;
		if(i == 3)
			return NORMAL;
		if(i == 4)
			return LARGE;
		if(i == 5)
			return EXTRA_LARGE;
		return NORMAL;
	}
}
