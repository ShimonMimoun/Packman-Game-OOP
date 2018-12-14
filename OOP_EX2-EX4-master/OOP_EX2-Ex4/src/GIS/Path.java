package GIS;

import java.util.ArrayList;

import Coords.Map;
import Coords.MyCoords;
import Geom.Point3D;

public class Path{

	public double totalTimePath;
	Map theMap = new Map();
	
	ArrayList<Fruit> thepath;


//	ArrayList<Furit> thePath = new ArrayList<>();
	
	public Path() {
		this.totalTimePath = 0;	
		thepath = new ArrayList<>();

	}
	
	public double getTheTime() {
		return this.totalTimePath;
	}
	
	public void setTheTotalTime(double total) {
		this.totalTimePath = total;
	}


	public ArrayList<Fruit> TheCurrentPath() {
		return this.thepath;
	}
	
	public double CalTime2Points(Packman p , Fruit f) {
		if (theMap.distancePixels(p.getPoint(), f.getFruitPoint()) < p.getradius()) {
			
			
			return 0;
		}
		else {	
			return (theMap.distancePixels(p.getPoint(), f.getFruitPoint())-p.getradius())/p.getSpeed();

		}
	}
	public double CalPathTime(Packman packman) {
		
		double totalTime = 0;
		double cal = 0;
		Packman temp = new Packman(packman);
		for (int i = 0; i < packman.getPath().TheCurrentPath().size(); i++) {
			cal = CalTime2Points(packman,packman.getPath().TheCurrentPath().get(i));
			System.out.println("dis "+i+" "+cal);
			totalTime = totalTime+cal;
			packman.setPackLocation(packman.getPath().TheCurrentPath().get(i).getFruitPoint());

		}
		System.out.println("total "+totalTime);
		packman.getPath().setTheTotalTime(totalTime);
		packman.setPackLocation(temp.getPoint());
		
		return totalTime;
	}
	

	
	public void setPath(ArrayList<Fruit> p) {
		this.thepath = p;
		
	}
	

}