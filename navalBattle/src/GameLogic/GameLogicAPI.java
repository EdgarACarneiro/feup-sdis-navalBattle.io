package GameLogic;

import java.util.Date;

public class GameLogicAPI {
	
	private int[][] map;
	private int length;
	private boolean gameOver = false;

    // Different behaviours if it is either a Server or a Player
    // Usar design pattern - nao me estou a recordar do nome
	
	public static void turn() {
		Date dNow = new Date( );
		
    		  
		//System.out.println("Current Date: " + ft.format(dNow));
	}
	
	public void parseMap(String mapString) {
						
		String[] columns = mapString.split("-");
		
		map = new int[columns.length][columns.length];
		
		gameOver = true;
		
		for(int i = 0; i < columns.length ; i++) {
			String[] rows = columns[i].split(",");
			for(int j = 0; j < rows.length ; j++) {
				map[i][j] = Integer.parseInt(rows[j]);
				if (Integer.parseInt(rows[j]) != 0)
					gameOver = false;
			}
		}
	}
	
	public int getLength() {
		return length;
	}
	
	public int get(int col, int row) {
		return map[col][row];
	}
	
	public boolean set(int col, int row) {
		if (map[col][row] == 0)
			return true;
		return false;
	}
	
	public String toString() { //Or send move?
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < getLength(); i++) {
			for(int j = 0; j < getLength(); j++) {
				sb.append(get(i,j));
				if(j != getLength()-1) sb.append(",");
			}
			sb.append("-");
		}
		sb.deleteCharAt(sb.length()-1);
		return sb.toString();
	}
}
