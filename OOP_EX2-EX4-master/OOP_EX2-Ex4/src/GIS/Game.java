package GIS;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import File_format.csv2kml;
import Geom.Point3D;
import Coords.Map;
public class Game {

	public  ArrayList<Packman> myPackmens = new ArrayList<>();
	public  ArrayList<Furit> myFruits = new ArrayList<>();
	public 	String file_directory = "/Desktop/";
	public Map theMap = new Map();
	
	
	
	
	
	public Game(ArrayList<Packman> p , ArrayList<Furit> f) {
		this.myPackmens = p;
		this.myFruits = f;
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
		sb.append(this.myPackmens.size());
		sb.append(',');
		sb.append(this.myFruits.size());
		sb.append('\n');

		for (int i = 0; i < myPackmens.size(); i++) {
			myPackmens.get(i).packLocation = theMap.Pixel2GPS(myPackmens.get(i).getPoint().x(), myPackmens.get(i).getPoint().y());
		}
		for (int i = 0; i < myFruits.size(); i++) {
			myFruits.get(i).FuritPoint = theMap.Pixel2GPS(myFruits.get(i).getFuritPoint().x(), myFruits.get(i).getFuritPoint().y());
		}

		for (int i = 0; i < this.myPackmens.size(); i++) {
			sb.append("P");
			sb.append(',');
			sb.append(i);
			sb.append(',');
			sb.append(myPackmens.get(i).packLocation.x());
			sb.append(',');
			sb.append(myPackmens.get(i).packLocation.y());
			sb.append(',');
			sb.append(myPackmens.get(i).packLocation.z());
			sb.append(',');
			sb.append(this.myPackmens.get(i).getSpeed());
			sb.append(',');
			sb.append(this.myPackmens.get(i).getradius());
			sb.append('\n');
		}
		for (int i = 0; i < myFruits.size(); i++) {
			sb.append("F");
			sb.append(',');
			sb.append(i);
			sb.append(',');
			sb.append(myFruits.get(i).FuritPoint.x());
			sb.append(',');
			sb.append(myFruits.get(i).FuritPoint.y());
			sb.append(',');
			sb.append(myFruits.get(i).FuritPoint.z());
			sb.append(',');	
			sb.append(this.myFruits.get(i).getWeight());
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
				int radius = Integer.parseInt(row[6]);
				myPackmens.add(new Packman(p, speed, radius));	
			}
			if(row[0].equals("F")) {
				Point3D p = new Point3D(row[2],row[3],row[4]);
				p = theMap.GPS2Pixel(p);
				int Weight = Integer.parseInt(row[5]);
				myFruits.add(new Furit(p, Weight));
			}
		}


	}
}




