package UI;

import java.awt.Graphics;
import javax.swing.JPanel;

public class PrintMap extends JPanel {

	private static final long serialVersionUID = 1L;
	private static final int DIMENSION = 25;
	private int xDimension, yDimension;
	private int[][] map;
	
	/**
	 * Create the panel
	 */
	public PrintMap() {
		super();
		xDimension = DIMENSION;
		yDimension = DIMENSION;
		Assets.init();
	}
	/**
	 * Attributes game to panel
	 * @param game
	 */
	public void setGame(int[][] map) {
		this.map = map;
		repaint();
	}
	/**
	 * Draws the map
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (map != null) {
			
			g.drawImage(Assets.water, 0, 0, xDimension, yDimension, null);
			g.drawImage(Assets.number0, 0, 1 * yDimension, xDimension, yDimension, null);
			g.drawImage(Assets.number0, 1 * yDimension, 0, xDimension, yDimension, null);
			g.drawImage(Assets.number1, 0, 2 * yDimension, xDimension, yDimension, null);
			g.drawImage(Assets.number1, 2 * yDimension, 0, xDimension, yDimension, null);
			g.drawImage(Assets.number2, 0, 3 * yDimension, xDimension, yDimension, null);
			g.drawImage(Assets.number2, 3 * yDimension, 0, xDimension, yDimension, null);
			g.drawImage(Assets.number3, 0, 4 * yDimension, xDimension, yDimension, null);
			g.drawImage(Assets.number3, 4 * yDimension, 0, xDimension, yDimension, null);
			g.drawImage(Assets.number4, 0, 5 * yDimension, xDimension, yDimension, null);
			g.drawImage(Assets.number4, 5 * yDimension, 0, xDimension, yDimension, null);
			g.drawImage(Assets.number5, 0, 6 * yDimension, xDimension, yDimension, null);
			g.drawImage(Assets.number5, 6 * yDimension, 0, xDimension, yDimension, null);
			g.drawImage(Assets.number6, 0, 7 * yDimension, xDimension, yDimension, null);
			g.drawImage(Assets.number6, 7 * yDimension, 0, xDimension, yDimension, null);
			g.drawImage(Assets.number7, 0, 8 * yDimension, xDimension, yDimension, null);
			g.drawImage(Assets.number7, 8 * yDimension, 0, xDimension, yDimension, null);
			g.drawImage(Assets.number8, 0, 9 * yDimension, xDimension, yDimension, null);
			g.drawImage(Assets.number8, 9 * yDimension, 0, xDimension, yDimension, null);
			g.drawImage(Assets.number9, 0, 10 * yDimension, xDimension, yDimension, null);
			g.drawImage(Assets.number9, 10 * yDimension, 0, xDimension, yDimension, null);
			g.drawImage(Assets.number10, 0, 11 * yDimension, xDimension, yDimension, null);
			g.drawImage(Assets.number10, 11 * yDimension, 0, xDimension, yDimension, null);
			g.drawImage(Assets.number11, 0, 12 * yDimension, xDimension, yDimension, null);
			g.drawImage(Assets.number11, 12 * yDimension, 0, xDimension, yDimension, null);
			g.drawImage(Assets.number12, 0, 13 * yDimension, xDimension, yDimension, null);
			g.drawImage(Assets.number12, 13 * yDimension, 0, xDimension, yDimension, null);
			g.drawImage(Assets.number13, 0, 14 * yDimension, xDimension, yDimension, null);
			g.drawImage(Assets.number13, 14 * yDimension, 0, xDimension, yDimension, null);
			g.drawImage(Assets.number14, 0, 15 * yDimension, xDimension, yDimension, null);
			g.drawImage(Assets.number14, 15 * yDimension, 0, xDimension, yDimension, null);
			g.drawImage(Assets.number15, 0, 16 * yDimension, xDimension, yDimension, null);
			g.drawImage(Assets.number15, 16 * yDimension, 0, xDimension, yDimension, null);
			g.drawImage(Assets.number16, 0, 17 * yDimension, xDimension, yDimension, null);
			g.drawImage(Assets.number16, 17 * yDimension, 0, xDimension, yDimension, null);
			g.drawImage(Assets.number17, 0, 18 * yDimension, xDimension, yDimension, null);
			g.drawImage(Assets.number17, 18 * yDimension, 0, xDimension, yDimension, null);
			g.drawImage(Assets.number18, 0, 19 * yDimension, xDimension, yDimension, null);
			g.drawImage(Assets.number18, 19 * yDimension, 0, xDimension, yDimension, null);
			g.drawImage(Assets.number19, 0, 20 * yDimension, xDimension, yDimension, null);
			g.drawImage(Assets.number19, 20 * yDimension, 0, xDimension, yDimension, null);
			g.drawImage(Assets.number20, 0, 21 * yDimension, xDimension, yDimension, null);
			g.drawImage(Assets.number20, 21 * yDimension, 0, xDimension, yDimension, null);
			g.drawImage(Assets.number21, 0, 22 * yDimension, xDimension, yDimension, null);
			g.drawImage(Assets.number21, 22 * yDimension, 0, xDimension, yDimension, null);
			g.drawImage(Assets.number22, 0, 23 * yDimension, xDimension, yDimension, null);
			g.drawImage(Assets.number22, 23 * yDimension, 0, xDimension, yDimension, null);
			g.drawImage(Assets.number23, 0, 24 * yDimension, xDimension, yDimension, null);
			g.drawImage(Assets.number23, 24 * yDimension, 0, xDimension, yDimension, null);
			g.drawImage(Assets.number24, 0, 25 * yDimension, xDimension, yDimension, null);
			g.drawImage(Assets.number24, 25 * yDimension, 0, xDimension, yDimension, null);
			
			for (int i = 1 ; i - 1 < map.length ; i++) {
				for (int j = 1 ; j - 1 < map[i-1].length ; j++) {
					if (map[i-1][j-1] == -1) 
						g.drawImage(Assets.water, j*xDimension, i*yDimension, xDimension, yDimension, null);
					else if (map[i-1][j-1] == -2) 
						g.drawImage(Assets.redShip, j*xDimension, i*yDimension, xDimension, yDimension, null);
					else 
						g.drawImage(Assets.blueShip, j*xDimension, i*yDimension, xDimension, yDimension, null);
				}
			}
		}
	}
}