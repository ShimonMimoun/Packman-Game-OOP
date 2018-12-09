package GIS;

import java.util.ArrayList;

import Geom.Point3D;

public class Path extends ArrayList<Furit>{


/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


//	ArrayList<Furit> thePath = new ArrayList<>();
		
	
	public Path() {
		
	}


	public int  getPath() {

		int sum = 0;
		
		for (int i = 0; i < this.size(); i++) {
			sum += this.get(i).distance3D(this.get(i+1));
		}
		return sum;
	}

}