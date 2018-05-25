package Server;

import Communication.UDP.UDPClient;
import Messages.RESTMessage;
import Utils.ThreadPool;

import java.util.HashMap;

public class Server {

    private PlayersListener listener;
    private ThreadPool threadPool;

    private static final int UPDATE_ALL_CLIENTS_TIME = 500;

    // Tem que ir p cima istooo
    private static final String CONTEXT = "/app";

    //Mapeamento do socket a ser usado para cada jogador/cliente, sendo que estes têm um id
    HashMap<Integer, UDPClient> players;

    public Server(String port) {
        threadPool = new ThreadPool();
        listener = new PlayersListener(this, Integer.parseInt(port));
        players = new HashMap<>();
        //gameAPI = new GameAPI();

        run();
    }

    private void run() {
        //gameAPI.startGame();
        threadPool.run(listener);
    }

    /**
     * METHODS FOR INTERACTION WITH HIGHER LAYERS
     */

    // TODO function might be more complex than this
    public boolean sendClient(String content, int clientID) {
        if (!players.containsKey(clientID))
            return false;

        // TODO posso fazer aqui a cena de repetir três vezes até mandar, pq aqui é udp que só é mandado uma vez
        UDPClient player = players.get(clientID);
        return threadPool.run(() -> player.sendUDP(content)) == null;
        ///TODO Se pintar passar em result o rsultado? no estilo do map também?
    }

    public void replyClient(RESTMessage clientMessage, int statusCode, String content) {
        threadPool.run(() -> new RESTMessage(clientMessage, statusCode, content).sendAsReply());
    }

    // TODO: Função a ser chamada pela UI quando está pronta a começar o jogo
    public void startGameUpdates() {
        threadPool.run(() -> {
            for (UDPClient player : players.values()) {
                player.sendUDP(requestMap());
            }
        }, 0, UPDATE_ALL_CLIENTS_TIME);
    }

    public void reportToLogic() {
        //TODO Call a game logic function to pass it the received info
        //TODO mudar tb o valor de retorno
    }

    // TODO rquest a string representation of the map from the UI
    public String requestMap() {
        //gameAPI.getMap();
        return "";
    }
}
