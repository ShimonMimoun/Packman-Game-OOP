package Coords;

import Geom.Point3D;


/**
 * This Class contains the Main function, which aims to test the functions of Mycoords
 * @author Mimoun Shimon and Omer Paz 
 *
 */
public class test {


	public static void main(String[] args) {
		MyCoords m = new MyCoords();



		Point3D pack=new Point3D(32.1040274470405,35.20560909618209,0.0);
		Point3D fruit = new Point3D(32.105082626168226,35.206251845080764,0.0);
		double[] arr = m.azimuth_elevation_dist(fruit, pack);
		
		for (int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);
		}




	}

}
