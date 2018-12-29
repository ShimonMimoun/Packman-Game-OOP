package Geom;

import Coords.LatLonAlt;

public class Box {
	
	private Point3D p1; // leftup
	private Point3D p2; // right up
	private double x_left_up;
	private double y_left_up;
	private double x_right_down;
	private double y_right_down;

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
//		Point3D in = new Point3D(32.10354505861542,35.20952377144009,0.0) ;
//		Point3D out = new Point3D(32.10406533944954,35.20631261588785,0.0);
//		Box b1 = new Box(new Point3D(32.10270219,35.2081838	,0),new Point3D(32.10498118,35.2085177,0));
//		Box b2 = new Box(new Point3D(32.10354703,35.2056557,0),new Point3D(32.10395902,35.2106165,0));
		if((m.x() >= p1.x() && m.y() >= p1.y()) && (m.x() <= p2.x() && m.y() <= p2.y())) {
			return true;
		}else {
			return false;
		}
	}
	
//	  public boolean isIn2D(LatLonAlt q) { boolean ans = false;
//	    if ((q.lat() >= _min.lat()) && (q.lon() >= _min.lon()) && (q.lat() <= _max.lat()) && (q.lon() <= _max.lon())) {
//	      ans = true;
//	    }
//	    return ans;
//	  }
	
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
