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
	distance_Comperator calDis = new distance_Comperator();
	Map themap = new Map();



	public ShortestPathAlgo(Game theGame) {	

		this.fruits = theGame.Fruits_arr;
		this.Packmans = theGame.Packman_arr;
	}


	public Path algoSinglePackman(Packman oriPackman){
		ArrayList<Fruit> Tempfruits = this.fruits;

		Packman tempPackman = new Packman(oriPackman.getPoint(),oriPackman.getSpeed(),oriPackman.getradius());
		Path Dis = disAlgo(tempPackman,Tempfruits);
		Path p = calFastDisOnePack(tempPackman, Tempfruits);
		
		oriPackman.getPath().setPath(Dis.TheCurrentPath());	
		double timeFor1 = Dis.CalPathTime(oriPackman);
		System.out.println(Dis.CalPathTime((oriPackman)));
		oriPackman.getPath().setPath(p.TheCurrentPath());
		System.out.println(p.CalPathTime((oriPackman)));
		double timeFor2 = p.CalPathTime(oriPackman);

		
		if(timeFor1 <= timeFor2) {
			System.out.println(oriPackman.getPoint());
			oriPackman.getPath().setPath(Dis.TheCurrentPath());	
			return oriPackman.getPath();

		}else {
			System.out.println("NOT timeFor1 < timeFor2"+oriPackman.getPoint());
			return oriPackman.getPath();

		}

		

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
	
	public Path disAlgo(Packman packman, ArrayList<Fruit> myFurits) {
		
		ArrayList<Double> SortPathByDis = new ArrayList<>();
		Path ans = new Path();
		
		for (int i = 0; i < myFurits.size(); i++) {
			double temp = themap.distancePixels(packman.getPoint(), myFurits.get(i).getFruitPoint());
			SortPathByDis.add(temp);
		}
		SortPathByDis.sort(calDis);
		double temp;
		int j = 0;
		while (!SortPathByDis.isEmpty()) {
			
			temp = themap.distancePixels(packman.getPoint(), myFurits.get(j).getFruitPoint());
			
			for (int i = 0; i < myFurits.size(); i++) {
				if(temp == SortPathByDis.get(i)){
					ans.TheCurrentPath().add(myFurits.get(j));
					SortPathByDis.remove(i);
					j++;
					break;
				}	
				
			}
			
		}


		return ans;
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
		double longestTime = myPath.CalPathTime(myPackmens.get(0));
		double temp = 0;
//		System.out.println(longestTime);
		
		for (int i = 1; i < myPackmens.size(); i++) {
			temp = myPath.CalPathTime(myPackmens.get(i));
//			System.out.println(temp);
			if (temp > longestTime) {
				longestTime = temp;
			}
		}
		System.out.println("the Total time is: "+longestTime);
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
