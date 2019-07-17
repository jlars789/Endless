package weapons;

import java.util.ArrayList;

import characters.Ninja;
import main.Entity;
import projectiles.Sword;
import sprite.LightSprite;

public class Shadowblade extends Weapon {
	
	private static final long serialVersionUID = -7953857794568638907L;
	private static LightSprite projectileSprite = new LightSprite("imgs/projectiles/sword.png", 4);
	private static final String WEAPON_NAME = "Shadow Blade";
	
	public static double damage = 134;
	public static int fireRate = 60;
	public static int pierce = 8;
	public static int projSpeed = 8;
	
	private boolean oddSwipe;

	public Shadowblade() {
		super(damage, fireRate, pierce, projSpeed);
		this.oddSwipe = true;
	}
	
	@Override
	public ArrayList<Entity> createProjectile() {
		ArrayList<Entity> shot = new ArrayList<Entity>();
		float lungeDist = 20;
		
		if(oddSwipe) oddSwipe = false;
		else oddSwipe = true;
		if(mainChar != null) {
			shot.add(new Sword(mainChar.getxCoor() + mainChar.getWidth()/2, mainChar.getyCoor() + mainChar.getHeight()/2, mainChar.getDirection(), this, (Ninja)mainChar));
		} 
		
		if(mainChar.inBounds(lungeDist, mainChar.getDirection())) {
			switch(mainChar.getDirection()){
			case 0:
				mainChar.setyCoor(mainChar.getyCoor() - lungeDist);
			break;
			
			case 1:
				mainChar.setxCoor(mainChar.getxCoor() + lungeDist);
			break;
			
			case 2:
				mainChar.setyCoor(mainChar.getyCoor() + lungeDist);
			break;
			
			case 3:
				mainChar.setxCoor(mainChar.getxCoor() - lungeDist);
			break;
			}
		}
		
		return shot;
	}
	
	public void swapTo() {
		mainChar.getStats().setSpeed(mainChar.getStats().getSpeed() + 0.5f, false);
	}
	
	public void swapFrom() {
		mainChar.getStats().setSpeed(mainChar.getStats().getSpeed() - 0.5f, false);
	}
	
	public boolean isOddSwipe() {
		return this.oddSwipe;
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
		return AnimationType.MELEE;
	}

	@Override
	public WeaponType getType() {
		return WeaponType.MELEE_WEAPON;
	}

}
