package com.texas.anexus.util;


import java.security.SecureRandom;

/**
 * Utility class for random generator.
 * 
 * @author Deependra Karki
 * @version 1.0.0
 * @since 1.0.0, April 24, 2018
 */
public class RandomUtils {
	private static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	private static SecureRandom rnd = new SecureRandom();

	/**
	 * Returns the random string as speciried length.
	 */
	public static String randomString(final int length) {
		StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < length; i++)
			sb.append(AB.charAt(rnd.nextInt(AB.length())));
		return sb.toString();
	}
}
