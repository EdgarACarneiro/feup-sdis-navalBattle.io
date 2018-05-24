package Communication;

import java.io.IOException;
import java.io.OutputStream;

import Messages.RESTMessage;
import Utils.HigherLayer;
import Utils.ThreadPool;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class RestServerHandler implements HttpHandler {

    /**
     * ThreadPool to quickly dispatch the received UDP received messages.
     * Dispatcher in this architecture level to minimize the waiting time of message in the UDP channel
     */
    private ThreadPool dispatcher;

	private HigherLayer superior;

	public RestServerHandler(HigherLayer higherlayer) {
        superior = higherlayer;
        dispatcher = new ThreadPool();
        // TODO -> Ver as alterações aqui com o Rubén, semelhantes ao UDPServer, para retirar logica de interpretação daqui
    }

	public void handle(HttpExchange exchange) {
        dispatcher.run(() -> reportToSuperior(exchange));
	}

	public static void sendResponse(HttpExchange t, int statusCode, String response) {
		try {
			t.sendResponseHeaders(statusCode, response.getBytes().length);
			OutputStream os = t.getResponseBody();
			os.write(response.getBytes(), 0, response.getBytes().length);
			os.flush();
			os.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

    private void reportToSuperior(HttpExchange exchange) {
        superior.receiveReport(new RESTMessage(exchange));
    }
}