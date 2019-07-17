package projectiles;

import java.awt.Graphics2D;

import abilities.Ninja.SwordThrow;
import characters.Characters;
import hostiles.Hostile;
import main.Gamepanel;
import sprite.LightSprite;

public class MovingSword extends Projectile {
	
	private static final int LIFESPAN = 120;
	public static int size = 90;
	private int ticks;
	private LightSprite sprite;

	public MovingSword(float xCoor, float yCoor, int direction, SwordThrow fromAbility) {
		super(xCoor, yCoor, direction, size, false, LIFESPAN, fromAbility);
		this.sprite = fromAbility.getProjectileSprite();
	}

	@Override
	protected void action() {
	}

	@Override
	protected void movement() {
		if(ticks < 60) {
			this.move();
		} 
		else if(ticks == 60) {
			this.swapDirection();
		}
		else if(ticks > 60) {
			this.returnToSender();
		}
		ticks++;
	}
	
	private void returnToSender() {
		Characters c = Gamepanel.mainChar;
		if(super.getDirection() % 2 == 0) {
			if(this.getCenterPoints()[0] > c.getCenterPoint()[0]) {
				this.move(-super.getSpeed());
			}
			else if(this.getCenterPoints()[0] < c.getCenterPoint()[0]) {
				this.move(super.getSpeed());
			} else {
				this.move();
			}
		} else {
			if(this.getCenterPoints()[1] > c.getCenterPoint()[1]) {
				this.move(-super.getSpeed());
			}
			else if(this.getCenterPoints()[1] < c.getCenterPoint()[1]) {
				this.move(super.getSpeed());
			} else {
				this.move();
			}
		}
		
		if(super.getFullHitbox().intersects(c.getPlayerHitbox())) {
			super.setPierce(0);
		}
	}
	
	private void swapDirection() {
		if(super.getDirection() > 1) super.setDirection(super.getDirection() - 2);
		else super.setDirection(super.getDirection() + 2);
	}

	@Override
	public void draw(Graphics2D g2d) {
		g2d.drawImage(this.sprite.currentRotated(-10 * ticks), null, (int)this.getxCoor(), (int)this.getyCoor());
	}

	@Override
	public void effect(Hostile enemy) {
	}

	@Override
	public ProjectileType getType() {
		return ProjectileType.BEAM_PROJECTILE;
	}

	@Override
	protected boolean lowLayer() {
		return false;
	}

}
