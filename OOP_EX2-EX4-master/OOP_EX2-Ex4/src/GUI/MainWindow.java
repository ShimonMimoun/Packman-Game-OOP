package GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;


public class MainWindow extends JFrame implements MouseListener
{
	public BufferedImage myImage;
	public ArrayList<String>test=new ArrayList<>();

	public MainWindow() 
	{
		initGUI();		
		this.addMouseListener(this); 
	}

	private void initGUI() 
	{
		MenuBar menuBar = new MenuBar();
		Menu menu = new Menu("Menu"); 
		MenuItem item1 = new MenuItem("Refresh");
		MenuItem item2 = new MenuItem("Reload");

		menuBar.add(menu);
		menu.add(item1);
		menu.add(item2);

		this.setMenuBar(menuBar);

		try {
			myImage = ImageIO.read(new File("c://image.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

	int x = -1;
	int y = -1;

	public void paint(Graphics g)
	{

		Image scaledImage = myImage.getScaledInstance(this.getWidth(),this.getHeight(),myImage.SCALE_SMOOTH);
		g.drawImage(scaledImage, 0, 0, null);


		if(x!=-1 && y!=-1)
		{
			int r = 10;
			x = x - (r / 2);
			y = y - (r / 2);
			g.setColor(Color.red);
			g.fillOval(x, y, r, r);
			
			for (int i=0; i<test.size(); i++) 
			{
				String s1[]=test.get(i).split(",");
				int x_temp=Integer.parseInt(s1[0])- (r / 2);;
				int y_temp=Integer.parseInt(s1[1])- (r / 2);;
				g.setColor(Color.cyan);
				g.fillOval(x_temp, y_temp, r, r);
			}
		}
	}
	
		public void point()
		{

		}

		@Override
		public void mouseClicked(MouseEvent arg) {
			System.out.println("mouse Clicked");
			System.out.println(getWidth()+" ,"+getHeight());

			System.out.println("("+ arg.getX() + "," + arg.getY() +")");
			test.add(arg.getX()+","+arg.getY());
			
			x = arg.getX();
			y = arg.getY();
			repaint();
		}


		@Override
		public void mouseEntered(MouseEvent arg0) {
			System.out.println("mouse entered");

		}

		@Override
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

	}
