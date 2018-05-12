package Server;

import Utils.ThreadPool;

public class ServerLogic {

    private ThreadPool threadPool;

    public ServerLogic() {
        threadPool = new ThreadPool();

        // TODO - The threadPool will control: laucnhing threads for answering and signup login and stuff using rest
        // TODO - The main thread handling the game will create other threads for sending udp messages to all the players
    }

    // Uses communication module
}
