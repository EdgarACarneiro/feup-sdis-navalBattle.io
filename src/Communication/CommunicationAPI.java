package Communication;

import Server.PlayersListener;

public class CommunicationAPI {

    // Primeiro -fazer conexao usando multicasts e cenas
    // Depois de estabelecida a conexao com o utilizador
    // usar comunicação estilo rest

    // INDEPENDENTE DE SE É SERVDIOR OU CLIENTE

    public static void channel(PlayersListener higherLayer) {
        new UDPServer(higherLayer).run();
    }
}
