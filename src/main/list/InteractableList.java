package main.list;

import interactable.Interactable;

public class InteractableList extends EntityList {

	public InteractableList() {
		super();
	}
	
	public Interactable get(int index) {
		return (Interactable) super.get(index);
	}

}
