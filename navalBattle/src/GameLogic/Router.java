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
            routes.put(new Pair<>("create", HTTPMethod.POST), "newPlayer"); // New Player
            routes.put(new Pair<>("attack", HTTPMethod.POST), "attack"); //jogador ataca -> server calcula se acertou mas o alvo só é atualizado no turno seguinte
            routes.put(new Pair<>("move", HTTPMethod.POST), "move"); //move o jogador
		}
		catch (Exception e) {
		    System.err.println("Failed to add Routing method to Routing table");
		}
	}


	public int callAction(Pair<String, String> route, HashMap<String, String> params, int clientID) {
        try {
        	Method method = null;
        	if (routes.get(route).equals("newPlayer")) {
        		method = ServerLogic.class.getMethod(routes.get(route), HashMap.class, Integer.class);
                return (Integer) method.invoke(logic, params, clientID);
        	}
        	else if (routes.get(route).equals("attack")) {
        		int col = Integer.parseInt(params.get("COL"));
        		int row = Integer.parseInt(params.get("ROW"));
        		method = ServerLogic.class.getMethod(routes.get(route), int.class, int.class);
                return (Integer) method.invoke(logic, col, row);
        	}

        } catch (NoSuchMethodException e) {
            System.err.println("Unable to find method for the given request.");
            return HTTPCode.NOT_FOUND;

        } catch (IllegalAccessException | InvocationTargetException e) {
            System.err.println("Failed to successfully the given method.");
            return HTTPCode.ERROR;
        }
		return 0;
    }
}