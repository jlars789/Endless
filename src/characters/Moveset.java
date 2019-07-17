package characters;

import java.io.Serializable;

import abilities.Ability;
import weapons.Weapon;

public class Moveset implements Serializable {

	private static final long serialVersionUID = -3979809907981574162L;
	private Ability[] abilities = new Ability[3];
	private Weapon[] weapons = new Weapon[2];

	public Moveset(Ability[] a, Weapon[] w) {
		this.abilities[0] = a[0];
		this.abilities[1] = a[1];
		this.abilities[2] = a[2];
		
		this.weapons[0] = w[0];
		this.weapons[1] = w[1];
	}
	
	public void update() {
		
		for(int i = 0; i < 3; i++) {
			if(abilities[i].getStatus()) {
				abilities[i].update();
			}
			if(i < 2) {
				if(abilities[i].getCooldown() > 0) {
					abilities[i].setToggle(false);
					abilities[i].cooldownTimer();
				}
			}
		}
	}
	
	public void swapWeapons() {
		
	}
	
	public void setAbility(Ability[] a) {
		abilities[0] = a[0];
		abilities[1] = a[1];
		abilities[2] = a[2];
	}
	
	public void setWeapon(Weapon[] w) {
		weapons[0] = w[0];
		weapons[1] = w[1];
	}
	
	public void setWeapon(Weapon weapon, int index) {
		weapons[index] = weapon;
	}
	
	public void reset() {
		for(int i = 0; i < abilities.length; i++) {
			abilities[i].reset();
		}
		
		for(int i = 0; i < weapons.length; i++) {
			weapons[i].reset();
		}
	}
	
	public Ability[] getAbilities() {
		return abilities;
	}
	
	public Weapon[] getWeapons() {
		return weapons;
	}

}
