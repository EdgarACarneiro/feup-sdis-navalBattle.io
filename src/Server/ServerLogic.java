package Server;

import Communication.CommunicationAPI;
import Utils.ThreadPool;

public class ServerLogic {

    private static final int NUM_MAIN_THREADS = 2;

    private ThreadPool threadPool;

    private CommunicationAPI communication;

    public ServerLogic() {
        threadPool = new ThreadPool(NUM_MAIN_THREADS);

        // TODO - The threadPool will control: laucnhing threads for answering and signup login and stuff using rest
        // TODO - The main thread handling the game will create other threads for sending udp messages to all the players
    }

    public void run() {

    }

    // Uses communication module
}
