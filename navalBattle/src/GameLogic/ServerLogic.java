package GameLogic;

import Communication.REST.HTTPCode;
import Utils.Pair;

import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class ServerLogic {

    public static final int MAP_DISPLAY_SIZE = 25;

	private int length;
	private Router router;

    /**
     * HashMap saving the pos of each boat and its id
     */
    private ConcurrentHashMap<String, Integer> boats;

    public ServerLogic() {
        boats = new ConcurrentHashMap<>();
        length = MAP_DISPLAY_SIZE;
        router = new Router(this);
    }
	
	public int getLength() {
		return length;
	}
	
	public int getFromId(int col, int row, int id) {
		if (boats.get(col + "+" + row) == id || boats.get(col + "+" + row) == -1 || boats.get(col + "+" + row) == -2)
			return boats.get(col + "+" + row);
		return -1;
	}

	public int attack(HashMap<String, String> params, Integer playerId) {
        String col = params.get("col");
        String row = params.get("row");

        if (boats.containsKey("")) {
            boats.put(col + "+" + row, GameCells.DESTROYED_BOAT);
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
		return HTTPCode.SUCCESS;
	}

	public String requestMap(int id) {
		return GameEncoder.encodeForPlayer(this, id);
	}

	public int triggerAction(Pair<String, String> route, HashMap<String, String> params, int clientID) {
        return router.callAction(route, params, clientID);
    }
}