package Server;

import Messages.RESTMessage;
import Utils.ThreadPool;
import GameLogic.ServerLogic;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import Utils.Pair;

public class Server {

    private PlayersHandler handler;
    private ThreadPool threadPool;
    private ServerLogic game;

    private static final int UPDATE_ALL_CLIENTS_TIME = 500;

    public Server(String port) {
        threadPool = new ThreadPool();
        handler = new PlayersHandler(this, Integer.parseInt(port));
        game = new ServerLogic();

        run();
    }

    private void run() {
        game.updateMap();
        threadPool.run(handler);

        // TODO Eliminar, está só para teste
        startGameUpdates();
    }

    /**
     * METHODS FOR INTERACTION WITH HIGHER LAYERS
     */

    public boolean sendClient(String content, int clientID) {
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
            Enumeration<Integer> keys = handler.getClientsIDs();

            while(keys.hasMoreElements()) {
                int key = keys.nextElement();
                threadPool.run(() -> handler.updateClient(requestMap(key), key));
            }
        }, 0, UPDATE_ALL_CLIENTS_TIME);
    }

    // TODO: to be called by logic when some1 shot my boat or smth - MBY IMPLEMENT
    public void noticeClient(String content, int id) {
        handler.updateClient(content, id);
    }

    // Result of bubbling up functions
    public void receiveReport(RESTMessage clientMessage, int clientID) {
        Pair<String, String> valuableInfo =
                new Pair<>(clientMessage.getContext(), clientMessage.getMethod());

        replyClient(
                clientMessage,
                reportToLogic(valuableInfo, clientMessage.getParams(), clientID),
                "Mapa talvez??"
        );
    }

    public int reportToLogic(Pair<String, String> info, HashMap<String, String> params, int clientID) {
        return game.triggerAction(info, params, clientID);
    }

    // TODO request a string representation of the map from the logic -> or the update to send wtv
    public String requestMap(int clientID) {
        game.updateMap();
        return game.requestMap(clientID);
    }
}
