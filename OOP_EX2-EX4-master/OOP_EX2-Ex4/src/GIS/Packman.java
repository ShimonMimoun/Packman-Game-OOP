package GIS;

import Geom.Circle;
import Geom.Point3D;

public class Packman extends Point3D {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Circle packCircle;
	int radius;
	Point3D packLocation;
	double Orientation;
	double speed;
	
	
	public Packman(Point3D point , double speed, int radius) {
		super(point);
//		this.packLocation = point;
		this.speed  = speed;
		this.radius = radius;
		
	}
	
	
	

}
