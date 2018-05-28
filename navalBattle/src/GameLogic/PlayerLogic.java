package GameLogic;

import Communication.REST.HTTPMethod;
import Player.Player;
import UI.UI_API;
import Utils.Pair;
import Utils.ThreadPool;

import java.util.HashMap;

public class PlayerLogic {

    // Player can only attack a position from 4 to 4 seconds
    private static final int TURN_TIME = 4000;
    private static final int MAX_POSSIBLE_CONCURRENT_THREADS = 3;

    private ThreadPool threadPool;
	private int[][] map;
    private int[] attack = {-1, -1};
	private boolean gameOver = false;

	private UI.UI_API ui;
	private Player bottomLayer;

	public PlayerLogic(Player bottomLayer) {
		this.bottomLayer = bottomLayer;
		threadPool = new ThreadPool(MAX_POSSIBLE_CONCURRENT_THREADS);
		
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
	
	public void setMap(int[][] map) {
		this.map = map;
	}

	private void allowNewAttack() {
        attack = new int[] {-1, -1};
    }
	
    public void initializeGame() {
        threadPool.run(this::createGame);
    }

    private void createGame() {
        bottomLayer.sendServer(new HashMap<>(), new Pair<>("app/create", HTTPMethod.POST));
    }

    public void attack(int col, int row) {
        if (col < 0 || map.length <= col) {
            System.err.println("Unknown column selected");
            return;
        }

        if (row < 0 || map[col].length <= row) {
            System.err.println("Unknown row selected");
            return;
        }

        if (map[col][row] != GameCells.WATER) {
            System.err.println("Unable to attack selected position");
            return;
        }

        if (attack == new int[] {-1, -1}) {
            System.err.println("You have to wait 4 seconds before attacking again!");
            return;
        }

        attack = new int[]{col, row};
        threadPool.run((Runnable) this::attack);
        threadPool.run(this::allowNewAttack, TURN_TIME);
    }

	private void attack() {
		HashMap<String, String> params = new HashMap<>();
		params.put("col", Integer.toString(attack[0]));
		params.put("row", Integer.toString(attack[1]));

		bottomLayer.sendServer(params, new Pair<>("app/attack", HTTPMethod.POST));
	}

	public void updateMap(String map) {
        GameDecoder.parseMap(this, map);
        ui.panel.setGame(this.map);
    }

	public int[][] getMap() {
		return map;
	}

}
