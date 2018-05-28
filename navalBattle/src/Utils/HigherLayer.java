package Utils;

import Messages.Message;


/**
 * The Interface HigherLayer representing a higher layer of abstraction, when compared to the Communication Layer.
 */

public interface HigherLayer {

    /** The fixed port across app. */
    // TODO: ser o jogador a escolher o port, para caso este nao esteja disponivel
    int FIXED_PORT_ACROSS_APP = 8080;

    /**
     * Receive report.
     *
     * @param message the message
     */
    void receiveReport(Message message);
}
