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
		MyCoords m = new MyCoords();
		if (m.distance3d(p.getPoint(), f.getFruitPoint()) < p.getradius()) {
			
			return 0;
		}
		else {	
			return (m.distance3d(p.getPoint(), f.getFruitPoint())-p.getradius())/p.getSpeed();

		}
	}
	public double CalPathTime(Packman p) {
		
		double totalTime = 0;
		for (int i = 0; i < p.getPath().TheCurrentPath().size(); i++) {
			totalTime = totalTime+ CalTime2Points(p,p.getPath().TheCurrentPath().get(i));
		}
		
		setTheTotalTime(totalTime);
		
		return totalTime;
	}
	

	
	public void setPath(ArrayList<Fruit> p) {
		this.thepath = p;
		
	}
	

}