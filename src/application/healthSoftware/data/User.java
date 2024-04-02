package application.healthSoftware.data;

import java.io.Serializable;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class User implements Serializable {
	public String username;
	private String passwordHash;
	
	public String firstName;
	public String lastName;
	public String birthday;
	
	public User() {
		
	}
	
	// Hash password before setting using SHA-256
	// More secure implementations would ideally use something like bcrypt, but this is simple to implement
	public void setPassword(String password) {
		try {
			passwordHash = sha256(password);
			System.out.println("Password: " + password);
			System.out.println("Password Hash: " + passwordHash);
		}
		catch(NoSuchAlgorithmException err) {
			// We only ever use one algorithm that we know exists, so something really went wrong
			err.printStackTrace();
		}
	}
	
	private String sha256(String input) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		byte[] digest = md.digest(input.getBytes(StandardCharsets.UTF_8));
		
		BigInteger num = new BigInteger(1, digest);
		StringBuilder hexString = new StringBuilder(num.toString(16));
		while(hexString.length() < 64) {
			hexString.insert(0, "0");
		}
		
		return hexString.toString();
	}
	
	public boolean testPassword(String input) {
		try {
			String testHash = sha256(input);
			return testHash.equals(passwordHash);
		}
		catch(NoSuchAlgorithmException err) {
			// We only ever use one algorithm that we know exists, so something really went wrong
			err.printStackTrace();
			return false;
		}
	}
}
