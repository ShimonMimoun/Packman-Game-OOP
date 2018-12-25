package GIS;

import Geom.Point3D;

public class Player {
	
	private Point3D Point_player;
	public Point3D getPoint_player() {
		return Point_player;
	}



	public void setPoint_player(Point3D point_player) {
		Point_player = point_player;
	}



	public double getSpeed() {
		return speed;
	}



	public void setSpeed(double speed) {
		this.speed = speed;
	}



	private double speed;
	
	
	
	public Player(Point3D p1, double speed) {
		
		this.Point_player = p1;
		this.speed = speed;
		
	}
}
