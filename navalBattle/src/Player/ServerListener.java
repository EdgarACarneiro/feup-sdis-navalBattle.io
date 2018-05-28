package Player;

import Communication.CommunicationAPI;
import Messages.Message;
import Messages.UDPMessage;
import Utils.HigherLayer;


/**
 * Class responsible for receiving messages sent by the server.
 *
 * @see ServerEvent
 */
public class ServerListener implements Runnable, HigherLayer {
    
    /** The superior. */
    // Class this class reports to
    Player superior;

    /**
     * Instantiates a new server listener.
     *
     * @param higherlayer the higherlayer
     */
    public ServerListener(Player higherlayer) {
        superior = higherlayer;
    }

    /* 
     * @see Utils.HigherLayer#receiveReport(Messages.Message)
     */
    @Override
    public void receiveReport(Message message) {
        if (message instanceof UDPMessage)
            superior.receiveReport((UDPMessage) message);
        else
            System.err.println("Received unexpected type of message");
    }

    /* (non-Javadoc)
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {
        //Starts listening to the respective channel
        CommunicationAPI.channel(this, FIXED_PORT_ACROSS_APP);
    }
}
