package GameLogic;

import GameLogic.ServerLogic;

public class GameEncoder {
    // TODO: Class to encode boards and boards information into a string to send to the players
    // TODO: all methods should be static, acts as a filter
   
    public static String encodeForPlayer(ServerLogic server, int id) {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < server.getLength(); i++) {
			sb.append("[");
			for(int j = 0; j < server.getLength(); j++) {
				sb.append(server.getFromId(i,j, id));
				if(j != server.getLength()-1) sb.append(",");
			}
			sb.append("]\n");
		}
		sb.deleteCharAt(sb.length()-1);
		return sb.toString();
	}
    
    public static String encodePlayer(GameLogicAPI player) { //Or send move?
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < player.getLength(); i++) {
			for(int j = 0; j < player.getLength(); j++) {
				sb.append(player.get(i,j));
				if(j != player.getLength()-1) sb.append(",");
			}
			sb.append("-");
		}
		sb.deleteCharAt(sb.length()-1);
		return sb.toString();
	}
    
    public static String sendMove(GameLogicAPI player) {
		return player.getAttackCol() + "+" + player.getAttackRow();
	}
}
