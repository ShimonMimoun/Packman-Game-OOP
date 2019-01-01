package Algorithm;

import Coords.MyCoords;
import Geom.Box;
import Geom.Point3D;

public class check {
	
	public static void main(String[] args) {
		MyCoords m = new MyCoords();

		Box b2 = new Box(new Point3D(32.1035470312093,35.2056556995029,0),new Point3D(32.1039590220811,35.2106165009524,0));
		Box b1 = new Box(new Point3D(32.1027021891685,35.2081838002415,0),new Point3D(32.1049811766491,35.2085177003391,0));
		
		Point3D a = new Point3D(32.1034880488877,35.20816543349475,0.0); //true
		Point3D a1 = new Point3D(32.10421456072313,35.20785785014635,0.0); // false
		Point3D a2 = new Point3D(32.10373127333334,35.20939944,0.0); // true
		Point3D a3 = new Point3D(32.103460303333335,35.20985539,0.0); // true

		
		


		
//		Point3D in1 = new Point3D(32.10269936580517,35.208364261865796,0.0);
//		Point3D in2 = new Point3D(32.10270706163022,35.208252326513914,0.0);
//		Point3D in3 = new Point3D(32.10325867946258,35.208151007217324,0.0);
//
//		System.out.println(b1.inBox(in1));
//		System.out.println(b1.inBox(in2));
//		System.out.println(b1.inBox(in3));
//
//		Point3D p = new Point3D(32.103541384767084,35.2056556995029,0);
		
		//System.out.println(m.distance3d(in1, di));


	}
	public static Point3D theNextPoint(Point3D p1 , Point3D f1) {
		
	double dt = 100; // the time from (x1,y1) to (x2,y2) example: 300.
	
	double Vx = p1.x()/dt;
	double Vy = p1.y()/dt;
	
	double xt = p1.x()+Vx*(f1.x()-p1.x());
	double yt= p1.y()+Vy*(f1.y()-p1.y());
	return new Point3D(xt,yt);
	
	}

}
