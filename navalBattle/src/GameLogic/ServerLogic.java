package GameLogic;

import Communication.REST.HTTPCode;
import Utils.Pair;
import Utils.ThreadPool;

import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;


/**
 * The Class ServerLogic.
 */
public class ServerLogic {

    /** The Constant MAP_DISPLAY_SIZE. */
    public static final int MAP_DISPLAY_SIZE = 25;
    
    /** The Constant SHOW_DESTROYED_BOAT_TIME. */
    public static final int SHOW_DESTROYED_BOAT_TIME = 8000;

    /** The threadpool. */
    private ThreadPool threadpool;
	
	/** The length. */
	private int length;
	
	/** The router. */
	private Router router;

    /** HashMap saving the pos of each boat and its id. */
    private ConcurrentHashMap<String, Integer> boats;

    /** Hashmap to save to each player where its map starts. */
    private ConcurrentHashMap<Integer, String> playersMapsPos;

    /** Concurrent Array displaying the destroyed boats. */
    private CopyOnWriteArrayList<String> destroyedBoats;

    /** Concurrent Array displaying the failed shots. */
    private CopyOnWriteArrayList<String> failedShots;

    /**
     * Instantiates a new server logic.
     */
    public ServerLogic() {
        boats = new ConcurrentHashMap<>();
        playersMapsPos = new ConcurrentHashMap<>();
        destroyedBoats = new CopyOnWriteArrayList<>();
        failedShots = new CopyOnWriteArrayList<>();

        router = new Router(this);
        threadpool = new ThreadPool();

        length = MAP_DISPLAY_SIZE;
    }

	/**
	 * Gets the map starting pos.
	 *
	 * @param player the player
	 * @return the map starting pos
	 */
	public String[] getMapStartingPos(int player) {
        if (playersMapsPos.containsKey(player))
            return playersMapsPos.get(player).split("\\+");
        return new String[] {};
    }

    /**
     * Gets the cell.
     *
     * @param col the col
     * @param row the row
     * @param id the id
     * @return the cell
     */
    public int getCell(int col, int row, int id) {
        String pos = col + "+" + row;

        if (boats.containsKey(pos)) {
            if (boats.get(pos) == id)
                return GameCells.BOAT;
        }

        if (destroyedBoats.contains(pos))
            return GameCells.DESTROYED_BOAT;

        if (failedShots.contains(pos))
            return GameCells.FAILED_ATTACK;

        return GameCells.WATER;
    }

	/**
	 * Attack.
	 *
	 * @param params the params
	 * @param playerId the player id
	 * @return the HTTPCode
	 */
	public int attack(HashMap<String, String> params, Integer playerId) {
        String pos = params.get("col") + "+" + params.get("row");

        if (boats.containsKey(pos)) {
            playersMapsPos.remove(boats.get(pos));
            boats.remove(pos);

            destroyedBoats.add(pos);
            threadpool.run(() -> cleanBot(pos), SHOW_DESTROYED_BOAT_TIME);
            return HTTPCode.SUCCESS;
        }

        failedShots.add(pos);
        threadpool.run(() -> cleanShot(pos), SHOW_DESTROYED_BOAT_TIME);
        return HTTPCode.UNSUCCESS;
    }

    /**
     * Clean all destroyed boats.
     *
     * @param pos the position
     */
    private void cleanBot(String pos) {
        destroyedBoats.remove(pos);
    }

    /**
     * Clean all failed shots.
     *
     * @param pos the position
     */
    private void cleanShot(String pos) {
        failedShots.remove(pos);
    }
	
	/**
	 * New player.
	 *
	 * @param params the params
	 * @param playerId the player id
	 * @return the HTTPcode
	 */
	public int newPlayer(HashMap<String, String> params, Integer playerId) {
		length += 1;
		
        Random rand = new Random();
        int col; int row;
        // Since the map is never densely populated (About 1 / (25*25), 76/(100 * 100)) it is not a problem to use trial and error
        // Probably often even faster than iterating over the boats container
        do {
            col = rand.nextInt(length);
            row = rand.nextInt(length);
        } while (boats.containsKey(col + "+" + row));
		
		boats.put(col + "+" + row, playerId);
		addStartingPos(col, row, playerId);

		return HTTPCode.SUCCESS;
	}

	/**
	 * Adds the starting pos.
	 *
	 * @param col the col
	 * @param row the row
	 * @param id the id
	 */
	public void addStartingPos(int col, int row, int id) {
        int colOffset = col - (MAP_DISPLAY_SIZE / 2);
        int rowOffset = row - (MAP_DISPLAY_SIZE / 2);
        int startCol = 0;
        int startRow = 0;

        if (colOffset > 0)
            startCol = colOffset;
        if (rowOffset > 0)
            startRow = rowOffset;
        playersMapsPos.put(id, startCol + "+" + startRow);
    }

	/**
	 * Request map.
	 *
	 * @param id the id
	 * @return the string
	 */
	public String requestMap(int id) {
		return GameEncoder.encodeForPlayer(this, id);
	}

	/**
	 * Trigger action.
	 *
	 * @param route the route
	 * @param params the params
	 * @param clientID the client ID
	 * @return the response
	 */
	public int triggerAction(Pair<String, String> route, HashMap<String, String> params, int clientID) {
        return router.callAction(route, params, clientID);
    }
}