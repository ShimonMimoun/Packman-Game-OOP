package Algorithm;

import java.util.ArrayList;

import Coords.Map;
import Coords.MyCoords;
import GIS.Fruit;
import GIS.Game;
import GIS.Ghost;
import GIS.Packman;
import GIS.Player;
import Geom.Box;
import Geom.Point3D;

public class AlgoTest {
	private ArrayList<Fruit> fruits = new ArrayList<>(); // Arraylist of fruit
	private ArrayList<Packman> Packmans = new ArrayList<>();//Arraylist of Packman 
	private ArrayList<Box> boxs = new ArrayList<>();//Arraylist of boxs 
	private ArrayList<Ghost> ghosts = new ArrayList<>();//Arraylist of ghosts 
	private ArrayList<Point3D> ans;
	private ArrayList<Box> newBoxs;
	private MyCoords m;
	private Player player = new Player(new Point3D(0,0,0),1,1);
	private Map theMap = new Map();// create a Map object
	int verif=0;


	/**
	 * Contractor of ShortestPathAlgo Who receives Game Object
	 * @param theGame Object Game receiv 
	 */
	public AlgoTest(Game theGame) {	

		ArrayList<Fruit> clone = new ArrayList<Fruit>(theGame.Fruits_arr.size());  for (Fruit item : theGame.Fruits_arr) clone.add(item);
		this.fruits = clone;	//Create a new fruit for not to overwrite Game data later
		this.Packmans = theGame.Packman_arr;
		this.player = theGame.Player_user;
		this.boxs = theGame.Boxs_arr;
		this.ghosts = theGame.Ghost_arr;
		this.m = new MyCoords();
		this.ans = new ArrayList<>();
		this.ans = addingTo1List(this.Packmans,this.fruits);
	this.newBoxs = boxList(boxs);
	
	




	}


	public ArrayList<Box> getBoxs() {
		return boxs;
	}


	public void setBoxs(ArrayList<Box> boxs) {
		this.boxs = boxs;
	}


	public double update_Game(Player p1 ,Player p2, double dir) { 

		Point3D theClose = TheCloserFurit(p2,ans);
		double ansBoxs = checkBox(p2.getPoint_player(), dir ,theClose );
		if(ansBoxs == 5) {
			dir = m.myDir(theClose,p2.getPoint_player());
			return dir;
		}

		return ansBoxs;
	}


	private ArrayList<Box> boxList(ArrayList<Box> boxs) {
		ArrayList<Box> ansBoxs = new ArrayList<>();
	
			for (int i = 0; i < boxs.size(); i++) {
				
				Box b1 = boxs.get(i).addtoConner();
				
				
				Point3D boxGPS_1 = theMap.Pixel2GPS(b1.getP1().x(), b1.getP1().y());
				Point3D boxGPS_2 = theMap.Pixel2GPS(b1.getP2().x(), b1.getP2().y());
				Box b = new Box(boxGPS_1,boxGPS_2);

				
				ansBoxs.add(b);
			
		}
		return ansBoxs;



	}

	private ArrayList<Point3D> addingTo1List(ArrayList<Packman> Packmans,ArrayList<Fruit> fruits) {


		ArrayList<Point3D> ans = new ArrayList<>();

		for (int i = 0; i < Packmans.size(); i++) {
			Point3D pGPS = theMap.Pixel2GPS(Packmans.get(i).getPoint().x(), Packmans.get(i).getPoint().y());
			ans.add(pGPS);
		}

		for (int i = 0; i < fruits.size(); i++) {
			Point3D fGPS = theMap.Pixel2GPS(fruits.get(i).getFruitPoint().x(), fruits.get(i).getFruitPoint().y());
			ans.add(fGPS);
		}


		return ans;
	}


	public Point3D TheCloserFurit(Player M,ArrayList<Point3D> fruits_packs) {



		double FastTime = CalTime2Points(M,fruits_packs.get(0));
		Point3D theMostCloser = fruits_packs.get(0);
		double tempTime = 0;

		for (int i = 1; i < fruits_packs.size(); i++) {
			tempTime = CalTime2Points(M, fruits_packs.get(i));

			if(tempTime < FastTime)	{
				FastTime = tempTime;
				theMostCloser = fruits_packs.get(i);
			}	
		}

		return theMostCloser;
	}


	public double CalTime2Points(Player M , Point3D point) {
		if (m.distance3d(M.getPoint_player(), point) < M.getRadius()) {

			return 0;
		}
		else {	
			return (m.distance3d(M.getPoint_player(), point)-M.getRadius())/M.getSpeed();

		}
	}


	public double checkBox(Point3D m, double dir, Point3D theClose) {
		for (int i = 0; i < newBoxs.size(); i++) {
			if(newBoxs.get(i).inBox(m, theClose) == 1) {
				dir = 90;
				return dir;
			}
			else if(newBoxs.get(i).inBox(m, theClose) == 2) {
				dir = 0;
				return dir;
			}
			else if(newBoxs.get(i).inBox(m, theClose) == 3) {
				dir = 270;
				return dir;
			}
			else if(newBoxs.get(i).inBox(m, theClose) == 4) {
				dir = 180;
				return dir;
			}

		 
		}
		return 5;

	}
	
	
	
}









