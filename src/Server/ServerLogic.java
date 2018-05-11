package Server;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

public class ServerLogic {
	
	private HashMap<String, Double> users = new HashMap<>();
	private HashMap<String, Double> loggedUsers = new HashMap<>();
    // Uses communication module

    // Uses Game Logic + Authentication Logic
	
	public void authentication(String username) throws NoSuchAlgorithmException {

		if (!users.containsKey(username))
			users.put(username, 0.0);
		
		double score = users.get(username);
		loggedUsers.put(username, score);
		
	}
	
	
	public HashMap<String, Double> getUsers(){
		return users;
	}
	
	public HashMap<String, Double> loggedUsers(){
		return loggedUsers;
	}

    // Singleton class num servidor -> somehow
}
