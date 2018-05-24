package Player;

import Communication.CommunicationAPI;
import Messages.Message;
import Messages.UDPMessage;
import Utils.HigherLayer;

/**
 * Class responsible for receiving messages sent by the server
 */
public class ServerListener implements Runnable, HigherLayer {
    // Class this class reports to
    Player superior;

    // TODO: ser o jogador a escolher o port, para caso este nao esteja disponivel
    private static final int FIXED_PORT_ACROSS_APP = 8080;

    public ServerListener(Player higherlayer) {
        superior = higherlayer;
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
        CommunicationAPI.channel(this, FIXED_PORT_ACROSS_APP);
    }
}
