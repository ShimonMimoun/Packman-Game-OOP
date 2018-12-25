package GIS;

import Geom.Point3D;

public class Ghost {
	
	private Point3D point;
	private double speed;
	private double radius;
	
	
	
	public Ghost(Point3D point , double speed, double radius) {
	
		this.point = point;
		this.speed = speed;
		this.radius = radius;
		
	}


	public double getRadius() {
		return radius;
	}


	public void setRadius(double radius) {
		this.radius = radius;
	}


	public Point3D getPoint() {
		return point;
	}



	public void setPoint(Point3D point) {
		this.point = point;
	}



	public double getSpeed() {
		return speed;
	}



	public void setSpeed(double speed) {
		this.speed = speed;
	}



	@Override
	public String toString() {
		return "Ghost [point=" + point + ", speed=" + speed + "]";
	}

	
	
}
