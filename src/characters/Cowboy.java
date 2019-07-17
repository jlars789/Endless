package characters;

import abilities.Ability;
import abilities.Cowboy.AnarchyAbility;
import abilities.Cowboy.Continuum;
import abilities.Cowboy.CriticalStrike;
import abilities.Cowboy.Dynamite;
import abilities.Cowboy.HeavyWhip;
import abilities.Cowboy.PortableCover;
import abilities.Cowboy.QuickHand;
import abilities.Cowboy.Showdown;
import abilities.Cowboy.Trickshot;
import sprite.CharacterSprite;
import weapons.Dessenger;
import weapons.Hawk;
import weapons.Weapon;

public class Cowboy extends Characters {
	
	private static final long serialVersionUID = -6545300571700635491L;
	public static final Weapon[] DEFAULT_WEAPONS = {new Dessenger(), new Hawk()};
	public static final Ability[] OUTLAW_ABILITIES = {new HeavyWhip(), new Dynamite(), new AnarchyAbility()};
	public static final Ability[] GUNSLINGER_ABILITIES = {new Trickshot(), new QuickHand(), new Showdown()};
	public static final Ability[] MARKSMAN_ABILITIES = {new PortableCover(), new CriticalStrike(), new Continuum()};
	
	public static int healthCap = 200;
	public static float naturalSpeed = 4.0f;
	
	public Cowboy() {
		super(healthCap, naturalSpeed);
		super.setSprite(new CharacterSprite("imgs/anims/cowboy_idle.png", 21, 15, 1, 1, 0, true));
	}
	
	public void reset() {
		super.getMoveset().setWeapon(DEFAULT_WEAPONS);
		super.reset();
	}
}
