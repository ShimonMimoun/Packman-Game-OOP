package GIS;

import Geom.Point3D;

public class Furit extends Point3D {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	 Point3D FuritPoint;
	int Weight;

	public Furit(Point3D p, int Weight) {
		super(p);
		this.FuritPoint = p;
		this.Weight = Weight;
	}


	public int getWeight() {
		return Weight;
	}

	@Override
	public String toString() {
		return "Furit [FuritPoint=" + FuritPoint.toString() + ", Weight=" + Weight + "]";
	}


}
