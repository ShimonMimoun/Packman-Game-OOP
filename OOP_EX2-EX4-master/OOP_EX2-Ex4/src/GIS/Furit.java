package GIS;

import Geom.Point3D;

public class Furit {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Point3D FuritPoint;
	int Weight;

	public Furit(Point3D p, int Weight) {
		
		this.FuritPoint = p;
		this.Weight = Weight;
	}


	public int getWeight() {
		return Weight;
	}

	public Point3D getFuritPoint() {
		return FuritPoint;
	}


	@Override
	public String toString() {
		return "Furit [FuritPoint=" + FuritPoint.toString() + ", Weight=" + Weight + "]";
	}


}
