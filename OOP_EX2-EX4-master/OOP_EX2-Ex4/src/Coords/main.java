package Coords;

import Geom.Point3D;

public class main {

	public static void main(String[] args) {

		MyCoords m = new MyCoords();
		Point3D p = new Point3D(1,2,3);
		Point3D p1 = new Point3D(2,3,4);
		
		Point3D b = p.ConvertToGps();
		System.out.println(b.toString());

		
		
		System.out.println(m.toString());
		

//		double x,y,z;
//		x=1;y=2;z=3;
//
//
//		double	r = Math.sqrt(x * x + y * y + z * z);
//		double longitude = Math.acos(x / Math.sqrt(x * x + y * y)) * (y < 0 ? -1 : 1);
//		double latidude= Math.acos(z / r);
//
//		System.out.println(r);
//		System.out.println(longitude);
//		System.out.println(latidude);
//
//		x = r * Math.sin(latidude) * Math.cos(longitude);
//		y = r * Math.sin(latidude) * Math.sin(longitude);
//		z = r * Math.cos(latidude);
//		System.out.println(x);
//		System.out.println(y);
//		System.out.println(z);

	}

}




