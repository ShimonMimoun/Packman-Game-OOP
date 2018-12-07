package Coords;

import Geom.Point3D;

public class Map {
	
									 //y		//x
	Point3D leftUp = new Point3D(32.105770,  35.202469);
	Point3D RightUp = new Point3D(32.105770 , 35.211588);
	Point3D leftDown = new Point3D(32.101899, 35.202469);
	Point3D RightDown = new Point3D(32.101899, 35.211588);
	double x_length = RightUp.y()-leftUp.y();
	double y_length = leftDown.x()-leftUp.x();

	
	public  Point3D Pixel2GPS(double Dx , double Dy) {
		
		
		double lon_x = Dx * x_length+leftUp.y();
		double lat_y = Dy* y_length+RightUp.x();
		
		Point3D ans_in_Gps = new Point3D(lat_y,lon_x);

		
		return ans_in_Gps;
	}
	
	public Point3D GPS2Pixel(Point3D p) {
		
		double Dx = (p.y()-leftUp.y())/x_length;
		double Dy = (p.x()-leftUp.x())/y_length;
		
	return new Point3D(Dx,Dy);
			
	}
	
	public double distancePixels(Point3D p1, Point3D p2) {
		
		Point3D ansX =  Pixel2GPS(p1.x(),p1.y());
		Point3D ansY =  Pixel2GPS(p2.x(),p2.y());
		
		double result = ansX.distance3D(ansY);
		
		return result;

	}

}
