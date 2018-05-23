package Player;

import java.awt.EventQueue;
import java.io.IOException;
import java.net.InetAddress;
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
        //TODO - Uma unica thread, esta thread controla todo o flow do programa.
    }

    public Player(String serverIP, String serverPort) {
    	try {
			InetAddress ip = InetAddress.getByName(serverIP);
		} catch (UnknownHostException readIP) {
			System.err.println("Failed to get IP from " + serverIP);
			readIP.printStackTrace();
		}
    	int port = Integer.parseInt(serverPort);
    	
    	//TODO - Using sockets connect to server.
    	
    	UI_API app = new UI_API();
    }

    public void run() {

    }

}
