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

	public Point3D nextPoint (double dir , Player player) {
		
		double vy = player.getSpeed()*Math.sin(dir);
		double vx = player.getSpeed()*Math.cos(dir);
		double t = 100.0D;
		
		double x = player.getPoint_player().x()+vx*t;
		double y = player.getPoint_player().y()+vy*t;		
		
		
		return new Point3D(y,x);
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
