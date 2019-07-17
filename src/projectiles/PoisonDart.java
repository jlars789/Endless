package projectiles;

import java.awt.Graphics2D;

import abilities.Ninja.PoisonDartAbility;
import hostiles.Hostile;
import sprite.LightSprite;
import statuseffect.Poison;

public class PoisonDart extends Projectile {
	
	private Poison poison;
	private LightSprite projectileSprite;
	
	public PoisonDart(float xCoor, float yCoor, int direction, PoisonDartAbility fromAbility, LightSprite projectileSprite) {
		super(xCoor, yCoor, direction, 3, false, 1, fromAbility);
		this.poison = fromAbility.getEffect();
		this.projectileSprite = projectileSprite;
		this.imageInstantiate();
	}
	
	
	public void draw(Graphics2D g2d) {
		g2d.drawImage(this.projectileSprite.getImage(), null, (int)super.getxCoor(), (int)super.getyCoor());
	}

	public void imageInstantiate() {
		switch (super.getDirection()) {
		case 0:
		break;
		
		case 1:
			this.projectileSprite.rotateImage(90);
		break;
		
		case 2: 
			this.projectileSprite.rotateImage(180);
		break;
		
		case 3:
			this.projectileSprite.rotateImage(270);
		break;
		}
	}

	public void action() {
		super.hit();
	}
	
	public void effect(Hostile enemy) {
		enemy.giveStatusEffect(this.poison.copy());
	}
	
	public void movement() {
		if(super.getDirection() % 2 == 0) {
			super.setHeight(9);
		} else {
			super.setWidth(9);
		}
		super.move();
	}


	@Override
	public ProjectileType getType() {
		return ProjectileType.NORMAL_PROJECTILE;
	}


	@Override
	public boolean lowLayer() {
		return false;
	}
	
	
}
