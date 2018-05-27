package GameLogic;

import Communication.REST.HTTPMethod;
import Player.Player;
import UI.UI_API;
import Utils.Pair;
import Utils.ThreadPool;

import java.util.HashMap;

public class PlayerLogic {
	
	private int[][] map;
	private boolean gameOver = false;
	private int attackcol = -1;
	private int attackrow = -1;
	private UI.UI_API window;

	private Player bottomLayer;

	public PlayerLogic(Player bottomLayer) {
		this.bottomLayer = bottomLayer;
		
        try {
        	window = new UI_API(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int getLength() {
		return this.map.length;
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
		bottomLayer.sendServer(new HashMap<>(), new Pair<>("app/create", HTTPMethod.POST));
	}
	
    public void initializeGame() {
		// Wait 500ms before creating player, to give to initialize all the listener and communication
		new ThreadPool(1).run(this::createGame, 0);
    }

	public void attack(int col, int row) {
		HashMap<String, String> params = new HashMap<>();
		params.put("col", Integer.toString(col));
		params.put("row", Integer.toString(row));

		bottomLayer.sendServer(params, new Pair<>("app/attack", HTTPMethod.POST));
	}

	public void updateMap(String map) {
        GameDecoder.parseMap(this, map);
        window.printMap(this.map);
    }

	public int[][] getMap() {
		return map;
	}

}
