package Server;

import Authentication.AuthManager;
import Communication.CommunicationAPI;
import Utils.ThreadPool;

public class ServerLogic {

    private static final int NUM_MAIN_THREADS = 2;

    private ThreadPool threadPool;

    private CommunicationAPI communication;

    private AuthManager authentication;

    private PlayersListener listener;

    public ServerLogic() {
        threadPool = new ThreadPool(NUM_MAIN_THREADS);
        communication = new CommunicationAPI();
        authentication = new AuthManager();
        listener = new PlayersListener(this);

        run();
        // TODO - The threadPool will control: laucnhing threads for answering and signup login and stuff using rest
        // TODO - The main thread handling the game will create other threads for sending udp messages to all the players
    }

    private void run() {
        threadPool.run(authentication);
        threadPool.run(listener);
    }

    // Delegated methods - called to GameLogic
    public void updatePlayers() {
        // TODO - use UDP to send attribute that is game state to all n the game or someshit
    }

    public void sendPlayerAction() {
        // TODO - notify a player sth happened - if failed a shoot, got a shoot or even got shot.
    }

    // Bubbling up methods - passed to GameLogic
    public void receivedAction() {
        // TODO - pass action to GameLogic on ServerSide, either its moving, shooting, etc
    }

    public void newPlayer() {
        // TODO - pass action to GameLogic on ServerSide, server side will after call a method to send the player its position, probably a new board
    }

    // TODO: ... other bubbling up methods...
}
