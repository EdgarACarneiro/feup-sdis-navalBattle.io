package GameLogic;

import Communication.REST.HTTPCode;
import Utils.Pair;

import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class ServerLogic {

    public static final int MAP_DISPLAY_SIZE = 25;

	private int length;
	private Router router;

    /**
     * HashMap saving the pos of each boat and its id
     */
    private ConcurrentHashMap<String, Integer> boats;

    /**
     * Hashmap to save to each player where its map starts
     */
    private ConcurrentHashMap<Integer, String> playersMapsPos;

    private CopyOnWriteArrayList<String> destroyedBoats;

    public ServerLogic() {
        boats = new ConcurrentHashMap<>();
        playersMapsPos = new ConcurrentHashMap<>();
        length = MAP_DISPLAY_SIZE;
        router = new Router(this);
        destroyedBoats = new CopyOnWriteArrayList<>();
    }

	public String getMapStartingPos(int player) {
        if (playersMapsPos.containsKey(player))
            return playersMapsPos.get(player);
        return null;
    }

    public int getCell(int col, int row, int id) {
        String pos = col + "+" + row;

        if (boats.containsKey(pos)) {
            if (boats.get(pos) == id)
                return GameCells.BOAT;
        }

        if (destroyedBoats.contains(pos))
            return GameCells.DESTROYED_BOAT;

        return GameCells.WATER;
    }

	public int attack(HashMap<String, String> params, Integer playerId) {
        String pos = params.get("col") + "+" + params.get("row");

        if (boats.containsKey(pos)) {
            playersMapsPos.remove(boats.get(pos));
            boats.remove(pos);

            destroyedBoats.add(pos);
            return HTTPCode.SUCCESS;
        }
        return HTTPCode.UNSUCCESS;
    }
	
	public int newPlayer(HashMap<String, String> params, Integer playerId) {
		length += 1;
		
        Random rand = new Random();
        int col; int row;
        do {
            col = rand.nextInt(length);
            row = rand.nextInt(length);
        } while (boats.containsKey(col + "+" + row));
		
		boats.put(col + "+" + row, playerId);
		addStartingPos(col, row, playerId);

		return HTTPCode.SUCCESS;
	}

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

	public String requestMap(int id) {
		return GameEncoder.encodeForPlayer(this, id);
	}

	public int triggerAction(Pair<String, String> route, HashMap<String, String> params, int clientID) {
        return router.callAction(route, params, clientID);
    }
}