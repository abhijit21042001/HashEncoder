package com.mrunali;
import java.security.SecureRandom;
import java.io.IOException;

public class HashEncoder {
	
	private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final SecureRandom RANDOM = new SecureRandom();
    
	public static void main(String[] args) throws IOException {
		String prn = args[0];
		String path = args[1];
		String data = JsonKeyFinder.findKeyInJsonFile(path,"destination");
		String random = generateRandomString(8);
		System.out.println(MD5HashGenerator.generateMD5Hash(prn+data+random)+";"+random);
	}
	
	public static String generateRandomString(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomIndex = RANDOM.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(randomIndex));
        }
        return sb.toString();
    }
}
