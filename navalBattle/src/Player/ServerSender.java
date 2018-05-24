package Player;

import Communication.REST.HTTPRequest;
import Communication.REST.HttpMethod;

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

    public boolean send(Map<String, String> content) {
        HTTPRequest request;

        try {
            request = new HTTPRequest(serverIP, serverPort);
            request.makeRequest(HttpMethod.GET,"app/create", content);
        } catch (java.io.IOException e) {
            System.err.println("Failed to create Http Request with server " + serverIP + " on port " + serverPort);
            return false;
        }
        return true;
    }
}
