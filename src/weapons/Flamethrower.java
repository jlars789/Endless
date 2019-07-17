package weapons;

import java.util.ArrayList;

import main.Entity;
import projectiles.FireProjectile;
import sprite.HeavySprite;

public class Flamethrower extends Weapon {
	
	private static final long serialVersionUID = -4696827025428509952L;
	private static HeavySprite projectileSprite = new HeavySprite("imgs/projectiles/flame.png", 3, 3, 1, 1, 0, false, 4);
	private static final String WEAPON_NAME = "Flamethrower";
	
	public static double damage = 9;
	public static int fireRate = 8;
	public static int magazine = 100;
	public static int projSpeed = 8;
	public static int reloadSpeed = 8;

	public Flamethrower() {
		super(damage, fireRate, magazine, projSpeed, reloadSpeed);
	}
	
	@Override
	public ArrayList<Entity> createProjectile() {
		ArrayList<Entity> shot = new ArrayList<Entity>();
		float releasedxCoor = mainChar.getxCoor() + mainChar.getWidth()/2;
		float releasedyCoor = mainChar.getyCoor() + mainChar.getHeight()/2;
		
		shot.add(new FireProjectile(releasedxCoor, releasedyCoor, mainChar.getDirection(), this, 0));
		
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
