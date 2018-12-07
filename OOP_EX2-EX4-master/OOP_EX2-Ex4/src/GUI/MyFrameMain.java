package GUI;

import javax.swing.JFrame;


public class MyFrameMain 
{
	public static void main(String[] args)
	{
		MyFarme window = new MyFarme();
		//MyFameTEST window = new MyFameTEST();
		window.setVisible(true);
		
		window.setSize(window.myImage.getWidth(),window.myImage.getHeight());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
}
