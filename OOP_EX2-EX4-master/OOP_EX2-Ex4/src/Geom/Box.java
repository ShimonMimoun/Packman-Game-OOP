package Geom;

import Coords.LatLonAlt;

public class Box {
	
	private Point3D p1; // left down
	private Point3D p2; // right up
	private double x_left_up;
	private double y_left_up;
	private double x_right_down;
	private double y_right_down;ss

	private static int _count = 0;
	
	public Box(Point3D p1 , Point3D p2) {
		
		this.p1 = p1;
		this.p2 = p2;
		this.x_left_up = p1.x();
		this.y_left_up = p2.y();
		this.x_right_down = p2.x();
		this.y_right_down = p1.y();
	}
	
	
	public boolean inBox(Point3D m) {

		if((m.x() >= p1.x() && m.x() <= p2.x()) && (m.y() >= p1.y() && m.y() <= p2.y())) {
			return true;
		}else {
			return false;
		}
	}
	
	
	public Point3D getP1() {
		return p1;
	}


	public void setP1(Point3D p1) {
		this.p1 = p1;
	}


	public Point3D getP2() {
		return p2;
	}


	public void setP2(Point3D p2) {
		this.p2 = p2;
	}
	@Override
	public String toString() {
		return "Box [p1=" + p1 + ", p2=" + p2 + "]";
	}


}
