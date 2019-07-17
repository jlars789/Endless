package characters;

import abilities.Ability;
import abilities.Ninja.Blademaster;
import abilities.Ninja.DeceptionAbility;
import abilities.Ninja.Evade;
import abilities.Ninja.KunaiToss;
import abilities.Ninja.Ninjutsu;
import abilities.Ninja.PoisonDartAbility;
import abilities.Ninja.ShadowSlice;
import abilities.Ninja.SmokeBomb;
import abilities.Ninja.SwordThrow;
import sprite.CharacterSprite;
import weapons.Shadowblade;
import weapons.ShurikenWeapon;
import weapons.Weapon;

public class Ninja extends Characters {
	
	private static final long serialVersionUID = 7304162442460009608L;
	public static final Weapon[] DEFAULT_WEAPONS = {new ShurikenWeapon(), new Shadowblade()};
	public static final Ability[] GUERILLA_ABILITIES = {new PoisonDartAbility(), new SmokeBomb(), new DeceptionAbility()};
	public static final Ability[] GRANDMASTER_ABILITIES = {new KunaiToss(), new Evade(), new Ninjutsu()};
	public static final Ability[] ASSASSIN_ABILITIES = {new ShadowSlice(), new SwordThrow(), new Blademaster()};
	
	public static float naturalSpeed = 4.2f;
	public static int healthCap = 150; 
	
	
	public Ninja() {
		super(healthCap, naturalSpeed);
		super.setSprite(new CharacterSprite("imgs/anims/ninja_idle.png", 21, 15, 1, 1, 0, true));
		
	}
	
	public void reset() {
		super.getMoveset().setWeapon(DEFAULT_WEAPONS);
		super.reset();
	}

}
