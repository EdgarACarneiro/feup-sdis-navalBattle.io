import Communication.RestServer;
import REST.RestServerHandler;
import Security.SecurityAPI;

public class Main {

	private static final String CONTEXT = "/app";
	private static final int PORT = 8000;

	public static void main(String[] args) throws Exception {
		SecurityAPI.generateCertificate();
		// Create a new SimpleHttpServer
		RestServer simpleHttpServer = new RestServer(PORT, CONTEXT, new RestServerHandler());
		simpleHttpServer.start();
		
		System.out.println("Server is started and listening on port " + PORT);

		UI.UI_API menu = new UI.UI_API();
	}
}
