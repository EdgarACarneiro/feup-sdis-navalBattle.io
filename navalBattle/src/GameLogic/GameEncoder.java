package GameLogic;

public class GameEncoder {
   
    public static String encodeForPlayer(ServerLogic server, int id) {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < server.getLength(); i++) {
			for(int j = 0; j < server.getLength(); j++) {
				sb.append(server.getFromId(i,j, id));
				if(j != server.getLength()-1) sb.append(",");
			}
			sb.append(";");
		}
		sb.deleteCharAt(sb.length()-1);
		return sb.toString();
	}

	// TODO: useless (?)
    public static String encodePlayer(PlayerLogic player) { //Or send move?
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < player.getLength(); i++) {
			for(int j = 0; j < player.getLength(); j++) {
				sb.append(player.get(i,j));
				if(j != player.getLength()-1) sb.append(",");
			}
			sb.append(";");
		}
		sb.deleteCharAt(sb.length()-1);
		return sb.toString();
	}
}
