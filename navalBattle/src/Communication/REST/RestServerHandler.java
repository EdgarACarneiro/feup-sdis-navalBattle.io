package Communication.REST;

import java.io.IOException;
import java.io.OutputStream;

import Messages.RESTMessage;
import Utils.HigherLayer;
import Utils.ThreadPool;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

/**
 * The Class RestServerHandler.
 */
public class RestServerHandler implements HttpHandler {

    /**
     * ThreadPool to quickly dispatch the received messages.
     * Dispatcher in this architecture level to minimize the waiting time of message in the REST channel
     */
    private ThreadPool dispatcher;

	/** The superior. */
	private HigherLayer superior;

	/**
	 * Instantiates a new rest server handler.
	 *
	 * @param higherlayer the higherlayer
	 */
	public RestServerHandler(HigherLayer higherlayer) {
        superior = higherlayer;
        dispatcher = new ThreadPool();
    }

	/* 
	 * @see com.sun.net.httpserver.HttpHandler#handle(com.sun.net.httpserver.HttpExchange)
	 */
	public void handle(HttpExchange exchange) {
		dispatcher.run(() -> reportToSuperior(exchange));
	}

	/**
	 * Send response.
	 *
	 * @param t the HttpExchange
	 * @param statusCode the status code
	 * @param response the response
	 */
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

    /**
     * Reports to superior.
     *
     * @param exchange the exchange
     */
    private void reportToSuperior(HttpExchange exchange) {
	    superior.receiveReport(new RESTMessage(exchange));
    }
}