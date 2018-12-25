package GUI;

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
		
		window.setSize(1000,500);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
}
