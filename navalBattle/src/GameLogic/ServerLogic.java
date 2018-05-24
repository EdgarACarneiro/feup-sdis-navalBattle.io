package GameLogic;

import java.util.concurrent.ConcurrentHashMap;

public class ServerLogic {
	
	private int height;
	private int width;

    /**
     * HashMap saving the id of the boat associated to each userName (user logged)
     */
    private ConcurrentHashMap<String, Integer> usersBoats;
    
	public boolean createMap() {
		return true;
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
		if (usersBoats.get(col + "+" + row) != 0) {
			usersBoats.replace(col + "+" + row, 0);
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
