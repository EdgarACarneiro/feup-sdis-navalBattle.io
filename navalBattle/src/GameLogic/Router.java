package GameLogic;


import java.io.Serializable;
import java.util.HashMap;
import java.lang.reflect.*;

import Communication.REST.HTTPCode;
import Communication.REST.HTTPMethod;
import Utils.Pair;


/**
 * The Class Router.
 */
public class Router implements Serializable {

	/** The logic. */
	ServerLogic logic;

	
	// <Pair< context, method>, callable>
	/** The routes. */
	private static HashMap<Pair<String, String>, String> routes = new HashMap<>();

	/**
	 * Instantiates a new router.
	 *
	 * @param server the server
	 */
	public Router(ServerLogic server){
		logic = server;

		try {
            routes.put(new Pair<>("create", HTTPMethod.POST), "newPlayer"); // New Player
            routes.put(new Pair<>("attack", HTTPMethod.POST), "attack"); //jogador ataca -> server calcula se acertou mas o alvo só é atualizado no turno seguinte
            routes.put(new Pair<>("move", HTTPMethod.POST), "move"); //move o jogador
		}
		catch (Exception e) {
		    System.err.println("Failed to add Routing method to Routing table");
		}
	}


	/**
	 * Call an action corresponding to a route.
	 *
	 * @param route the route
	 * @param params the params
	 * @param clientID the client ID
	 * @return the HTTPCode
	 */
	public int callAction(Pair<String, String> route, HashMap<String, String> params, int clientID) {
        try {
            Method method = ServerLogic.class.getMethod(routes.get(route), HashMap.class, Integer.class);
            return (Integer) method.invoke(logic, params, clientID);

        } catch (NoSuchMethodException e) {
            System.err.println("Unable to find method for the given request.");
            return HTTPCode.NOT_FOUND;

        } catch (IllegalAccessException | InvocationTargetException e) {
            System.err.println("Failed to successfully the given method.");
            return HTTPCode.ERROR;
        }
    }
}