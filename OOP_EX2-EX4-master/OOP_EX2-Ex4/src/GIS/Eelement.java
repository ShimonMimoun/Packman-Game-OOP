package GIS;

import Coords.MyCoords;
import Geom.Geom_element;
import Geom.Point3D;

public class Eelement  implements GIS_element  {
	
	MetaData mt = new MetaData();
	
	private String Lat;
	private String Lon;
	private String RSSI; 
	private String timeDate;
	private String AltitudeMeters;	
	private String AccuracyMeters;
	private String type;
	
	
	public Eelement(String timeDate , String RSSI,String Latitude,String Longitude,String AltitudeMeters, String AccuracyMeters,String type) {
		
		this.RSSI = RSSI;
		this.Lat = Latitude;
		this.Lon = Longitude;
		this.timeDate = timeDate;
		this.AltitudeMeters = AltitudeMeters;
		this.AccuracyMeters = AccuracyMeters;
		this.type = type;
		this.mt = null;
	}
	
	/// Getters ///
	
	
	public String getlat() {return this.Lat;}
	public String getLon() {return this.Lon;}
	
	public String getAltitudeMeters() {return this.AltitudeMeters;}
	
	
	/// end Getters //
	
	/// Setters ///
	
	public void setLat(String lat) {
		this.Lat = lat;
	}
	
	public void setLon(String lon) {
		this.Lon = lon;
	}
	
	public void setaltMeters(String altmeter) {
		this.AltitudeMeters = altmeter;
	}
	
	/// end Setters ///


	@Override
	public Geom_element getGeom() {
		
		double Latitude = Double.parseDouble(this.Lat);
		double Longitude = Double.parseDouble(this.Lon);
		double AltitudeMeters = Double.parseDouble(this.AltitudeMeters);
	
		return new Point3D (Latitude,Longitude,AltitudeMeters);
	}

	@Override
	public Meta_data getData() {
		return this.mt;
	}

	@Override
	public void translate(Point3D vec) {
		MyCoords m = new MyCoords();
		
		String x = this.getlat();
		String y = this.getLon();
		String z = this.getAltitudeMeters();
		
		Point3D coverted_To_num = String_2_Point3D(x,y,z);
		
		Point3D ans = m.add(coverted_To_num, vec);
		
		Point3D_2_String(ans.x(),ans.y(),ans.z());
		
	}
	
	// Helping functions //
	
	// convert String point to number and return num.
	
	public Point3D String_2_Point3D(String lat , String lon , String AltitudeMeters) {
		
		Double x = Double.parseDouble(lat);
		Double y = Double.parseDouble(lon);
		Double z = Double.parseDouble(AltitudeMeters);
		return new Point3D(x,y,z);
	}
	
	
	// convert point to string and set the varulbs
	public void Point3D_2_String(double lat , double lon, double AltitudeMeters) {
		
		setLat(lat+"");
		setLon(lon+"");
		setaltMeters(AltitudeMeters+"");
		
	}

	
	
	
	
}
