package Geom;

import Coords.Map;
import Coords.MyCoords;

public class Box {

	private Point3D p1; // left down
	private Point3D p2; // right up
	private double x_left_up;
	private double y_left_up;
	private double x_right_down;
	private double y_right_down;
	private Map theMap = new Map();
	private MyCoords my = new MyCoords();

	private static int _count = 0;

	public Box(Point3D p1 , Point3D p2) {

		this.p1 = p1;
		this.p2 = p2;
		this.x_left_up = p1.x();
		this.y_left_up = p2.y();
		this.x_right_down = p2.x();
		this.y_right_down = p1.y();
	}


	public boolean checkit1 (Point3D m, Point3D theClose) {
		Point3D temp  = new Point3D(m);
		Point3D ans;
		while(my.distance3d(temp, theClose) >= 1) {
			if((temp.x() >= p1.x() && temp.x() <= p2.x()) && (temp.y() <= p2.y() && temp.y() >= p1.y())) {
				return true;
			}
			ans = theNextPoint(temp, theClose);
			temp = ans;
		}
		return false;
	}


	public double inBox(Point3D m, Point3D theClose) {
		double meter = 4;
		Point3D Width_y_down = new Point3D(p1.x(),m.y());
		Point3D Height_x_right = new Point3D(m.x(),p2.y());
		Point3D Height_x_left = new Point3D(m.x(),p1.y());
		Point3D Width_y_up = new Point3D(p2.x(),m.y());


		if((my.distance3d(m, Width_y_down) <= meter) && (m.y() < p2.y()) && (m.y() > p1.y())){
			if(!checkit1(m,theClose)) {
				System.out.println("can do stright 1");

				return 5;
			}
			return 1;
		}
		if((my.distance3d(m, Height_x_right) <= meter) && m.x() < p2.x() &&	 m.y() > p2.y()){
			if(!checkit1(m,theClose)) {
				return 5;

			}
			return 2;
		}
		if((my.distance3d(m, Width_y_up) <=meter) && m.y() > p1.y() && m.y() < p2.y()){
			if(!checkit1(m,theClose)) {
				System.out.println("can do stright 3");
				return 5;
			}
			return 3;
		}
		if((my.distance3d(m, Height_x_left) <= meter) && m.x() > p2.x() &&	 m.y() < p2.y()){
			System.out.println(my.distance3d(m, Height_x_left));
			if(!checkit1(m,theClose)) {

				System.out.println("can do stright 4");
				return 5;
			}
			return 4;

		}
		return 5;

	}


	public  Point3D theNextPoint(Point3D p1 , Point3D f1) {

		double dt = 1000; // the time from (x1,y1) to (x2,y2) example: 300.

		double Vx = p1.x()/dt;
		double Vy = p1.y()/dt;

		double xt = p1.x()+Vx*(f1.x()-p1.x());
		double yt= p1.y()+Vy*(f1.y()-p1.y());
		return new Point3D(xt,yt);

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

