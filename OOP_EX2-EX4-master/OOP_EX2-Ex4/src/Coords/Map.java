package Coords;

import Geom.Point3D;

public class Map {
	

	  Point3D leftUp = new Point3D(32.105738, 35.202279);
	  Point3D leftDown = new Point3D(32.101972, 35.202491);
	  Point3D RightDown = new Point3D(32.102016, 35.213001);
	  Point3D RightUp = new Point3D(32.106301, 35.213259);

	
	public  Point3D Pixel2GPS(double x , double y , int x_lengthMap , int y_lengthMap) {
		
		
		double dis_x = leftUp.distance3D(RightUp);
		System.out.println(dis_x);
		double x_1pxiel2Meter = dis_x/x_lengthMap;
		
		double ans_x = x*x_1pxiel2Meter;
		
		double dis_y = leftUp.distance3D(leftDown);
		double y_1pixle2Meter = dis_y/y_lengthMap;
		
		double ans_y = y*y_1pixle2Meter;		
		Point3D ans_in_Gps = new Point3D((leftUp.x()+(ans_x*0.000009054)),(leftUp.y()-(ans_y*0.000012019)),(leftUp.z()));
		
		return ans_in_Gps;
	}

}
