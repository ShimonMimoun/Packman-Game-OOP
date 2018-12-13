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
		ArrayList<Fruit> Tempfruits = this.fruits;

		Packman tempPackman = new Packman(Packmans.get(0).getPoint(),Packmans.get(0).getSpeed(),Packmans.get(0).getradius());

		Path p = calFastDisOnePack(tempPackman, Tempfruits);
		Packmans.get(0).getPath().setPath(p.TheCurrentPath());
		p.CalPathTime((Packmans.get(0)));
		Packmans.get(0).getPath().setPath(p.TheCurrentPath());
		return Packmans.get(0).getPath();

	}
	
	public Path calFastDisOnePack (Packman packman, ArrayList<Fruit> myFurits) {

		if (myFurits.isEmpty()) {
			return packman.getPath();
		}

		Fruit theCloserTemp = TheCloserFurit(packman,myFurits); // the closer furit to packman 
		packman.getPath().TheCurrentPath().add(theCloserTemp);
		packman.setPackLocation(theCloserTemp.getFruitPoint());
		myFurits.remove(getIndexFurit(theCloserTemp, myFurits));

		return calFastDisOnePack(packman,myFurits);


	}



	public ArrayList<Packman> algoMultiPackmans (){
		Path myPath = new Path();
		ArrayList<Fruit> myFurits = this.fruits;
		ArrayList<Packman> ans = new ArrayList<>();
		ArrayList<Packman> myPackmens = this.Packmans;

		for (int i = 0; i < myPackmens.size(); i++) {
			Packman p = new Packman(myPackmens.get(i).getPoint(),myPackmens.get(i).getSpeed(), myPackmens.get(i).getradius());
			ans.add(p);
		}
		myPackmens = Algomulti(myPackmens,myFurits);
	
		for (int i = 0; i < myPackmens.size(); i++) {
			myPackmens.get(i).setPackLocation(ans.get(i).getPoint());	
		}
		double sum = 0;
		
		for (int i = 0; i < myPackmens.size(); i++) {
			sum = sum +myPath.CalPathTime(myPackmens.get(i));
		}
		System.out.println("the Total time is: "+sum);
		return myPackmens;
	}

	public ArrayList<Packman> Algomulti (ArrayList<Packman> myPackmans , ArrayList<Fruit>myFurits) {
		Path myPath = new Path();
		if(myFurits.isEmpty()) {
			return myPackmans;
		}
		
		Packman thePackman = myPackmans.get(0);
		Fruit theCloserFurit = TheCloserFurit(myPackmans.get(0),myFurits);
		double FastTime = myPath.CalTime2Points(myPackmans.get(0),theCloserFurit);
		
		Packman tempPack;
		Fruit tempFruit;
		double tempTime = 0;

		for (int i = 1; i < myPackmans.size(); i++) {

			tempPack = myPackmans.get(i);
			tempFruit = TheCloserFurit(myPackmans.get(i),myFurits);
			tempTime = myPath.CalTime2Points(myPackmans.get(i),tempFruit);

			if (tempTime < FastTime) {
				thePackman = tempPack;
				FastTime = tempTime;
				theCloserFurit = tempFruit;
			}

		}
		thePackman.getPath().TheCurrentPath().add(theCloserFurit);
		thePackman.setPackLocation(theCloserFurit.getFruitPoint());
		myFurits.remove(getIndexFurit(theCloserFurit, myFurits));
	
	return Algomulti(myPackmans ,myFurits);

}



 double FastSpeedToFriut(Packman packman ,ArrayList<Fruit> myFurits ) {
	 Path p = new Path();
	double fastTime = p.CalTime2Points(packman, myFurits.get(0));
	double tempTime = 0;

	for (int i = 1; i < myFurits.size(); i++) {

		tempTime = p.CalTime2Points(packman, myFurits.get(i));

		if(tempTime <fastTime) {
			fastTime = tempTime;
		}

	}
	return fastTime;

}

// return the most cloers furit to the packman
public Fruit TheCloserFurit(Packman packman,ArrayList<Fruit> myFurits) {
	 Path p = new Path();

	double FastTime = p.CalTime2Points(packman,myFurits.get(0));
	Fruit theMostCloser = myFurits.get(0);
	double tempTime = 0;

	for (int i = 1; i < myFurits.size(); i++) {
		tempTime = p.CalTime2Points(packman, myFurits.get(i));

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



}
