package item;

import java.awt.Graphics2D;
import java.awt.image.RasterFormatException;

import characters.Characters;
import main.Gamepanel;
import sprite.HeavySprite;
import sprite.LightSprite;

public class LaserSight extends Item {
	
	private static final HeavySprite LASER = new HeavySprite("imgs/hud/aimlaser.png", 1,600, 1, 1, 0, false);
	private static final LightSprite SPRITE = new LightSprite("imgs/items/lasersight.png");
	private static final int W = 4;
	private int displayX;
	private int displayY;
	private int width, height;
	private HeavySprite laser;

	public LaserSight() {
		super(Item.CONSTANT_MOD, SPRITE.copy());
		laser = LASER.copy();
	}

	@Override
	protected void instantEffect(Characters mainChar) {
		mainChar.getStats().increaseAttackSpeed(0.5);
		mainChar.getStats().increaseSpeed(0.4);
	}

	@Override
	public void lingerEffect(Characters mainChar) {
		switch(mainChar.getDirection()) {
		case 0:
			this.displayX = (int)(Gamepanel.mainChar.getxCoor() + (Gamepanel.mainChar.getWidth()/2));
			this.displayY = Characters.Y_MIN;
			this.width = W;
			this.height = (int)(Gamepanel.mainChar.getyCoor() - Characters.Y_MIN);
		break;
		
		case 1:
			this.displayX = (int) (Gamepanel.mainChar.getxCoor() + (Gamepanel.mainChar.getWidth()));
			this.displayY = (int) (Gamepanel.mainChar.getyCoor() + (Gamepanel.mainChar.getHeight()/2));
			this.width = (int)(Characters.X_BOUNDS - (Gamepanel.mainChar.getxCoor() + Gamepanel.mainChar.getWidth()));
			this.height = W;
		break;
		
		case 2:
			this.displayX = (int) (Gamepanel.mainChar.getxCoor() + (Gamepanel.mainChar.getWidth()/2));
			this.displayY = (int) (Gamepanel.mainChar.getyCoor() + (Gamepanel.mainChar.getHeight()));
			this.width = W;
			this.height = (int)(Characters.Y_BOUNDS - (Gamepanel.mainChar.getyCoor() + Gamepanel.mainChar.getHeight()));
		break;
		
		case 3:
			this.displayX = Characters.X_MIN;
			this.displayY = (int) (Gamepanel.mainChar.getyCoor() + (Gamepanel.mainChar.getHeight()/2));
			this.width = (int)(Gamepanel.mainChar.getxCoor() - Characters.X_MIN);
			this.height = W;
			System.out.println(width);
			//System.out.println(Gamepanel.mainChar.getDirection());
		break;
		}
	}

	@Override
	public boolean challengeItem() {
		return false;
	}

	@Override
	public int getID() {
		return 11;
	}

	@Override
	public String getName() {
		return "Laser Sight";
	}

	@Override
	public void drawCostume(Graphics2D g2d) {
		try {
			if(Gamepanel.mainChar.getDirection() % 2 == 0) {
				g2d.drawImage(laser.getSprite(0, 0, width, height), null, displayX, displayY);
			} else {
				g2d.drawImage(laser.getRotated(0, 0, height, width, 90 * Gamepanel.mainChar.getDirection()), null, displayX, displayY);
				
			}
		} catch(RasterFormatException e) {
		}
	}

}
