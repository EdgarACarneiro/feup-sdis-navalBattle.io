import Server.Server;
import Player.Player;


public class Main {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {

		if (args[0].equals("game") && args.length == 2)
            new Server(args[1]);

        else if (args[0].equals("player") && args.length == 3)
		    new Player(args[1], args[2]);

        else if (args[0].equals("game") && args.length == 1) {
            System.out.println("This feature is not yet completely implemented. You may experience some bugs.");
            Server.initServer();
        }

        else printUsage();
	}

	/**
	 * Prints the usage.
	 */
	private static void printUsage() {
	    System.err.println("Wrong usage of program!");
	    System.out.println("Correct usage:\n" +
                "* game <port>, in case you intend to launch a game server;\n" +
                "* player <serverIP> <serverPort>, in case you intend to play;\n");
    }
}
