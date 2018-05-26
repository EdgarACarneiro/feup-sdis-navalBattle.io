package GameLogic;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class ServerLogic {
	
	private int length;
	private int numPlayers;

    /**
     * HashMap saving the id of the boat associated to each userName (user logged)
     */
    private ConcurrentHashMap<String, Integer> usersBoats;
    
	public void updateMap() {
		length = numPlayers*4;
		for (int i = 0 ; i < length ; i++) {
			for (int j = 0 ; j < length ; j++) {
				usersBoats.putIfAbsent(i + "+" + j, -1); // Populate with water
			}
		}
	}
	
	public int getLength() {
		return length;
	}
	
	public int get(int col, int row) {
		return usersBoats.get(col + "+" + row);
	}
	
	public int getFromId(int col, int row, int id) {
		int pos = usersBoats.get(col + "+" + row);
		if (pos == id)
			return pos;
		return 0;
	}
	
	public boolean attack(int col, int row) {
		if (usersBoats.get(col + "+" + row) != -1) { // -1 - Water
			usersBoats.put(col + "+" + row, -2); // -2 - Destroyed ship
			return true;
		}
		
		return false;
	}
	
	public void newPlayer(int id) {
		System.out.println(numPlayers);
		numPlayers++;
		
		Random rand = new Random();
		
		System.out.println(id);
			
		int  col = rand.nextInt(length);
		int  row = rand.nextInt(length);
		
		usersBoats.put(col + "+" + row, id);
	}
}
