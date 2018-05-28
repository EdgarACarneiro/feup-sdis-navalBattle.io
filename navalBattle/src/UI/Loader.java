package UI;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;



/**
 * The Class Loader.
 */
public class Loader {
	
	/**
	 * Deals with image loading.
	 *
	 * @param path the path
	 * @return null if unable
	 */
	public static BufferedImage loadImage(String path){
		try {
			return ImageIO.read(Loader.class.getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return null;
	}
	
}