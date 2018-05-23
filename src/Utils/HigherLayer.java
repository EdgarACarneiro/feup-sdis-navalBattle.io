package Utils;

import Messages.RESTMessage;

// Class representing a higher layer of abstraction, when compared to the Communication Layer
public interface HigherLayer {

    void receiveReport(RESTMessage message);
}
