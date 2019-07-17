package projectiles;

import java.awt.Graphics2D;

import characters.Characters;
import hostiles.Hostile;
import main.Gamepanel;
import sprite.HeavySprite;
import weapons.DeathRay;

public class LaserBeam extends Projectile {
	
	private static final int LIFESPAN = 6;
	private static int width = 20;
	
	private HeavySprite projectileSprite;

	public LaserBeam(float xCoor, float yCoor, int direction, DeathRay fromWeapon, Characters c) {
		super(xCoor, yCoor, direction, width, true, LIFESPAN, fromWeapon);
		projectileSprite = fromWeapon.getProjectileSprite().copy();
	}
	
	public void draw(Graphics2D g2d) {
		if(super.getDirection() % 2 == 0) {
			g2d.drawImage(projectileSprite.getSprite(0, 0, super.getWidth(), super.getHeight()), null, (int)super.getxCoor(), (int)super.getyCoor());
		} else {
			g2d.drawImage(projectileSprite.getRotated(0, 0, super.getHeight(), super.getWidth(), 90 * super.getDirection()), null, (int)super.getxCoor(), (int)super.getyCoor());
		}
	}
	
	public void movement() {
		switch(super.getDirection()) {
		case 0:
			super.setBounds(Gamepanel.mainChar.getxCoor() + (Gamepanel.mainChar.getWidth()/2), Characters.Y_MIN, width, (int)(Gamepanel.mainChar.getyCoor() - Characters.Y_MIN));
		break;
		
		case 1:
			super.setBounds(Gamepanel.mainChar.getxCoor() + (Gamepanel.mainChar.getWidth()), Gamepanel.mainChar.getyCoor() + (Gamepanel.mainChar.getHeight()/2), 
					(int)(Characters.X_BOUNDS - (Gamepanel.mainChar.getxCoor() + Gamepanel.mainChar.getWidth())), width);
		break;
		
		case 2:
			super.setBounds(Gamepanel.mainChar.getxCoor() + (Gamepanel.mainChar.getWidth()/2), Gamepanel.mainChar.getyCoor() + (Gamepanel.mainChar.getHeight()),
					width, (int)(Characters.Y_BOUNDS - (Gamepanel.mainChar.getyCoor() + Gamepanel.mainChar.getHeight())));
		break;
		
		case 3:
			super.setBounds(Characters.X_MIN, Gamepanel.mainChar.getyCoor() + (Gamepanel.mainChar.getHeight()/2), (int)(Gamepanel.mainChar.getxCoor() - Characters.X_MIN), width);
		break;
		}
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
