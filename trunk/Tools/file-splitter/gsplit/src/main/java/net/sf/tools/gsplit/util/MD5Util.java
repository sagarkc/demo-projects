/**
 * 
 */
package net.sf.tools.gsplit.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 * 
 */
public final class MD5Util {

	private static final byte[] DIGEST_UPDATE_KEY = new byte[] { 1, 56, 23, 9,
			0, 12, 32, 8, 1, 21, 45, 7, 0, 85, 12, 6 };

	/**
	 * Generate a MD5 byte array from a file.
	 * 
	 * @param fileName
	 * @return
	 */
	public static byte[] generateMD5(String fileName) {
		if (null == fileName) {
			return null;
		}
		if ("".equals(fileName.trim())) {
			return null;
		}
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			InputStream inputStream = new FileInputStream(fileName);
			try {
				inputStream = new DigestInputStream(inputStream, messageDigest);
			} finally {
				if (null != inputStream) {
					inputStream.close();
				}
			}
			return messageDigest.digest(DIGEST_UPDATE_KEY);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static boolean checkMD5(String sourceFileName, String targetFileName) {
		return checkMD5(generateMD5(sourceFileName),
				generateMD5(targetFileName));
	}

	public static boolean checkMD5(byte[] source, String targetFileName) {
		return checkMD5(source, generateMD5(targetFileName));
	}

	public static boolean checkMD5(String sourceFileName, byte[] target) {
		return checkMD5(generateMD5(sourceFileName), target);
	}

	public static boolean checkMD5(byte[] source, byte[] target) {
		return Arrays.equals(source, target);
	}

	/**
	 * @param file
	 * @return
	 */
	public static byte[] generateMD5(File file) {
		return generateMD5(file.getAbsolutePath());
	}

	public static byte[] getSalt(String text) throws NoSuchAlgorithmException,
			UnsupportedEncodingException {
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		digest.reset();
		return digest.digest(text.getBytes("UTF-8"));
	}

	
}
