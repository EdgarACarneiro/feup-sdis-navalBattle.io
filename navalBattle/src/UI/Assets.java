package UI;

import java.awt.image.BufferedImage;


public class Assets {
	public static BufferedImage water ,redShip , yellowShip , greenShip, blueShip;

	/**
	 * Initializes Sprites attribution
	 */
	public static void init(){
		try {
			water = Loader.loadImage("../Images/water.png");
			redShip = Loader.loadImage("../Images/red.png");
			yellowShip = Loader.loadImage("../Images/yellow.png");
			greenShip = Loader.loadImage("../Images/green.png");
			blueShip = Loader.loadImage("../Images/blue.png");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}