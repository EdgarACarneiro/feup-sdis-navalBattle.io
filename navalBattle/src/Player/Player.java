package Player;

import UI.UI_API;

import java.util.HashMap;
import java.util.Map;

public class Player {

	private ServerSender sender;
	private ServerListener listener;

    public Player(String serverIP, String serverPort) {
        sender = new ServerSender(serverIP, serverPort);
        listener = new ServerListener(this);
    	
    	UI_API app = new UI_API();
    }

    public void run() {
        // Como vai o conteudo dos requests po servidor
        Map<String, String> params= new HashMap<>();
        params.put("x", "1");
        params.put("b", "2");

    }

}
