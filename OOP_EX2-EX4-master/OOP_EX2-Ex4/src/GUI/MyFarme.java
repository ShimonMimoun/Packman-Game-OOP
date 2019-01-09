package GUI;
import java.awt.Color;
import java.awt.Graphics;
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
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import Algorithm.AlgoTest;
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
	public boolean verif_game_player=false;
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
		Menu Add_import=new Menu ("Import");



		menuBarOption.add(OptionMenu);
		menuBarOption.add(AddMenu);
		menuBarOption.add(Add_import);


		MenuItem runItem_Manual = new MenuItem("Run Manual");
		MenuItem runItem_Auto= new MenuItem("Run Automatic");
		MenuItem reload_item = new MenuItem("Reload");
		MenuItem exit = new MenuItem("Exit");	
		MenuItem Player_User_item = new MenuItem("Player Manual");	
		MenuItem Csv_read = new MenuItem("Csv");


		OptionMenu.add(runItem_Manual);
		OptionMenu.add(runItem_Auto);
		OptionMenu.add(reload_item);
		OptionMenu.add(exit);

		AddMenu.add(Player_User_item);
		Add_import.add(Csv_read);



		this.setMenuBar(menuBarOption);


		//Turn on the buttons

		runItem_Manual.addActionListener(new ActionListener() {
			@Override

			public void actionPerformed(ActionEvent e) {

				playGame.setIDs(342685898, 311326490);
				if (verif_game_player==true) {
					if(myGame.Player_user != null) {

						playGame.getBoard();
						isGamer = 4;
						click = true;
						playGame.start();

						Thread thread = new Thread(){
							ArrayList<String> board_data = playGame.getBoard();


							public void run(){ 


								while(playGame.isRuning()){ 

									try {sleep(200);} catch (InterruptedException e) {	e.printStackTrace();	}

									playGame.rotate(dir);
									board_data = playGame.getBoard();
									String info = playGame.getStatistics();
									System.out.println(info);


									try {myGame.CreateGame(board_data);} catch (IOException e1) {e1.printStackTrace();}

									repaint();

								}
							}
						};

						thread.start();

					}

				}
				else 
					JOptionPane.showMessageDialog(null,"EROR: Enter Player to launch te Game ");

			}
		});






		runItem_Auto.addActionListener(new ActionListener() {
			@Override

			public void actionPerformed(ActionEvent e) {

				
				if (verif_game_player==true) {
					JOptionPane.showMessageDialog(null,"The player will be written to a new random point close to a Fruit ");
				}
				
				playGame.setIDs(342685898, 311326490);
				click = false;

				int ran = (int)(Math.random()*myGame.Fruits_arr.size());
				
				System.out.println(myGame.Fruits_arr.get(ran).getFruitPoint().x());
				Point3D temp_point_locat=theMap.Pixel2GPS(myGame.Fruits_arr.get(ran).getFruitPoint().x(), myGame.Fruits_arr.get(ran).getFruitPoint().y());
					
				playGame.setInitLocation(temp_point_locat.x(),temp_point_locat.y());

				if(myGame.Player_user != null) {
					playGame.getBoard();
					isGamer = 4;

					playGame.start();

					Thread thread = new Thread(){
						ArrayList<String> board_data = playGame.getBoard();


						public void run(){ 




							while(playGame.isRuning()){ 

								try {sleep(200);} catch (InterruptedException e) {e.printStackTrace();}

								playGame.rotate(dir);
								board_data = playGame.getBoard();
								String info = playGame.getStatistics();
								System.out.println(info);

								try {	Game_temp_run.CreateGame(board_data);
								Point3D covertedfromPixel2 = theMap.Pixel2GPS(myGame.Player_user.getPoint_player().x(), myGame.Player_user.getPoint_player().y());
								Point3D covertedfromPixel3 = theMap.Pixel2GPS(Game_temp_run.Player_user.getPoint_player().x(), Game_temp_run.Player_user.getPoint_player().y());

								AlgoTest algo = new AlgoTest(Game_temp_run);

								Game_temp_run.Player_user.setPoint_player(covertedfromPixel3);
								myGame.Player_user.setPoint_player(covertedfromPixel2);



								double theDir = algo.update_Game(Game_temp_run.Player_user , dir);

								dir = theDir;

								} catch (IOException e) {	e.printStackTrace();	}


								try {myGame.CreateGame(board_data);} catch (IOException e1) {e1.printStackTrace();}
								repaint();						
							}
						}
					};
					thread.start();
				}
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
				ArrayTemp=new ArrayList<>();
				verif_game_player=false;
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


		Player_User_item.addActionListener  (new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				isGamer = 2;

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



	}
	public void update(Graphics g){

		paint(g);
	}
	public void paint(Graphics g) {

		if(dbg==null){
			image = createImage(5000,5000);
			dbg = image.getGraphics();

		}


		dbg.drawImage(myImage, 8,50, this.getWidth()-17, this.getHeight()-60,null);


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
			// probleme Player icon 
			if(myGame.Player_user!=null){
				x1=(myGame.Player_user.getPoint_player().x()*getWidth());
				y1=(myGame.Player_user.getPoint_player().y()*getHeight());	

				//dbg.drawImage(player,(int)x1,(int) y1,30, 30,null);
				dbg.setColor(Color.cyan);
				dbg.fillOval((int)x1,(int) y1, 10, 10);
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
			double finalnum = m.myDir(covertedfromPixel,playerConert);
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
