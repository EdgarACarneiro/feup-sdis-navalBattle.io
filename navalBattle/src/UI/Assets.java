package UI;

import java.awt.image.BufferedImage;


public class Assets {
	public static BufferedImage water ,redShip , yellowShip , greenShip, blueShip;

	/**
	 * Initializes Sprites attribution
	 */
	public static void init(){
		try {
			water = Loader.loadImage("/water.png");
			redShip = Loader.loadImage("/red.png");
			yellowShip = Loader.loadImage("/yellow.png");
			greenShip = Loader.loadImage("/green.png");
			blueShip = Loader.loadImage("/blue.png");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}