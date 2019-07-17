package sprite;

import java.awt.image.BufferedImage;

import main.Gamepanel;

/**
	Class used for creating basic, non-animated sprites.
	<p> 
	These will have all of the available methods in the abstract parent class, {@link Sprite}
	By default, all one needs to do is create the sprite with the path to the image, stating with "imgs/"
	This will automatically scale the image by 4x
 */

public class LightSprite extends Sprite{

	public LightSprite(String spriteLocation) {
		super(spriteLocation);
		this.scaleImage(4 * Gamepanel.sizeMult);
	}
	
	public LightSprite(String spriteLocation, double scalar) {
		super(spriteLocation);
		this.scaleImage(scalar * Gamepanel.sizeMult);
	}
	
	public LightSprite(BufferedImage bi) {
		super(bi);
	}
	
	public LightSprite copy() {
		return new LightSprite(super.getImage());
	}

}
