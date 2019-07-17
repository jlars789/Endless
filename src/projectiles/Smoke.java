package projectiles;

import java.awt.Color;
import java.awt.Graphics2D;

import abilities.ProjectileAbility;
import hostiles.Hostile;
import main.Gamepanel;
import statuseffect.Stun;

public class Smoke extends Projectile {
	
	private static final int SIZE = 150;
	public static int stunDur = 100;

	public Smoke(ProjectileAbility fromAbility) {
		super(Gamepanel.mainChar.getxCoor() - ((SIZE) - Gamepanel.mainChar.getWidth())/2, Gamepanel.mainChar.getyCoor() - ((SIZE) 
				- Gamepanel.mainChar.getHeight())/2, 0, SIZE, true, 3, fromAbility);
	}

	@Override
	protected void action() {
	}

	@Override
	protected void movement() {
	}

	@Override
	public void draw(Graphics2D g2d) {
		g2d.setColor(Color.BLACK);
		g2d.drawRect((int)super.getxCoor(), (int)super.getyCoor(), SIZE, SIZE);
		//TODO change this
	}

	@Override
	public void effect(Hostile enemy) {
		enemy.giveStatusEffect(new Stun(stunDur));
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
