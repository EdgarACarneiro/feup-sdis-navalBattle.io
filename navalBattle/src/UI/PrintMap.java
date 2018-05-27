package UI;

import java.awt.Graphics;
import javax.swing.JPanel;

public class PrintMap extends JPanel {

	private static final long serialVersionUID = 1L;
	private int xDimension, yDimension;
	private int[][] map;
	
	/**
	 * Create the panel
	 */
	public PrintMap() {
		super();
		Assets.init();
	}
	/**
	 * Attributes game to panel
	 * @param game
	 */
	public void setGame(int[][] map) {
		this.map = map;
		if (this.map != null)
			changeImagesDimension();
		repaint();
	}
	/**
	 * Draws the map
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (map != null)
			for (int i = 0 ; i < map.length ; i++) {
				for (int j = 0 ; i < map[i].length ; j++) {
					if (map[i][j] == -1) 
						g.drawImage(Assets.water, j*xDimension+16, i*yDimension+16, xDimension/2, yDimension/2, null);
					else if (map[i][j] == -2) 
						g.drawImage(Assets.redShip, j*xDimension+16, i*yDimension+16, xDimension/2, yDimension/2, null);
					else 
						g.drawImage(Assets.blueShip, j*xDimension+16, i*yDimension+16, xDimension/2, yDimension/2, null);
				}
			}
	}


	/**
	 * Changes images dimension to fit the map
	 */
	public void changeImagesDimension() {
		int height = map.length;// altura do mapa
		int width = map[0].length;// largura do mapa
		xDimension = 500 / width;
		yDimension = 500 / height;
	}

}