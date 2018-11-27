package GIS;


import java.time.Instant;
import java.time.ZoneOffset;
import java.util.Date;

import com.sun.jmx.snmp.Timestamp;

import Geom.Point3D;
public class Meta_Data_using implements Meta_data {
	private long UTC;
	private Point3D Orientation;
	private String color;
	
	public Meta_Data_using() {
		this.UTC = getUTC();
		this.Orientation = get_Orientation();
		this.color = getColor();
	}

	public long getUTC() {
		long time = new Date().getTime();
		this.UTC = time;
		return this.UTC;
	}
	public String UTFFormat() {
		String s = Instant.ofEpochMilli(getUTC()).atOffset(ZoneOffset.UTC).toString();
		String Time = s.substring(11, 19);
		String Date = s.substring(0, 10);
		String Total = ""+Date  +" "+  Time+"";
		return Total;
	}

	@Override
	public Point3D get_Orientation() {
		return null;
	}
	
	public String getColor() {
		return this.color;
	}
	
	public void setColor(String Color) {
		this.color = Color;
	}

	@Override
	public String toString() {
		return ""+UTFFormat() + "," + Orientation + "," + color+"";
	}



	
	
	//@Override
	/*public String toString() {
		String s = Instant.ofEpochMilli(getUTC()).atOffset(ZoneOffset.UTC).toString();
		String Time = s.substring(11, 19);
		String Date = s.substring(0, 10);
		String Total = "Date: " + Date + ", " + "Time: " +  Time;
		return Total;
	}
*/
	
}
