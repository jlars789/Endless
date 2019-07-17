package projectiles;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import hostiles.Hostile;
import sprite.HeavySprite;
import weapons.Flamethrower;

public class FireProjectile extends Projectile {
	
	private float accuracy;
	
	private HeavySprite sprite;

	public FireProjectile(float xCoor, float yCoor, int direction, Flamethrower fromWeapon, float accuracy) {
		super(xCoor, yCoor, direction, 36, true, 45, fromWeapon);
		this.accuracy = accuracy;
		this.sprite = fromWeapon.getProjectileSprite().copy();
	}
	
	public void draw(Graphics2D g2d) {
		
		BufferedImage drawImg[] = new BufferedImage[3];
		
		drawImg[0] = sprite.getSprite(2, 0);
		drawImg[1] = sprite.getSprite(1, 0);
		drawImg[2] = sprite.getSprite(1, 0);
		
		if(super.getDirection() == 1 || super.getDirection() == 3) {
			g2d.drawImage(drawImg[0], null, (int)super.getxCoor(), (int)super.getyCoor());
			g2d.drawImage(drawImg[1], null, (int)super.getxCoor(), (int)super.getyCoor() + (48 - super.getLifespan())/2);
			g2d.drawImage(drawImg[2], null, (int)super.getxCoor(), (int)super.getyCoor() - (48 - super.getLifespan())/2);
		}
		else if(super.getDirection() == 0 || super.getDirection() == 2) {
			g2d.drawImage(drawImg[0], null, (int)super.getxCoor(), (int)super.getyCoor());
			g2d.drawImage(drawImg[1], null, (int)super.getxCoor() + (48 - super.getLifespan())/2, (int)super.getyCoor());
			g2d.drawImage(drawImg[2], null, (int)super.getxCoor() - (48 - super.getLifespan())/2, (int)super.getyCoor() - 20);
		}
	}
	
	public void action() {
	}
	
	public void movement() {
		super.move(this.accuracy);
		if(super.getLifespan() % 6 == 0) {
			super.adjustSpeed(-1);
			super.scale(1.2);
		}
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
		return false;
	}

}
