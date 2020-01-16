package main.list;

import friendlies.Friendly;

public class FriendlyList extends EntityList {

	public FriendlyList() {
		super();
	}
	
	public Friendly get(int index) {
		return (Friendly)super.get(index);
	}

}
