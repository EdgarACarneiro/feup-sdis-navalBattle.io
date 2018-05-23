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

	private InetAddress serverIP;

	private int serverPort;

    public static void main(String[] args) throws UnknownHostException, IOException {
		HTTPRequest request=new HTTPRequest("192.168.1.4",9999);
		Map<String, String> params= new HashMap<>();
		params.put("x", "1");
		params.put("b", "2");
		request.makeRequest(HttpMethod.GET,"app/create", params);

	}

    public Player(String serverIP, String serverPort) {
    	try {
			this.serverIP = InetAddress.getByName(serverIP);
		} catch (UnknownHostException readIP) {
			System.err.println("Failed to get IP from " + serverIP);
			readIP.printStackTrace();
		}
    	this.serverPort = Integer.parseInt(serverPort);
    	
    	//TODO - Using sockets connect to server.
    	
    	UI_API app = new UI_API();
    }

    public void run() {

    }

}
