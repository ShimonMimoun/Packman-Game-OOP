package GIS;

import org.junit.experimental.theories.Theories;

import Geom.Circle;
import Geom.Point3D;

public class Packman {
	
	
	Circle packCircle;
	int radius;
	Point3D packLocation;
	double Orientation;
	double speed;
	
	
	public Packman(Point3D point , double speed, int radius) {
		this.packLocation = point;
		this.speed  = speed;
		this.radius = radius;
		
	}
	
	

}
