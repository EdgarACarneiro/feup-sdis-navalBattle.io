package Utils;

import Messages.Message;

// Class representing a higher layer of abstraction, when compared to the Communication Layer
public interface HigherLayer {

    void receiveReport(Message message);
}
