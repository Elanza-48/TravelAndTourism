package com.elanza48.TMS.model;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class PasswordHasher {
	
	private PasswordHasher() {}
	
	public static String hashNow(String password)  {
		MessageDigest digester;
		try {
			digester=MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
			byte[] hashedBytes = digester.digest(password.getBytes());
			StringBuilder hashedStringBuilder= new StringBuilder();
			
			for(byte b : hashedBytes) {
				hashedStringBuilder.append(String.format("%02x", b));
			}
			System.out.println("Hashed: "+hashedStringBuilder.toString());
			return hashedStringBuilder.toString();
	}
	
}
