package sprite;

import java.awt.image.BufferedImage;

public class CharacterSprite extends HeavySprite {
	
	private int direction = 2;

	public CharacterSprite(String spriteLocation, int xLength, int yLength, int rows, int columns, int frameRate,
			boolean repeats) {
		super(spriteLocation, xLength, yLength, rows, columns, frameRate, repeats);
	}

	public CharacterSprite(String spriteLocation, int xLength, int yLength, int rows, int columns, int frameRate,
			boolean repeats, double scalar) {
		super(spriteLocation, xLength, yLength, rows, columns, frameRate, repeats, scalar);
	}

	public CharacterSprite(BufferedImage image, int xLength, int yLength, int rows, int columns, int frameRate,
			boolean repeats) {
		super(image, xLength, yLength, rows, columns, frameRate, repeats);
	}

	public CharacterSprite(String spriteLocation, int xLength, int yLength, int rows, int columns, int frameRate,
			boolean repeats, boolean reverse) {
		super(spriteLocation, xLength, yLength, rows, columns, frameRate, repeats, reverse);
	}
	
	public void adjustDirection(int direction) {
		this.direction = direction;
	}
	
	public BufferedImage drawWithDirection() {
		return super.cardinalRotate(this.direction);
	}
	
	public CharacterSprite copy() {
		return new CharacterSprite(super.getSpriteSheet(), super.getxLength(), super.getyLength(), super.getRows(), super.getColumns(), 
				super.getAnimation().getFrameRate(), super.getAnimation().doesRepeat());
	}

}
