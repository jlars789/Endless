package weapons;

import java.util.ArrayList;

import main.Entity;
import projectiles.Nuke;

public class WoMD extends Weapon {
	
	private static final long serialVersionUID = 1508143791374307469L;
	public static double damage = 9999999;
	public static int fireRate = 30;
	public static int magazine = 100000;
	public static float projSpeed = 0;
	public static int reloadSpeed = 1;
	
	public WoMD() {
		super(damage, fireRate, magazine, projSpeed, reloadSpeed);
	}

	@Override
	public ArrayList<Entity> createProjectile() {
		ArrayList<Entity> nuke = new ArrayList<Entity>();
		nuke.add(new Nuke(this));
		return nuke;
	}

	@Override
	public WeaponType getType() {
		return WeaponType.BEAM_WEAPON;
	}

	@Override
	public AnimationType getAnimType() {
		return AnimationType.THROWN;
	}

	@Override
	public String weaponName() {
		return "That's Not Fair";
	}

}
