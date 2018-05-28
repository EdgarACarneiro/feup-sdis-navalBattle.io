package Server;

import Communication.CommunicationAPI;
import Communication.UDP.UDPClient;
import Messages.Message;
import Messages.RESTMessage;
import Utils.HigherLayer;

import java.net.InetAddress;
import java.util.Enumeration;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The Class PlayersHandler.
 */
public class PlayersHandler implements Runnable, HigherLayer {

    /** The class this class reports to. */
    Server superior;

    /** Class responsible for checking disconnections. */
    ConnectionChecker checker;

    /** The port. */
    int port;

    /** Mapping of the socket and its respective plaeyr */
    private ConcurrentHashMap<InetAddress, Integer> players;
    
    /** Mapping of the player id to the player udp connection */
    private ConcurrentHashMap<Integer, UDPClient> playersUDP;

    /**
     * Instantiates a new players handler.
     *
     * @param server the server
     * @param port the port
     */
    public PlayersHandler(Server server, int port) {
        superior = server;
        this.port = port;
        checker = new ConnectionChecker(this);
        players = new ConcurrentHashMap<>();
        playersUDP = new ConcurrentHashMap<>();
    }

    /* 
     * @see Utils.HigherLayer#receiveReport(Messages.Message)
     */
    @Override
    public void receiveReport(Message message) {
        if (!(message instanceof RESTMessage))
            System.err.println("Received unexpected type of message");

        RESTMessage restMsg = (RESTMessage) message;
        int clientID;

        InetAddress address = restMsg.getAddress().getAddress();
        if (players.containsKey(address))
            clientID = players.get(address);
        else {
            clientID = players.size();
            players.put(address, clientID);
            playersUDP.put(clientID, new UDPClient(address, FIXED_PORT_ACROSS_APP));
        }
        checker.confirmPlayer(address);

        superior.receiveReport((RESTMessage) message, clientID);
    }

    /* 
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {
        // Starts listening to the respective channel
        CommunicationAPI.channel(this, port);
        checker.run();
    }

    /**
     * Gets the clients Id's.
     *
     * @return the clients id's
     */
    public Enumeration<Integer> getClientsIDs() {
        return playersUDP.keys();
    }

    /**
     * Update client.
     *
     * @param update the update
     * @param clientId the client id
     */
    public void updateClient(String update, int clientId) {
        if (playersUDP.containsKey(clientId)) {
            playersUDP.get(clientId).sendUDP(update);
        } else
            System.err.println("Unable to send message to Client, unknown client ID received: " + clientId);
    }

    /**
     * Disconnect the player by sending a map without its bot, to trigger game over, and by removing him of the ip map and id map
     *
     * @param player the player to be disconnected
     */
    public void disconnectPlayer(InetAddress player) {
        System.out.println("Disconnecting Player: " + player.toString());
        int id = players.get(player);
        players.remove(player);

        shutDownClient(superior.requestGameOver(id), id);
    }

    /**
     * Send the player a game over map
     *
     * @param content game over map
     * @param clientID the client that will lose
     */
    public void shutDownClient(String content, int clientID) {
        UDPClient player = playersUDP.get(clientID);
        player.sendUDP(content);
        playersUDP.remove(clientID);
    }

}
