package sprite;
import java.awt.image.BufferedImage;

import main.Gamepanel;

public class HeavySprite extends Sprite{
	
	//private BufferedImage spriteSheet;
	private BufferedImage currentImage;
	private Animation mainAnimation;
	
	private int xLength;
	private int yLength;
	
	private int rows;
	private int columns;
	
	/**
	 * Creates a sprite that can be animated. Sprite sheets must have an equal amount of sprites on each row or column
	 * @param spriteLocation File location of spritesheet
	 * @param xLength Size of each sprite's width
	 * @param yLength Size of each sprite's height
	 * @param rows Amount of rows
	 * @param columns Amount of columns
	 * @param frameRate How many in game frames pass before next animation is run
	 * @param repeats Determines if animation is played over and over again
	 */
	
	public HeavySprite(String spriteLocation, int xLength, int yLength, int rows, int columns, int frameRate, boolean repeats) {
		super(spriteLocation);
		
		this.xLength = xLength;
		this.yLength = yLength;
		
		this.rows = rows;
		this.columns = columns;
		this.mainAnimation = new Animation(this, frameRate, repeats);
		
		this.currentImage = mainAnimation.getCurrentFrame();
		this.scaleWithRaster(4 * Gamepanel.sizeMult);
	}
	
	public HeavySprite(String spriteLocation, int xLength, int yLength, int rows, int columns, int frameRate, boolean repeats, double scalar) {
		super(spriteLocation);
		
		this.xLength = xLength;
		this.yLength = yLength;
		
		this.rows = rows;
		this.columns = columns;
	
		this.mainAnimation = new Animation(this, frameRate, repeats);
		
		this.scaleWithRaster(scalar * Gamepanel.sizeMult);	
		
		this.currentImage = mainAnimation.getCurrentFrame();
	}
	
	public HeavySprite(BufferedImage image, int xLength, int yLength, int rows, int columns, int frameRate, boolean repeats) {
		super(image);
		this.xLength = xLength;
		this.yLength = yLength;
		
		this.rows = rows;
		this.columns = columns;
		
		this.mainAnimation = new Animation(this, frameRate, repeats);
		this.currentImage = mainAnimation.getCurrentFrame();
	}
	
	public HeavySprite(String spriteLocation, int xLength, int yLength, int rows, int columns, int frameRate, boolean repeats, boolean reverse) {
		this(spriteLocation, xLength, yLength, rows, columns, frameRate, repeats);
		this.mainAnimation = new Animation(this, frameRate, repeats, reverse);
	}
	
	/**
	 * Updates the animation tied to the sprite
	 */
	
	public void update() {
		this.mainAnimation.update();
		this.currentImage = mainAnimation.getCurrentFrame();
	}
	
	@Override
	public BufferedImage getImage() {
		return this.currentImage;
	}
	
	/**
	 * Retrieves a subimage of the sprite at a certain location within the image
	 * Method can only be used with equally sized images within a spritesheet
	 * @param xLoc xLocation of the subimage
	 * @param yLoc yLocation of the subimage
	 * @return The image contained at the given location in the form of a Buffered Image
	 */
	
	public BufferedImage getSprite(int xLoc, int yLoc) {
		return super.getImage().getSubimage(xLoc * xLength, yLoc * yLength, xLength, yLength);
	}
	
	/**
	 * Retrieves a subimage in the rectangle created
	 * Can be used with differently sized images
	 * @param xLoc xLocation of the subimage
	 * @param yLoc yLocation of the subimage
	 * @param xLength Length of the image
	 * @param yLength Width of the image
	 * @return The image contained at the given location in the form of a Buffered Image
	 */
	
	public BufferedImage getSprite(int xLoc, int yLoc, int xLength, int yLength) {
		return super.getImage().getSubimage(xLoc, yLoc, xLength, yLength);
	}
	
	public BufferedImage currentRotated(double angle) {
		BufferedImage subimg = this.currentImage;
		subimg = super.scalarProperty().rotateImageByDegrees(subimg, angle);
		return subimg;
	}
	
	public BufferedImage getRotated(int xLoc, int yLoc, double angle) {
		BufferedImage subimg = super.getImage().getSubimage(xLoc * xLength, yLoc * yLength, xLength, yLength);
		subimg = super.scalarProperty().rotateImageByDegrees(subimg, angle);
		return subimg;
	}
	
	public BufferedImage getRotated(int xLoc, int yLoc, int xLength, int yLength, double angle) {
		BufferedImage subimg = super.getImage().getSubimage(xLoc, yLoc, xLength, yLength);
		subimg = super.scalarProperty().rotateImageByDegrees(subimg, angle);
		return subimg;
	}
	
	public void scaleImage(double scaleValue) {
		super.modifyImage(super.scalarProperty().getScaledImage(super.getImage(), scaleValue));
		this.currentImage = this.mainAnimation.getCurrentFrame();
	}
	
	@Override
	public HeavySprite copy() {
		return new HeavySprite(super.getImage(), this.xLength, this.yLength, this.rows, this.columns, this.mainAnimation.getFrameRate(), this.mainAnimation.doesRepeat());
	}
	
	public void scaleWithRaster(double scaleValue) {
		super.modifyImage(super.scalarProperty().getScaledImage(super.getImage(), scaleValue));
		this.xLength *= scaleValue;
		this.yLength *= scaleValue;
	}
	
	public void scaleCurrentFrame(double scaleValue) {
		this.currentImage = super.scalarProperty().getScaledImage(this.currentImage, scaleValue);
		this.xLength *= scaleValue;
		this.yLength *= scaleValue;
	}
	
	public void rotateImage(double degrees) {
		this.currentImage = super.scalarProperty().rotateImageByDegrees(this.currentImage, degrees);
	}
	
	public void rotateAll(double degrees) {
		for(int i = 0; i < xLength; i++) {
			this.rotateImage(degrees);
			this.mainAnimation.update();
		}
	}
	
	public void reflectImage() {
		super.modifyImage(super.scalarProperty().reflectImage(super.getImage()));
	}
	
	protected BufferedImage getSpriteSheet() {
		return super.getImage();
	}
	
	protected Animation getAnimation() {
		return this.mainAnimation;
	}
	
	public int getRows() {
		return this.rows;
	}
	
	public int getColumns() {
		return this.columns;
	}
	
	public int getxLength() {
		return this.xLength;
	}
	
	public void setxLength(int xLength) {
		this.xLength = xLength;
	}
	
	public int getyLength() {
		return this.yLength;
	}
	
	public void setYLength(int yLength) {
		this.yLength = yLength;
	}

}
 