package GameLogic;


import java.util.HashMap;
import java.lang.reflect.*;

import Utils.Pair;
import Utils.RESTMethod;

import Player.Player;
import Server.Server;

public class Routes {

	Player player;
	Server server;

	private static HashMap<Pair<String, RESTMethod>, String> routes = new HashMap<>();

	public Routes(Player player){
		this.player = player;
		
		try {
			routes.put(new Pair<>("attack", RESTMethod.GET), "attack"); //jogador ataca -> server calcula se acertou mas o alvo s� � atualizado no turno seguinte
			routes.put(new Pair<>("move", RESTMethod.POST), "move"); //move o jogador
			
		}
		catch (Exception e) {e.printStackTrace();}
		
	}

	public Routes(Server server){
		this.server = server; 

		try {

		routes.put(new Pair<>("updateGame", RESTMethod.GET),"updateGame"); //manda estado do jogo a todos os jogadores
		routes.put(new Pair<>("create", RESTMethod.POST), "create"); // New Player

		}
		catch (Exception e) {e.printStackTrace();}
		
	}


	public void callAction(Pair<String, RESTMethod> route, String params) {

		String action = routes.get(route);


		switch(action){
			case "attack":
				//this.player.attack();
				break;
			case "move":
				this.player.move();
				break;
			case "updateGame":
				this.server.updateGame();
				break;
			case "create":
				//this.server.create();
				break;

		}
	}
}