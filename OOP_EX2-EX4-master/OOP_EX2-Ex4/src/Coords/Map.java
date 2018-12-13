package Coords;

import Geom.Point3D;

public class Map {
	
									 //y		//x
	Point3D leftUp;
	Point3D RightDown;
	double x_length;
	double y_length;
	String diractroyMap;
	
	
	public Map() {
		this.leftUp = new Point3D(32.105770,  35.202469);
		this.RightDown = new Point3D(32.101899, 35.211588);
		this.x_length = this.RightDown.y()-this.leftUp.y();
		this.y_length = this.RightDown.x()-this.leftUp.x();
		this.diractroyMap = "Pictures&Icones/Ariel1.png";

	}
	public Map(Point3D LeftUpPointGps , Point3D RightDownGps, String MapPath) {
		this.leftUp = LeftUpPointGps;
		this.RightDown = RightDownGps;
		this.x_length = this.RightDown.y()-this.leftUp.y();
		this.y_length = this.RightDown.x()-this.leftUp.x();
		this.diractroyMap = MapPath;

	}
	
	// getting // 
	
	public void setMap(String PathPic) {
		this.diractroyMap = PathPic;
	}
	public String GetMapPath() {
		return this.diractroyMap;
	}
	
	
	public String getMapDiractory() {
		return this.diractroyMap;
	}
	
	public  Point3D Pixel2GPS(double Dx , double Dy) {
		
		
		double lon_x = Dx * x_length+leftUp.y();
		double lat_y = Dy * y_length+leftUp.x(); // or RightdDown.x()
		
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
		MyCoords m = new MyCoords();
		double result = m.distance3d(ansX, ansY);
		return result;

	}
	

}
