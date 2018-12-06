package Coords;

import Geom.Point3D;

public class Map {
	

	  Point3D leftUp = new Point3D(32.105738, 35.202279);
	  Point3D leftDown = new Point3D(32.101972, 35.202491);
	  Point3D RightDown = new Point3D(32.102016, 35.213001);
	  Point3D RightUp = new Point3D(32.106301, 35.213259);

	
	public  Point3D Pixel2GPS(double x , double y , int x_lengthMap , int y_lengthMap) {
		
		
		
		double dis_x = leftUp.distance3D(RightUp);
		double x_1pxiel2Meter = dis_x/x_lengthMap;
		
		double ans_x = x*x_1pxiel2Meter;
		
		double dis_y = leftUp.distance3D(leftDown);
		double y_1pixle2Meter = dis_y/y_lengthMap;
		
		double ans_y = y*y_1pixle2Meter;		
		Point3D ans_in_Gps = new Point3D((leftUp.x()+(ans_x*0.000009054)),(leftUp.y()-(ans_y*0.000012019)),(leftUp.z()));
		
		return ans_in_Gps;
	}
	
	public Point3D GPS2Pixel(Point3D p, int x_lengthMap ,int y_lengthMap ) {
		
		double dis_x = leftUp.distance3D(RightUp);
		double x_1pxiel2Meter = dis_x/x_lengthMap;

		double ans_x = (p.x()-leftUp.x())/0.000009054;
		double x = ans_x/x_1pxiel2Meter;
		
		double dis_y = leftUp.distance3D(leftDown);
		double y_1pixle2Meter = dis_y/y_lengthMap;

		double ans_y = (p.y()+leftUp.y()/0.000012019);
		double y = ans_y/y_1pixle2Meter;
		
		return new Point3D(x,y,p.z());
			
	}
	
	public double distancePixels(Point3D p1, Point3D p2, int x_lengthMap ,int y_lengthMap) {
		
		Point3D ansX =  Pixel2GPS(p1.x(),p1.y(), x_lengthMap, y_lengthMap);
		Point3D ansY =  Pixel2GPS(p2.x(),p2.y(), x_lengthMap, y_lengthMap);
		
		double result = ansX.distance3D(ansY);
		
		return result;

	}

}
