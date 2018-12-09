package Algorithm;

import java.util.Comparator;

import GIS.Furit;
import GIS.Packman;

public class Distance_Comperator implements Comparator<Double> {

	@Override
	public int compare(Double o1, Double o2) {
		
		if(o1 > o2) {
			return 1;
		}
		if (o1 < o2) {
			return -1;
		}
		return 0;
	}
}

	
	
	
	


