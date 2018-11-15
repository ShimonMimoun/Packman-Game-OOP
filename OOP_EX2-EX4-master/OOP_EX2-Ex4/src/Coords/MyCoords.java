package Coords;

import Geom.Point3D;




public class MyCoords implements coords_converter {
	
	public static final int RADUIS_EARTH = 6371000;
	

	@Override
	public Point3D add(Point3D gps, Point3D local_vector_in_meter) {
		Point3D meterToGPs = local_vector_in_meter.ConvertToGps();
		System.out.println(meterToGPs.toString());
		double ans_x = gps.ix()+meterToGPs.ix();
		double ans_y = gps.iy()+meterToGPs.iy();
		double ans_z = gps.iz()+meterToGPs.iz();

		return new Point3D(ans_x,ans_y,ans_z);
	}

	@Override
	public double distance3d(Point3D gps0, Point3D gps1) {
		double diff_p1_x=gps0.x()-gps1.x();
		double diff_p1_y=gps0.y()-gps1.y();
		double diff_radian_x=(diff_p1_x*Math.PI)/180;
		double diff_radian_y=(diff_p1_y*Math.PI)/180;
		double long_norm=Math.cos((gps0.x()*Math.PI)/180);
		double to_meter_x=Math.sin(diff_radian_x)*RADUIS_EARTH;
		double to_meter_y=Math.sin(diff_radian_y)*RADUIS_EARTH*long_norm;

		double result =Math.sqrt((to_meter_x*to_meter_x)+(to_meter_y*to_meter_y));

		return result;

		
		// TODO Auto-generated method stub
	
	}

	@Override
	public Point3D vector3D(Point3D gps0, Point3D gps1) {
		
		Point3D gps0ToMeters = new Point3D(gps0);
		Point3D gps1ToMeters = new Point3D(gps1);
		
		double m_x = gps1ToMeters.ix()-gps0ToMeters.ix();
		double m_y = gps1ToMeters.ix()-gps0ToMeters.ix();
		double m_z = gps1ToMeters.ix()-gps0ToMeters.ix();


		return new Point3D (m_x,m_y,m_z);
	}

	@Override
	public double[] azimuth_elevation_dist(Point3D gps0, Point3D gps1) {
		
		return null;
	}

	@Override
	public boolean isValid_GPS_Point(Point3D p) {
		// TODO Auto-generated method stub
		return false;
	}
	
	

}
