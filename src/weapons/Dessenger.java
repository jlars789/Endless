package weapons;

import java.util.ArrayList;
import java.util.Random;

import main.Entity;
import projectiles.Bullet;
import sprite.LightSprite;

public class Dessenger extends Weapon {
	
	private static final String WEAPON_NAME = "Dessenger";
	
	private static final long serialVersionUID = -8020229274165654119L;
	private static LightSprite projectileSprite = new LightSprite("imgs/projectiles/bullet.png");
	
	public static double damage = 150;
	public static int fireRate = 32;
	public static int magazine = 6;
	public static int pierce = 1;
	public static float projSpeed = 26;
	public static int reloadSpeed = 110;

	public Dessenger() {
		super(damage, fireRate, magazine, pierce, projSpeed, reloadSpeed);
	}
	
	@Override
	public ArrayList<Entity> createProjectile() {
		ArrayList<Entity> shot = new ArrayList<Entity>();
		float tempAccuracy = randomFloat((-super.getAccuaracy()), super.getAccuaracy());
		shot.add(new Bullet(mainChar.getxCoor() + mainChar.getWidth()/2, (float)(mainChar.getyCoor() + (mainChar.getHeight()/1.4)), mainChar.getDirection(), this, tempAccuracy));
		
		return shot;
	}
	
	public LightSprite getProjectileSprite() {
		return projectileSprite;
	}
	
	private float randomFloat(float min, float max) {
		Random random = new Random();
		return (random.nextFloat() * (max - min)) + min;
	}

	@Override
	public String weaponName() {
		return WEAPON_NAME;
	}

	@Override
	public AnimationType getAnimType() {
		return AnimationType.HANDGUN;
	}

	@Override
	public WeaponType getType() {
		return WeaponType.NORMAL_WEAPON;
	}

}
