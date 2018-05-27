package GameLogic;

import Communication.REST.HTTPMethod;
import Player.Player;
import UI.UI_API;
import Utils.Pair;

import java.util.HashMap;

public class PlayerLogic {

	int[][] map;
    private int[] attack = {-1, -1};
	private boolean gameOver = false;
	private UI.UI_API ui;

	private Player bottomLayer;

	public PlayerLogic(Player bottomLayer) {
		this.bottomLayer = bottomLayer;
		
        try {
        	ui = new UI_API(this);
		} catch (Exception e) {
			System.err.println("Failed to create Player UI");
		}
	}

	public int getLength() {
		return this.map.length;
	}

	public boolean isGameOver() {
		return gameOver;
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
	
	public boolean attack(int col, int row) {
	    if (col < 0 || map.length <= col) {
	        System.err.println("Unknown column selected");
	        return false;
        }

        if (row < 0 || map[col].length <= row) {
            System.err.println("Unknown row selected");
            return false;
        }

		if (map[col][row] != GameCells.WATER) {
			System.err.println("Unable to attack selected position");
			return false;
		}

        if (attack == new int[] {-1, -1}) {
            System.err.println("You already attacked this turn");
            return false;
        }

        attack = new int[]{col, row};
		attack();
		return true;
	}
	
    public void initializeGame() {
        createGame();
    }

    private void createGame() {
        bottomLayer.sendServer(new HashMap<>(), new Pair<>("app/create", HTTPMethod.POST));
    }

	public void attack() {
		HashMap<String, String> params = new HashMap<>();
		params.put("col", Integer.toString(attack[0]));
		params.put("row", Integer.toString(attack[1]));

		bottomLayer.sendServer(params, new Pair<>("app/attack", HTTPMethod.POST));
	}

	public void updateMap(String map) {
        GameDecoder.parseMap(this, map);
        ui.printMap(this.map);
        ui.panel.setGame(this.map);
    }

	public int[][] getMap() {
		return map;
	}

}
