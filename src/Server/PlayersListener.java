package Server;

import Communication.CommunicationAPI;
import Messages.UDPMessage;

public class PlayersListener implements Runnable {

    // Class this class reports to
    Server superior;

    // TODO: Provavelmente fica mais a cima
    private static final String CONTEXT = "/app";

    int port;

    public PlayersListener(Server server, int port) {
        superior = server;
        this.port = port;
    }

    public void receiveReport(UDPMessage message) {
        // TODO -- Do stuff with received UDPMessage (bubble it up)
    }

    @Override
    public void run() {
        // Starts listening to the respective channel
        CommunicationAPI.channel(this, port, CONTEXT);
    }
}
