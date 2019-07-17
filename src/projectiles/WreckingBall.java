package projectiles;

import java.awt.Graphics2D;

import abilities.Pirate.WreckingCrew;
import hostiles.Hostile;
import sprite.LightSprite;

public class WreckingBall extends Projectile {
	
	public static float knockback = 60;
	public static int size = 60;
	private LightSprite projectileSprite;

	public WreckingBall(float xCoor, float yCoor, int direction, WreckingCrew fromAbility) {
		super(xCoor, yCoor, direction, size, false, 400, fromAbility);
		super.setPierce(9999);
		this.projectileSprite = fromAbility.getSprite();
	}

	@Override
	protected void action() {
	}

	@Override
	protected void movement() {
		switch(super.getDirection()) {
		case 0:
			super.moveY(true);
		break;
		
		case 1:
			super.moveX(false);
		break;
		
		case 2: 
			super.moveY(false);
		break;
		
		case 3:
			super.moveX(true);
		break;
		}
		
		for(int i = 0; i < 4; i++) {
			if(this.outOfBounds()[i]) this.destroy();
		}

	}

	@Override
	public void draw(Graphics2D g2d) {
		g2d.drawImage(projectileSprite.getImage(), null, (int)this.getxCoor(), (int)this.getyCoor());
	}

	@Override
	public void effect(Hostile enemy) {
		if(this.getDirection() % 2 == 0) {
			if(Math.random() > 0.5) {
				enemy.knockback(1, knockback);
			} else {
				enemy.knockback(3, knockback);
			}
		} else {
			if(Math.random() > 0.5) {
				enemy.knockback(0, knockback);
			} else {
				enemy.knockback(2, knockback);
			}
		}
	}

	@Override
	public ProjectileType getType() {
		return ProjectileType.NORMAL_PROJECTILE;
	}

	@Override
	protected boolean lowLayer() {
		return false;
	}

}
