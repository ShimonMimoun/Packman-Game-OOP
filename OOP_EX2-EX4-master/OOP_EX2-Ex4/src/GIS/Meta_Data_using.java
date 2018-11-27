package GIS;


import com.sun.jmx.snmp.Timestamp;

import Geom.Point3D;
public class Meta_Data_using implements Meta_data {

	@Override
	public long getUTC() {
		// TODO Auto-generated method s	tub
		 return new Timestamp(System.currentTimeMillis()- Calendar.getInstance().getTimeZone().getOffset(System.currentTimeMillis()));
	}

	@Override
	public Point3D get_Orientation() {
		// TODO Auto-generated method stub
		return null;
	}

}
