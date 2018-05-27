package GameLogic;

public class GameEncoder {
   
    public static String encodeForPlayer(ServerLogic server, int id) {
		StringBuilder sb = new StringBuilder();
		int size = ServerLogic.MAP_DISPLAY_SIZE;

		String[] startPos = server.getMapStartingPos(id).split("\\+");
		int startCol = Integer.parseInt(startPos[0]);
		int startRow = Integer.parseInt(startPos[1]);

		for (int i = 0; i < size; i++) {
			for( int j = 0; j < size; j++) {
				sb.append(server.getCell(startCol + i, startRow + j, id));

				if (j != size - 1)
				    sb.append(",");
			}
			sb.append(";");
		}
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}

}
