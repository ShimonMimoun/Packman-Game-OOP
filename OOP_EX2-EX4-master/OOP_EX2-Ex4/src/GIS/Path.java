package GIS;

import java.util.ArrayList;

import Coords.Map;
import Geom.Point3D;

public class Path{

	public double totalTimePath;
	Map theMap = new Map();
	
	ArrayList<Fruit> thepath;
	ArrayList<Packman> test;

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
	
	public ArrayList<Packman> GetmyTest(){
		return this.test;
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
			totalTime = totalTime+cal;
			packman.setPackLocation(packman.getPath().TheCurrentPath().get(i).getFruitPoint());

		}
		packman.getPath().setTheTotalTime(totalTime);
		packman.setPackLocation(temp.getPoint());

		return totalTime;
	}

	
	public Point3D theNextPoint(Packman p1 , Fruit f1, double t) {
		
	double dt = p1.getPath().getTheTime(); // the time from (x1,y1) to (x2,y2) example: 300.
	
	double Vx = p1.getPoint().x()/dt+0.05;
	double Vy = p1.getPoint().y()/dt+0.05;
	
	double xt = p1.getPoint().x()+Vx*(f1.getFruitPoint().x()-p1.getPoint().x());
	double yt= p1.getPoint().y()+Vy*(f1.getFruitPoint().y()-p1.getPoint().y());
	return new Point3D(xt,yt);
	
	}
	

	
	public void setPath(ArrayList<Fruit> p) {
		this.thepath = p;
		
	}
	

}