package GIS;


import java.util.Date;

import com.sun.jmx.snmp.Timestamp;

import Geom.Point3D;
public class Meta_Data_using implements Meta_data {

	@Override

	public long getUTC() {
		long time = new Date().getTime();
		return time;
	}

	@Override
	public Point3D get_Orientation() {
		// TODO Auto-generated method stub
		return null;
	}
}
