package weapons;

import java.util.ArrayList;

import characters.Characters;
import main.Entity;
import main.Gamepanel;
import projectiles.Fist;

public class Fisticuffs extends Weapon {
	
	private static final long serialVersionUID = -1816490511528512067L;

	private static final String WEAPON_NAME = "Fisticuffs";
	
	public static double damage = 230;
	public static int fireRate = 40;
	public static int pierce = 2;

	public Fisticuffs() {
		super(damage, fireRate, pierce, 0);
	}

	@Override
	public ArrayList<Entity> createProjectile() {
		Characters c = Gamepanel.mainChar;
		ArrayList<Entity> fist = new ArrayList<Entity>();
		fist.add(new Fist(-100, -100, Gamepanel.mainChar.getDirection(), this));
		c.shift(c.getDirection(), 30);
		return fist;
		
	}

	@Override
	public WeaponType getType() {
		return WeaponType.MELEE_WEAPON;
	}

	@Override
	public AnimationType getAnimType() {
		return AnimationType.MELEE;
	}

	@Override
	public String weaponName() {
		return WEAPON_NAME;
	}

}
