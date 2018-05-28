package Server;

import Messages.RESTMessage;
import Utils.ThreadPool;
import GameLogic.ServerLogic;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import Utils.Pair;

public class Server {

    private PlayersHandler handler;
    private ThreadPool threadPool;
    private ServerLogic game;

    private static final int UPDATE_ALL_CLIENTS_TIME = 500;

    public Server(String port) {
        threadPool = new ThreadPool();
        handler = new PlayersHandler(this, Integer.parseInt(port));
        game = new ServerLogic();

        run();
    }

    private void run() {
        threadPool.run(handler);
        startGameUpdates();
    }

    /**
     * METHODS FOR INTERACTION WITH LAYERS
     */

    private void replyClient(RESTMessage clientMessage, int statusCode, String content) {
        threadPool.run(() -> new RESTMessage(clientMessage, statusCode, content).sendAsReply());
    }

    // Function that updates all the players board every 0.5 seconds
    private void startGameUpdates() {

        threadPool.run(() -> {
            Enumeration<Integer> keys = handler.getClientsIDs();

            while(keys.hasMoreElements()) {
                int key = keys.nextElement();
                threadPool.run(() -> handler.updateClient(requestMap(key), key));
            }
        }, 0, UPDATE_ALL_CLIENTS_TIME);
    }

    // Result of bubbling up functions
    public void receiveReport(RESTMessage clientMessage, int clientID) {
        Pair<String, String> valuableInfo =
                new Pair<>(clientMessage.getContext(), clientMessage.getMethod());

        replyClient(
                clientMessage,
                reportToLogic(valuableInfo, clientMessage.getParams(), clientID),
                "Message from Server"
        );
    }

    private int reportToLogic(Pair<String, String> info, HashMap<String, String> params, int clientID) {
        return game.triggerAction(info, params, clientID);
    }

    private String requestMap(int clientID) {
        return game.requestMap(clientID);
    }
}
