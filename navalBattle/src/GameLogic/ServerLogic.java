package GameLogic;

import java.util.concurrent.ConcurrentHashMap;

public class ServerLogic {

    /**
     * HashMap saving the id of the boat associated to each userName (user logged)
     */
    ConcurrentHashMap<String, Integer> usersBoats;
}
