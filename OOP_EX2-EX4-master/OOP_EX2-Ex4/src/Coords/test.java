package Coords;

import Geom.Point3D;

public class test {
	public static final int RADUIS_EARTH = 6371000;




	public static void main(String[] args) {
		// TODO Auto-generated method stub


		Point3D gps0=new Point3D(32.103315,35.209039,670);
		Point3D gps1=new Point3D(32.106352,35.205225,650);

		double a=distance3d(gps0, gps1);
		System.out.println(a);

/*
		double x,y,z;
		x=1;y=2;z=3;
			
		
	double	r = Math.sqrt(x * x + y * y + z * z);
				double longitude = Math.acos(x / Math.sqrt(x * x + y * y)) * (y < 0 ? -1 : 1);
				double latidude= Math.acos(z / r);
				
				System.out.println(r);
				System.out.println(longitude);
				System.out.println(latidude);
				
				x = r * Math.sin(latidude) * Math.cos(longitude);
				y = r * Math.sin(latidude) * Math.sin(longitude);
				z = r * Math.cos(latidude);
				System.out.println(x);
				System.out.println(y);
System.out.println(z);

	*/
	}
	

	public static double  distance3d(Point3D gps0, Point3D gps1) {
		Point3D gps0Change = gps0.ConvertToCartesian();
		Point3D gps1Change = gps1.ConvertToCartesian();
//		double diff_p1_x=gps0.x()-gps1.x();
//		double diff_p1_y=gps0.y()-gps1.y();
//		double diff_radian_x=(diff_p1_x*Math.PI)/180;
//		double diff_radian_y=(diff_p1_y*Math.PI)/180;
//		double long_norm=Math.cos((gps0.x()*Math.PI)/180);
//		double to_meter_x=Math.sin(diff_radian_x)*RADUIS_EARTH;
//		double to_meter_y=Math.sin(diff_radian_y)*RADUIS_EARTH*long_norm;
//
//		double result =Math.sqrt((to_meter_x*to_meter_x)+(to_meter_y*to_meter_y));

		return Math.abs(gps0Change.distance3D(gps1Change));
	}
}

