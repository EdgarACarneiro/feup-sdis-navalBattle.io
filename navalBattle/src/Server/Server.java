package Server;

import Communication.UDP.UDPClient;
import Messages.RESTMessage;
import Utils.ThreadPool;

public class Server {

    private PlayersHandler listener;
    private ThreadPool threadPool;

    private static final int UPDATE_ALL_CLIENTS_TIME = 500;

    // Tem que ir p cima istooo
    private static final String CONTEXT = "/app";

    public Server(String port) {
        threadPool = new ThreadPool();
        listener = new PlayersHandler(this, Integer.parseInt(port));
        //gameAPI = new GameAPI();

        run();
    }

    private void run() {
        //gameAPI.startGame();
        threadPool.run(listener);

        // TODO Eliminar, está só para teste
        startGameUpdates();
    }

    /**
     * METHODS FOR INTERACTION WITH HIGHER LAYERS
     */

    public boolean sendClient(String content, int clientID) {
        return threadPool.run(() -> listener.sendClient(content, clientID)) == null;

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

    // TODO change ver o que ele vai receber
    public void receiveReport(RESTMessage clientMessage, int clientID) {

        // TODO: so para testar, tem de ser mudado
        replyClient(clientMessage, 200, "PINTA BRO");
    }

    public void reportToLogic() {
        //TODO Call a game logic function to pass it the received info
        //TODO mudar tb o valor de retorno
    }

    // TODO request a string representation of the map from the UI -> or the update to send wtv
    public String requestMap() {
        //gameAPI.getMap();
        // TODO change- only ffor testing
        return "CHICABALA";
    }
}
