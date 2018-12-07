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
	public 	String path;
	public Map theMap = new Map();

	public Game(ArrayList<Packman> p , ArrayList<Furit> f, String path) {
		this.myPackmens = p;
		this.myFruits = f;
		this.path = path;
	}



	public  void save2Csv() throws FileNotFoundException {

		PrintWriter pw = new PrintWriter(new File(path+".csv"));
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
		
//		for (int i = 0; i < myPackmens.size(); i++) {
//			theMap.Pixel2GPS(x, y, x_lengthMap, y_lengthMap)
//			myPackmens.get(i).
//			
//		}

		for (int i = 0; i < this.myPackmens.size(); i++) {
		
			sb.append("Packman");
			sb.append(',');
			sb.append(i);
			sb.append(',');
			sb.append(this.myPackmens.get(i).x());
			sb.append(',');
			sb.append(this.myPackmens.get(i).y());
			sb.append(',');
			sb.append(this.myPackmens.get(i).z());
			sb.append(',');
			sb.append(this.myPackmens.get(i).getSpeed());
			sb.append(',');
			sb.append(this.myPackmens.get(i).getradius());
			sb.append('\n');
		}
		for (int i = 0; i < myFruits.size(); i++) {
			sb.append("Fruit");
			sb.append(',');
			sb.append(i);
			sb.append(',');
			sb.append(this.myFruits.get(i).x());
			sb.append(',');
			sb.append(this.myFruits.get(i).y());
			sb.append(',');
			sb.append(this.myFruits.get(i).z());
			sb.append(',');	
			sb.append(this.myFruits.get(i).getWeight());
			sb.append('\n');

		}
		sb.append('\n');

		pw.write(sb.toString());
		pw.close();

	}


	public void Csvread() throws IOException{		
		//File file = CsvFileHelper.getResource(path);
		List<String[]> s = csv2kml.readFile2(path);

		for (int i = 1; i < s.size(); i++) {
			String[] row = s.get(i);

			if(row[0].equals("Packman")) {
				Point3D p = new Point3D(row[2],row[3],row[4]);
				double speed = Double.parseDouble(row[5]);
				int radius = Integer.parseInt(row[6]);
				myPackmens.add(new Packman(p, speed, radius));	
			}
			if(row[0].equals("Fruit")) {
				Point3D p = new Point3D(row[2],row[3],row[4]);
				int Weight = Integer.parseInt(row[5]);
				myFruits.add(new Furit(p, Weight));
			}
		}

		//		for (int j = 0; j < myPackmens.size(); j++) {
		//			System.out.println(myPackmens.get(j).toString());
		//		}
		//		for (int i = 0; i < myFruits.size(); i++) {
		//			System.out.println(myFruits.get(i).toString());
		//
		//		}

	}
}




