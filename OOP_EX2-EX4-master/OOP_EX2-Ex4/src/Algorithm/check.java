package Algorithm;

import Coords.MyCoords;
import Geom.Box;
import Geom.Point3D;

public class check {
	
	public static void main(String[] args) {
		
		Point3D in1 = new Point3D(32.103533790848324,35.209525377268,0.0);
		MyCoords m = new MyCoords();

		Box b1 = new Box(new Point3D(32.1027021891685,35.2081838002415,0),new Point3D(32.1049811766491,35.2085177003391,0));
		Box b2 = new Box(new Point3D(32.1035470312093,35.2056556995029,0),new Point3D(32.1039590220811,35.2106165009524,0));
		System.out.println(b1.inBox(in1));
		System.out.println(b2.inBox(in1));
		
		double b =m.distance3d(new Point3D(32.103533790848324,35.209525377268,0.0), new Point3D(32.1027021891685,35.2081838002415,0));
		System.out.println(b);
	}

}
