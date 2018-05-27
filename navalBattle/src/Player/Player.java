package Player;

import Utils.Pair;
import Utils.ThreadPool;
import GameLogic.PlayerLogic;

import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.*;

public class Player {

    private ServerSender sender;
    private ServerListener listener;
    private ThreadPool threadPool;

    public Player(String serverIP, String serverPort) {
        threadPool = new ThreadPool();
        sender = new ServerSender(serverIP, serverPort);
        listener = new ServerListener(this);
        new PlayerLogic(this);
        
        /*try {
        	new UI_API(this);
		} catch (Exception e) {
			e.printStackTrace();
		}*/

        run();
    }

    private void run() {
        threadPool.run(listener);

        //TODO DELETE AFTER, for tests
        threadPool.run(this::teste);
    }

    /**
     * METHODS FOR INTERACTION WITH HIGHER LAYER
     */

    // Method to be called by logic to make requests to the Server
    public boolean sendServer(HashMap<String, String> params, Pair<String, String> route) {
        // TODO posso fazer aqui a cena de repetir três vezes até mandar
        Future result = threadPool.run(() -> sender.sendRequest(route, params));
        try {
            System.out.println(result.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return true;
    }

    public void reportToLogic() {
        //TODO Call a game logic function to pass it the received info
        //TODO mudar tb o valor de retorno
    }

    private void teste() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            HashMap<String, String> teste = new HashMap<>();
            teste.put("value", sc.nextLine());
            sendServer(teste, new Pair<>("app/", "POST"));
        }
    }

}