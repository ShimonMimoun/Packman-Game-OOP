package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JLabel;




import javax.swing.JMenuBar;
import javax.swing.JMenu;

import javax.swing.JMenuItem;

import com.sun.prism.Image;

import javax.swing.ImageIcon;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import javax.swing.ButtonGroup;


public class Check extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JLabel myMap;
	private final Action action = new SwingAction();
	private final ButtonGroup buttonGroup = new ButtonGroup();

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
		buttonGroup.add(mntmSabba);
		mnOmer.add(mntmSabba);
		
		JMenuItem SaveMenu = new JMenuItem("Save");
		mnOmer.add(SaveMenu);
		
		JMenu mnOmer_1 = new JMenu("omer");
		menuBar.add(mnOmer_1);
		
		JMenuItem mntmAaa = new JMenuItem("aaa");
		mnOmer_1.add(mntmAaa);
		
		JMenuBar menuBar_1 = new JMenuBar();
		menuBar.add(menuBar_1);
		getContentPane().setLayout(null);
		
		myMap = new JLabel("");
//		Image map = new ImageIcon(this.getClass().getResource("Ariel1.png")).getImage();
//		myMap.setIcon(new ImageIcon(map));
		myMap.setBounds(0, 0, 700, 307);
		getContentPane().add(myMap);
				
	}
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
}
	

