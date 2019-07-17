package weapons;

import java.util.ArrayList;

import main.Entity;
import projectiles.*;
import sprite.LightSprite;

public class Scylla extends Weapon {
	
	private static final long serialVersionUID = 1270678874270256602L;
	private static LightSprite projectileSprite = new LightSprite("imgs/projectiles/cannonball.png");
	
	public static double damage = 134;
	public static int magazine = 5;
	public static int fireRate = 55;
	
	public static float projSpeed = 5.5f;
	public static int reloadSpeed = 105;
	
	private static final String WEAPON_NAME = "Scylla";

	public Scylla() {
		super(damage, fireRate, magazine, 1, projSpeed, reloadSpeed);
	}
	
	@Override
	public ArrayList<Entity> createProjectile() {
		ArrayList<Entity> shot = new ArrayList<Entity>();
		shot.add(new Cannonball(mainChar.getxCoor() + mainChar.getWidth()/3, mainChar.getyCoor() + mainChar.getHeight()/3, mainChar.getDirection(), this));
		return shot;
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
		return AnimationType.RIFLE;
	}

	@Override
	public WeaponType getType() {
		return WeaponType.NORMAL_WEAPON;
	}

}
