package GameLogic;


import java.util.ArrayList;
import java.util.HashMap;
import java.lang.reflect.*;

import Communication.REST.HTTPCodes;
import Communication.REST.HTTPRequest;
import Utils.Pair;
import Utils.RESTMethod;

import Player.Player;
import Server.Server;

public class Router {

	ServerLogic logic;

	private static HashMap<Pair<String, RESTMethod>, String> routes = new HashMap<>();

	public Router(Player player){
		//this.player = player;
		
		try {
			routes.put(new Pair<>("attack", RESTMethod.GET), "attack"); //jogador ataca -> server calcula se acertou mas o alvo s� � atualizado no turno seguinte
			routes.put(new Pair<>("move", RESTMethod.POST), "move"); //move o jogador
			
		}
		catch (Exception e) {e.printStackTrace();}
		
	}

	public Router(ServerLogic server){
		logic = server;

		try {
            routes.put(new Pair<>("updateGame", RESTMethod.GET),"updateGame"); //manda estado do jogo a todos os jogadores
            routes.put(new Pair<>("create", RESTMethod.POST), "newPlayer"); // New Player
		}
		catch (Exception e) {
		    System.err.println("Failed to add Routing method to Routing table");
		}
	}


	public int callAction(Pair<String, RESTMethod> route, HashMap<String, String> params) {

        try {
            Method method = logic.getClass().getMethod(routes.get(route), HashMap.class);
            return (Integer) method.invoke(logic, params);

        } catch (NoSuchMethodException e) {
            System.err.println("Unable to find method for the given request.");
            return HTTPCodes.NOT_FOUND;

        } catch (IllegalAccessException | InvocationTargetException e) {
            System.err.println("Failed to successfully the given method.");
            return HTTPCodes.ERROR;
        }
    }
}