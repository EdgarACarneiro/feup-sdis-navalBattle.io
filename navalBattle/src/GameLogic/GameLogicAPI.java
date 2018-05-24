package GameLogic;

public class GameLogicAPI {
	
	private int[][] map;
	private int height;
	private int width;

    // Different behaviours if it is either a Server or a Player
    // Usar design pattern - nao me estou a recordar do nome
	
	public void parseMap(String mapString) {
						
		String[] columns = mapString.split("-");
		
		map = new int[columns.length][columns.length];
		
		for(int i = 0; i < columns.length ; i++) {
			String[] rows = columns[i].split(",");
			for(int j = 0; j < rows.length ; j++) {
				map[i][j] = Integer.parseInt(rows[j]);
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
		return map[col][row];
	}
	
	public boolean attack(int col, int row) {
		if (map[col][row] == 0)
			return true;
		return false;
	}
	
	public String toString() { //Or send move?
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < height(); i++) {
			for(int j = 0; j < width(); j++) {
				sb.append(get(i,j));
				if(j != width()-1) sb.append(",");
			}
			sb.append("-");
		}
		sb.deleteCharAt(sb.length()-1);
		return sb.toString();
	}
}
