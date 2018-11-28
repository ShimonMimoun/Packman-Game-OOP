package GIS;

import java.util.Collection;

import Coords.MyCoords;
import Geom.Geom_element;
import Geom.Point3D;

/**
 *  This class implements GIS_element functions.
 * @author Shimon Mimoun and Omer Paz
 *
 */



public class Gis_element_using  implements GIS_element  {
	
	
	
	Point3D elem_Point;
	Meta_Data_using mt;
	private String MAC;
	private String SSID_name;
	private String AuthMode;
	private String FirstSeen;
	private String Lat;
	private String Lon;
	private String AltitudeMeters;
	private int Channel;
	private int RSSI; 
	private double AccuracyMeters;
	private String type;



	/**
	 * this Constractor include all the information foreach element.
	 * all the elements that the constartor get he is String type cuse we read it from csv file.
	 * 
	 * @param MAC
	 * @param SSID
	 * @param AuthMode
	 * @param FirstSeen
	 * @param Channel
	 * @param RSSI
	 * @param Lat
	 * @param Lon
	 * @param AltitudeMeters
	 * @param AccuracyMeters
	 * @param type
	 */

	public Gis_element_using(String MAC , String SSID_name,String AuthMode,String FirstSeen,String Channel,
			String RSSI,String Lat,String Lon , String  AltitudeMeters,String AccuracyMeters, String type) {

		this.MAC = MAC; // String 
		this.SSID_name = SSID_name; //String 
		this.AuthMode= AuthMode; //String
		this.FirstSeen = FirstSeen; // String to date 
		this.type = type; // String 
		setPoint(Lat,Lon,AltitudeMeters);
		setChannel(Channel); // int
		setRSSI(RSSI); // int
		setAccuracyMeters(AccuracyMeters);

	}

	/// Getters ///

	public Meta_Data_using getMt() {
		return mt;
	}

	public String getMAC() {
		return MAC;
	}

	public String getSSID_name() {
		return SSID_name;
	}

	public String getAuthMode() {
		return AuthMode;
	}

	public String getFirstSeen() {
		return FirstSeen;
	}

	public String getLat() {
		return Lat;
	}

	public String getLon() {
		return Lon;
	}

	public String getAltitudeMeters() {
		return AltitudeMeters;
	}

	public int getChannel() {
		return Channel;
	}

	public int getRSSI() {
		return RSSI;
	}

	public double getAccuracyMeters() {
		return AccuracyMeters;
	}

	public String getType() {
		return type;
	}
	
	/// end Getters //

	/// Setters ///

	

	public void setChannel(String Channel) {
		int theChannel = Integer.parseInt(Channel);
		this.Channel = theChannel;
	}
	public void setRSSI(String RSSI) {
		int theRSSI = Integer.parseInt(RSSI);
		this.RSSI = theRSSI;
	}
	public void setAccuracyMeters(String AccuracyMeters) {
		double theAccuracyMeters = Double.parseDouble(AccuracyMeters);
		this.AccuracyMeters = theAccuracyMeters;
	}
	
	public void setPoint(String lat, String lon , String AltitudeMeters) {
	elem_Point = new Point3D(String_2_Point3D(Lat,Lon,AltitudeMeters));
	}	
	
	public void setType(String type) {
		this.type = type;
	}



	/// end Setters ///


	@Override
	public Geom_element getGeom() {

		Point3D ans = String_2_Point3D(this.Lat,this.Lon,this.AltitudeMeters);
		return ans;
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

	private Point3D String_2_Point3D(String lat , String lon , String AltitudeMeters) {

		Double x = Double.parseDouble(lat);
		Double y = Double.parseDouble(lon);
		Double z = Double.parseDouble(AltitudeMeters);
		return new Point3D(x,y,z);
	}


	// convert point to string and set the varulbs
	private void Point3D_2_String(double lat , double lon, double AltitudeMeters) {

		setLat(lat+"");
		setLon(lon+"");
		setaltMeters(AltitudeMeters+"");

	}

	/**
	 * Create a String of the element and adiing the meta data information.
	 * @return the element
	 */
	public String toString() {
		return ""+MAC +"," +SSID+","+AuthMode+","+FirstSeen+","+Channel+","+RSSI+","+Lat
				+","+Lon+","+AltitudeMeters+","+AccuracyMeters+","+type+","+mt.toString();
	}



}