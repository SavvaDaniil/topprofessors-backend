package com.topprofessors.Component;

import java.util.Random;

public class RandomComponent {
	
    private final static Random random = new Random();
    private final static String allowedChars = "ABCDEFGHJKLMNOPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz0123456789";
    private final static String allowedNumbers = "0123456789";
    
    public static String RandomString(int length)
    {
    	StringBuilder sb = new StringBuilder(length);
		for(int i = 0; i < length; i++)
			sb.append(allowedChars.charAt(random.nextInt(allowedChars.length())));
        return sb.toString();
    }

    public static String RandomIntAsString(int length)
    {
    	StringBuilder sb = new StringBuilder(length);
		for(int i = 0; i < length; i++)
			sb.append(allowedNumbers.charAt(random.nextInt(allowedNumbers.length())));
        return sb.toString();
    }
}
