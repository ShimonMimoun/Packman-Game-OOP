package GIS;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import Algorithm.ShortestPathAlgo;
import File_format.csv2kml;

import Geom.Point3D;
import Coords.Map;
public class Game {

	public  ArrayList<Packman> Packman_arr = new ArrayList<>();
	public  ArrayList<Fruit> Fruits_arr = new ArrayList<>();
	public 	String file_directory;
	public Map theMap = new Map();





	public Game(ArrayList<Packman> p , ArrayList<Fruit> f) {
		this.Packman_arr = p;
		this.Fruits_arr = f;
	}

	public String getDiractroy() {
		return this.file_directory;
	}
	public void setfile_directory(String file_directory) {
		this.file_directory = file_directory;
	}



	public  void save2Csv() throws FileNotFoundException {

		PrintWriter pw = new PrintWriter(new File(getDiractroy()+".csv"));
		StringBuilder sb = new StringBuilder();
		String[] heders = {"Type","id"	,"Lat"	,"Lon"	,"Alt"	,"Speed/Weight"	,"Radius"};

		for (int i = 0; i < heders.length; i++) {
			sb.append(heders[i]);
			sb.append(",");	
		}
		sb.append(this.Packman_arr.size());
		sb.append(',');
		sb.append(this.Fruits_arr.size());
		sb.append('\n');

		for (int i = 0; i < Packman_arr.size(); i++) {
			Packman_arr.get(i).packLocation = theMap.Pixel2GPS(Packman_arr.get(i).getPoint().x(), Packman_arr.get(i).getPoint().y());
		}
		for (int i = 0; i < Fruits_arr.size(); i++) {
			Fruits_arr.get(i).FuritPoint = theMap.Pixel2GPS(Fruits_arr.get(i).getFruitPoint().x(), Fruits_arr.get(i).getFruitPoint().y());

		}

		for (int i = 0; i < this.Packman_arr.size(); i++) {
			sb.append("P");
			sb.append(',');
			sb.append(i);
			sb.append(',');
			sb.append(Packman_arr.get(i).packLocation.x());
			sb.append(',');
			sb.append(Packman_arr.get(i).packLocation.y());
			sb.append(',');
			sb.append(Packman_arr.get(i).packLocation.z());
			sb.append(',');
			sb.append(this.Packman_arr.get(i).getSpeed());
			sb.append(',');
			sb.append(this.Packman_arr.get(i).getradius());
			sb.append('\n');
		}
		for (int i = 0; i < Fruits_arr.size(); i++) {
			sb.append("F");
			sb.append(',');
			sb.append(i);
			sb.append(',');
			sb.append(Fruits_arr.get(i).FuritPoint.x());
			sb.append(',');
			sb.append(Fruits_arr.get(i).FuritPoint.y());
			sb.append(',');
			sb.append(Fruits_arr.get(i).FuritPoint.z());
			sb.append(',');	
			sb.append(this.Fruits_arr.get(i).getWeight());
			sb.append('\n');

		}
		sb.append('\n');

		pw.write(sb.toString());
		pw.close();

	}


	public void Csvread() throws IOException{		
		List<String[]> s = csv2kml.readFile2(getDiractroy());

		for (int i = 1; i < s.size(); i++) {
			String[] row = s.get(i);

			if(row[0].equals("P")) {
				Point3D p = new Point3D(row[2],row[3],row[4]);
				p = theMap.GPS2Pixel(p);

				double speed = Double.parseDouble(row[5]);
				double radius = Double.parseDouble(row[6]);
				Packman_arr.add(new Packman(p, speed, radius));	
			}
			if(row[0].equals("F")) {
				Point3D p = new Point3D(row[2],row[3],row[4]);
				p = theMap.GPS2Pixel(p);
				int Weight = Integer.parseInt(row[5]);
				Fruits_arr.add(new Fruit(p, Weight));
			}
		}


	}

	public void save_to_kml() throws FileNotFoundException {
		PrintWriter pw = new PrintWriter(new File(getDiractroy()+".kml"));

		ArrayList<String> content = new ArrayList<String>();
		String kmlstart = 
				"<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" + 
						"<kml xmlns=\"http://www.opengis.net/kml/2.2\"><Document>\r\n<name> Points with TimeStamps</name>\r\n <Style id=\"red\">\r\n" + 
						"<IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/red-dot.png</href></Icon></IconStyle>\r\n" + 
						"</Style><Style id=\"Packman\"><IconStyle><Icon><href>http://www.iconhot.com/icon/png/quiet/256/pac-man.png</href></Icon></IconStyle>\r\n" + 
						"</Style><Style id=\"Fruit\"><IconStyle><Icon><href>http://www.stickpng.com/assets/images/580b57fcd9996e24bc43c316.png</href></Icon></IconStyle></Style>\r\n" + 
						"\r\n" + 
						"    <Style id=\"check-hide-children\">\r\n" + 
						"      <ListStyle>\r\n" + 
						"        <listItemType>checkHideChildren</listItemType>\r\n" + 
						"      </ListStyle>\r\n" + 
						"    </Style>\r\n" + 
						"    <styleUrl>#check-hide-children</styleUrl>"+
						"\r\n";

		content.add(kmlstart);
		String[] nameData = {"Type","id","Lat","Lon","Speed/Weight"	,"Radius"};

		String kmlend = "</Document>\n</kml>";


		ShortestPathAlgo algo = new ShortestPathAlgo(this);
		Path p = algo.algoSinglePackman(this.Packman_arr.get(0));
		ArrayList<Fruit>my_fruit  = p.TheCurrentPath();

		for (int i = 0; i < Packman_arr.size(); i++) {
			Packman_arr.get(i).packLocation = theMap.Pixel2GPS(Packman_arr.get(i).getPoint().x(), Packman_arr.get(i).getPoint().y());
		}
		for (int i = 0; i < my_fruit.size(); i++) {
			my_fruit.get(i).FuritPoint = theMap.Pixel2GPS(my_fruit.get(i).getFruitPoint().x(), my_fruit.get(i).getFruitPoint().y());

		}


		for (int j = 0; j < this.Packman_arr.size(); j++) 
		{

			String kmlelement2 ="<Placemark>\n" +
					"<name><![CDATA[ PACKMAN "+j+"]]></name>\n" +
					"<description>"+
					"<![CDATA[B"
					+nameData[0]+": <b> PACKMAN  </b><br/>"
					+nameData[1]+": <b> PACKMAN Number"+j+" </b><br/>"
					+nameData[2]+": <b>"+Packman_arr.get(j).packLocation.x()+" </b><br/>" 
					+nameData[3]+": <b>"+Packman_arr.get(j).packLocation.y()+" </b><br/>" 
					+nameData[4]+": <b>"+Packman_arr.get(j).getSpeed()+" </b><br/>" 
					+nameData[5]+": <b>"+Packman_arr.get(j).getradius()+" </b><br/>" // altito to meter



					+"]]></description>\n" +
					"<TimeStamp>\r\n" + 
					"        <when>>2018-11-14T21:05:0"+(j)+"Z</when>\r\n" + 
					"      </TimeStamp>"+
					"<styleUrl>#Packman</styleUrl>"+
					"<Point>\n" +
					"<coordinates>"+Packman_arr.get(j).packLocation.y()+","+Packman_arr.get(j).packLocation.x()+"</coordinates>" +
					"</Point>\n" +
					"</Placemark>\n";


			content.add(kmlelement2);
		}

		
		
		
		
		
		
		for (int i = 0; i < my_fruit.size(); i++) 
		{
			int temp=0;

			String kmlelement ="<Placemark>\n" +
					"<name><![CDATA[ FRUIT  "+(i)+"]]></name>\n" +
					"<description>"+
					"<![CDATA[B"
					+nameData[0]+": <b> FRUIT  </b><br/>"
					+nameData[1]+": <b> FRUIT Number"+i+" </b><br/>"
					+nameData[2]+": <b>"+my_fruit.get(i).getFruitPoint().x()+" </b><br/>" 
					+nameData[3]+": <b>"+my_fruit.get(i).getFruitPoint().y()+" </b><br/>" 
					+nameData[4]+": <b>"+my_fruit.get(i).getWeight()+" </b><br/>" 


						+"]]></description>\n" +
						"<TimeStamp>\r\n" + 
						"        <when>>2018-11-14T21:05:0"+(i+1)+"Z</when>\r\n" + 
						"      </TimeStamp>"+
						"<styleUrl>#Fruit</styleUrl>"+
						"<Point>\n" +
						"<coordinates>"+Fruits_arr.get(i).getFruitPoint().y()+","+Fruits_arr.get(i).getFruitPoint().x()+"</coordinates>" +
						"</Point>\n" +
						"</Placemark>\n";



			content.add(kmlelement);
		
				String kmlelement2 ="<Placemark>\n" +
						"<name><![CDATA[ PACKMAN "+temp+"]]></name>\n" +
						"<description>"+
						"<![CDATA[B"
						+nameData[0]+": <b> PACKMAN  </b><br/>"
						+nameData[1]+": <b> PACKMAN Number"+temp+" </b><br/>"
						+nameData[2]+": <b>"+Packman_arr.get(temp).packLocation.x()+" </b><br/>" 
						+nameData[3]+": <b>"+Packman_arr.get(temp).packLocation.y()+" </b><br/>" 
						+nameData[4]+": <b>"+Packman_arr.get(temp).getSpeed()+" </b><br/>" 
						+nameData[5]+": <b>"+Packman_arr.get(temp).getradius()+" </b><br/>" // altito to meter



						+"]]></description>\n" +
						"<TimeStamp>\r\n" + 
						"        <when>2018-11-14T21:05:0"+(i+2)+"Z</when>\r\n" + 
						"      </TimeStamp>"+
						"<styleUrl>#Packman</styleUrl>"+
						"<Point>\n" +
						"<coordinates>"+Fruits_arr.get(i).getFruitPoint().y()+","+Fruits_arr.get(i).getFruitPoint().x()+"</coordinates>" +
						"</Point>\n" +
						"</Placemark>\n";


				content.add(kmlelement2);
			


		}

		content.add(kmlend);
		pw.write(String.join("\n", content));
		System.out.println("Operation Complete");
		pw.close();
	} 

}











