package projectiles;

import java.awt.Graphics2D;

import abilities.Ninja.KunaiToss;
import hostiles.Hostile;
import sprite.LightSprite;
import statuseffect.Weaken;

public class Kunai extends Projectile {
	private float accuracy;
	private Weaken weaken;
	
	private LightSprite projectileSprite;

	public Kunai(float xCoor, float yCoor, int direction, KunaiToss fromAbility, float accuracy, LightSprite projectileSprite) {
		super(xCoor, yCoor, direction, 12, false, 15, fromAbility);
		this.accuracy = accuracy;
		this.weaken = fromAbility.getEffect();
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
		enemy.giveStatusEffect(weaken.copy());
	}
	
	public void movement() {
		
		if(super.getDirection() % 2 == 0) {
			super.setHeight(28);
		} else {
			super.setWidth(28);
		}
		
		super.move(this.accuracy);
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
