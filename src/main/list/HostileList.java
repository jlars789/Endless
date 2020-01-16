package main.list;

import hostiles.Hostile;

public class HostileList extends EntityList {

	public HostileList() {
		super();
	}
	
	public Hostile get(int index) {
		return (Hostile) super.get(index);
	}

}
