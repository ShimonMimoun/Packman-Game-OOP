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
public class GIS_projet_using implements GIS_project{
	
	    Set<GIS_layer> layer_List = new HashSet<GIS_layer>(); 

	    Meta_Data_using mt = new Meta_Data_using();

		@Override
		public boolean add(GIS_layer e) {
			return layer_List.add(e);
		}

		@Override
		public boolean addAll(Collection<? extends GIS_layer> c) {
			// TODO Auto-generated method stub
			return layer_List.addAll(c);
		}

		@Override
		public void clear() {
			layer_List.clear();			
		}

		@Override
		public boolean contains(Object o) {
			// TODO Auto-generated method stub
			return layer_List.contains(o);
		}

		@Override
		public boolean containsAll(Collection<?> c) {
			// TODO Auto-generated method stub
			return layer_List.containsAll(c);
		}

		@Override
		public boolean isEmpty() {
			// TODO Auto-generated method stub
			return layer_List.isEmpty();
		}

		@Override
		public Iterator<GIS_layer> iterator() {
			// TODO Auto-generated method stub
			return layer_List.iterator();
		}

		@Override
		public boolean remove(Object o) {
			// TODO Auto-generated method stub
			return layer_List.remove(o);
		}

		@Override
		public boolean removeAll(Collection<?> c) {
			// TODO Auto-generated method stub
			return layer_List.removeAll(c);
		}

		@Override
		public boolean retainAll(Collection<?> c) {
			// TODO Auto-generated method stub
			return layer_List.retainAll(c);
		}

		@Override
		public int size() {
			// TODO Auto-generated method stub
			return layer_List.size();
		}

		@Override
		public Object[] toArray() {
			// TODO Auto-generated method stub
			return layer_List.toArray();
		}

		@Override
		public <T> T[] toArray(T[] a) {
			// TODO Auto-generated method stub
			return layer_List.toArray(a);
		}

		@Override
		public Meta_data get_Meta_data() {
			// TODO Auto-generated method stub
			return this.mt;
		}
		
		
		
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
	            
		            for(GIS_layer current_layer: layer_List){
		            	
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
