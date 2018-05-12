package Server;

import java.io.IOException;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class RestServer {

	private HttpServer httpServer;

	/**
	 * Instantiates a http server.
	 *
	 * @param port the port
	 * @param context the context eg: /navalBattle
	 * @param handler the handler
	 */
	public RestServer(int port, String context, HttpHandler handler) {
		try {
			//Create HttpServer which is listening on the given port 
			httpServer = HttpServer.create(new InetSocketAddress(port), 0);
			//Create a new context for the given context and handler
			httpServer.createContext(context, handler);
			//Create a default executor
			httpServer.setExecutor(null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Start.
	 */
	public void start() {
		this.httpServer.start();
	}
}
