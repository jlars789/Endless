package characters;

import abilities.Ability;
import abilities.Wish;
import abilities.Ninja.Blademaster;
import abilities.Ninja.Evade;
import sprite.CharacterSprite;
import weapons.StormSurge;
import weapons.Weapon;
import weapons.WoMD;

public class Diety extends Characters {

	private static final long serialVersionUID = -1533460588263728865L;
	
	private static final int HEALTH = 99999999;
	private static final float SPEED = 5.5f;
	private static final Weapon[] WEAPONS = {new StormSurge(), new WoMD()};
	private static final Ability[] ABILITIES = {new Wish(), new Evade(), new Blademaster()};

	public Diety() {
		super(HEALTH, SPEED);
		super.setSprite(new CharacterSprite("imgs/anims/diety.png", 21, 15, 1, 1, 0, true, 4));
		super.setMoves(ABILITIES, WEAPONS);
	}
	
	

}
