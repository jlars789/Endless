package weapons;

import java.util.ArrayList;

import main.Entity;
import main.Gamepanel;
import projectiles.LightningProjectile;
import sprite.LightSprite;

public class StormSurge extends Weapon {

	private static final long serialVersionUID = 3490377235818238067L;
	private static LightSprite projectileSprite = new LightSprite("imgs/projectiles/lightning.png", 4);
	private static final String WEAPON_NAME = "Storm Surge";
	
	public static double damage = 1000;
	public static int fireRate = 10;
	public static int pierce = 2;

	public StormSurge() {
		super(damage, fireRate, pierce, 0);
	}

	@Override
	public ArrayList<Entity> createProjectile() {
		ArrayList<Entity> zap = new ArrayList<Entity>();
		zap.add(new LightningProjectile(Gamepanel.mainChar.getCenterPoint()[0], Gamepanel.mainChar.getCenterPoint()[1], 
				Gamepanel.mainChar.getDirection(), this));
		return zap;
	}
	
	public LightSprite getProjectileSprite() {
		return projectileSprite;
	}

	@Override
	public String weaponName() {
		return WEAPON_NAME;
	}

	@Override
	public AnimationType getAnimType() {
		return AnimationType.THROWN;
	}

	@Override
	public WeaponType getType() {
		return WeaponType.MELEE_WEAPON;
	}

}
