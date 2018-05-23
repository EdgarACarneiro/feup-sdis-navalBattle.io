package Player;

import java.awt.EventQueue;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import Communication.HTTPRequest;
import Communication.HttpMethod;
import UI.UI_API;

public class Player {

    public static void main(String[] args) throws UnknownHostException, IOException {
		HTTPRequest request=new HTTPRequest("192.168.1.4",9999);
		Map<String, String> params= new HashMap<>();
		params.put("x", "1");
		params.put("b", "2");
		request.makeRequest(HttpMethod.GET,"app/create", params);

	}
    public Player() {
        //TODO - Uma unica thread, esta thread controla todo o flow do programa
    }

    public Player(String serverIP, String serverPort) {

    }

    public void run() {

    }

}
