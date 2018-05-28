package Server;

import Communication.CommunicationAPI;
import Communication.UDP.UDPClient;
import Messages.Message;
import Messages.RESTMessage;
import Utils.HigherLayer;

import java.net.InetAddress;
import java.util.Enumeration;
import java.util.concurrent.ConcurrentHashMap;

public class PlayersHandler implements Runnable, HigherLayer {

    // Class this class reports to
    Server superior;
    ConnectionChecker checker;
    int port;


    //Mapeamento do socket a ser usado para cada jogador/cliente, sendo que estes têm um id e o respetivo cliente udp
    private ConcurrentHashMap<InetAddress, Integer> players;
    private ConcurrentHashMap<Integer, UDPClient> playersUDP;

    public PlayersHandler(Server server, int port) {
        superior = server;
        this.port = port;
        checker = new ConnectionChecker(this);
        players = new ConcurrentHashMap<>();
        playersUDP = new ConcurrentHashMap<>();
    }

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

    @Override
    public void run() {
        // Starts listening to the respective channel
        CommunicationAPI.channel(this, port);
        checker.run();
    }

    public Enumeration<Integer> getClientsIDs() {
        return playersUDP.keys();
    }

    public void updateClient(String update, int clientId) {
        if (playersUDP.containsKey(clientId)) {
            playersUDP.get(clientId).sendUDP(update);
        } else
            System.err.println("Unable to send message to Client, unknown client ID received: " + clientId);
    }

    public void disconnectPlayer(InetAddress player) {
        int id = players.get(player);
        players.remove(player);

        playersUDP.remove(id);
    }
}
