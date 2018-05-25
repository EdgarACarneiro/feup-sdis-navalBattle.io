package Player;

import UI.UI_API;
import Utils.ThreadPool;

import java.util.Map;

public class Player {

	private ServerSender sender;
	private ServerListener listener;
    private ThreadPool threadPool;

    public Player(String serverIP, String serverPort) {
        threadPool = new ThreadPool();
        sender = new ServerSender(serverIP, serverPort);
        listener = new ServerListener(this);
        // TODO initialize logic here

        run();
        new UI_API();
    }

    private void run() {
        threadPool.run(listener);
    }

    // TODO function might be more complex than this
    public boolean sendServer(Map<String, String> content, String context, String result) {
        // TODO posso fazer aqui a cena de repetir três vezes até mandar
        return threadPool.run(() -> sender.sendRequest(content, context)) == null;

        ///TODO Se pintar passar em result o rsultado? no estilo do map também?
    }

    public void reportToLogic() {
        //TODO Call a game logic function to pass it the received info
        //TODO mudar tb o valor de retorno
    }

    /*
    // Como vai o conteudo dos requests po servidor
    Map<String, String> params= new HashMap<>();
    params.put("x", "1");
    params.put("b", "2");*/

}
