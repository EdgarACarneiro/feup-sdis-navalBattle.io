package Server;

import Communication.UDPServer;

public class PlayersListener implements Runnable {

    ServerLogic logic;

    UDPServer channel;

    public PlayersListener(ServerLogic serverLogic) {
        logic = serverLogic;
        channel = new UDPServer();
    }

    @Override
    public void run() {
        channel.run();
    }
}
