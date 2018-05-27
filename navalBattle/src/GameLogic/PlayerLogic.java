package GameLogic;

import Communication.REST.HTTPMethod;
import Player.Player;
import Utils.Pair;

import java.util.HashMap;

public class PlayerLogic {
	
	private int[][] map;
	private int length;
	private boolean gameOver = false;
	private int attackcol = -1;
	private int attackrow = -1;

	private Player bottomLayer;

	public PlayerLogic(Player bottomLayer) {
		this.bottomLayer = bottomLayer;
	}
	
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

	private void  createGame() {
		bottomLayer.sendServer(null, new Pair<>("app/create", HTTPMethod.POST));
	}

	private void attack(int col, int row) {
		HashMap<String, String> params = new HashMap<>();
		params.put("col", Integer.toString(col));
		params.put("row", Integer.toString(row));

		bottomLayer.sendServer(params, new Pair<>("app/attack", HTTPMethod.POST));
	}

}
