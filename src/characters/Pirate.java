package characters;

import abilities.Ability;
import abilities.Pirate.Barrage;
import abilities.Pirate.Bloodrage;
import abilities.Pirate.Cauterize;
import abilities.Pirate.GunpowderAbility;
import abilities.Pirate.Haymaker;
import abilities.Pirate.Ignite;
import abilities.Pirate.InfernoAbility;
import abilities.Pirate.SonicBoom;
import abilities.Pirate.WreckingCrew;
import sprite.CharacterSprite;
import weapons.Blunderbuss;
import weapons.Scylla;
import weapons.Weapon;

public class Pirate extends Characters {
	
	private static final long serialVersionUID = -4071847715883200856L;
	public static final Weapon[] DEFAULT_WEAPONS = {new Scylla(), new Blunderbuss()};
	public static final Ability[] PYROMANIAC_ABILITIES = {new Ignite(), new Cauterize(), new InfernoAbility()};
	public static final Ability[] BUCCANEER_ABILITIES = {new WreckingCrew(), new GunpowderAbility(), new Barrage()};
	public static final Ability[] BRAWLER_ABILITIES = {new SonicBoom(), new Haymaker(), new Bloodrage()}; 
	
	public static int healthCap = 250;
	public static float naturalSpeed = 3.8f;

	public Pirate() {
		super(healthCap, naturalSpeed);
		super.setSprite(new CharacterSprite("imgs/anims/pirate_idle.png", 21, 15, 1, 1, 0, true));
	}
	
	public void reset() {
		super.getMoveset().setWeapon(DEFAULT_WEAPONS);
		super.reset();
	}

}


