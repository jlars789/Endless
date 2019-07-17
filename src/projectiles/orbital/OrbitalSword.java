package projectiles.orbital;

import java.awt.Graphics2D;

import abilities.Ninja.Blademaster;
import hostiles.Hostile;
import projectiles.Sword;
import sprite.LightSprite;

public class OrbitalSword extends Orbital {
	
	private static final float RADIUS = 125;
	public static int lifespan = 480;
	public static int size = Sword.range;
	
	private LightSprite sprite;

	public OrbitalSword(int initPos, Blademaster fromAbility) {
		super(initPos, size, lifespan, fromAbility, RADIUS);
		sprite = fromAbility.getSprite();
	}

	@Override
	protected void action() {
	}

	@Override
	public void draw(Graphics2D g2d) {
		g2d.drawImage(this.sprite.currentRotated(-6 * super.getTicks()), null, (int)this.getxCoor(), (int)this.getyCoor());
	}

	@Override
	public void effect(Hostile enemy) {
	}

}
