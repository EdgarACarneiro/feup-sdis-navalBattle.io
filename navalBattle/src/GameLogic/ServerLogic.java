package GameLogic;

import java.util.concurrent.ConcurrentHashMap;

public class ServerLogic {
	
	private int height;
	private int width;
	private int numPlayers;

    /**
     * HashMap saving the id of the boat associated to each userName (user logged)
     */
    private ConcurrentHashMap<String, Integer> usersBoats;
    
	public void createMap() {
		for (int i = 0 ; i < numPlayers*4 ; i++) {
			for (int j = 0 ; j < numPlayers*4 ; j++) {
				usersBoats.put(i + "+" + j, 0); // Populate with water
			}
		}
	}
	
	public int height() {
		return height;
	}

	public int width() {
		return width;
	}
	
	public int get(int col, int row) {
		return usersBoats.get(col + "+" + row);
	}
	
	public int getFromId(int col, int row, int id) {
		if (usersBoats.get(col + "+" + row) == id)
			return usersBoats.get(col + "+" + row);
		return 0;
	}
	
	public boolean attack(int col, int row, String position) {
		if (usersBoats.get(col + "+" + row) != 0) { // 0 - Water
			usersBoats.replace(col + "+" + row, 1); // 1 - Destroyed ship
			return true;
		}
		
		return false;
	}
	
	public String encodeForPlayer(int id) {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < height(); i++) {
			sb.append("[");
			for(int j = 0; j < width(); j++) {
				sb.append(getFromId(i,j, id));
				if(j != width()-1) sb.append(",");
			}
			sb.append("]\n");
		}
		sb.deleteCharAt(sb.length()-1);
		return sb.toString();
	}
}
