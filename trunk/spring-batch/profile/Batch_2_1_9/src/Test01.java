import java.util.Arrays;

/**
 * -------------------------------------------------------------------------- *
 * 								ETL Manager
 * 						Monitor | Manage | Admin
 * -------------------------------------------------------------------------- *
 * Type:	.Test01
 * Date:	Oct 17, 2013  6:50:21 PM
 * 
 * -------------------------------------------------------------------------- *
 */

/**
 * @author Sabuj Das | sabuj.das@asia.xchanging.com
 * 
 */
public class Test01 {

	public static void main(String[] args) {
		long[] x = get(5);
		System.out.println(Arrays.toString(x));
		//System.out.println(factorial(25));
	}
	
	public static long[] get(int n) {
		if(n>0)
			n = n-1;
		long[] x = new long[n+1];
		for (int c = 0; c <= n; c++)
			x[c] = factorial(n) / (factorial(c) * factorial(n - c));
		
		return x;
	}

	public static long factorial(int n) {
		int c;
		long result = 1;

		for (c = 1; c <= n; c++)
			result = result * c;

		return (result);
	}

}


