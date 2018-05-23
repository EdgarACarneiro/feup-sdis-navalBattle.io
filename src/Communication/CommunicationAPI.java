package Communication;

import Security.SecurityAPI;
import Server.PlayersListener;
import Player.Player;

public class CommunicationAPI {

    public static void channel(PlayersListener higherLayer, int port, String context) {
        //new UDPServer(higherLayer).run();

        SecurityAPI.generateCertificate();
        new RestServer(port, context, new RestServerHandler(higherLayer)).run();
    }

    public static void channel(Player higherLayer) {
        // TODO: Ver o que vamos ter do lado do cliente para receção da mensagens
    }

    //TODO: mais funções que fazem o Request
}
