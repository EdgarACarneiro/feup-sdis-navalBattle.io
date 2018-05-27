package Communication;

import Communication.REST.RestServer;
import Communication.REST.RestServerHandler;
import Communication.UDP.UDPServer;
import Security.SecurityAPI;
import Server.PlayersHandler;
import Player.ServerListener;

public class CommunicationAPI {

    public static void channel(PlayersHandler higherLayer, int port) {
        SecurityAPI.generateCertificate();
        new RestServer(port, "/app", new RestServerHandler(higherLayer)).run();
    }

    public static void channel(ServerListener higherLayer, int port) {
        new UDPServer(higherLayer, port).run();
    }

}
