package weapons;

import java.util.ArrayList;
import java.util.Random;

import main.Entity;
import projectiles.Shuriken;
import sprite.HeavySprite;


public class ShurikenWeapon extends Weapon {
	
	private static final long serialVersionUID = 4038262956275837763L;
	private static HeavySprite projectileSprite = new HeavySprite("imgs/projectiles/shuriken.png", 5, 5, 1, 3, 4, true);
	private static final String WEAPON_NAME = "Shuriken";
	
	public static int damage = 39;
	public static int fireRate = 15;
	public static int magazine = 15;
	public static int pierce = 2;
	public static float projSpeed = 9;
	public static int reloadSpeed = 80;
	
	private int projectiles;

	public ShurikenWeapon() {
		super(damage, fireRate, magazine, pierce, projSpeed, reloadSpeed);
		this.projectiles = 1;
	}
	
	public ShurikenWeapon(int projectiles) {
		super(damage, fireRate, magazine, pierce, projSpeed, reloadSpeed);
		this.projectiles = projectiles;
	}
	
	@Override
	public ArrayList<Entity> createProjectile() {
		
		ArrayList<Entity> shot = new ArrayList<Entity>();
		
		if(this.projectiles == 1) {
			switch (mainChar.getDirection()) {
			case 0:
				shot.add(new Shuriken(mainChar.getxCoor() + (int)(mainChar.getWidth()/3), mainChar.getyCoor() + mainChar.getHeight()/2, mainChar.getDirection(), this));  
			break;
			
			case 1: 
				shot.add(new Shuriken(mainChar.getxCoor() + mainChar.getWidth()/2, mainChar.getyCoor() + mainChar.getHeight()/4, mainChar.getDirection(), this));
			break;
			
			case 2:
				shot.add(new Shuriken(mainChar.getxCoor() + (int)(mainChar.getWidth()/4), mainChar.getyCoor() + mainChar.getHeight()/2, mainChar.getDirection(), this));
			break;
			
			case 3:
				shot.add(new Shuriken(mainChar.getxCoor() + mainChar.getWidth()/8, mainChar.getyCoor() + mainChar.getHeight()/4, mainChar.getDirection(), this));
			break;
			
			}
		} else {
			for(int i = 0; i < projectiles; i++) {
				shot.add(new Shuriken(mainChar.getCenterPoint()[0], mainChar.getCenterPoint()[1], mainChar.getDirection(), this, randomFloat(-2, 2)));
			}
		}
		
		return shot;
	}
	
	public HeavySprite getProjectileSprite() {
		return projectileSprite;
	}

	@Override
	public String weaponName() {
		return WEAPON_NAME;
	}
	
	private float randomFloat(float min, float max) {
		Random random = new Random();
		return (random.nextFloat() * (max - min)) + min;
	}

	@Override
	public AnimationType getAnimType() {
		return AnimationType.THROWN;
	}

	@Override
	public WeaponType getType() {
		return WeaponType.NORMAL_WEAPON;
	}

}
