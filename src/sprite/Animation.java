package sprite;
import java.awt.image.BufferedImage;

public class Animation {

	private int tickCounter;
	private int frameRate;
	
	private int xLoc;
	private int yLoc;
	
	private boolean active;
	
	private HeavySprite reel;
	private boolean repeats;
	private boolean reverse;
	
	/**
	 * Creates an object that allows for controlled animations with a spritesheet
	 * @param sprite A loaded spritesheet
	 * @param frameRate How many in game frames pass before a frame in this animation passes
	 * @param repeats Boolean that determines if the animation should repeat or not
	 */
	
	public Animation(HeavySprite sprite, int frameRate, boolean repeats) {
		this.reel = sprite;
		this.repeats = repeats;
		
		this.xLoc = 0;
		this.yLoc = 0;
		
		this.frameRate = frameRate;
	}
	
	public Animation (HeavySprite sprite, int frameRate, boolean repeats, boolean reverse) {
		this(sprite, frameRate, repeats);
		this.xLoc = reel.getColumns() - 1;
		this.yLoc = reel.getRows() - 1;
		this.reverse = reverse;
	}
	
	/**
	 * Updates the timers of the animation. Allows the animation to advance. Should be called from 
	 * HeavySprite's {@code update()} method.
	 */
	
	public void update() {
		tickCounter++;
		
		if(tickCounter == frameRate) {
			nextFrame();
			tickCounter = 0;
		}
	}
	
	/**
	 * Advances to the next frame
	 */
	
	private void nextFrame() {
		if(!reverse) {
			if(xLoc < reel.getColumns() - 1) {
				xLoc++;
			} 
			else if(yLoc < reel.getRows() - 1){
				xLoc = 0;
				yLoc++;
			} 
			else if(repeats) {
				xLoc = 0;
				yLoc = 0;
			} else {
				end();
			}
		} else {
			if(xLoc > 0) {
				xLoc--;
			} 
			else if(yLoc > 0){
				xLoc = reel.getColumns() - 1;
				yLoc--;
			} 
			else if(repeats) {
				xLoc = reel.getColumns() - 1;
				yLoc = reel.getRows() - 1;
			} else {
				end();
			}
		}
	}
	
	private void end() {
		this.active = false;
	}
	
	public void pause() {
		this.active = false;
	}
	
	public void start() {
		this.active = true;
	}
	
	/**
	 * Returns the current frame the animation is on
	 * @return The current frame in the form of a buffered image
	 */
	
	public BufferedImage getCurrentFrame() {
		return reel.getSprite(xLoc, yLoc);
	}
	
	public boolean isActive() {
		return this.active;
	}
	
	public boolean doesRepeat() {
		return this.repeats;
	}
	
	public int getFrameRate() {
		return this.frameRate;
	}
	
	public void reset() {
		xLoc = 0;
		yLoc = 0;
	}

}
