package GameLogic;

public class GameEncoder {
   
    public static String encodeForPlayer(ServerLogic server, int id) {
		StringBuilder sb = new StringBuilder();
		int size = ServerLogic.MAP_DISPLAY_SIZE;

		for (int i = 0; i < size; i++) {
			for( int j = 0; j < size; j++) {
				sb.append(server.getFromId(i,j, id));

				if (j != server.getLength()-1)
				    sb.append(",");
			}
			sb.append(";");
		}
		sb.deleteCharAt(sb.length()-1);
		return sb.toString();
	}

}
