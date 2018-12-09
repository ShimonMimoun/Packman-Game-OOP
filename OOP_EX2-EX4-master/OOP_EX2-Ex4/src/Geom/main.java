package Geom;

import GIS.Packman;

public class main {
	
	
	
	public static void main(String[] args) {
		
		
		Point3D point = new Point3D(2,2);
				
		
		Packman p2 = new Packman(new Point3D(3,2), 1, 1);
		
		//System.out.println(point.distance3D(p2));

		
		Circle c = new Circle(p2,p2.getradius());
		
		System.out.println(c.inRange(point));
		
		//System.out.println(c.distance3D(point));

		

	}
	

}
