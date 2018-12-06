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

import Coords.Map;
import Geom.Point3D;


public class MyFarme extends JFrame implements MouseListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Map theMap = new Map();
	public BufferedImage myImage;
	public ArrayList<String>Fruits=new ArrayList<>();
	public ArrayList<String>Packmans=new ArrayList<>();
	private int x = -1;
	private int y = -1;
	




	public MyFarme() 
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


	public void paint(Graphics g)
	{

		Image scaledImage = myImage.getScaledInstance(this.getWidth(),this.getHeight(),myImage.SCALE_SMOOTH);
		g.drawImage(scaledImage, 0, 0, null);

		if(x!=-1 && y!=-1)
		{

			for (int i=0; i<Fruits.size(); i++) 
			{
				int r = 10;
				String s1[]=Fruits.get(i).split(",");
				int x_temp=(int)((Double.parseDouble(s1[0])*getWidth()));
				int y_temp=(int)((Double.parseDouble(s1[1])*getHeight()));	
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

		double x_temp=arg.getX();
		x_temp=x_temp/getWidth();


		double y_temp=arg.getY();
		y_temp=y_temp/getHeight();
		
		
		Point3D p = new Point3D(32.10573800004135,35.20227899996007,0);
		Point3D ans;
		
		ans = theMap.GPS2Pixel(p, getWidth(), getHeight());
		
		
		System.out.println(ans.x()+","+ans.y());
		Fruits.add(x_temp+","+y_temp);

		x = arg.getX();
		y = arg.getY();
		repaint();
	}

	public void mouseClickedOmer (ActionListener actionListener) {
		System.out.println("test");
		repaint();
	}	@Override
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
