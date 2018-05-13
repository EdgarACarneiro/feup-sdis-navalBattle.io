import Communication.UDPClient;
import Communication.UDPServer;

public class Main {

	private static final String CONTEXT = "/app";
	private static final int PORT = 8000;

	public static void main(String[] args) throws Exception {

		/*// Create a new SimpleHttpServer
		RestServer simpleHttpServer = new RestServer(PORT, CONTEXT,
				new RestServerHandler());

		// Start the server
		simpleHttpServer.start();
		System.out.println("Server is started and listening on port "+ PORT);

		UI.UI_API menu = new UI.UI_API();*/

        if (args[0].equals("1"))
            new UDPServer().run();
        else
            new UDPClient().sendUDP("lmaooooooooo");
	}
}
