package Communication.REST;


import java.net.InetSocketAddress;

import javax.net.ssl.SSLContext;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpsConfigurator;
import com.sun.net.httpserver.HttpsServer;

import Security.SecurityAPI;

public class RestServer {

	private HttpsServer httpsServer;
	private static int BACKLOG = 0;

	/**
	 * Instantiates a http server.
	 *
	 * @param port
	 *            the port
	 * @param context
	 *            the context eg: /navalBattle
	 * @param handler
	 *            the handler
	 */
	public RestServer(int port, String context, HttpHandler handler) {
		try {
			this.httpsServer = HttpsServer.create(new InetSocketAddress(port), BACKLOG);
			SSLContext sslContext = SecurityAPI.getSSLContext();

			httpsServer.setHttpsConfigurator(new HttpsConfigurator(sslContext));
			httpsServer.createContext(context, handler);

		} catch (Exception e) {
			System.err.println("An unexpected occurred creating the https server, try again!!!");
		}
	}

	/**
	 * Start running the server.
	 */
	public void run() {
		this.httpsServer.start();
	}
}
