package Geom;

import java.io.Serializable;

public class Box  implements Serializable{

	private int _id = 0;
	private Point3D _min;
	private Point3D _max;
	private double _weight;
	private static int _count = 0;

	public Box(Point3D p1, Point3D p2) { _min = new Point3D(Math.min(p1.x(), p2.x()), 
			Math.min(p1.y(), p2.y()), Math.min(p1.x(), p2.x()));
	_max = new Point3D(Math.max(p1.x(), p2.x()), 
			Math.max(p1.y(), p2.y()), Math.max(p1.x(), p2.x()));
	_id = (_count++);
	_weight = 1.0D;
	}

	public Box(String line) {
		String[] arr = line.split(",");
		_id = Integer.parseInt(arr[1]);
		String p = arr[2] + "," + arr[3] + "," + arr[4];
		_min = new Point3D(p);
		p = arr[5] + "," + arr[6] + "," + arr[7];
		_max = new Point3D(p);
		_weight = Double.parseDouble(arr[8]);
	}

	public Box(Box BOx2)
	{
		this(BOx2.toString());
	}

	public String toString() {
		String ans = "B," + _id + "," + _min + "," + _max + "," + _weight;
		return ans; }

	public double getWeight() { return _weight; }
	public void setWeight(double w) { _weight = w; }

	public boolean isIn2D(Point3D q) { boolean ans = false;
	if ((q.y() >= _min.x()) && (q.y() >= _min.y()) && (q.x() <= _max.x()) && (q.y() <= _max.y())) {
		ans = true;
	}
	return ans;
	}

	public boolean isIn3D(Point3D q) { boolean ans = isIn2D(q);
	ans = (ans) && (q.x() >= _min.x()) && (q.x() <= _max.x());
	return ans; }

	public Point3D getMin() { return _min; }
	public Point3D getMax() { return _max; }

public static void main(String[] args) {
	
	Box a=new Box(new Point3D(32.10354703120936,35.2056556995029),new Point3D(32.10395902208114,35.210616500952405));
	Point3D b= new Point3D(32.10353216683579,35.20953020326105);
	
	System.out.println(a.isIn2D(b));
	
}

}


