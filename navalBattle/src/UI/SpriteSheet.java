package UI;

import java.awt.image.BufferedImage;

/**
 * SpriteSheet.java - Class that deals with the sprite sheet's reading
 */
public class SpriteSheet {

	/** The sheet. */
	private BufferedImage sheet;
	
	/**
	 * Constructor for the spritesheet.
	 *
	 * @param sheet the sheet
	 */
	public SpriteSheet(BufferedImage sheet){
		this.sheet = sheet;
	}
	
	/**
	 * Crops the sheet according to the provided pixel position and size.
	 *
	 * @param x the x
	 * @param y the y
	 * @param width the width
	 * @param height the height
	 * @return BufferedImage
	 */
	public BufferedImage crop(int x, int y, int width, int height){
		return sheet.getSubimage(x, y, width, height);
	}
	
}