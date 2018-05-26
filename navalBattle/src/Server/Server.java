package Server;

import Messages.RESTMessage;
import Utils.ThreadPool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class Server {

    private PlayersHandler handler;
    private ThreadPool threadPool;

    private static final int UPDATE_ALL_CLIENTS_TIME = 500;

    public Server(String port) {
        threadPool = new ThreadPool();
        handler = new PlayersHandler(this, Integer.parseInt(port));
        //gameAPI = new GameAPI();

        run();
    }

    private void run() {
        //gameAPI.startGame();
        threadPool.run(handler);

        // TODO Eliminar, está só para teste
        startGameUpdates();
    }

    /**
     * METHODS FOR INTERACTION WITH HIGHER LAYERS
     */

    public boolean sendClient(String content, int clientID) {
        // Missing the get to get the value
        Future result = threadPool.run(() -> handler.sendClient(content, clientID));

        // TODO Faze cenas enquanto a outra thread corre
        try {
            return result.get() == null;
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        return false;
        // TODO Se pintar passar em result o rsultado? no estilo do map também?
    }

    public void replyClient(RESTMessage clientMessage, int statusCode, String content) {
        threadPool.run(() -> new RESTMessage(clientMessage, statusCode, content).sendAsReply());
    }

    // TODO: Função a ser chamada pela UI quando está pronta a começar o jogo
    public void startGameUpdates() {
        threadPool.run(() -> {
            handler.updateAllClients(requestMap(), threadPool);
        }, 0, UPDATE_ALL_CLIENTS_TIME);
    }

    // TODO: to be called by UI when some1 shot my boat or smth
    public void noticeClient(String content, int id) {
        handler.updateClient(content, id);
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
