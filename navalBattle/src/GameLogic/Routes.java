package GameLogic;


import java.util.HashMap;
import java.lang.reflect.*;

import Utils.Pair;
import Utils.RESTMethod;

import Player.Player;
import Server.Server;

public class Routes {

	private static HashMap<Pair<String, RESTMethod>, Method> routes = new HashMap<>();

	static{
		try {
			routes.put(new Pair<>("attack", RESTMethod.GET), Player.class.getMethod("attack")); //jogador ataca -> server calcula se acertou mas o alvo s� � atualizado no turno seguinte
			routes.put(new Pair<>("move", RESTMethod.POST), Player.class.getMethod("move")); //move o jogador
			routes.put(new Pair<>("updateGame", RESTMethod.GET), Server.class.getMethod("updateGame")); //manda estado do jogo a todos os jogadores
			routes.put(new Pair<>("create", RESTMethod.GET), Server.class.getMethod("create")); // New Player
			//routes.put(new Pair<>("targetHit", RESTMethod.POST), TestApp.class.getMethod("targetHit"));	// resposta do server (se acertou ou n�o)
			//routes.put(new Pair<>("removeBlock", PATCH), TestApp.class.getMethod("removeBlock")); //se o jogador ficou com tamanho reduzido
		}
		catch (Exception e) {e.printStackTrace();}
	}

	public static void callAction(Pair<String, RESTMethod> route, String params) {
		Method action = routes.get(route);

		if (action == null){
			System.out.println("Error");
			return;
		}

		try {
			action.invoke(null, params);
		} catch (Exception e) {
			System.out.println("An unexpected error ocurred");
		}
	}

}
