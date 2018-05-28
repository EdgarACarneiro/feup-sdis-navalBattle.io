package Server;

import Messages.RESTMessage;
import Utils.ThreadPool;
import GameLogic.ServerLogic;

import java.io.*;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import Utils.Pair;


/**
 * The Class Server.
 */
public class Server implements Serializable {

    /** The Constant UPDATE_ALL_CLIENTS_TIME. */
    private static final int UPDATE_ALL_CLIENTS_TIME = 500;

    /** The Constant representing the name of the serializable file. */
    private static final String SERIALIZABLE_FILE = "Server.ser";

    /** The Constant representing the constant time out used to save to the serializable file - 4 seconds. */
    private static final int SERIALIZABLE_LOOP_TIME = 4000;

    /** The handler. */
    private transient PlayersHandler handler;
    
    /** The thread pool. */
    private transient ThreadPool threadPool;
    
    /** The game. */
    private ServerLogic game;

    private int port;

    /**
     * Instantiates a new server.
     *
     * @param port the port
     */
    public Server(String port) {
        this.port = Integer.parseInt(port);
        threadPool = new ThreadPool();
        handler = new PlayersHandler(this, this.port);
        game = new ServerLogic();

        if (! (new File(SERIALIZABLE_FILE).exists())) {
            try {
                (new File(SERIALIZABLE_FILE)).createNewFile();
            } catch (IOException e) {
                System.err.println("Failed to create serialization file for Server");
            }
        }

        run();
    }

    /**
     * Initialize the server using either serializable files or the default constructors.
     *
     * @return The Server created by serialization or null, if no files were found
     */
    public static void initServer() {
        File serverFile = new File(SERIALIZABLE_FILE);
        Server server = null;

        try {
            if (serverFile.exists()) {
                server = (Server) (new ObjectInputStream(new FileInputStream(serverFile))).readObject();
                server.threadPool = new ThreadPool();
                server.handler = new PlayersHandler(server, server.port);
                server.game.serializeActions();
            }

        } catch (java.io.IOException | java.lang.ClassNotFoundException e) {
            System.err.println("Failed to initialize database from files");
        }

        if (server != null)
            server.run();
        else
            System.err.println("Failed to serialize Server");
    }

    /**
     * Function responsible for launching a thread that saves the Server class every SERIALIZABLE_LOOP_TIME seconds
     */
    private void startSaverThread() {
        threadPool.run(() -> {
            try {
                (new ObjectOutputStream(new FileOutputStream(new File(SERIALIZABLE_FILE)))).writeObject(this);

            } catch (IOException e) {
                System.err.println("Failed to save server for serialization");
                e.printStackTrace();
            }
        }, SERIALIZABLE_LOOP_TIME, SERIALIZABLE_LOOP_TIME);
    }


    /**
     * Runs the handler.
     */
    private void run() {
        threadPool.run(handler);
        startGameUpdates();
        startSaverThread();
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
        String test = game.requestMap(clientID);
        return test;
    }
}
