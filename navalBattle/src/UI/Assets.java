package UI;

import java.awt.image.BufferedImage;
import java.util.HashMap;


// TODO: Auto-generated Javadoc
/**
 * The Class Assets.
 */
public class Assets {
	
	/** The number 24. */
	public static BufferedImage water ,redShip , yellowShip , greenShip, blueShip, fail, flame, number0, number1, number2, number3, number4, number5, number6, number7, number8, number9, number10, number11, number12, number13, number14, number15, number16, number17, number18, number19, number20, number21, number22, number23, number24;

	/**
	 * Initializes Sprites attribution.
	 */
	public static void init(){
		try {
			water = Loader.loadImage("../Images/water.png");
			redShip = Loader.loadImage("../Images/red.png");
			yellowShip = Loader.loadImage("../Images/yellow.png");
			greenShip = Loader.loadImage("../Images/green.png");
			blueShip = Loader.loadImage("../Images/blue.png");
			fail = Loader.loadImage("../Images/fail.png");
			flame = Loader.loadImage("../Images/flame.png");
			number0 = Loader.loadImage("../Images/0.png");
			number1 = Loader.loadImage("../Images/1.png");
			number2 = Loader.loadImage("../Images/2.png");
			number3 = Loader.loadImage("../Images/3.png");
			number4 = Loader.loadImage("../Images/4.png");
			number5 = Loader.loadImage("../Images/5.png");	
			number6 = Loader.loadImage("../Images/6.png");	
			number7 = Loader.loadImage("../Images/7.png");	
			number8 = Loader.loadImage("../Images/8.png");	
			number9 = Loader.loadImage("../Images/9.png");	
			number10 = Loader.loadImage("../Images/10.png");	
			number11 = Loader.loadImage("../Images/11.png");	
			number12 = Loader.loadImage("../Images/12.png");	
			number13 = Loader.loadImage("../Images/13.png");	
			number14 = Loader.loadImage("../Images/14.png");	
			number15 = Loader.loadImage("../Images/15.png");	
			number16 = Loader.loadImage("../Images/16.png");	
			number17 = Loader.loadImage("../Images/17.png");	
			number18 = Loader.loadImage("../Images/18.png");	
			number19 = Loader.loadImage("../Images/19.png");	
			number20 = Loader.loadImage("../Images/20.png");	
			number21 = Loader.loadImage("../Images/21.png");	
			number22 = Loader.loadImage("../Images/22.png");	
			number23 = Loader.loadImage("../Images/23.png");	
			number24 = Loader.loadImage("../Images/24.png");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}