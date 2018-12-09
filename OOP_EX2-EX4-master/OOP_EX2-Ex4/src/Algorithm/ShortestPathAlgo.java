package Algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import Coords.Map;
import GIS.Furit;
import GIS.Game;
import GIS.Packman;
import GIS.Path;
import Geom.Circle;
import javafx.geometry.Point3D;

public class ShortestPathAlgo {
	
	ArrayList<Furit> fruits = new ArrayList<>();
	ArrayList<Packman> Packmans = new ArrayList<>();
	private Distance_Comperator  DistSortbyPackman = new Distance_Comperator();

	
	
	public ShortestPathAlgo(Game theGame) {	
		
		this.fruits = theGame.myFruits;
		this.Packmans = theGame.myPackmens;
	}
	
	
	public static void main(String[] args) {
		
		
		
	}
	
	
	public Path algoSinglePackman(Packman packman){
		
		Path p = new Path();
		Iterator<Furit> furitIter = this.Furit_iteretor();
//		Map theMap = new Map();
		
		
		while(furitIter.hasNext()) {

			ArrayList<Double> tempDistance = new ArrayList<>();
			
			Furit temp = furitIter.next();
//			temp = (Furit) theMap.Pixel2GPS(temp.x(), temp.y());
//			packman = (Packman) theMap.Pixel2GPS(packman.x(), packman.y());
//			
			tempDistance.add(packman.distance3D(temp));
			
			
			System.out.println(furitIter.next().toString()+"the dis is: "+packman.distance3D(temp));
			tempDistance.sort(DistSortbyPackman);
			
			System.out.println(tempDistance.toString());
		}
		

		
		return p;
	}
	
	
	public Iterator<Furit> Furit_iteretor() {
		return this.fruits.iterator();
	}



	
	


}
