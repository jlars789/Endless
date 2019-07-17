package projectiles;

import java.awt.Graphics2D;

import abilities.Pirate.Ignite;
import characters.Characters;
import hostiles.Hostile;
import main.Gamepanel;
import sprite.Sprite;

public class RingOfFire extends Projectile {
	
	private Characters charRef;
	private Sprite moving;

	public RingOfFire(float xCoor, float yCoor, int direction, Ignite fromAbility, int duration) {
		super(xCoor, yCoor, direction, Gamepanel.mainChar.getHeight() * 2, true, duration, fromAbility);
		
		this.charRef = Gamepanel.mainChar;
		this.moving = fromAbility.getSprite();
	}
	
	public void draw(Graphics2D g2d) {
		g2d.drawImage(moving.getImage(), null, (int)super.getxCoor(), (int)super.getyCoor());
	}
	
	public void movement() {
		super.setxCoor(this.charRef.getxCoor() - (super.getWidth()/2));
		super.setyCoor(this.charRef.getyCoor() - (super.getHeight()/2));
	}
	
	public void setCharacter(Characters charRef) {
		this.charRef = charRef;
	}

	@Override
	public void action() {
		
	}

	@Override
	public void effect(Hostile enemy) {
		
	}

	@Override
	public ProjectileType getType() {
		return ProjectileType.BEAM_PROJECTILE;
	}

	@Override
	public boolean lowLayer() {
		return true;
	}

}
