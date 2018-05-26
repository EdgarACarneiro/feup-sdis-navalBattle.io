package Server;

import Messages.RESTMessage;
import Utils.ThreadPool;
import GameLogic.ServerLogic;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import GameLogic.Routes;

import Utils.Pair;
import Utils.RESTMethod;

public class Server {

    private PlayersHandler handler;
    private ThreadPool threadPool;
    private ServerLogic game;
    private Routes routes;

    private static final int UPDATE_ALL_CLIENTS_TIME = 500;

    public Server(String port) {
        threadPool = new ThreadPool();
        handler = new PlayersHandler(this, Integer.parseInt(port));
        game = new ServerLogic();
        routes = new Routes(this);

        //route testing
        RESTMethod r = RESTMethod.GET;
        String action = "updateGame";
        Pair<String, RESTMethod> url = new Pair<String, RESTMethod>(action, r);
        routes.callAction(url, "teste");

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

        game.newPlayer(0);
        threadPool.run(() -> {
            Enumeration<Integer> keys = handler.getClientsIDs();

            while(keys.hasMoreElements()) {
                int key = keys.nextElement();
                threadPool.run(() -> handler.updateClient(requestMap(key), key));
            }
        }, 0, UPDATE_ALL_CLIENTS_TIME);
    }

    // TODO: to be called by UI when some1 shot my boat or smth
    public void noticeClient(String content, int id) {
        handler.updateClient(content, id);
    }

    // TODO change ver o que ele vai receber
    public void receiveReport(RESTMessage clientMessage, int clientID) {
        //String statusCode =
        reportToLogic(clientMessage.getContext(), clientMessage.getParams(), clientID);
        // TODO: so para testar, tem de ser mudado
        replyClient(clientMessage, 200 /* statusCode */, "PINTA BRO");
    }

    public void reportToLogic(String context, HashMap params, int clientID) {
        //TODO Call a game logic function to pass it the received info
        //TODO mudar tb o valor de retorno
    }

    // TODO request a string representation of the map from the UI -> or the update to send wtv
    public String requestMap(int clientID) {
        game.updateMap();
        return game.requestMap(clientID);
    }

    public void updateGame(){
        System.out.println("update the game to everyone here");
    }

     public void create(){
        System.out.println("create a new player here");
    }
}
