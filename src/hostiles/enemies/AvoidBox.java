package hostiles.enemies;

import java.awt.Rectangle;

import hostiles.Hostile;

public class AvoidBox {
	
	private float fullxCoor;
	private float fullyCoor;
	private int size;
	
	private Rectangle[] rectArray;
	private Rectangle avoidBox;
	
	private Hostile enemyRef;

	public AvoidBox(Hostile enemy, int size) {
		
		this.enemyRef = enemy;
		this.size = size;
		
		this.rectArray = new Rectangle[4];
		
		this.avoidBox = new Rectangle((int)enemy.getxCoor(), (int)enemy.getyCoor(), size, size);
		
		this.rectArray[0] = new Rectangle((int)enemy.getCenterPoint()[0] - (size/2), (int)enemy.getCenterPoint()[1] - (size/2), size, (size/3));
		this.rectArray[1] = new Rectangle((int)enemy.getCenterPoint()[0] + (size/2), (int)enemy.getCenterPoint()[1] - (size/2), (size/3), size);
		this.rectArray[2] = new Rectangle((int)enemy.getCenterPoint()[0] - (size/2), (int)enemy.getCenterPoint()[1] + (size/2), size, (size/3));
		this.rectArray[3] = new Rectangle((int)enemy.getCenterPoint()[0] - (size/2), (int)enemy.getCenterPoint()[1] - (size/2), (size/3), size);
	}
	
	public void update() {
		this.fullxCoor = enemyRef.getCenterPoint()[0] - (size/2);
		this.fullyCoor = enemyRef.getCenterPoint()[1] - (size/2);
		
		this.avoidBox.setLocation((int)this.fullxCoor, (int)this.fullyCoor);
		this.rectArray[0].setLocation((int)this.fullxCoor, (int)this.fullyCoor);
		this.rectArray[1].setLocation((int)(this.fullxCoor + (size * 0.5)), (int)this.fullyCoor);
		this.rectArray[2].setLocation((int)this.fullxCoor, (int)(this.fullyCoor + (size * 0.5)));
		this.rectArray[3].setLocation((int)this.fullxCoor, (int)this.fullyCoor);
	}
	
	public Rectangle[] getArray() {
		return this.rectArray;
	}
	
	public Rectangle getGeneral() {
		return this.avoidBox;
	}

}
