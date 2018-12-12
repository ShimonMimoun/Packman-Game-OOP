package GIS;

import Geom.Circle;
import Geom.Geom_element;
import Geom.Point3D;

public class Packman {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int radius;
	Point3D packLocation;
	double Orientation;
	double speed;
	Path packmanPath;
	
	
	public Packman(Point3D point , double speed, int radius) {
		this.packLocation = point;
		this.speed  = speed;
		this.radius = radius;
		packmanPath = new Path();
		
	}
	public Packman(Packman p) {
		this(p.packLocation,p.speed,p.radius);
	}
	
	public void setPackLocation(Point3D p) {
		this.packLocation = p;
	}
	public int getradius() {
		return this.radius;
	}
	public double getOrientation() {
		return this.Orientation;
	}
	public double getSpeed() {
		return this.speed;
	}
	public Path getPath() {
		return this.packmanPath;
	}
	public Point3D getPoint() {
		return this.packLocation;
	}
	
	@Override
	public String toString() {
		return "Packman ["+packLocation.toString()+" Radius= "+radius+"speed=" + speed + "]";
	}
	
	
	


}
