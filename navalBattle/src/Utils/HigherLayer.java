package Utils;

import Messages.Message;

// Class representing a higher layer of abstraction, when compared to the Communication Layer
public interface HigherLayer {

    // TODO: ser o jogador a escolher o port, para caso este nao esteja disponivel
    int FIXED_PORT_ACROSS_APP = 8080;

    void receiveReport(Message message);
}
