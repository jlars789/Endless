package console;

import main.Gamepanel;

enum Fields {
	damage, firerate, speed, health
}

public class ModifyStats extends Command {

	public ModifyStats() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String name() {
		return "modchar";
	}

	@Override
	public boolean validField(String args0) {
		boolean hit = false;
		if(args0.contains(Fields.damage.toString())) {
			double damage = Double.parseDouble(args0.substring(7));
			Gamepanel.mainChar.getStats().setDamage(damage);
			hit = true;
		}
		else if(args0.contains(Fields.firerate.toString())) {
			double fireRate = Double.parseDouble(args0.substring(9));
			Gamepanel.mainChar.getStats().setAttackSpeed(fireRate);
			hit = true;
		}
		else if(args0.contains(Fields.speed.toString())) {
			float speed = Float.parseFloat(args0.substring(6));
			Gamepanel.mainChar.getStats().setSpeed(speed, true);
			hit = true;
		} 
		else if(args0.contains(Fields.health.toString())) {
			int health = Integer.parseInt(args0.substring(7));
			Gamepanel.mainChar.getStats().getHealth().changeMax(health);
			hit = true;
		}
		
		return hit;
	}

	@Override
	public void action() {
	}

	@Override
	public boolean forceClose() {
		// TODO Auto-generated method stub
		return false;
	}

}
