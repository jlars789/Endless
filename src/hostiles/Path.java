package hostiles;

import com.vividsolutions.jts.geom.LineSegment;

import main.MutableCoordinate;

public class Path {
	
	private Hostile main;
	private LineSegment line;
	private MutableCoordinate[] coor = new MutableCoordinate[2];

	public Path(Hostile h, float x2, float y2) {
		this.main = h;
		
		coor[0] = new MutableCoordinate(main.getCenterPoint()[0], main.getCenterPoint()[1]);
		coor[1] = new MutableCoordinate(x2, y2);
		
		this.line = new LineSegment(coor[0], coor[1]);
	}
	
	public void update(float x2, float y2) {
		
		this.coor[1].setPoints(x2, y2);
		
		this.line.setCoordinates(coor[0], coor[1]);
	}
	
	//TODO Adjust if slope formula is not applicable in inverted plane
	
	public float findSlope() {
		return (coor[0].getValues()[1] - coor[1].getValues()[1]) / (coor[0].getValues()[0] - coor[1].getValues()[0]);
	}
	
	public boolean checkIntersect(Hostile h) {
		return (h.getFullHitbox().lineIntersects(this.line));
	}
	
	public double[] endpoints() {
		double[] arr = {coor[1].x, coor[1].y};
		return arr;
	}

}
