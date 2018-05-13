package GameLogic;

import java.util.concurrent.ConcurrentHashMap;

public class ServerGameLogic {

    /**
     * HashMap saving the id of the boat associated to each userName (user logged)
     */
    ConcurrentHashMap<String, Integer> usersBoats;
}
