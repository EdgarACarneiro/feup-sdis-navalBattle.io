package GameLogic;


import java.util.HashMap;
import java.lang.reflect.*;

import Communication.REST.HTTPCode;
import Communication.REST.HTTPMethod;
import Utils.Pair;

public class Router {

	ServerLogic logic;

	// <Pair< context, method>, callable>
	private static HashMap<Pair<String, String>, String> routes = new HashMap<>();

	public Router(ServerLogic server){
		logic = server;

		try {
            routes.put(new Pair<>("app/create", HTTPMethod.POST), "newPlayer"); // New Player
            routes.put(new Pair<>("app/attack", HTTPMethod.POST), "attack"); //jogador ataca -> server calcula se acertou mas o alvo só é atualizado no turno seguinte
            routes.put(new Pair<>("app/move", HTTPMethod.POST), "move"); //move o jogador
		}
		catch (Exception e) {
		    System.err.println("Failed to add Routing method to Routing table");
		}
	}


	public int callAction(Pair<String, String> route, HashMap<String, String> params, int clientID) {

        try {
            Method method = logic.getClass().getMethod(routes.get(route), HashMap.class, Integer.class);
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