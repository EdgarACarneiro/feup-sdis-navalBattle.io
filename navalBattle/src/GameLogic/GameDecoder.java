package GameLogic;

public class GameDecoder {
    // TODO: Class to decode the players requests. Received as a String
    // TODO: all methods should be static, acts as a filter

    public static void decodeAttack(ServerLogic server, String attack) {
    	String[] coords = attack.split("+");
    	
    	server.attack(Integer.parseInt(coords[0]), Integer.parseInt(coords[1]));
    }
    
    public static void parseMap(GameAPI player, String mapString) {
		
		String[] columns = mapString.split("-");
		
		int[][] map = new int[columns.length][columns.length];
		
		boolean gameOver = true;
		
		for(int i = 0; i < columns.length ; i++) {
			String[] rows = columns[i].split(",");
			for(int j = 0; j < rows.length ; j++) {
				map[i][j] = Integer.parseInt(rows[j]);
				if (Integer.parseInt(rows[j]) != 0)
					gameOver = false;
			}
		}
		
		player.setGameOver(gameOver);	
		player.setMap(map);
	}
}
