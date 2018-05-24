package Player;

import Communication.CommunicationAPI;
import Messages.Message;
import Messages.UDPMessage;
import Utils.HigherLayer;

public class ServerListener implements Runnable, HigherLayer {
    // Class this class reports to
    Player superior;

    int port;

    public ServerListener(Player server, int port) {
        superior = server;
        this.port = port;
    }

    @Override
    public void receiveReport(Message message) {
        if (!(message instanceof UDPMessage))
            System.err.println("Received unexpected type of message");

        // TODO -- Do stuff with received UDPMessage (bubble it up)
    }

    @Override
    public void run() {
        //Starts listening to the respective channel
        CommunicationAPI.channel(this, port);
    }
}
