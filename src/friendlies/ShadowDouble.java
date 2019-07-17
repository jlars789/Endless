package friendlies;

import java.awt.Graphics2D;
import java.util.ArrayList;

import hostiles.Hostile;
import main.Entity;
import main.Gamepanel;
import projectiles.Shuriken;
import sprite.HeavySprite;
import sprite.LightSprite;

public class ShadowDouble extends Friendly {
	
	private static final HeavySprite SPRITE = new HeavySprite("imgs/projectiles/shuriken.png", 5, 5, 1, 3, 4, true, 4);
	private static final LightSprite FULL_SPRITE = new LightSprite("imgs/friendly/shadow_double.png");
	private LightSprite mainAnim;
	private float[] speed = new float[2];
	private Hostile enemy;

	public ShadowDouble() {
		super(Gamepanel.mainChar.getxCoor(), Gamepanel.mainChar.getyCoor(), Gamepanel.mainChar.getWidth(), Gamepanel.mainChar.getHeight(), 
				Gamepanel.mainChar.getMoveset().getWeapons()[0].getDamage(), 2, 28, Gamepanel.mainChar.getMoveset().getAbilities()[2].getDuration());
		this.mainAnim = FULL_SPRITE.copy();
	}
	
	public void draw(Graphics2D g) {
		g.drawImage(mainAnim.currentRotated(this.getAngleFacing()), null, (int)this.getxCoor(), (int)this.getyCoor());
	}
	
	public void action(Hostile enemy) {
		speed[0] = super.getCenterPoint()[0] - (enemy.getCenterPoint()[0]);
		speed[1] = super.getCenterPoint()[1] - (enemy.getCenterPoint()[1]);
		this.enemy = enemy;
	}
	
	protected double getAngleFacing() {
		if(this.enemy != null) {
			double dx = super.getCenterPoint()[0] - (enemy.getCenterPoint()[0]);
			double dy = -(super.getCenterPoint()[1] - (enemy.getCenterPoint()[1]));
			return Math.toDegrees(Math.atan2(dx, dy));
		}
		else return 0;
	}
	
	public HeavySprite getProjectileSprite() {
		return SPRITE.copy();
	}

	@Override
	public Friendly copy() {
		return new ShadowDouble();
	}

	@Override
	protected ArrayList<Entity> getProjectile() {
		ArrayList<Entity> p = new ArrayList<Entity>();
		p.add( new Shuriken(super.getMidpoint()[0], super.getMidpoint()[1], speed[0], speed[1], this));
		return p;
	}

}
