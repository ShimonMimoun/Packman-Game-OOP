package GUI;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;


import Algorithm.ShortestPathAlgo;
import Coords.Map;
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

	MenuBar menuBarOption = new MenuBar();
	public Map theMap = new Map();

	public BufferedImage myImage;
	public BufferedImage packimage;
	public BufferedImage Fruitimage;
	public BufferedImage DEED_Fruit;
	public BufferedImage DEED_Pack;

	double radius = 1;
	int speed = 1;

	public  ArrayList<Packman> Packman_arr = new ArrayList<>();
	public  ArrayList<Fruit> Fruits_arr = new ArrayList<>();
	private Game myGame=new Game(Packman_arr, Fruits_arr);
	private int isGamer=0;// if is Gamer==1 --> Fruit :::: if is Gamer == -1 --> Packman 
	private boolean Start_game=false;

	Path TheCloserPackman;


	public MyFarme() 
	{
		initGUI();		
		this.addMouseListener(this); 

	}

	private void initGUI() {


		try {	myImage = ImageIO.read(new File(theMap.getMapDiractory())); } catch (IOException e) { e.printStackTrace();	}	
		try {	packimage = ImageIO.read(new File("Pictures&Icones/packman.jpg")); } catch (IOException e) { e.printStackTrace();	}
		try {	Fruitimage = ImageIO.read(new File("Pictures&Icones/furit.png")); } catch (IOException e) { e.printStackTrace();	}
		try {	DEED_Fruit = ImageIO.read(new File("Pictures&Icones/DEED_Fruit.png")); } catch (IOException e) { e.printStackTrace();	}
		try {	DEED_Pack = ImageIO.read(new File("Pictures&Icones/DEED_Pack.png")); } catch (IOException e) { e.printStackTrace();	}




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
		AddMenu.add(Packman_Item);
		AddMenu.add(Fruit_item);

		Menu SetMenu = new Menu("Set"); 

		MenuItem setraduisAll = new MenuItem("Radius All");
		MenuItem setradius2Pack = new MenuItem("Radius To Packman");
		MenuItem setSpeedAll = new MenuItem("Speed All");
		MenuItem setSpeed2Pack = new MenuItem("Speed To Packman");
		MenuItem setWeightAll= new MenuItem("Weight All");
		MenuItem setWeight2Friut= new MenuItem("Weight to Friut");


		SetMenu.add(setraduisAll);
		SetMenu.add(setradius2Pack);
		SetMenu.add(setSpeedAll);
		SetMenu.add(setSpeed2Pack);
		SetMenu.add(setWeightAll);
		SetMenu.add(setWeight2Friut);


		menuBarOption.add(SetMenu);


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
				if(myGame.Packman_arr.size() >0 && myGame.Fruits_arr.size() > 0 ) {
					Start_game=true;

					double	x1=(int)(myGame.Packman_arr.get(0).getPoint().x()*getWidth());
					double y1=(int)(myGame.Packman_arr.get(0).getPoint().y()*getHeight());	
					isGamer=2;

					for (double i = 0; i < 0.0005; i=i+0.00001) {

						myGame.Packman_arr.get(0).setPackLocation(new Point3D((x1/getWidth())+i,(y1/getWidth())+i));


						repaint();

					}

				}
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

		setraduisAll.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (myGame.Packman_arr.size() > 0) {
					String radius= JOptionPane.showInputDialog("Please input the Radius for all the Packmans: ");
					double input_radius = Double.parseDouble(radius);
					for (int i = 0; i < myGame.Packman_arr.size(); i++) {
						myGame.Packman_arr.get(i).setRadius(input_radius);
					}
				} else {
					JOptionPane.showMessageDialog(null,"EROR: There is no Packmans in the Game");

				}

			}
		});
		setradius2Pack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(myGame.Packman_arr.size() > 0) {
					String i = JOptionPane.showInputDialog("Please input which packman you want to change (starting from 0)");
					int numberPack = Integer.parseInt(i);
					if(numberPack > myGame.Packman_arr.size()) {
						JOptionPane.showMessageDialog(null,"EROR: Cant find this Packman");

					}else {
						String radius= JOptionPane.showInputDialog("Please input the Radius for this Packman: ");
						double input_radius = Double.parseDouble(radius);
						myGame.Packman_arr.get(numberPack).setRadius(input_radius);	
					}
				}else {
					JOptionPane.showMessageDialog(null,"EROR: There is no Packmans in the Game");

				}
			}
		});


		setSpeed2Pack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(myGame.Packman_arr.size() > 0) {
					String i = JOptionPane.showInputDialog("Please input which packman you want to change (starting from 0)");
					int numberPack = Integer.parseInt(i);
					if(numberPack > myGame.Packman_arr.size()) {
						JOptionPane.showMessageDialog(null,"EROR: Cant find this Packman");

					}else {
						String Speed= JOptionPane.showInputDialog("Please input the Speed for this Packman: ");
						double input_Speed = Double.parseDouble(Speed);
						myGame.Packman_arr.get(numberPack).setSpeed(input_Speed);	
					}
				}else {
					JOptionPane.showMessageDialog(null,"EROR: There is no Packmans in the Game");

				}
			}
		});
		setSpeedAll.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (myGame.Packman_arr.size() > 0) {
					String speed= JOptionPane.showInputDialog("Please input the Speed for all the Packmans: ");
					double input_speed = Double.parseDouble(speed);
					for (int i = 0; i < myGame.Packman_arr.size(); i++) {
						myGame.Packman_arr.get(i).setSpeed(input_speed);
					}
				} else {
					JOptionPane.showMessageDialog(null,"EROR: There is no Packmans in the Game");

				}

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
						myGame.file_directory = theGame.file_directory;
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


					myGame.setfile_directory(selectedFile.getAbsolutePath());
					
					Path path = new Path();
					
					double theTime = path.CalTime2Points(myGame.Packman_arr.get(0), myGame.Fruits_arr.get(0)); 
					myGame.Packman_arr.get(0).getPath().setTheTotalTime(theTime);
					for (int i = 0; i < myGame.Fruits_arr.size(); i++) {
						
						for (int j = 0; j < theTime; j++) {
						Point3D ans =path.theNextPoint(myGame.Packman_arr.get(0),myGame.Fruits_arr.get(i) , 10);
						myGame.Packman_arr.get(0).setPackLocation(ans);
							System.out.println(ans.toString());
						}
						
					}
					
					
					try {
						myGame.save_to_kml();
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}


				}


			}

		});

	}


	public void paint(Graphics g) {

		Image scaledImage = myImage.getScaledInstance(this.getWidth(),this.getHeight(),myImage.SCALE_SMOOTH);
		g.drawImage(scaledImage, 8,50, getWidth()-17, getHeight()-60,null);

		double x1 = 0;
		double y1 = 0 ;

		if (isGamer!=0) {
			for (int i=0; i<myGame.Fruits_arr.size(); i++) 
			{
				x1=(int)(myGame.Fruits_arr.get(i).getFruitPoint().x()*getWidth());
				y1=(int)(myGame.Fruits_arr.get(i).getFruitPoint().y()*getHeight());	

				g.drawImage(Fruitimage, (int)x1-5, (int)y1-6,30, 30, null);

			}
		}
		for (int j=0; j<myGame.Packman_arr.size(); j++) 
		{


			x1=(myGame.Packman_arr.get(j).getPoint().x()*getWidth());
			y1=(myGame.Packman_arr.get(j).getPoint().y()*getHeight());	

			g.drawImage(packimage, (int)x1-6,(int) y1-7,30, 30, null);

			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
	//
	//
	//
	//		if(Start_game==true) {
	//
	//			Graphics2D g2 = (Graphics2D)g;
	//
	//			g2.setStroke(new BasicStroke(3));
	//
	//			ShortestPathAlgo algo = new ShortestPathAlgo(myGame);
	//
	//
	//			if(myGame.Packman_arr.size()== 1) {
	//
	//				Path p = algo.algoSinglePackman(myGame.Packman_arr.get(0));
	//				myGame.Fruits_arr = p.TheCurrentPath();
	//
	//				Thread thread = new Thread("New Thread") {
	//					public void run(){
	//
	//						double Timer = p.CalTime2Points(myGame.Packman_arr.get(0), myGame.Fruits_arr.get(0));
	//						
	//						try {
	//							sleep((long) (Timer*10));	
	//						} catch (InterruptedException e) {
	//							// TODO Auto-generated catch block
	//							e.printStackTrace();
	//						}
	//						g.setColor(Color.orange);
	//
	//						double x1 =  myGame.Fruits_arr.get(0).getFruitPoint().x();
	//						double y1 =  myGame.Fruits_arr.get(0).getFruitPoint().y();
	//						double x2 =  myGame.Packman_arr.get(0).getPoint().x();
	//						double y2 =  myGame.Packman_arr.get(0).getPoint().y();
	//
	//
	//						g.drawLine((int)(x1*getWidth()),(int)(y1*getHeight()),(int)(x2*getWidth()),(int)(y2*getHeight()));
	//						x1=(int)(myGame.Fruits_arr.get(0).getFruitPoint().x()*getWidth());
	//						y1=(int)(myGame.Fruits_arr.get(0).getFruitPoint().y()*getHeight());	
	//
	//						g.drawImage(DEED_Fruit, (int)x1-5, (int)y1-6,30, 30, null);
	//
	//						Packman StarterPack = new Packman(myGame.Packman_arr.get(0));
	//
	//						myGame.Packman_arr.get(0).setPackLocation(myGame.Fruits_arr.get(0).getFruitPoint());
	//
	//
	//						for (int i = 1; i < myGame.Fruits_arr.size(); i++) 	{		
	//
	//							Timer = p.CalTime2Points(myGame.Packman_arr.get(0), myGame.Fruits_arr.get(i));
	//							try {
	//								sleep((long) (10*Timer));
	//							} catch (InterruptedException e) {
	//								// TODO Auto-generated catch block
	//								e.printStackTrace();
	//							}
	//
	//							x1 =  myGame.Fruits_arr.get(i).getFruitPoint().x();
	//							y1 =  myGame.Fruits_arr.get(i).getFruitPoint().y();
	//							x2 =  myGame.Packman_arr.get(0).getPoint().x();
	//							y2 =  myGame.Packman_arr.get(0).getPoint().y();
	//
	//							g.setColor(Color.orange);
	//							g.drawLine((int)(x1*getWidth()), (int)(y1*getHeight()),(int)(x2*getWidth()), (int)(y2*getHeight()));
	//
	//
	//							x1=(int)(myGame.Fruits_arr.get(i).getFruitPoint().x()*getWidth());
	//							y1=(int)(myGame.Fruits_arr.get(i).getFruitPoint().y()*getHeight());	
	//
	//							g.drawImage(DEED_Fruit, (int)x1-5, (int)y1-6,30, 30, null);
	//
	//							myGame.Packman_arr.get(0).setPackLocation(myGame.Fruits_arr.get(i).getFruitPoint());
	//
	//						}
	//						System.out.println("The Total Time is:"+p.getTheTime());
	//						// Deed PAckman Frist
	//						x1=(int)(StarterPack.getPoint().x()*getWidth());
	//						y1=(int)(StarterPack.getPoint().y()*getHeight());
	//						g.drawImage(DEED_Pack, (int)x1-6, (int)y1-7,35, 35, null);
	//
	//
	//						// Replace a  New PAckman in the arrival 
	//						x1=(int)(myGame.Packman_arr.get(0).getPoint().x()*getWidth());
	//						y1=(int)(myGame.Packman_arr.get(0).getPoint().y()*getHeight());	
	//
	//						g.drawImage(packimage,(int) x1-6,(int) y1-7,30, 30, null);
	//
	//					}
	//				};
	//
	//				thread.run();
	//
	//			}
	//
	//
	//			if (myGame.Packman_arr.size()>1)
	//			{
	//
	//				ArrayList<Packman> myPackmens = new ArrayList<>();
	//				myPackmens = algo.algoMultiPackmans();
	//
	//
	//
	//				for (int i = 0; i <myPackmens.size(); i++) {
	//					if(!myPackmens.get(i).getPath().TheCurrentPath().isEmpty() ) {
	//						g.setColor(Color.GREEN);
	//
	//
	//						g.drawLine((int)(myPackmens.get(i).getPath().TheCurrentPath().get(0).getFruitPoint().x()*getWidth()), 
	//								(int)(myPackmens.get(i).getPath().TheCurrentPath().get(0).getFruitPoint().y()*getHeight()),
	//								(int)(myPackmens.get(i).getPoint().x()*getWidth()),
	//								(int)(myPackmens.get(i).getPoint().y()*getHeight()));
	//
	//					}
	//					for (int j = 1; j < myPackmens.get(i).getPath().TheCurrentPath().size(); j++) {
	//
	//						g.setColor(Color.GREEN);
	//						g.drawLine((int)(myPackmens.get(i).getPath().TheCurrentPath().get(j).getFruitPoint().x()*getWidth()), 
	//								(int)(myPackmens.get(i).getPath().TheCurrentPath().get(j).getFruitPoint().y()*getHeight()),
	//								(int)(myPackmens.get(i).getPath().TheCurrentPath().get(j-1).getFruitPoint().x()*getWidth()), 
	//								(int)(myPackmens.get(i).getPath().TheCurrentPath().get(j-1).getFruitPoint().y()*getHeight()));
	//
	//
	//
	//					}
	//
	//				}
	//			}
	//
	//
	//
	//
	//		}




	public void mouseClicked(MouseEvent arg) {

		double x_temp=arg.getX();
		x_temp=x_temp/getWidth();


		double y_temp=arg.getY();
		y_temp=y_temp/getHeight();
		Point3D point_return=new Point3D(x_temp, y_temp, 0);
		Point3D covertedfromPixel = theMap.Pixel2GPS(x_temp, y_temp);
		System.out.println(x_temp+" , "+y_temp);


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
