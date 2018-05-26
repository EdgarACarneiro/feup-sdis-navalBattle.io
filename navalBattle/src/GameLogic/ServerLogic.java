package GameLogic;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class ServerLogic {

    private static final int MAX_AUGMENTATION = 20;
	
	private int length;
	private int numPlayers;

    /**
     * HashMap saving the id of the boat associated to each userName (user logged)
     */
    private ConcurrentHashMap<String, Integer> usersBoats;

    public ServerLogic() {
        usersBoats = new ConcurrentHashMap<>();
    }
    
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
		numPlayers++;

        Random rand = new Random(100);
		int  col = rand.nextInt(MAX_AUGMENTATION);
		int  row = rand.nextInt(MAX_AUGMENTATION);
		
		usersBoats.put(col + "+" + row, id);
	}

	public String requestMap(int id) {
		return GameEncoder.encodeForPlayer(this, id);
	}
}
