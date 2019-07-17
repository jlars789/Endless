package menus;

import java.awt.Color;
import java.awt.Graphics2D;

import characters.Characters;
import characters.Cowboy;
import characters.Ninja;
import characters.Pirate;
import main.Gamepanel;
import weapons.Weapon;

public class SkillSelect extends Menu {
	
	private static final byte hMax = 2;
	private static final byte vMax = 0;
	
	private static int abilitySlotX = 225;
	private static int abilityNameX = 320;
	
	private Characters c;

	public SkillSelect(Characters c) {
		super(hMax, vMax);
		this.c = c;
	}

	@Override
	public void select() {
		if(c instanceof Cowboy) {
			Weapon[] w = Cowboy.DEFAULT_WEAPONS;
			if(super.getHoriSelector() == 0) {
				c.setMoves(Cowboy.OUTLAW_ABILITIES, w);
			}
			else if(super.getHoriSelector() == 1) {
				c.setMoves(Cowboy.GUNSLINGER_ABILITIES, w);
			}
			else if(super.getHoriSelector() == 2) {
				c.setMoves(Cowboy.MARKSMAN_ABILITIES, w);
			}
		}
		else if(c instanceof Ninja) {
			Weapon[] w = Ninja.DEFAULT_WEAPONS;
			if(super.getHoriSelector() == 0) {
				c.setMoves(Ninja.GUERILLA_ABILITIES, w);
			}
			else if(super.getHoriSelector() == 1) {
				c.setMoves(Ninja.GRANDMASTER_ABILITIES, w);
			}
			else if(super.getHoriSelector() == 2) {
				c.setMoves(Ninja.ASSASSIN_ABILITIES, w);
			}
		}
		else if(c instanceof Pirate) {
			Weapon[] w = Pirate.DEFAULT_WEAPONS;
			if(super.getHoriSelector() == 0) {
				c.setMoves(Pirate.PYROMANIAC_ABILITIES, w);
			}
			else if(super.getHoriSelector() == 1) {
				c.setMoves(Pirate.BUCCANEER_ABILITIES, w);
			}
			else if(super.getHoriSelector() == 2) {
				c.setMoves(Pirate.BRAWLER_ABILITIES, w);
			}
		}
		Gamepanel.currentMenu = null;
		Gamepanel.gameStart();
	}

	@Override
	public void back() {
		Gamepanel.currentMenu = new CharSelect();
	}

	@Override
	public void draw(Graphics2D g2d) {
		
		g2d.setColor(Color.WHITE);
		g2d.setFont(Menu.MAIN);
		g2d.drawString("Select Skills", 510, 50);
		
		g2d.setFont(Menu.SMALL);
		g2d.drawString("Ability 1: ", abilitySlotX, 350);
		g2d.drawString("Ability 2: ", abilitySlotX, 500);
		g2d.drawString("Super: ", abilitySlotX, 650);
		
		switch(super.getHoriSelector()) {
		case 0:
			g2d.drawRect(395, 200, 130, 105);
		break;
		
		case 1:
			g2d.drawRect(540, 200, 130, 105);
		break;
		
		case 2:
			g2d.drawRect(685, 200, 130, 105);
		break;
		}
		
		if(c instanceof Cowboy) {
			this.drawCowboy(g2d);
		}
		else if(c instanceof Ninja) {
			this.drawNinja(g2d);
		}
		else if(c instanceof Pirate) {
			this.drawPirate(g2d);
		}

	}
	
	private void drawPirate(Graphics2D g2d) {
		g2d.drawString("Pyromaniac", 410, 300);
		g2d.drawString("Buccaneer", 555, 300);
		g2d.drawString("Brawler", 710, 300);
		
		if(super.getHoriSelector() == 0) {
			g2d.setFont(Menu.SMALL_BOLD);
			g2d.drawString("Immolate", abilityNameX, 350);
			g2d.drawString("Cauterize", abilityNameX, 500);
			g2d.drawString("Hades", abilityNameX, 650);
			
			g2d.setFont(Menu.SMALL);
			g2d.drawString("Set yourself on fire, dealing damage to all within range while increasing your speed", abilitySlotX, 400);
			g2d.drawString("Reduce all incoming damage and gain a bit of lifesteal", abilitySlotX, 550);
			g2d.drawString("Pull out a powerful Flamethrower", abilitySlotX, 700);
		}
		else if(super.getHoriSelector() == 1) {
			g2d.setFont(Menu.SMALL_BOLD);
			g2d.drawString("Wrecking Ball", abilityNameX, 350);
			g2d.drawString("Gunpowder", abilityNameX, 500);
			g2d.drawString("Barrage", abilityNameX, 650);
			
			g2d.setFont(Menu.SMALL);
			g2d.drawString("Shoot a massive cannonball that will deal high damage and shove enemies", abilitySlotX, 400);
			g2d.drawString("Throw a handful of Gunpowder, causing them to take additional damage and explode upon death", abilitySlotX, 550);
			g2d.drawString("Cause explosions to fill the room for a period of time", abilitySlotX, 700);
		}
		else if(super.getHoriSelector() == 2) {
			g2d.setFont(Menu.SMALL_BOLD);
			g2d.drawString("Sonic Boom", abilityNameX, 350);
			g2d.drawString("Haymaker", abilityNameX, 500);
			g2d.drawString("Bloodrage", abilityNameX, 650);
			
			g2d.setFont(Menu.SMALL);
			g2d.drawString("Shoot a Sonic Boom, stunning and knocking back all enemies", abilitySlotX, 400);
			g2d.drawString("Dash forwards, punching the first enemy you hit, dealing immense damage", abilitySlotX, 550);
			g2d.drawString("Pull out your fists, dealing heavy close range damage and providing lifesteal", abilitySlotX, 700);
		}
	}
	
	private void drawNinja(Graphics2D g2d) {
		g2d.drawString("Guerilla", 418, 300);
		g2d.drawString("Grandmaster", 547, 300);
		g2d.drawString("Assassin", 710, 300);
		
		if(super.getHoriSelector() == 0) {
			g2d.setFont(Menu.SMALL_BOLD);
			g2d.drawString("Poison Dart", abilityNameX, 350);
			g2d.drawString("Smoke Bomb", abilityNameX, 500);
			g2d.drawString("Deception", abilityNameX, 650);
			
			g2d.setFont(Menu.SMALL);
			g2d.drawString("Shoot a dart, applying a poison effect to any enemy it touches", abilitySlotX, 400);
			g2d.drawString("Go invisible and increase damage for a period of time", abilitySlotX, 550);
			g2d.drawString("Deploy a Shadow Double that will attack enemies while you go invisible", abilitySlotX, 700);
		}
		else if(super.getHoriSelector() == 1) {
			g2d.setFont(Menu.SMALL_BOLD);
			g2d.drawString("Kunai Toss", abilityNameX, 350);
			g2d.drawString("Evade", abilityNameX, 500);
			g2d.drawString("Ninjutsu", abilityNameX, 650);
			
			g2d.setFont(Menu.SMALL);
			g2d.drawString("Throw a handful of Kunai, reducing the damage of all enemies it hits", abilitySlotX, 400);
			g2d.drawString("Shift in a certain direction and retainn the ability to attack", abilitySlotX, 550);
			g2d.drawString("Throw three shurikens per attack for a short period of time", abilitySlotX, 700);
		}
		else if(super.getHoriSelector() == 2) {
			g2d.setFont(Menu.SMALL_BOLD);
			g2d.drawString("Shadow Slice", abilityNameX, 350);
			g2d.drawString("Seeking Sword", abilityNameX, 500);
			g2d.drawString("Blademaster", abilityNameX, 650);
			
			g2d.setFont(Menu.SMALL);
			g2d.drawString("Dash forwards, dealing damage to all enemies you cross", abilitySlotX, 400);
			g2d.drawString("Throw a spinning blade out that will return to you, dealing high damage to all it touches", abilitySlotX, 550);
			g2d.drawString("Summon two orbiting swords that will deal heavy damage and block enemy projectiles", abilitySlotX, 700);
		}
	}
	
	private void drawCowboy(Graphics2D g2d) {
		g2d.drawString("Outlaw", 428, 300);
		g2d.drawString("Gunslinger", 555, 300);
		g2d.drawString("Marksman", 700, 300);
		
		if(super.getHoriSelector() == 0) {
			g2d.setFont(Menu.SMALL_BOLD);
			g2d.drawString("Heavy Whip", abilityNameX, 350);
			g2d.drawString("Dynamite", abilityNameX, 500);
			g2d.drawString("Anarchy", abilityNameX, 650);
			
			g2d.setFont(Menu.SMALL);
			g2d.drawString("Use your Whip to knock enemies back and cause them to take more damage", abilitySlotX, 400);
			g2d.drawString("Throw a stick of Dynamite that detonates on a timer", abilitySlotX, 550);
			g2d.drawString("For a short period of time, all shots fired will deal more damage and stun enemies", abilitySlotX, 700);
			
		}
		else if(super.getHoriSelector() == 1) {
			g2d.setFont(Menu.SMALL_BOLD);
			g2d.drawString("Trickshot", abilityNameX, 350);
			g2d.drawString("Quick Hand", abilityNameX, 500);
			g2d.drawString("Showdown", abilityNameX, 650);
			
			g2d.setFont(Menu.SMALL);
			g2d.drawString("Fire a bullet that will bounce off enemies and walls", abilitySlotX, 400);
			g2d.drawString("Shift in a certain direction and reload all weapons", abilitySlotX, 550);
			g2d.drawString("Gain infinite ammo, fire faster, but have reduced accuracy for a period of time", abilitySlotX, 700);
		}
		else if(super.getHoriSelector() == 2) {
			g2d.setFont(Menu.SMALL_BOLD);
			g2d.drawString("Portable Cover", abilityNameX, 350);
			g2d.drawString("Critical Trance", abilityNameX, 500);
			g2d.drawString("Continuum", abilityNameX, 650);
			
			g2d.setFont(Menu.SMALL);
			g2d.drawString("Drop a barrier that will block all enemy projectiles, but allow yours to pass", abilitySlotX, 400);
			g2d.drawString("Double all damage for a very short period of time. Kills extend duration", abilitySlotX, 550);
			g2d.drawString("Fire a bullet that warps through walls, piercing everything it touches", abilitySlotX, 700);
		}
	}

}
