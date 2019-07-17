package main;

import java.awt.Rectangle;
import com.vividsolutions.jts.geom.LineSegment;

import characters.Characters;

public class Hitbox extends Rectangle {
	
	private double x;
	private double y;
	
	private static final long serialVersionUID = 290254639860847387L;
	private LineSegment[] border = new LineSegment[4];

	public Hitbox() {
		super();
	}
	
	public Hitbox(Rectangle r) {
		super(r);
		border[0] = new LineSegment(r.x, r.y, r.x + r.width, r.y); //top border
		border[1] = new LineSegment(r.x + r.width, r.y, r.x + r.width, r.y + r.height); //right border
		border[2] = new LineSegment(r.x + r.width, r.y + r.height, r.x, r.y + r.height); //bottom border
		border[3] = new LineSegment(r.x, r.y + r.height, r.x, r.y); //left border
	}

	public Hitbox(float x, float y, int width, int height) {
		super((int)x, (int)y, (int)(width * Gamepanel.sizeMult), (int)(height * Gamepanel.sizeMult));
		int w = (int)(width * Gamepanel.sizeMult);
		int h = (int)(height * Gamepanel.sizeMult);
		this.x = x;
		this.y = y;
		border[0] = new LineSegment(x, y, x + w, y); //top
		border[1] = new LineSegment(x + w, y, x + w, y + h); //right
		border[2] = new LineSegment(x + w, y + h, x, y + h); //bottom
		border[3] = new LineSegment(x, y + h, x, y); //left
	}
	
	public void move(boolean[] move, double speed) {
		
		double reducer = 0;
		
		for(int i = 0; i < move.length; i++) {
			if(move[i]) reducer++;
		}
		
		if(reducer > 1) reducer = Math.sqrt(2);
		else reducer = 1;

		
		if(move[0] && this.y > Characters.Y_MIN) this.y -= (speed / reducer) * Characters.delta;
		else if (move[2] && this.y < Characters.Y_BOUNDS - height) this.y += (speed / reducer)* Characters.delta;
		if(move[1] && this.x < Characters.X_BOUNDS - width) this.x += (speed / reducer)* Characters.delta;
		else if(move[3] && this.x > Characters.X_MIN) this.x -= (speed / reducer)* Characters.delta;
		
		this.update();
	}
	
	public void update() {
		int width = super.width;
		int height = super.height;
		
		border[0] = new LineSegment(x, y, x + width, y); //top
		border[1] = new LineSegment(x + width, y, x + width, y + height); //right
		border[2] = new LineSegment(x + width, y + height, x, y + height); //bottom
		border[3] = new LineSegment(x, y + height, x, y); //left
		
		super.x = (int) this.x;
		super.y = (int) this.y;
	}
	
	/**
	 * Checks if a given line segment intersects the rectangle
	 * @param s Line segment to be evaluated
	 * @return True if there is a point of intersection, false if not
	 */
	
	public boolean lineIntersects(LineSegment s) {
		
		boolean intersect = false;
		for(int i = 0; i < border.length; i++) {
			if(s.intersection(border[i]) != null) {
				intersect = true;
			}
		}
		return intersect;
	}
	
	public int intersectsSide(Hitbox h) {
		
		int l1 = -1;
		
		for(int i = 0; i < 4; i++) {
			if(h.lineIntersects(border[i])){
				if(l1 < 0) {
					l1 = i;
					break;
				} 
			}
		}
		
		return l1;
	}
	
	public void rotate(int times) {
		int tempnum;
		if(times % 2 != 0) {
			tempnum = this.width;
			this.width = this.height;
			this.height = tempnum;
		}
	}
	
	/**
	 * The border of the hitbox in the form of an Array of LineSegments
	 * @return Array of 4 line segments
	 */
	
	public LineSegment[] getBorder() {
		return border;
	}
	
	public float[] getCenterPoints() {
		float[] arr = {(float)(x + (width/2)), (float)(y + (height/2))};
		return arr;
	}
	
	public double getX() {
		return this.x;
	}
	
	public double getY() {
		return this.y;
	}
	
	@Override
	public double getWidth() {
		return this.width;
	}
	
	public double getHeight() {
		return this.height;
	}
	
	public void setBounds(double x, double y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = (int)(width * Gamepanel.sizeMult);
		this.height = (int)(height * Gamepanel.sizeMult);
	}
	
	public void setWidth(int width) {
		this.width = (int)(width * Gamepanel.sizeMult);
	}
	
	public void setHeight(int height) {
		this.height = (int)(height * Gamepanel.sizeMult);
	}
	
	public void scale(double scalar) {
		this.height *= scalar;
		this.width *= scalar;
	}
	
	public void setX(float x) {
		this.x = (int) x;
	}
	
	public void setY(float y) {
		this.y = (int) y;
	}
	
	public void incY(double d) {
		this.y += (d * Characters.delta);
	}
	
	public void incX(double d) {
		this.x += (d * Characters.delta);
	}

}
