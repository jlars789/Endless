package weapons;

import java.util.ArrayList;

import main.Entity;
import projectiles.SniperBullet;

public class Hawk extends Weapon {

	private static final long serialVersionUID = 7558675096982654486L;
	private static final String WEAPON_NAME = "Hawk";
	
	private boolean slowed;
	
	public static double damage = 560;
	public static int fireRate = 50;
	public static int magazine = 1;
	public static int pierce = 12;
	public static float projSpeed = 40;
	public static int reloadSpeed = 120;

	public Hawk() {
		super(damage, fireRate, magazine, pierce, projSpeed, reloadSpeed);
		super.setShotDelay(super.getFireRate());
		super.setDelayed(true);
	}
	
	@Override
	public ArrayList<Entity> createProjectile() {
		ArrayList<Entity> shot = new ArrayList<Entity>();
		shot.add(new SniperBullet(mainChar.getxCoor() + mainChar.getWidth()/2, (float)(mainChar.getyCoor() + (mainChar.getHeight()/1.4)), mainChar.getDirection(), this));
		return shot;
	}
	
	public void timer() {
		
		if(!mainChar.isShoot()) {
			super.setShotDelay(super.getFireRate());
		}
		
		if(!mainChar.isShoot() && slowed) {
			mainChar.getStats().setSpeed(mainChar.getStats().getSpeed() * 2, false);
			slowed = false;
		} 
		else if(mainChar.isShoot() && !slowed) {
			mainChar.getStats().setSpeed((mainChar.getStats().getSpeed()/2), false);
			slowed = true;
		}
		
		super.timer();
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
