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
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import Coords.*;
import Geom.Point3D;



public class MyFameTEST extends JFrame implements MouseListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public BufferedImage myImage;
	public ArrayList<String>Fruits=new ArrayList<>();
	public ArrayList<String>Packmans=new ArrayList<>();
	public Point3D ans = new Point3D(0,0,0);
	public Map m = new Map();



	public MyFameTEST() 
	{
		initGUI();		
		this.addMouseListener(this); 
	}

	private void initGUI() {
		MenuBar menuBarOption = new MenuBar();

		Menu OptionMenu = new Menu("File"); 
		Menu AddMenu = new Menu("Add"); 
		
		menuBarOption.add(OptionMenu);
		menuBarOption.add(AddMenu);

		MenuItem runItem = new MenuItem("Run");
		MenuItem reload_item = new MenuItem("Reload");
		MenuItem save_item = new MenuItem("Save");	
		OptionMenu.add(runItem);
		OptionMenu.add(reload_item);
		OptionMenu.add(save_item);

		MenuItem Packman_Item = new MenuItem("Packman");
		MenuItem Furit_item = new MenuItem("Furit");

		AddMenu.add(Packman_Item);
		AddMenu.add(Furit_item);

		this.setMenuBar(menuBarOption);
		
		// menu item functions. //
		
		Packman_Item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mouseClickedOmer(this);
	
			}
		});

		
		try {
			myImage = ImageIO.read(new File("Ariel1.png"));
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

			for (int i=0; i<Fruits.size(); i++) 
			{
				String s1[]=Fruits.get(i).split(",");
				int x_temp=Integer.parseInt(s1[0])- (r / 2);;
				int y_temp=Integer.parseInt(s1[1])- (r / 2);;
				
				g.setColor(Color.cyan);
				g.fillOval(x_temp, y_temp, r, r);
			}
		}
	}


	@Override
	public void mouseClicked(MouseEvent arg) {

		x = arg.getX();
		y = arg.getY();
		
		//System.out.println(getWidth()+" ,"+getHeight());
		ans = m.Pixel2GPS(x, y, getWidth(), getHeight());
		System.out.println("("+ ans.x() + "," + ans.y() +")");
		//Fruits.add(arg.getX()+","+arg.getY());

		
		
		repaint();
	}
	public void mouseClickedOmer (ActionListener actionListener) {


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

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
