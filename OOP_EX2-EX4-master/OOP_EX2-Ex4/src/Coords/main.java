package Coords;

import Geom.Point3D;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Point3D gps0=new Point3D(32.103315,35.209039,670);
		Point3D gps1=new Point3D(32.106352,35.205225,650);
		//System.out.println(a);
		
		Point3D convert=new Point3D(0, 0, 0);
		convert=gps0.ConvertToCartesian();
		
		System.out.println(gps0);
		
		
		System.out.println(convert);
		System.out.println((Math.asin(0.5)*180)/3.14);


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





