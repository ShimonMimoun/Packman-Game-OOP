package GIS;

import java.util.ArrayList;

import Geom.Point3D;

public class Path extends ArrayList<Fruit>{

	public double totalTimePath = 0;
	private static final long serialVersionUID = 1L;


//	ArrayList<Furit> thePath = new ArrayList<>();
		
	
	public double getTheTime() {
		return this.totalTimePath;
	}
	
	public void setTheTotalTime(double total) {
		this.totalTimePath = total;
	}


	public int  getPath() {

		int sum = 0;
		
		for (int i = 0; i < this.size(); i++) {
			sum += this.get(i).getFruitPoint().distance3D(this.get(i+1).getFruitPoint());
		}
		return sum;
	}

}