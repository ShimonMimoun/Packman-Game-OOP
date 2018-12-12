package GIS;

import Geom.Point3D;

public class Fruit {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Point3D FuritPoint;
	int Weight;

	public Fruit(Point3D p, int Weight) {
		
		this.FuritPoint = p;
		this.Weight = Weight;
	}
	public Fruit(Fruit f) {
		this(f.FuritPoint, f.Weight);
	}


	public int getWeight() {
		return Weight;
	}

	public Point3D getFruitPoint() {
		return FuritPoint;
	}


	@Override
	public String toString() {
		return "Furit [FuritPoint=" + FuritPoint.toString() + ", Weight=" + Weight + "]";
	}


}
