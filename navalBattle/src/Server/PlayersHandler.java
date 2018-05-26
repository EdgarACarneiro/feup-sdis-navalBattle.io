package Server;

import Communication.CommunicationAPI;
import Communication.UDP.UDPClient;
import Messages.Message;
import Messages.RESTMessage;
import Utils.HigherLayer;
import Utils.ThreadPool;

import java.net.InetSocketAddress;
import java.util.concurrent.ConcurrentHashMap;

public class PlayersHandler implements Runnable, HigherLayer {

    // Class this class reports to
    Server superior;

    // TODO: Fica mais a cima
    private static final String CONTEXT = "/app";

    int port;

    //Mapeamento do socket a ser usado para cada jogador/cliente, sendo que estes têm um id e o respetivo cliente udp
    private ConcurrentHashMap<InetSocketAddress, Integer> players;
    private ConcurrentHashMap<Integer, UDPClient> playersUDP;

    public PlayersHandler(Server server, int port) {
        superior = server;
        this.port = port;
        players = new ConcurrentHashMap<>();
        playersUDP = new ConcurrentHashMap<>();
    }

    @Override
    public void receiveReport(Message message) {
        if (!(message instanceof RESTMessage))
            System.err.println("Received unexpected type of message");

        RESTMessage restMsg = (RESTMessage) message;
        int clientID;

        if (players.containsKey(restMsg.getAddress()))
            clientID = players.get(restMsg.getAddress());
        else {
            clientID = players.size();
            players.put(restMsg.getAddress(), clientID);
            playersUDP.put(clientID, new UDPClient(restMsg.getAddress(), FIXED_PORT_ACROSS_APP));
        }

        // TODO -- Do stuff with received RESTMessage (bubble it up)
        superior.receiveReport((RESTMessage) message, clientID);
    }

    @Override
    public void run() {
        // Starts listening to the respective channel
        CommunicationAPI.channel(this, port, CONTEXT);
    }

    // TODO function might be more complex than this
    public String sendClient(String content, int clientID) {
        if (!playersUDP.containsKey(clientID))
            return null;

        UDPClient player = playersUDP.get(clientID);
        // TODO posso fazer aqui a cena de repetir três vezes até mandar, pq aqui é udp que só é mandado uma vez
        return player.sendUDP(content);
    }

    public void updateAllClients(String update, ThreadPool threadPool) {
        // TODO: test purposes
        System.out.println("Sending message to " + playersUDP.size() + " players");

        for (UDPClient player : playersUDP.values())
            threadPool.run(() -> player.sendUDP(update));
    }

    public void updateClient(String update, int clientId) {
        if (playersUDP.containsKey(clientId)) {
            playersUDP.get(clientId).sendUDP(update);
        } else
            System.err.println("Unable to send message to Client, unknown client ID received: " + clientId);
    }
}
