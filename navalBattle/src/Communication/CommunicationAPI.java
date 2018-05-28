package Communication;

import Communication.REST.RestServer;
import Communication.REST.RestServerHandler;
import Communication.UDP.UDPServer;
import Security.SecurityAPI;
import Server.PlayersHandler;
import Player.ServerListener;


/**
 * The Class CommunicationAPI.
 */
public class CommunicationAPI {

    /**
     * Creates a new Rest Server.
     *
     * @param higherLayer the higher layer
     * @param port the port
     */
    public static void channel(PlayersHandler higherLayer, int port) {
        SecurityAPI.generateCertificate();
        new RestServer(port, "/app", new RestServerHandler(higherLayer)).run();
    }

    /**
     * Creates a new UDP Server.
     *
     * @param higherLayer the higher layer
     * @param port the port
     */
    public static void channel(ServerListener higherLayer, int port) {
        new UDPServer(higherLayer, port).run();
    }

}
