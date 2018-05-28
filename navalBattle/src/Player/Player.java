package Player;

import Communication.REST.HTTPCode;
import Messages.UDPMessage;
import Utils.Pair;
import Utils.ThreadPool;
import GameLogic.PlayerLogic;

import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.*;

public class Player {

    private static final int RETRY_TIME = 400;

    private ServerSender sender;
    private ServerListener listener;
    private ThreadPool threadPool;
    private PlayerLogic game;

    public Player(String serverIP, String serverPort) {
        threadPool = new ThreadPool();
        sender = new ServerSender(serverIP, serverPort);
        listener = new ServerListener(this);
        game = new PlayerLogic(this);
        
        run();
    }

    private void run() {
        threadPool.run(listener);
    }

    /**
     * METHODS FOR INTERACTION WITH LAYERS
     */

    // Method to be called by logic to make requests to the Server
    // Tries until up to 3 times of receiving error messages
    public void sendServer(HashMap<String, String> params, Pair<String, String> route) {
        try {
            int waitingTime = 0;
            Random rand = new Random();

            int result;
            do {
                Future future = threadPool.run(() -> sender.sendRequest(route, params), waitingTime);
                waitingTime += rand.nextInt(RETRY_TIME);
                result = (Integer) future.get();

            } while (result != HTTPCode.SUCCESS && result != HTTPCode.UNSUCCESS);

        } catch (InterruptedException | ExecutionException e) {
            System.err.println("Failed to obtain a positive response, after 3 tries!");
        }
    }

    // Result of bubbling up functions
    public void receiveReport(UDPMessage clientMessage) {
        reportToLogic(clientMessage.getContent());
    }

    // No need for threadPool as the threadPool was already launched in UDP level
    public void reportToLogic(String updatedMap) {
        game.updateMap(updatedMap);
    }

}