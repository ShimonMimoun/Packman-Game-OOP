package GUI;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import com.sun.javafx.tk.Toolkit;
import com.sun.xml.internal.bind.v2.runtime.reflect.Lister.Pack;

import Algorithm.ShortestPathAlgo;
import Coords.Map;
import File_format.CsvFileHelper;
import File_format.csv2kml;
import GIS.Fruit;
import GIS.Game;
import GIS.Packman;
import GIS.Path;
import Geom.Point3D;


public class MyFarme extends JFrame implements MouseListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public Map theMap = new Map();
	public BufferedImage myImage;
	public BufferedImage packimage;
	public BufferedImage Fruitimage;

	double radius = 1;
	int speed = 1;

	public  ArrayList<Packman> Packman_arr = new ArrayList<>();
	public  ArrayList<Fruit> Fruits_arr = new ArrayList<>();
	private Game myGame=new Game(Packman_arr, Fruits_arr);
	//	private ArrayList<Fruit>Fruits=new ArrayList<>();
	//	private ArrayList<Packman>Packmans=new ArrayList<>();
	private int isGamer=0;// if is Gamer==1 --> Fruit :::: if is Gamer == -1 --> Packman 
	private boolean Start_game=false;

	Path TheCloserPackman;


	public MyFarme() 
	{
		initGUI();		
		this.addMouseListener(this); 

	}

	private void initGUI() {


		try {	myImage = ImageIO.read(new File("Pictures&Icones/Ariel1.png")); } catch (IOException e) { e.printStackTrace();	}	
		try {	packimage = ImageIO.read(new File("Pictures&Icones/packman.jpg")); } catch (IOException e) { e.printStackTrace();	}
		try {	Fruitimage = ImageIO.read(new File("Pictures&Icones/furit.png")); } catch (IOException e) { e.printStackTrace();	}



		MenuBar menuBarOption = new MenuBar();

		Menu OptionMenu = new Menu("File"); 
		menuBarOption.add(OptionMenu);
		MenuItem runItem = new MenuItem("Run");
		MenuItem reload_item = new MenuItem("Reload");
		OptionMenu.add(runItem);
		OptionMenu.add(reload_item);
		MenuItem exit = new MenuItem("Exit");
		OptionMenu.add(exit);




		Menu AddMenu = new Menu("Add"); 
		menuBarOption.add(AddMenu);
		MenuItem Packman_Item = new MenuItem("Packman");
		MenuItem Fruit_item = new MenuItem("Fruit");
		MenuItem setraduis = new MenuItem("Set Radius");

		AddMenu.add(Packman_Item);
		AddMenu.add(Fruit_item);
		AddMenu.add(setraduis);


		Menu Add_import=new Menu ("Import");
		MenuItem Csv_read = new MenuItem("Csv");
		Add_import.add(Csv_read);


		menuBarOption.add(Add_import);
		Menu Add_export=new Menu ("Export");
		menuBarOption.add(Add_export);
		MenuItem Csv_writing_csv = new MenuItem(" Csv ");
		MenuItem Csv_writing_kml = new MenuItem(" Kml ");
		Add_export.add(Csv_writing_csv);
		Add_export.add(Csv_writing_kml);


		this.setMenuBar(menuBarOption);






		//Turn on the buttons

		runItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				Start_game=true;
				repaint();

			}

		});



		reload_item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {


				theMap = new Map();
				ArrayList<Packman> Packman_arr = new ArrayList<>();
				ArrayList<Fruit> Fruits_arr = new ArrayList<>();
				myGame=new Game(null,null);
				myGame.Packman_arr = Packman_arr;
				myGame.Fruits_arr =Fruits_arr;
				isGamer=0;
				Start_game=false;
				new MyFarme();
				repaint();
			}
		});




		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		Fruit_item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				isGamer = 1;
			}
		});

		Packman_Item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				isGamer = -1;

			}
		});
		Csv_read.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
				fileChooser.setDialogTitle("Select an Csv File");
				fileChooser.setAcceptAllFileFilterUsed(false);
				FileNameExtensionFilter filter = new FileNameExtensionFilter("csv","CSV");
				fileChooser.addChoosableFileFilter(filter);

				int returnValue = fileChooser.showOpenDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					System.out.println(fileChooser.getSelectedFile().getPath());
					Game theGame = new Game(myGame.Packman_arr,myGame.Fruits_arr);
					theGame.setfile_directory(fileChooser.getSelectedFile().getPath());
					try {
						theGame.Csvread();
						myGame.Packman_arr = theGame.Packman_arr;
						myGame.Fruits_arr = theGame.Fruits_arr;
						isGamer = 2;

						repaint();

					} catch (IOException e1) {
						e1.printStackTrace();
					}

				}
			}
		});





		Csv_writing_csv.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
				jfc.setDialogTitle("Export to Csv File");
				jfc.setAcceptAllFileFilterUsed(false);
				FileNameExtensionFilter filter = new FileNameExtensionFilter("csv","CSV");
				jfc.addChoosableFileFilter(filter);

				int returnValue = jfc.showSaveDialog(null);

				if (returnValue == JFileChooser.APPROVE_OPTION) {
					File selectedFile = jfc.getSelectedFile();
					System.out.println(selectedFile.getAbsolutePath());

					myGame.setfile_directory(selectedFile.getAbsolutePath());
					try {
						myGame.save2Csv();
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}
				}


			}

		});


		Csv_writing_kml.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
				jfc.setDialogTitle("Export to KML File");
				jfc.setAcceptAllFileFilterUsed(false);
				FileNameExtensionFilter filter = new FileNameExtensionFilter("kml","KML");
				jfc.addChoosableFileFilter(filter);

				int returnValue = jfc.showSaveDialog(null);

				if (returnValue == JFileChooser.APPROVE_OPTION) {
					File selectedFile = jfc.getSelectedFile();
					System.out.println(selectedFile.getAbsolutePath());

				}


			}

		});

	}


	public void paint(Graphics g)
	{

		Image scaledImage = myImage.getScaledInstance(this.getWidth(),this.getHeight(),myImage.SCALE_SMOOTH);
		g.drawImage(scaledImage, 0, 0, null);
		int  x_temp_fruits = 0;
		int  y_temp_fruits =0 ;
		int  x_temp_Packmans = 0;
		int  y_temp_Packmans = 0;



		if (isGamer!=0)
		{
			for (int i=0; i<myGame.Fruits_arr.size(); i++) 
			{
				x_temp_fruits=(int)(myGame.Fruits_arr.get(i).getFruitPoint().x()*getWidth());
				y_temp_fruits=(int)(myGame.Fruits_arr.get(i).getFruitPoint().y()*getHeight());	


				g.setColor(Color.red);
				g.drawImage(Fruitimage, x_temp_fruits-5, y_temp_fruits-6,30, 30, null);


			}
			for (int j=0; j<myGame.Packman_arr.size(); j++) 
			{

				x_temp_Packmans=(int)(myGame.Packman_arr.get(j).getPoint().x()*getWidth());
				y_temp_Packmans=(int)(myGame.Packman_arr.get(j).getPoint().y()*getHeight());	

				g.drawImage(packimage, x_temp_Packmans-6, y_temp_Packmans-7,30, 30, null);



			}
		}
			if(Start_game==true) {

				ShortestPathAlgo algo = new ShortestPathAlgo(myGame);
				if(myGame.Packman_arr.size()== 1) {
					Path p = algo.algoSinglePackman();
					myGame.Fruits_arr = p.TheCurrentPath();
					g.setColor(Color.MAGENTA);
					g.drawLine((int)(myGame.Fruits_arr.get(0).getFruitPoint().x()*getWidth()),
							(int)(myGame.Fruits_arr.get(0).getFruitPoint().y()*getHeight()),
							(int)(myGame.Packman_arr.get(0).getPoint().x()*getWidth()),
							(int)(myGame.Packman_arr.get(0).getPoint().y()*getHeight()));

					for (int i = 1; i < myGame.Fruits_arr.size(); i++) {
						g.setColor(Color.MAGENTA);
						g.drawLine((int)(myGame.Fruits_arr.get(i).getFruitPoint().x()*getWidth()), 
								(int)(myGame.Fruits_arr.get(i).getFruitPoint().y()*getHeight()),
								(int)(myGame.Fruits_arr.get(i-1).getFruitPoint().x()*getWidth()), 
								(int)(myGame.Fruits_arr.get(i-1).getFruitPoint().y()*getHeight()));
					}
					System.out.println("the time is:" +algo.algoSinglePackman().getTheTime());



					x_temp_Packmans=(int)(myGame.Fruits_arr.
							get(myGame.Fruits_arr.size()-1).getFruitPoint().x()*getWidth());
					y_temp_Packmans=(int)(myGame.Fruits_arr.
							get(myGame.Fruits_arr.size()-1).getFruitPoint().y()*getHeight());	

					g.drawImage(packimage, x_temp_Packmans-6, y_temp_Packmans-7,30, 30, null);

				}
				if (myGame.Packman_arr.size()>1)
				{

					ArrayList<Packman> myPackmens = new ArrayList<>();
					myPackmens = algo.algoMultiPackmans();
					
					for (int i = 0; i < myPackmens.size(); i++) {
						
						System.out.println(myPackmens.get(i).getPath().TheCurrentPath().toString());
					}


					for (int i = 0; i <myPackmens.size(); i++) {
						if(myPackmens.get(i).getPath().TheCurrentPath().size() !=0 ) {
							g.setColor(Color.GREEN);
							g.drawLine((int)(myPackmens.get(i).getPath().TheCurrentPath().get(0).getFruitPoint().x()*getWidth()), 
									(int)(myPackmens.get(i).getPath().TheCurrentPath().get(0).getFruitPoint().y()*getHeight()),
									(int)(myPackmens.get(i).getPoint().x()*getWidth()),
									(int)(myPackmens.get(i).getPoint().y()*getHeight()));
						}
						for (int j = 1; j < myPackmens.get(i).getPath().TheCurrentPath().size(); j++) {

							g.setColor(Color.GREEN);
							g.drawLine((int)(myPackmens.get(i).getPath().TheCurrentPath().get(j).getFruitPoint().x()*getWidth()), 
									(int)(myPackmens.get(i).getPath().TheCurrentPath().get(j).getFruitPoint().y()*getHeight()),
									(int)(myPackmens.get(i).getPath().TheCurrentPath().get(j-1).getFruitPoint().x()*getWidth()), 
									(int)(myPackmens.get(i).getPath().TheCurrentPath().get(j-1).getFruitPoint().y()*getHeight()));

						}
					}

					System.out.println("the time is:" +algo.algoSinglePackman().getTheTime());




				}

			}
		}
		
	


			public void mouseClicked(MouseEvent arg) {

				double x_temp=arg.getX();
				x_temp=x_temp/getWidth();


				double y_temp=arg.getY();
				y_temp=y_temp/getHeight();
				Point3D point_return=new Point3D(x_temp, y_temp, 0);
				Point3D covertedfromPixel = theMap.Pixel2GPS(x_temp, y_temp);

				if (isGamer==(1))
				{	
					myGame.Fruits_arr.add(new Fruit(point_return,1));

					System.out.println("Fruit "+covertedfromPixel.toString());

					repaint();

				}else if (isGamer==(-1))
				{
					myGame.Packman_arr.add(new Packman(point_return, radius, speed));
					System.out.println("Packman "+covertedfromPixel.toString());

					repaint();
				}

			}



			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub		

			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}


		}
