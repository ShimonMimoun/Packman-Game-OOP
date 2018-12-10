package GIS;

import Geom.Circle;
import Geom.Geom_element;
import Geom.Point3D;

public class Packman extends Point3D{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int radius;
	Point3D packLocation;
	double Orientation;
	double speed;
	
	
	public Packman(Point3D point , double speed, int radius) {
		super(point);
		this.packLocation = point;
		this.speed  = speed;
		this.radius = radius;
		
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
	
	@Override
	public String toString() {
		return "Packman ["+packLocation.toString()+" Radius= "+radius+"speed=" + speed + "]";
	}
	
	
	


}
