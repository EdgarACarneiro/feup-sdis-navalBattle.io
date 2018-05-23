package Communication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.HashMap;

import Messages.RESTMessage;
import Utils.HigherLayer;
import Utils.ThreadPool;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import Communication.HttpMethod;

public class RestServerHandler implements HttpHandler {

    /**
     * ThreadPool to quickly dispatch the received UDP received messages.
     * Dispatcher in this architecture level to minimize the waiting time of message in the UDP channel
     */
    private ThreadPool dispatcher;

	private HigherLayer higherLayer;

	public RestServerHandler(HigherLayer higherlayer) {
        this.higherLayer = higherlayer;
        // TODO -> Ver as alterações aqui com o Rubén, semelhantes ao UDPServer, para retirar logica de interpretação daqui
    }

	public void handle(HttpExchange exchange) throws IOException {

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
        higherLayer.receiveReport(new RESTMessage(exchange);
    }
}