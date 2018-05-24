package Communication;

import Security.SecurityAPI;
import Server.PlayersListener;
import Player.ServerListener;

public class CommunicationAPI {

    public static void channel(PlayersListener higherLayer, int port, String context) {
        SecurityAPI.generateCertificate();
        new RestServer(port, context, new RestServerHandler(higherLayer)).run();
    }

    public static void channel(ServerListener higherLayer, int port) {
        new UDPServer(higherLayer, port).run();
    }

    //TODO: mais funções que fazem o Request
}
