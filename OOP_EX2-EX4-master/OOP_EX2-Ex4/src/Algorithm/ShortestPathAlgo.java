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

		this.fruits = theGame.myFruits;
		this.Packmans = theGame.myPackmens;
	}


	public Path algoSinglePackman(){
		Path temp = new Path();
		ArrayList<Fruit> Tempfruits = this.fruits;
		
		Packman tempPackman = new Packman(Packmans.get(0).getPoint(), 
				Packmans.get(0).getSpeed(),  Packmans.get(0).getradius());
		
		Path resultPath = calFastDisOnePack(tempPackman, Tempfruits,temp);
		resultPath.setTheTotalTime(getTimePathPerPackman(tempPackman, resultPath));

		return resultPath;

	}

//	public ArrayList<Packman> algoMultiPackmans() {
//
////		ArrayList<Path> PackmansPath = new ArrayList<>();
//		ArrayList<Path> temp = new ArrayList<>();
//		ArrayList<Furit> Tempfruits = this.fruits;
//		ArrayList<Packman> TempPackmans = this.Packmans;
//		
//
//
//	}
	
	public ArrayList<Packman> algoMultiPackmans (ArrayList<Fruit> myFurits ,ArrayList<Packman> myPackmans){
		
		double TheFastTime = CalTime(myPackmans.get(0),myFurits.get(0));
		Packman TheCloserPackman = myPackmans.get(0); 

		
		TheFastTime = CalTime(myPackmans.get(0),myFurits.get(0));
		
		for (int i = 0; i < myFurits.size(); i++) {
			
			for (int j = 0; j < myPackmans.size(); j++) {
				
				double temp = CalTime(myPackmans.get(j),myFurits.get(i));
	
				if (temp < TheFastTime) {
					TheFastTime = temp;
					TheCloserPackman = myPackmans.get(j);
				}
				
			}
			TheCloserPackman.getPath().add(myFurits.get(i));
			
		}
		
		return myPackmans;
		

		
	}
	
//	public Path theCloserPackman(ArrayList<Packman> packmans, Furit Furit){
//		
//		double TheFastTime = CalTime(packmans.get(0),Furit);
//		Packman TheCloserPackman = null; 
//		
//		for (int i = 0; i < packmans.size(); i++) {
//			
//			double temp = CalTime(packmans.get(i),Furit);
//			
//			if (temp < TheFastTime) {
//				TheFastTime = temp;
//				TheCloserPackman.getPath().add(Furit); 
//			}
//			
//		}	
//	}
	


	public double theFastTime2Furit (Packman packman ,ArrayList<Fruit> myFurits) {
		
		double theFast = CalTime(packman, myFurits.get(0));
		double temp = 0;
		for (int i = 0; i < myFurits.size(); i++) {
			
			temp = CalTime(packman, myFurits.get(i));
			if(temp < theFast) {
				theFast = temp;
			}
			
		}
		return theFast;
		
	}






	public Path calFastDisOnePack (Packman packman, ArrayList<Fruit> myFurits, Path myPackPath) {
		
		if (myFurits.isEmpty()) {
			return myPackPath;
		}

		Fruit theCloserTemp = TheCloserFurit(packman,myFurits); // the closer furit to packman 
		myPackPath.add(theCloserTemp); 
		packman.setPackLocation(theCloserTemp.getFruitPoint());
		myFurits.remove(getIndexFurit(theCloserTemp, myFurits));



		return calFastDisOnePack(packman,myFurits,myPackPath);

	}
	// return the most cloers furit to the packman
	public Fruit TheCloserFurit(Packman packman,ArrayList<Fruit> myFurits) {

		double FastTime = CalTime(packman,myFurits.get(0));
		Fruit theMostCloser = myFurits.get(0);
		double tempTime = 0;

		for (int i = 1; i < this.fruits.size(); i++) {
			tempTime = CalTime(packman, fruits.get(i));

			if(tempTime < FastTime)	{
				FastTime = tempTime;
				theMostCloser = this.fruits.get(i);
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

	public double getTimePathPerPackman(Packman packman,Path thePath) {
		double totalTime = 0;

		for (int i = 0; i < thePath.size(); i++) {
			totalTime += CalTime(packman,thePath.get(i));	

		}
		return totalTime;
	}


	// helping functions //

	public double CalTime(Packman p , Fruit f) {
		MyCoords m = new MyCoords();
		Geom.Point3D pack = themap.Pixel2GPS(p.getPoint().x(), p.getPoint().y());
		Geom.Point3D furit = themap.Pixel2GPS(f.getFruitPoint().x(), f.getFruitPoint().y());
			System.out.println("the dis is:"+m.distance3d(pack, furit));	
		if (m.distance3d(pack, furit) < p.getradius()) {
			System.out.println("the dis is:???"+m.distance3d(pack, furit));	

			return 0;
		}
		else {	
			return (m.distance3d(pack, furit)-p.getradius())/p.getSpeed();

		}
	}



	public Iterator<Fruit> Furit_iteretor() {
		return this.fruits.iterator();
	}







}
