package Server;

import Utils.ThreadPool;

import java.net.InetAddress;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Class responsible for checking if there are inactive players
 */
public class ConnectionChecker {

    // 60 seconds of inactivity and the player is disconnected
    public static final int PLAYER_TIME_OUT = 60000;

    // Hasmap relating a player ip with a boolean
    private ConcurrentHashMap<InetAddress, Boolean> inactivityTable;

    private PlayersHandler handler;
    private ThreadPool threadPool;

    public ConnectionChecker(PlayersHandler handler) {
        this.handler = handler;
        this.threadPool = new ThreadPool();
        inactivityTable = new ConcurrentHashMap<>();
    }

    public void run() {

        threadPool.run(() -> {
            for (Map.Entry<InetAddress, Boolean> entry : inactivityTable.entrySet()) {
                threadPool.run(() -> {
                    if (entry.getValue()) {
                        handler.disconnectPlayer(entry.getKey());
                        inactivityTable.remove(entry.getKey());

                    } else
                        inactivityTable.put(entry.getKey(), true);
                });
            }
        }, 0, PLAYER_TIME_OUT);
    }

    public void confirmPlayer(InetAddress player) {
        inactivityTable.put(player, true);
    }
}
