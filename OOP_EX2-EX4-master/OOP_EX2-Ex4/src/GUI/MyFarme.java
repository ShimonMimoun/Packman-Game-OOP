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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;
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

import Coords.Map;
import Coords.MyCoords;
import GIS.Fruit;
import GIS.Game;
import GIS.Ghost;
import GIS.Packman;
import GIS.Path;
import GIS.Player;
import Geom.Box;
import Geom.Point3D;
import Robot.Play;

/**
 * This Class manages the graphical representation of the entire program.
 * the class is an implements of MouseListener is an extents of JFrame.
 * More: http://www.ntu.edu.sg/home/ehchua/programming/java/j4a_gui.html
 * @author Mimoun Shimon and Omer Paz
 *
 */
public class MyFarme extends JFrame implements MouseListener , KeyListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private boolean click = false;
	Image image;
	public boolean Start_game=false;
	public boolean verif_game_player=false;
	public boolean drwaline = false;
	private double dir;
	boolean solo_Play=false;
	private int isGamer=0;



	public  Graphics dbg;
	public BufferedImage myImage;
	public BufferedImage packimage;
	public BufferedImage Fruitimage;
	public BufferedImage ghost;
	public BufferedImage box;
	public BufferedImage player;



	double radius = 1;
	int speed = 1;

	public  ArrayList<Packman> Packman_arr = new ArrayList<>();
	public  ArrayList<Fruit> Fruits_arr = new ArrayList<>();
	public  ArrayList<Box> Boxs_arr = new ArrayList<>();
	public  ArrayList<Ghost> Ghost_arr = new ArrayList<>();
	public ArrayList<Packman> ArrayTemp=new ArrayList<>();
	private Game myGame=new Game(Packman_arr, Fruits_arr,Boxs_arr,Ghost_arr);
	public Map theMap = new Map();
	Path TheCloserPackman;
	public Play playGame;
	private MyCoords m = new MyCoords();


	MenuBar menuBarOption = new MenuBar();






	Game Game_temp_run=new Game(Packman_arr, Fruits_arr,Boxs_arr,Ghost_arr);

	public MyFarme() 
	{
		initGUI();		
		this.addMouseListener(this); 
		this.addKeyListener(this);

	}

	private void initGUI() {


		try {	myImage = ImageIO.read(new File(theMap.getMapDiractory())); } catch (IOException e) { e.printStackTrace();	}	
		try {	packimage = ImageIO.read(new File("Pictures&Icones/packnew.png")); } catch (IOException e) { e.printStackTrace();	}
		try {	Fruitimage = ImageIO.read(new File("Pictures&Icones/fruit.png")); } catch (IOException e) { e.printStackTrace();	}
		try {	box = ImageIO.read(new File("Pictures&Icones/box.png")); } catch (IOException e) { e.printStackTrace();	}
		try {	ghost = ImageIO.read(new File("Pictures&Icones/ghost.png")); } catch (IOException e) { e.printStackTrace();	}
		try {	player = ImageIO.read(new File("Pictures&Icones/Player.png")); } catch (IOException e) { e.printStackTrace();	}





		Menu OptionMenu = new Menu("File"); 
		Menu AddMenu = new Menu("Add"); 
		Menu AddLimite = new Menu("Limit"); 
		Menu SetMenu = new Menu("Set"); 
		Menu Add_import=new Menu ("Import");
		Menu Add_export=new Menu ("Export");



		menuBarOption.add(OptionMenu);
		menuBarOption.add(AddMenu);
		menuBarOption.add(AddLimite);
		menuBarOption.add(SetMenu);
		menuBarOption.add(Add_import);
		menuBarOption.add(Add_export);


		MenuItem runItem = new MenuItem("Run");
		MenuItem reload_item = new MenuItem("Reload");
		MenuItem exit = new MenuItem("Exit");

		MenuItem Packman_Item = new MenuItem("Packman");
		MenuItem Fruit_item = new MenuItem("Fruit");	
		MenuItem Player_User_item = new MenuItem("Player_User");	
		MenuItem Ghost_item = new MenuItem("Ghost");

		MenuItem Box_item = new MenuItem("Box");

		MenuItem setraduisAll = new MenuItem("Radius All");
		MenuItem setradius2Pack = new MenuItem("Radius To Packman");
		MenuItem setSpeedAll = new MenuItem("Speed All");
		MenuItem setSpeed2Pack = new MenuItem("Speed To Packman");
		MenuItem setWeightAll= new MenuItem("Weight All");
		MenuItem setWeight2Friut= new MenuItem("Weight to Friut");


		MenuItem Csv_read = new MenuItem("Csv");

		MenuItem Csv_writing_csv = new MenuItem(" Csv ");
		MenuItem Csv_writing_kml = new MenuItem(" Kml ");


		OptionMenu.add(runItem);
		OptionMenu.add(reload_item);
		OptionMenu.add(exit);

		AddMenu.add(Player_User_item);
		AddMenu.add(Packman_Item);
		AddMenu.add(Fruit_item);
		AddMenu.add(Ghost_item);

		AddLimite.add(Box_item);	

		SetMenu.add(setraduisAll);
		SetMenu.add(setradius2Pack);
		SetMenu.add(setSpeedAll);
		SetMenu.add(setSpeed2Pack);
		SetMenu.add(setWeightAll);
		SetMenu.add(setWeight2Friut);


		Add_import.add(Csv_read);



		this.setMenuBar(menuBarOption);


		//Turn on the buttons

		runItem.addActionListener(new ActionListener() {
			@Override

			public void actionPerformed(ActionEvent e) {

				if (verif_game_player==true) {
					if(myGame.Player_user != null) {


						Start_game=true;
						playGame.getBoard();
						isGamer = 4;
						click = true;
						playGame.start();




						Thread thread = new Thread(){
							ArrayList<String> board_data = playGame.getBoard();

							public void run(){ 
								int p=0;
								while(playGame.isRuning()){ 
									p++;
									try {
										sleep(200);
									} catch (InterruptedException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}

									playGame.rotate(dir);
									board_data = playGame.getBoard();
									//									String info = playGame.getStatistics();
									//									System.out.println(info);


									
									/// ************************************************************************************************/
									// ************************************************************************************************/
									// ************************************************************************************************/
									// ************************************************************************************************/
									try {	Game_temp_run.CreateGame(board_data);

									Point3D covertedfromPixel2 = theMap.Pixel2GPS(myGame.Player_user.getPoint_player().x()*getWidth(), myGame.Player_user.getPoint_player().y()*getHeight());
									Point3D covertedfromPixel3 = theMap.Pixel2GPS(Game_temp_run.Player_user.getPoint_player().x()*getWidth(), Game_temp_run.Player_user.getPoint_player().y()*getHeight());
									
									playGame.setInitLocation(covertedfromPixel2.x(), covertedfromPixel2.y());
									
									System.out.println("Point old : " +covertedfromPixel2.toString());
									System.out.println("Point new : " +covertedfromPixel3.toString()+"\n\n\n");
									
									
									
									} catch (IOException e) {	e.printStackTrace();	}
									
									/// ************************************************************************************************/
									// ************************************************************************************************/
									// ************************************************************************************************/
									// ************************************************************************************************/
									
									
									
									
									
									try {myGame.CreateGame(board_data);} catch (IOException e1) {e1.printStackTrace();}
									repaint();						
								}
								String info = playGame.getStatistics();
								System.out.println(info);

							}
						};
						thread.start();

					}
					Start_game = false;
				}
				else 
					JOptionPane.showMessageDialog(null,"EROR: Enter Player to launch te Game ");

			}
		});

		reload_item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				solo_Play=false;
				radius = 1;
				speed = 1;
				new MenuBar();
				new Map();
				Packman_arr = new ArrayList<>();
				Fruits_arr = new ArrayList<>();
				Boxs_arr = new ArrayList<>();
				Ghost_arr = new ArrayList<>();
				myGame=new Game(Packman_arr, Fruits_arr,Boxs_arr,Ghost_arr);
				isGamer=0;
				Start_game=false;
				drwaline = false;
				ArrayTemp=new ArrayList<>();
				TheCloserPackman=null;
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



		Ghost_item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				isGamer = 3;
			}
		});



		Box_item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				createdbox();
				repaint();

			}
		});


		Packman_Item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				isGamer = -1;

			}
		});

		Player_User_item.addActionListener  (new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				isGamer = 2;





			}
		});

		//
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
					System.out.println(fileChooser.getSelectedFile().getPath())	;

					playGame = new Play(fileChooser.getSelectedFile().getPath());

					try {
						myGame = new Game(playGame);
						solo_Play=true;
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					isGamer = 4;
					myGame.setfile_directory(fileChooser.getSelectedFile().getPath());
					repaint();

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




					try {
						myGame.save_to_kml();
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}


				}


			}

		});

	}
	public void update(Graphics g){

		paint(g);
	}
	public void paint(Graphics g) {

		if(dbg==null){
			image = createImage(5000,5000);
			dbg = image.getGraphics();

		}


		//Image scaledImage = myImage.getScaledInstance(this.getWidth(),this.getHeight(),myImage.SCALE_SMOOTH);
		dbg.drawImage(myImage, 8,50, this.getWidth()-17, this.getHeight()-60,null);

		//
		//		Graphics2D g2 = (Graphics2D)dbg;
		//
		//		g2.setStroke(new BasicStroke(3));

		double x1 = 0;
		double y1 = 0 ;
		double x2 = 0;
		double y2 = 0 ;


		if (isGamer!=0) {

			if(myGame.Fruits_arr.size() > 0) {

				for (int j=0; j<myGame.Boxs_arr.size(); j++) {

					x1=(myGame.Boxs_arr.get(j).getP1().x()*getWidth());
					y1=(myGame.Boxs_arr.get(j).getP1().y()*getHeight());
					x2=(myGame.Boxs_arr.get(j).getP2().x()*getWidth());
					y2=(myGame.Boxs_arr.get(j).getP2().y()*getHeight());	
					double width = x2-x1;
					double height = y2-y1;
					dbg.drawImage(box, (int)x1,(int) y1,(int)width, (int)height, null);

				}
				for (int i=0; i<myGame.Fruits_arr.size(); i++) 	{
					x1=(int)(myGame.Fruits_arr.get(i).getFruitPoint().x()*getWidth());
					y1=(int)(myGame.Fruits_arr.get(i).getFruitPoint().y()*getHeight());	

					dbg.drawImage(Fruitimage, (int)x1, (int)y1,20, 20, null);
				}

			}

			for (int j=0; j<myGame.Packman_arr.size(); j++) {

				x1=(myGame.Packman_arr.get(j).getPoint().x()*getWidth());
				y1=(myGame.Packman_arr.get(j).getPoint().y()*getHeight());	


				dbg.drawImage(packimage, (int)x1,(int) y1,20, 20, null);

			}

			for (int j=0; j<myGame.Ghost_arr.size(); j++) {
				x1=(myGame.Ghost_arr.get(j).getPoint().x()*getWidth());
				y1=(myGame.Ghost_arr.get(j).getPoint().y()*getHeight());	

				dbg.drawImage(ghost, (int)x1,(int) y1,20, 20, null);

			}

			if(myGame.Player_user!=null){
				x1=(myGame.Player_user.getPoint_player().x()*getWidth());
				y1=(myGame.Player_user.getPoint_player().y()*getHeight());	

				dbg.drawImage(player,(int)x1,(int) y1,30, 30,null);
			}
		}

		g.drawImage(image, 0, 0, this);

	}





	@Override
	public void mouseClicked(MouseEvent arg) {

		double x_temp=arg.getX();
		x_temp=x_temp/getWidth();

		double y_temp=arg.getY();
		y_temp=y_temp/getHeight();
		Point3D point_return=new Point3D(x_temp, y_temp, 0);
		Point3D covertedfromPixel = theMap.Pixel2GPS(x_temp, y_temp);


		if(click == true) {
			Point3D playerConert = theMap.Pixel2GPS(myGame.Player_user.getPoint_player().x(), myGame.Player_user.getPoint_player().y());
			double finalnum = m.azimuth(covertedfromPixel,playerConert);
			System.out.println(finalnum);
			dir = finalnum;
			playGame.rotate(dir);
		}


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
		}else if(isGamer==3)
		{
			myGame.Ghost_arr.add(new Ghost(point_return, radius, speed));
			System.out.println("Ghost "+covertedfromPixel.toString());
			repaint();
		}else if(isGamer==2)
		{
			myGame.Player_user=new Player(point_return, speed,radius);

			System.out.println("Player "+covertedfromPixel.toString());
			if(solo_Play==true)
			{playGame.setInitLocation(covertedfromPixel.x(), covertedfromPixel.y());
			verif_game_player=true;
			repaint();
			}
		}

	}

	public void createdbox() {
		double X1=0;
		double X2=0;
		double Y1=0;
		double Y2=0;


		do {
			String	x1=JOptionPane.showInputDialog("Enter X1 Pixel");
			X1=Double.parseDouble(x1);
		} while ((X1<0) ||(X1>this.getWidth()));

		do {
			String	y1=JOptionPane.showInputDialog("Enter Y1 Pixel");
			Y1=Double.parseDouble(y1);
		} while ((Y1<0) ||(Y1>this.getHeight()));

		do {
			String	x2=JOptionPane.showInputDialog("Enter X2 Pixel");
			X2=Double.parseDouble(x2);
		} while ((X2<0) ||(X2>this.getWidth())||(X2<X1));

		do {
			String	y2=JOptionPane.showInputDialog("Enter Y2 Pixel");	
			Y2=Double.parseDouble(y2);
		} while ((Y2<0) ||(Y2>this.getWidth())||(Y2<Y1));

		Boxs_arr.add(new Box(new Point3D(X1/getWidth(), Y1/getHeight()),
				new Point3D(X2/getWidth(), Y2/getHeight())));

	}






	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}



}
