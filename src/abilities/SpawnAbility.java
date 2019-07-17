package abilities;

import java.util.ArrayList;

import main.Entity;
import main.EntityLists;

public abstract class SpawnAbility extends Ability {
	
	private static final long serialVersionUID = -7157318933775455146L;

	public SpawnAbility(int slot, int cooldown, boolean independent, int duration) {
		super();
		super.setAbilitySlot(slot);
		super.setCooldownMax(cooldown);
		super.setIndependent(independent);
		super.setDuration(duration);
	}
	
	public SpawnAbility(int cost, boolean independent, int duration) {
		super();
		super.setAbilitySlot(2);
		super.setCost(cost);
		super.setIndependent(independent);
		super.setDuration(duration);
	}
	
	public void use() {
		this.addFriends();
		super.use();
	}
	
	protected abstract ArrayList<Entity> createFriend();
	
	private void addFriends() {
		EntityLists.addNew(this.createFriend());
	}

}
