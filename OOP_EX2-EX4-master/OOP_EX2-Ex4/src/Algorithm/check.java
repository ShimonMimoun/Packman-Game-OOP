package Algorithm;

import Geom.Box;
import Geom.Point3D;

public class check {
	
	public static void main(String[] args) {
		
		Point3D in1 = new Point3D(32.1037580212766,35.2066766414791,0.0);
		Point3D in2 = new Point3D(32.104311021276594,35.208333308681674,0.0);
		Point3D in3 = new Point3D(32.10301088297872,35.20831864790997,0.0);
		Point3D in4 = new Point3D(32.103734489361706,35.20960879581993,0.0);

		//Point3D out = new Point3D(32.10406533944954,35.20631261588785,0.0);
		Box b1 = new Box(new Point3D(32.10270219,35.2081838	,0),new Point3D(32.10498118,35.2085177,0));
		Box b2 = new Box(new Point3D(32.10354703,35.2056557,0),new Point3D(32.10395902,35.2106165,0));
		System.out.println(b1.inBox(in1));
		System.out.println(b2.inBox(in1));
		System.out.println(b1.inBox(in2));
		System.out.println(b2.inBox(in2));
		System.out.println(b1.inBox(in3));
		System.out.println(b2.inBox(in3));
		System.out.println(b1.inBox(in4));
		System.out.println(b2.inBox(in4));
		
	}

}
