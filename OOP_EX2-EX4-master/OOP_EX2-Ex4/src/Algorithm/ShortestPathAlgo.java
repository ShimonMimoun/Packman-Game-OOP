package Algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import Coords.Map;
import Coords.MyCoords;
import GIS.Fruit;
import GIS.Game;
import GIS.Packman;
import GIS.Path;
import Geom.Circle;
import javafx.geometry.Point3D;

public class ShortestPathAlgo {

	ArrayList<Fruit> fruits = new ArrayList<>();
	ArrayList<Packman> Packmans = new ArrayList<>();
	Map themap = new Map();



	public ShortestPathAlgo(Game theGame) {	

		this.fruits = theGame.Fruits_arr;
		this.Packmans = theGame.Packman_arr;
	}


	public Path algoSinglePackman(){
		Path temp = new Path();
		ArrayList<Fruit> Tempfruits = this.fruits;
		
		Packman tempPackman = new Packman(Packmans.get(0).getPoint(),Packmans.get(0).getSpeed(),Packmans.get(0).getradius());
		
		Path resultPath = calFastDisOnePack(tempPackman, Tempfruits);
		resultPath.setTheTotalTime(getTimePathPerPackman(tempPackman));

		return resultPath;

	}


	
	public ArrayList<Packman> algoMultiPackmans (){
		ArrayList<Fruit> myFurits = this.fruits;
		ArrayList<Packman> myPackmans = this.Packmans;
		
		
		double TheFastTime = 0;
		double tempTime = 0;
		Packman TheCloserPackman = myPackmans.get(0);
		
		TheFastTime = CalTime(myPackmans.get(0),myFurits.get(0));
		
		for (int i = 0; i < myFurits.size(); i++) {
			
			for (int j = 1; j < myPackmans.size() ; j++) {
				
				tempTime = 	CalTime(myPackmans.get(j),myFurits.get(i));
				
				if(tempTime < TheFastTime) {
					TheFastTime = tempTime;
					TheCloserPackman = myPackmans.get(j);
				}
				
			}
			TheCloserPackman.getPath().TheCurrentPath().add(myFurits.get(i));
		}
		
		
		
		

		return myPackmans;
	
		
	}
	
	public Path calFastDisOnePack (Packman packman, ArrayList<Fruit> myFurits) {
		
//		if (myFurits.isEmpty()) {
//			return packman.getPath();
//		}
//
//		Fruit theCloserTemp = TheCloserFurit(packman,myFurits); // the closer furit to packman 
//		packman.getPath().TheCurrentPath().add(theCloserTemp);
//		packman.setPackLocation(theCloserTemp.getFruitPoint());
//		myFurits.remove(getIndexFurit(theCloserTemp, myFurits));
		while(!myFurits.isEmpty()) {
			Fruit theCloserTemp = TheCloserFurit(packman,myFurits); // the closer furit to packman 
			packman.getPath().TheCurrentPath().add(theCloserTemp);
			packman.setPackLocation(theCloserTemp.getFruitPoint());
			myFurits.remove(getIndexFurit(theCloserTemp, myFurits));
			
		}
		
		return packman.getPath();


	}
	// return the most cloers furit to the packman
	public Fruit TheCloserFurit(Packman packman,ArrayList<Fruit> myFurits) {

		double FastTime = CalTime(packman,myFurits.get(0));
		Fruit theMostCloser = myFurits.get(0);
		double tempTime = 0;

		for (int i = 1; i < myFurits.size(); i++) {
			tempTime = CalTime(packman, myFurits.get(i));

			if(tempTime < FastTime)	{
				FastTime = tempTime;
				theMostCloser = myFurits.get(i);
			}	
		}

		return theMostCloser;
	}

	//return the index of the furit.
	public int getIndexFurit(Fruit furit , ArrayList<Fruit> myFurits) {

		for (int i = 0; i < myFurits.size(); i++) {

			if(furit.equals(myFurits.get(i))) {
				return i;
			}

		}
		return -1;
	}

	public double getTimePathPerPackman(Packman packman) {
		double totalTime = 0;

		for (int i = 0; i < packman.getPath().TheCurrentPath().size(); i++) {
			totalTime += CalTime(packman,packman.getPath().TheCurrentPath().get(i));

		}
		return totalTime;
	}


	// helping functions //

	public double CalTime(Packman p , Fruit f) {
		MyCoords m = new MyCoords();
		Geom.Point3D pack = themap.Pixel2GPS(p.getPoint().x(), p.getPoint().y());
		Geom.Point3D furit = themap.Pixel2GPS(f.getFruitPoint().x(), f.getFruitPoint().y());
		if (m.distance3d(pack, furit) < p.getradius()) {

			return 0;
		}
		else {	
			return (m.distance3d(pack, furit)-p.getradius())/p.getSpeed();

		}
	}






}
