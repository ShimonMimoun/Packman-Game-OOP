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


	public Path algoSinglePackman(){
		Path temp = new Path();
		ArrayList<Furit> Tempfruits = this.fruits;

		Path resultPath = calFastDis(Packmans.get(0), Tempfruits,temp);
		resultPath.setTheTotalTime(getTimePathPerPackman(Packmans.get(0), resultPath));
		
		return resultPath;

	}
	
	public Path calFastDis (Packman packman, ArrayList<Furit> myFurits, Path myPackPath) {
		
		if (myFurits.isEmpty()) {
			return myPackPath;
		}
		
		Furit theCloserTemp = TheCloserFurit(packman); // the closer furit to packman 
		myPackPath.add(theCloserTemp); 
		this.Packmans.get(0).setPackLocation(theCloserTemp);
		myFurits.remove(getIndexFurit(theCloserTemp, myFurits));
	
		
		return calFastDis(packman,myFurits,myPackPath);
		
	}
	// return the most cloers furit to the packman
	public Furit TheCloserFurit(Packman packman) {
		
		double FastTime = CalTime(packman,fruits.get(0));
		Furit theMostCloser = this.fruits.get(0);
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
	public int getIndexFurit(Furit furit , ArrayList<Furit> myFurits) {
		
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
	
	public double CalTime(Packman p , Furit f) {
		return (p.distance3D(f))/p.getSpeed();
	}
	


	public Iterator<Furit> Furit_iteretor() {
		return this.fruits.iterator();
	}







}
