package Server;

import Communication.CommunicationAPI;
import Messages.UDPMessage;

public class PlayersListener implements Runnable {

    ServerLogic logic;

    public PlayersListener(ServerLogic serverLogic) {
        logic = serverLogic;
    }

    public void receiveReport(UDPMessage message) {
        // TODO -- Do stuff with received UDPMessage (bubble it up)
    }

    @Override
    public void run() {
        // Starts listening to the respective channel
        CommunicationAPI.channel(this);
    }
}
