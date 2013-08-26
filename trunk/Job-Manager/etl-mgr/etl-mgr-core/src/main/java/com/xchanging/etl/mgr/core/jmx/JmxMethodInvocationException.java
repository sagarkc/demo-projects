/**
 * -------------------------------------------------------------------------- *
 * 								ETL Manager
 * 						Monitor | Manage | Admin
 * -------------------------------------------------------------------------- *
 * Type:	com.xchanging.etl.mgr.core.jmx.JmxMethodInvocationException
 * Date:	Aug 26, 2013  7:53:03 PM
 * 
 * -------------------------------------------------------------------------- *
 */
package com.xchanging.etl.mgr.core.jmx;

/**
 * @author Sabuj Das | sabuj.das@asia.xchanging.com
 *
 */
public class JmxMethodInvocationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7755447546313813628L;

	public JmxMethodInvocationException() {
		super();
	}

	public JmxMethodInvocationException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public JmxMethodInvocationException(String message, Throwable cause) {
		super(message, cause);
	}

	public JmxMethodInvocationException(String message) {
		super(message);
	}

	public JmxMethodInvocationException(Throwable cause) {
		super(cause);
	}

}
