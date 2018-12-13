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
}




