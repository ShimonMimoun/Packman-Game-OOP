package GIS;

import java.util.ArrayList;

import Geom.Point3D;

public class Path{

	public double totalTimePath;
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
	
	public void setPath(ArrayList<Fruit> p) {
		
	}

}