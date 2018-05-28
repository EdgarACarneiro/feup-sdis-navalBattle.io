package Server;

import Messages.RESTMessage;
import Utils.ThreadPool;
import GameLogic.ServerLogic;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import Utils.Pair;


/**
 * The Class Server.
 */
public class Server {

    /** The handler. */
    private PlayersHandler handler;
    
    /** The thread pool. */
    private ThreadPool threadPool;
    
    /** The game. */
    private ServerLogic game;

    /** The Constant UPDATE_ALL_CLIENTS_TIME. */
    private static final int UPDATE_ALL_CLIENTS_TIME = 500;

    /**
     * Instantiates a new server.
     *
     * @param port the port
     */
    public Server(String port) {
        threadPool = new ThreadPool();
        handler = new PlayersHandler(this, Integer.parseInt(port));
        game = new ServerLogic();

        run();
    }

    /**
     * Runs the handler.
     */
    private void run() {
        threadPool.run(handler);
        startGameUpdates();
    }

    /**
     * METHODS FOR INTERACTION WITH LAYERS
     */

    /**
     * Reply client.
     *
     * @param clientMessage the client message
     * @param statusCode the status code
     * @param content the content
     */
    private void replyClient(RESTMessage clientMessage, int statusCode, String content) {
        threadPool.run(() -> new RESTMessage(clientMessage, statusCode, content).sendAsReply());
    }

    /**
     * Start game updates, by updating all players every 0.5 seconds.
     */
    private void startGameUpdates() {

        threadPool.run(() -> {
            Enumeration<Integer> keys = handler.getClientsIDs();

            while(keys.hasMoreElements()) {
                int key = keys.nextElement();
                threadPool.run(() -> handler.updateClient(requestMap(key), key));
            }
        }, 0, UPDATE_ALL_CLIENTS_TIME);
    }

    /**
     * Receive report.
     * Result of bubbling up functions and reports to superiors.
     *
     * @param clientMessage the client message
     * @param clientID the client ID
     */
    public void receiveReport(RESTMessage clientMessage, int clientID) {
        Pair<String, String> valuableInfo =
                new Pair<>(clientMessage.getContext(), clientMessage.getMethod());

        replyClient(
                clientMessage,
                reportToLogic(valuableInfo, clientMessage.getParams(), clientID),
                "Message from Server"
        );
    }

    /**
     * Report to logic.
     *
     * @param info the info
     * @param params the params
     * @param clientID the client ID
     * @return the response
     */
    private int reportToLogic(Pair<String, String> info, HashMap<String, String> params, int clientID) {
        return game.triggerAction(info, params, clientID);
    }

    /**
     * Remove a player that was inactive for to long, from the game
     *
     * @param id the client id
     */
    public String requestGameOver(int id) {
        game.removePlayer(id);
        return requestMap(id);
    }

    /**
     * Request map.
     *
     * @param clientID the client ID
     * @return the map
     */
    private String requestMap(int clientID) {
        return game.requestMap(clientID);
    }
}
