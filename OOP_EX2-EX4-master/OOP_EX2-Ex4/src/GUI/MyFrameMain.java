package GUI;

import java.awt.Toolkit;

import javax.swing.JFrame;



/**
 * Main function that launches my GUI program
 * @author Mimoun Shimon and Omer paz 
 */
public class MyFrameMain 
{
	public static void main(String[] args)
	{
		MyFarme window = new MyFarme();
		window.setVisible(true);
		
//		Toolkit tk=java.awt.Toolkit.getDefaultToolkit();
//		int xsize=(int)tk.getScreenSize().getWidth();
//		int ysize=(int)tk.getScreenSize().getHeight();
//		window.setSize(xsize+50,ysize);
//		
		
		window.setSize(300,300);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
}
