package GIS;

import Geom.Point3D;

public class Player {
	
	private Point3D Point_player;
	private double speed;
	private double radius;
	
	
	public Player(Point3D p1, double speed, double radius) {
		
		this.Point_player = p1;
		this.speed = speed;
		this.radius = radius;
	}
	
	public Point3D getPoint_player() {
		return Point_player;
	}



	public void setPoint_player(Point3D point_player) {
		Point_player = point_player;
	}

	public Point3D nextPoint (double dir) {
		double fixDir = (dir*Math.PI/180);

		double vy = this.getSpeed()*Math.sin(fixDir);
		double vx = this.getSpeed()*Math.cos(fixDir);
		double t = 100.0D;
		double x = this.getPoint_player().x()+vx*t;
		double y = this.getPoint_player().y()+vy*t;		
		
		
		return new Point3D(x,y);
	}



	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}
	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

}
