package Coords;

import Geom.Point3D;


//		Point3D gps0=new Point3D(32.103315,35.209039,670);
//      Point3D gps1=new Point3D(32.106352,35.205225,650);

public class MyCoords implements coords_converter {
	
	
	public static final int RADUIS_EARTH = 6371000;
	

	@Override
	public Point3D add(Point3D gps, Point3D local_vector_in_meter) {
		Point3D covertedCartisian = ConvertToCartesian(gps);
		
		double ans_x = covertedCartisian.x()+local_vector_in_meter.x();
		double ans_y = covertedCartisian.y()+local_vector_in_meter.y();
		double ans_z = covertedCartisian.z()+local_vector_in_meter.z();

		Point3D ans = ConvertToGps(new Point3D(ans_x,ans_y,ans_z));
	

	return ans;
	}

	@Override
	public double distance3d(Point3D gps0, Point3D gps1) {
		
		Point3D gps0Change = ConvertToCartesian(gps0);
		Point3D gps1Change = ConvertToCartesian(gps1);
		return Math.abs(gps0Change.distance3D(gps1Change));

		
		// TODO Auto-generated method stub
	
	}

	@Override
	public Point3D vector3D(Point3D gps0, Point3D gps1) {
		
		Point3D gps0ToMeters = ConvertToCartesian(gps0);
		Point3D gps1ToMeters = ConvertToCartesian(gps1);
		

		double m_x = gps1ToMeters.x()-gps0ToMeters.x();
		double m_y = gps1ToMeters.y()-gps0ToMeters.y();
		double m_z = gps1ToMeters.z()-gps0ToMeters.z();


		return new Point3D (m_x,m_y,m_z);
	}

	@Override
	public double[] azimuth_elevation_dist(Point3D gps0, Point3D gps1) {

		double[] ans = new double[3];
		ans[0] = gps1.north_angle(gps0);
		ans[1] = Math.toDegrees(Math.acos((gps1.z()-gps0.z())/(distance3d(gps1 , gps0))))-90;
		ans[2] = distance3d(gps1, gps0);
		
		return ans;
	}

	@Override
	public boolean isValid_GPS_Point(Point3D p) {
		
		return (p.x()<-180 || p.x() >180 ||p.y() <-90 || p.y() >90 || p.z() <-450) ? false : true;
		
	
	}
	
	/// covnert functions ///
	
	public Point3D ConvertToGps(Point3D p) {		


		double r=Math.asin(p.z()/RADUIS_EARTH)*180/Math.PI;
		double longitude=Math.atan2(p.y(), p.x())*180/Math.PI;
		double latidude =Math.sqrt(Math.pow(p.x(), 2)+Math.pow(p.y(), 2)+Math.pow(p.z(), 2));

		return new Point3D (r,longitude,latidude);

	}

	public Point3D ConvertToCartesian(Point3D p) {


		double Gps_x = Math.cos(p.y()/180*Math.PI) * Math.cos(p.x()/180*Math.PI)*(RADUIS_EARTH+p.z());;
		double Gps_y =Math.sin(p.y()/180*Math.PI) * Math.cos(p.x()/180*Math.PI)*(RADUIS_EARTH+p.z());
		double Gps_z = (RADUIS_EARTH+p.z())*Math.sin(p.x()/180*Math.PI);


		return new Point3D(Gps_x,Gps_y,Gps_z);
	}
	
	
}
