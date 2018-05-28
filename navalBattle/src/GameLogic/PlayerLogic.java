package GameLogic;

import Communication.REST.HTTPMethod;
import Player.Player;
import UI.UI_API;
import Utils.Pair;
import Utils.ThreadPool;

import java.util.HashMap;


/**
 * The Class PlayerLogic.
 */
public class PlayerLogic {

    /** The Constant TURN_TIME. */
    // Player can only attack a position from 4 to 4 seconds
    private static final int TURN_TIME = 4000;
    
    /** The Constant MAX_POSSIBLE_CONCURRENT_THREADS. */
    private static final int MAX_POSSIBLE_CONCURRENT_THREADS = 3;

    /** The thread pool. */
    private ThreadPool threadPool;
	
	/** The map. */
	private int[][] map;
    
    /** The attack. */
    private int[] attack = {-1, -1};
	
	/** The game over. */
	private boolean gameOver = false;

	/** The ui. */
	private UI.UI_API ui;
	
	/** The bottom layer. */
	private Player bottomLayer;

	/**
	 * Instantiates a new player logic.
	 *
	 * @param bottomLayer the bottom layer
	 */
	public PlayerLogic(Player bottomLayer) {
		this.bottomLayer = bottomLayer;
		threadPool = new ThreadPool(MAX_POSSIBLE_CONCURRENT_THREADS);
		
        try {
        	ui = new UI_API(this);
		} catch (Exception e) {
			System.err.println("Failed to create Player UI");
		}
	}

	/**
	 * Gets the length of the map.
	 *
	 * @return the map length
	 */
	public int getLength() {
		return this.map.length;
	}

	/**
	 * Checks if is game over.
	 *
	 * @return true, if is game over
	 */
	public boolean isGameOver() {
		return gameOver;
	}
	
	/**
	 * Sets the game over.
	 *
	 * @param gameOver the new game over
	 */
	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}
	
	/**
	 * Sets the map.
	 *
	 * @param map the new map
	 */
	public void setMap(int[][] map) {
		this.map = map;
	}

	/**
	 * Allow new attack.
	 */
	private void allowNewAttack() {
        attack = new int[] {-1, -1};
    }
	
    /**
     * Initialize game.
     */
    public void initializeGame() {
        threadPool.run(this::createGame);
    }

    /**
     * Creates the game.
     */
    private void createGame() {
        bottomLayer.sendServer(new HashMap<>(), new Pair<>("app/create", HTTPMethod.POST));
    }

    /**
     * Processes an attack.
     *
     * @param col the col
     * @param row the row
     */
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

	/**
	 * Attack.
	 */
	private void attack() {
		HashMap<String, String> params = new HashMap<>();
		params.put("col", Integer.toString(attack[0]));
		params.put("row", Integer.toString(attack[1]));

		bottomLayer.sendServer(params, new Pair<>("app/attack", HTTPMethod.POST));
	}

	/**
	 * Update map.
	 *
	 * @param map the map
	 */
	public void updateMap(String map) {
        GameDecoder.parseMap(this, map);
        ui.panel.setGame(this.map);
    }

	/**
	 * Gets the map.
	 *
	 * @return the map
	 */
	public int[][] getMap() {
		return map;
	}

}
