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
	private distance_Comperator  DistSortbyPackman = new distance_Comperator();

	
	
	public ShortestPathAlgo(Game theGame) {	
		
		this.fruits = theGame.myFruits;
		this.Packmans = theGame.myPackmens;
	}
	
	
	public static void main(String[] args) {
		
		
		
	}
	
	
	public Path algoSinglePackman(Packman packman, ArrayList<Furit> myfurit){
	
		Path p = new Path();
		

		return p;
			
		}
//		System.out.println("Before sort :"+tempDistance.toString());
//		tempDistance.sort(DistSortbyPackman);
//		System.out.println("After sort :"+tempDistance.toString());

	
	public Iterator<Furit> Furit_iteretor() {
		return this.fruits.iterator();
	}



	
	


}
