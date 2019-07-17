package weapons;

import java.util.ArrayList;
import java.util.Random;

import main.Entity;
import projectiles.Pellet;
import sprite.LightSprite;

public class Blunderbuss extends Weapon {
	
	private static final long serialVersionUID = 906056982583803781L;
	
	
	private static final String WEAPON_NAME = "Blunderbuss";
	private static LightSprite projectileSprite = new LightSprite("imgs/projectiles/pellet.png");
	
	public static int projectilesReleased = 14;
	public static double damage = 45;
	public static int magazine = 2;
	public static int fireRate = 30;
	public static float projectileSpeed = 9;
	public static int reloadSpeed = 140;

	public Blunderbuss() {
		super(damage, fireRate, magazine, 1, projectileSpeed, reloadSpeed);
	}
	
	@Override
	public ArrayList<Entity> createProjectile() {
		ArrayList<Entity> shot = new ArrayList<Entity>();
		
		float releasedxCoor = mainChar.getxCoor() + mainChar.getWidth()/3;
		float releasedyCoor = mainChar.getyCoor() + mainChar.getHeight()/3;
		
		shot.add(new Pellet(releasedxCoor, releasedyCoor, mainChar.getDirection(), this, 0, projectileSprite.copy()));
		for(int i = 1; i < projectilesReleased; i++) {
			float accuracy = randomFloat((float)-2.5, (float)2.5);
			shot.add(i, new Pellet(releasedxCoor, releasedyCoor, mainChar.getDirection(), this, accuracy, projectileSprite.copy()));
		}
		
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
		return AnimationType.RIFLE;
	}

	@Override
	public WeaponType getType() {
		return WeaponType.NORMAL_WEAPON;
	}

}
