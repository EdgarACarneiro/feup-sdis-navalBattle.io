package Player;

import Communication.REST.HTTPRequest;
import Communication.REST.HTTPMethod;

import java.util.Map;

/**
 *  Class responsible for sending messages to the server
 */
public class ServerSender {

    private String serverIP;

    private int serverPort;

    ServerSender(String serverIP, String serverPort) {
        this.serverIP = serverIP;
        this.serverPort = Integer.parseInt(serverPort);
    }

    public String sendRequest(String context, Map<String, String> content) {
        try {
            HTTPRequest request = new HTTPRequest(serverIP, serverPort);
            return request.makeRequest(HTTPMethod.GET, context, content);

        } catch (java.io.IOException e) {
            System.err.println("Failed to create Http Request with server " + serverIP + " on port " + serverPort);
            return null;
        }
    }
}
