package GameLogic;

public class GameLogicAPI {
	
	private int[][] map;
	private int length;
	private boolean gameOver = false;
	private int attackcol = -1;
	private int attackrow = -1;
	
	public int getLength() {
		return length;
	}
	
	public boolean isGameOver() {
		return gameOver;
	}
	
	public int getAttackCol() {
		return attackcol;
	}
	
	public int getAttackRow() {
		return attackrow;
	}
	
	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}
	
	public int get(int col, int row) {
		return map[col][row];
	}
	
	public void setMap(int[][] map) {
		this.map = map;
	}
	
	public boolean set(int col, int row) {
		if (map[col][row] == 0) {
			attackcol = col; 
			attackrow = row;			
			return true;
		}
		return false;
	}
}
