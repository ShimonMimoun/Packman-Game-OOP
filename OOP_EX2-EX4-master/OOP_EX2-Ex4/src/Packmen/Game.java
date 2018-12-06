package Packmen;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

import GIS.Furit;
import GIS.Packman;

public class Game {
	
	public static ArrayList<Packman> myPackmens = new ArrayList<>();
	public static ArrayList<Furit> myFruits = new ArrayList<>();

	public Game(ArrayList<Packman> p , ArrayList<Furit> f) {
		this.myPackmens = p;
		this.myFruits = f;
	}

	
	public static void main(String[] args) throws FileNotFoundException {
		
        PrintWriter pw = new PrintWriter(new File("test.csv"));
        StringBuilder sb = new StringBuilder();
        String[] heders = {"Type","id"	,"Lat"	,"Lon"	,"Alt"	,"Speed/Weight"	,"Radius"};

        for (int i = 0; i < heders.length; i++) {
            sb.append(heders[i]);
            sb.append(",");	
		}
        sb.append(myPackmens.size());
        sb.append(',');
        sb.append(myFruits.size());
        sb.append('\n');
        
        for (int i = 0; i < myPackmens.size(); i++) {
        	heders[0] = "P";
        	heders[1] = i+"";
        	heders[2] = myPackmens.get(i).x()+"";
		}


        pw.write(sb.toString());
        pw.close();
        System.out.println("done!");
	}
	public  void save2Csv(ArrayList<Furit> Fruits , ArrayList<Packman> Packmans) {
		

    }
}
	
	
