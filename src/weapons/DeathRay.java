package weapons;

import java.util.ArrayList;

import main.Entity;
import projectiles.LaserBeam;
import sprite.HeavySprite;

public class DeathRay extends Weapon {
	
	private static final long serialVersionUID = -9105488930647296303L;
	private static HeavySprite projectileSprite = new HeavySprite("imgs/projectiles/laserbeam.png", 5, 600, 1, 1, 0, false);
	private static final String WEAPON_NAME = "Death Ray";
	
	public static double damage = 6;
	public static int magazine = 70;
	public static int fireRate = 6;
	public static int reloadSpeed = 110;

	public DeathRay() {
		super(damage, fireRate, magazine, 0, reloadSpeed);
	}
	
	@Override
	public ArrayList<Entity> createProjectile() {
		ArrayList<Entity> shot = new ArrayList<Entity>();
		
		shot.add(0, new LaserBeam(mainChar.getxCoor() + mainChar.getWidth()/2, (float)(mainChar.getyCoor() + (mainChar.getHeight()/1.4)), mainChar.getDirection(), this, mainChar));
		
		return shot;
	}
	
	public HeavySprite getProjectileSprite() {
		return projectileSprite;
	}

	@Override
	public String weaponName() {
		return WEAPON_NAME;
	}

	@Override
	public AnimationType getAnimType() {
		return AnimationType.HEAVY;
	}

	@Override
	public WeaponType getType() {
		return WeaponType.BEAM_WEAPON;
	}

}
