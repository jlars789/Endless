package weapons;

import java.util.ArrayList;
import java.util.Random;

import main.Entity;
import projectiles.Bullet;
import sprite.LightSprite;

public class Minigun extends Weapon {
	
	private static final long serialVersionUID = 94983517582809430L;
	private static LightSprite projectileSprite = new LightSprite("imgs/projectiles/bullet.png");
	private static final String WEAPON_NAME = "Minigun";
	private int spinUp;
	
	public static double damage = 44;
	public static int fireRate = 30;
	public static int magazine = 100;
	public static int pierce = 2;
	public static int projSpeed = 26;
	public static int reloadSpeed = 130;

	public Minigun() {
		super(damage, fireRate, magazine, pierce, projSpeed, reloadSpeed);
		super.setAccuracy(2.5f);
		this.spinUp = super.getFireRateDefault();
	}
	
	@Override
	public ArrayList<Entity> createProjectile() {
		ArrayList<Entity> shot= new ArrayList<Entity>();
		float tempAccuracy = randomFloat((-super.getAccuaracy()), super.getAccuaracy());
		shot.add(new Bullet(mainChar.getxCoor() + mainChar.getWidth()/2, (float)(mainChar.getyCoor() + (mainChar.getHeight()/1.4)), mainChar.getDirection(), this, tempAccuracy));
		return shot;
	}
	
	public void timer() {
		
		if(mainChar.isShoot()) {
			if(super.getShotDelay() % 4 == 0 && spinUp > Weapon.FIRE_RATE_CAP) {
				spinUp--;
			}
		} else {
			if(super.getShotDelay() % 4 == 0 && spinUp < super.getFireRateDefault()) {
				spinUp++;
			}
		}
		
		super.setFireRate(this.spinUp);
		super.timer();
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
		return AnimationType.HEAVY;
	}

	@Override
	public WeaponType getType() {
		return WeaponType.NORMAL_WEAPON;
	}

}
