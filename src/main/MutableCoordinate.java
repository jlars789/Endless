package main;

import com.vividsolutions.jts.geom.Coordinate;

public class MutableCoordinate extends Coordinate {
	
	private static final long serialVersionUID = 3917202781169185649L;
	
	public MutableCoordinate(double x, double y) {
		super(x, y);
	}
	
	public void setPoints(double x, double y) {
		super.x = x;
		super.y = y;
	}
	
	public float[] getValues() {
		float[] coor = new float[2];
		coor[0] = (float)super.x;
		coor[1] = (float)super.y;
		return coor;
	}

}
