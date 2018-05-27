package Player;

import Utils.ThreadPool;
import GameLogic.GameAPI;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.*;
import GameLogic.Router;
import GameLogic.GameEncoder;
import Utils.Pair;
import Utils.RESTMethod;

public class Player {

    private ServerSender sender;
    private ServerListener listener;
    private ThreadPool threadPool;
    private GameAPI game;
    private Router router;

    public Player(String serverIP, String serverPort) {
        threadPool = new ThreadPool();
        sender = new ServerSender(serverIP, serverPort);
        listener = new ServerListener(this);
        game = new GameAPI();
        router = new Router(this);
        
        /*try {
        	new UI_API(this);
		} catch (Exception e) {
			e.printStackTrace();
		}*/

        //route testing
        RESTMethod r = RESTMethod.POST;
        String action = "move";
        Pair<String, RESTMethod> url = new Pair<String, RESTMethod>(action, r);
        router.callAction(url, "move");

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

    // TODO function might be more complex than this
    public boolean sendServer(Map<String, String> content, String context) {
        // TODO posso fazer aqui a cena de repetir três vezes até mandar
        Future result = threadPool.run(() -> sender.sendRequest(context, content));
        try {
            System.out.println(result.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return true;

        ///TODO Ao pintar passar o resultado para o dispatcher do Vitor
    }

    public void reportToLogic() {
        //TODO Call a game logic function to pass it the received info
        //TODO mudar tb o valor de retorno
    }

    /*
    // Como vai o conteudo dos requests po servidor
    Map<String, String> params= new HashMap<>();
    params.put("x", "1");
    params.put("b", "2");
    */


    public void attack(int col, int row){
    	HashMap<String, String> attack = new HashMap<>();
    	
        if (game.set(col, row)) {
        	attack.put("attack", GameEncoder.sendMove(game));
        	sendServer(attack, "app/");
        	// TODO - How to send 
        }
    }

    public void create(){
    	HashMap<String, String> creationReq = new HashMap<>();
    	
    	creationReq.put("create", "username?");
    	sendServer(creationReq, "create");
    	// TODO - How to send 
    }

    private void teste() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            HashMap<String, String> teste = new HashMap<>();
            teste.put("value", sc.nextLine());
            sendServer(teste, "app/");
        }
    }

}