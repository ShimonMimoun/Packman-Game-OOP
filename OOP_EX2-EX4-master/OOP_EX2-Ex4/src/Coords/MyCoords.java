package Coords;

import Geom.Point3D;




public class MyCoords implements coords_converter {
	
	
	public static final int RADUIS_EARTH = 6371000;
	

	@Override
	public Point3D add(Point3D gps, Point3D local_vector_in_meter) {
		Point3D meterToGPs = local_vector_in_meter.ConvertToGps();
		System.out.println(meterToGPs.toString());
	//	double ans_x = gps.x()+meterToGPs.x();
	//	double ans_y = gps.y()+meterToGPs.y();
	//	double ans_z = gps.z()+meterToGPs.z();

	gps.add(meterToGPs);
	return gps;
	}

	@Override
	public double distance3d(Point3D gps0, Point3D gps1) {
		
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

		return gps0Change.distance3D(gps1Change);

		
		// TODO Auto-generated method stub
	
	}

	@Override
	public Point3D vector3D(Point3D gps0, Point3D gps1) {
		
		Point3D gps0ToMeters = new Point3D(gps0);
		Point3D gps1ToMeters = new Point3D(gps1);
		

		double m_x = gps1ToMeters.x()-gps0ToMeters.x();
		double m_y = gps1ToMeters.y()-gps0ToMeters.y();
		double m_z = gps1ToMeters.z()-gps0ToMeters.z();


		return new Point3D (m_x,m_y,m_z);
	}

	@Override
	public double[] azimuth_elevation_dist(Point3D gps0, Point3D gps1) {
		
		double[] azimuth_elevation_dist = new double[3];
		azimuth_elevation_dist[0] = gps1.north_angle(gps0);
		azimuth_elevation_dist[1] = gps1.up_angle(gps0);
		azimuth_elevation_dist[2] = distance3d(gps1, gps0);
		
		return azimuth_elevation_dist;
	}

	@Override
	public boolean isValid_GPS_Point(Point3D p) {
		
		
		
		return (p.x()<-180 || p.x() >180 ||p.y() <-90 || p.y() >90 || p.z() <-450);
		
	
	}
	
	
}
