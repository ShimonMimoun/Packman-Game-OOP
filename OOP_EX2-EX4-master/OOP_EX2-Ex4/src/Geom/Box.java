package Geom;

public class Box {
	
	private Point3D p1;
	private Point3D p2;
	
	
	public Box(Point3D p1 , Point3D p2) {
		
		this.p1 = p1;
		this.p2 = p2;
	}
	
	
	
	public Point3D getP1() {
		return p1;
	}


	public void setP1(Point3D p1) {
		this.p1 = p1;
	}


	public Point3D getP2() {
		return p2;
	}


	public void setP2(Point3D p2) {
		this.p2 = p2;
	}
	@Override
	public String toString() {
		return "Box [p1=" + p1 + ", p2=" + p2 + "]";
	}


}
