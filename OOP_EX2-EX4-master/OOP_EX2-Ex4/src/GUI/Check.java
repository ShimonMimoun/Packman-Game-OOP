package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JLabel;




import javax.swing.JMenuBar;
import javax.swing.JMenu;

import javax.swing.JMenuItem;



import javax.swing.ImageIcon;


public class Check extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JLabel myMap;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Check frame = new Check();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Check() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 722, 394);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnOmer = new JMenu("Menu");
		menuBar.add(mnOmer);
		
		JMenuItem RunMenu = new JMenuItem("Run");
		mnOmer.add(RunMenu);
		
		JMenuItem mntmSabba = new JMenuItem("Load");
		mnOmer.add(mntmSabba);
		
		JMenuItem SaveMenu = new JMenuItem("Save");
		mnOmer.add(SaveMenu);
		getContentPane().setLayout(null);
		
		myMap = new JLabel("");
		java.awt.Image map = new ImageIcon(this.getClass().getResource("Ariel1.png")).getImage();
		myMap.setIcon(new ImageIcon(map));
		myMap.setBounds(0, 0, 700, 307);
		getContentPane().add(myMap);
				
	}
}
	

