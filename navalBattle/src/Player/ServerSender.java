package Player;

import Communication.REST.HTTPRequest;
import Communication.REST.HTTPMethod;
import Utils.Pair;

import java.util.Map;


/**
 *  Class responsible for sending messages to the server.
 */
public class ServerSender {

    /** The server IP. */
    private String serverIP;

    /** The server port. */
    private int serverPort;

    /**
     * Instantiates a new server sender.
     *
     * @param serverIP the server IP
     * @param serverPort the server port
     */
    ServerSender(String serverIP, String serverPort) {
        this.serverIP = serverIP;
        this.serverPort = Integer.parseInt(serverPort);
    }

    /**
     * Send request.
     *
     * @param route the route
     * @param content the content
     * @return the response
     */
    public int sendRequest(Pair<String, String> route, Map<String, String> content) {
        try {
            HTTPRequest request = new HTTPRequest(serverIP, serverPort);
            return request.makeRequest(route.getValue(), route.getKey(), content);

        } catch (java.io.IOException e) {
            System.err.println("Failed to create Http Request with server " + serverIP + " on port " + serverPort);
            return -1;
        }
    }
}
