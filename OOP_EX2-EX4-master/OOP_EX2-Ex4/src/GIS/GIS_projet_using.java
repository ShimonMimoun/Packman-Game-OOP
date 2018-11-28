package GIS;
import java.io.BufferedWriter;


import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * This class implements GIS_Project functions.
 * @author Shimon Mimoun and Omer Paz
 *
 */

public class GIS_projet_using extends HashSet<GIS_layer> implements GIS_project{
	
	    /**
	 * 
	 */
	ArrayList<Meta_data> myElemData = new ArrayList<>();
	
	private static final long serialVersionUID = 1L;

	    Meta_Data_using mt = new Meta_Data_using();

		@Override
		public Meta_data get_Meta_data() {
			// TODO Auto-generated method stub
			return this.mt;
		}
		
		/**
		 * This functions take all the layer that he got and made a project file that include all the layers and the elements made a kml file.
		 * @param output
		 */
		
		public void to_KML(String output) {
		       String[]name= {"MAC", "SSID", "AuthMode", "FirstSeen", "Channel", "RSSI", "Lat", "Lon", "AltitudeMeters", "AccuracyMeters", "type","UTC","Orientation ","Color"};
		   
			 ArrayList<String> content = new ArrayList<String>();
		        String kmlstart = 
		                 "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" + 
		                "<kml xmlns=\"http://www.opengis.net/kml/2.2\"><Document><Style id=\"red\">\r\n" + 
		                "<IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/red-dot.png</href></Icon></IconStyle>\r\n" + 
		                "</Style><Style id=\"yellow\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/yellow-dot.png</href></Icon></IconStyle>\r\n" + 
		                "</Style><Style id=\"green\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/green-dot.png</href></Icon></IconStyle></Style>\r\n" + 
		                "\r\n" + 
		                "\r\n" + 
		                "<Folder><name>Wifi Networks</name>\n\n";
		        content.add(kmlstart);
		        String kmlend = "</Folder>\n" + 
		        		"</Document>\n</kml>";
		        try{
		            FileWriter fw = new FileWriter(output);
		            BufferedWriter bw = new BufferedWriter(fw);
	            
		            for(Meta_data current_layer: myElemData){
		            	
		            	Iterator<GIS_element> myelm = current_layer.iterator();
		            	
		            	while(myelm.hasNext()) {
		            		
		            	String s = myelm.next().toString();
		            	System.out.println(s);
		            	String[] data=s.split(",");
		            	
		                String kmlelement ="<Placemark>\n" +
		                        "<name><![CDATA["+data[1]+"]]></name>\n" +
		                        "<description>"+
		                        "<![CDATA[B"
		                        +name[0]+": <b>"+data[0]+" </b><br/>"
		                        +name[2]+": <b>"+data[2]+" </b><br/>"
		                        +name[3]+": <b>"+data[3]+" </b><br/>" // time and date
		                        +name[4]+": <b>"+data[4]+" </b><br/>"
		                        +name[5]+": <b>"+data[5]+" </b><br/>" // rssi
		                        +name[6]+": <b>"+data[6]+" </b><br/>" // latitauo
		                        +name[7]+": <b>"+data[7]+" </b><br/>" // logntiue
		                        +name[8]+": <b>"+data[8]+" </b><br/>" // altito to meter
		                        +name[9]+": <b>"+data[9]+" </b><br/>" //accaryto meter
		                        +name[10]+": <b>"+data[10]+" </b><br/>" //type wifi
		                        +name[11]+": <b>"+data[11]+" </b><br/>" //type wifi
		                        +name[12]+": <b>"+data[12]+" </b><br/>" //type wifi
		                        +name[13]+": <b>"+data[13]+" </b><br/>" //type wifi


		                        
		                        +"]]></description>\n" +"<styleUrl>#"+data[13]+"</styleUrl>"+
		                        "<Point>\n" +
		                        "<coordinates>"+data[7]+","+data[6]+"</coordinates>" +
		                        "</Point>\n" +
		                        "</Placemark>\n";

		                content.add(kmlelement);

		            	}
		            }
		            
		            content.add(kmlend);
		            bw.write(String.join("\n", content));
		            System.out.println("Operation Complete");
		            bw.close();
		        } 
		        catch (IOException e) {
		            e.printStackTrace();
		        }
		    }
		}
