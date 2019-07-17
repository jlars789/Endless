package projectiles;

import java.awt.Color;
import java.awt.Graphics2D;

import hostiles.Hostile;
import main.Gamepanel;
import weapons.Weapon;

public class Nuke extends Projectile {
	
	private static final int lifespan = 5;
	private static final int size = 9999999;

	public Nuke(Weapon fromWeapon) {
		super(0, 0, 0, size, true, lifespan, fromWeapon);
	}

	@Override
	protected void action() {
	}

	@Override
	protected void movement() {
	}

	@Override
	public void draw(Graphics2D g2d) {
		g2d.setColor(Color.WHITE);
		g2d.fillRect((int)super.getxCoor(), (int)super.getyCoor(), Gamepanel.WIDTH, Gamepanel.HEIGHT);
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
