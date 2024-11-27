/*
Author: Steven Bates
Date: 5/4/19
Notes: Sprite Game Team Project - Team Members: William Mortensen & Steven Bates, Game Name: Sky League
 */
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class StartScreen extends JPanel{
	
	
	private ImageIcon image;
	private javax.swing.Timer timer;
	private boolean sel = false;
	private Image pic1, pic2, selectedPic;
	private boolean egg = false;
	

	public StartScreen() {
		
		image = new ImageIcon("images/SkyBack.jpg");
		addMouseListener(new PanelListener());
		addMouseMotionListener(new PanelMotionListener());
		timer = new javax.swing.Timer(40, new MoveListener());
		timer.start();
		
		pic1 = new ImageIcon("images/Start.png").getImage(); 
		pic2 = new ImageIcon("images/2019.png").getImage();
		
		
		
	}
	
	public void startScreen(StartScreen s){
		boolean run = false;
		System.out.println(egg);
		JFrame theGUI = new JFrame();
		theGUI.setTitle("SkyLeague");
		theGUI.setSize(1920, 1080);
		theGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		theGUI.setUndecorated(false);
		Container pane = theGUI.getContentPane();
		theGUI.setVisible(true);
		pane.add(s);
		theGUI.setFocusable(true);
		while(!run){
			if (s.getSel()) {
				theGUI.remove(theGUI);
				theGUI.dispose();
				run = true;

			}
		}
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Font f1 = new Font("CopperplateTBol", Font.BOLD, 80);
		g.setFont(f1);
		g.setColor(Color.red);
		
		image.paintIcon(this, g, 0, 0);
		
		g.drawString("Sky League", 700, 120);
		
		Font f2 = new Font("Comic Sans MS", Font.BOLD, 60);
		g.setFont(f2);
		g.setColor(Color.blue);
		
		g.drawImage(pic1, 850, 600, 230, 200, null);
		g.drawImage(pic2, 100, 20, 200, 150, null);
	}
	
	public boolean getSel(){
		//System.out.println("");
		return sel;
	}
	
	public Image getImage(){
		return selectedPic;
	}
	
	
	private class MoveListener implements ActionListener {  //timer
		
		public void actionPerformed(ActionEvent e) {
			//System.out.println("timer");
			
			repaint();
		}
	}
	
	
	public class PanelMotionListener extends MouseMotionAdapter {
		
		public void mouseMoved(MouseEvent e) {
			
			
		}
	}
	

	public class PanelListener implements MouseListener{

		public void mouseClicked(MouseEvent e) {
			System.out.println("clicked");
			
			
			// make rectangles of the pics to check mouse contains point
			Rectangle pic1Rect, pic2Rect;
			pic1Rect = new Rectangle(850, 600, 230, 200);
			pic2Rect = new Rectangle(100, 20, 200, 150);
			
			int x = e.getX();
			int y = e.getY();
			
			// every click see if the mouse is in the pics area. If so, it is selected
			if (pic1Rect.contains(x, y)) {
				selectedPic = pic1;
				sel = true;
			}
			else if (pic2Rect.contains(x, y)) {
				String input = "";
				input = JOptionPane.showInputDialog(null, "Enter a code: ");
				if (input.equals("2019")) {
					egg = true;
					JOptionPane.showMessageDialog(null, "Code Applied");
				}
				else{
					JOptionPane.showMessageDialog(null, "Invalid Code");
				}
			}
		
			
		}

		public void mouseEntered(MouseEvent e) {
		
		}

		public void mouseExited(MouseEvent e) {
		
		}

		public void mousePressed(MouseEvent e) {
		
		}

		public void mouseReleased(MouseEvent e) {
		
		}


	} // closes PanelListener

	
	public class PanelKeyListener implements KeyListener{

		public void keyPressed(KeyEvent e){
			System.out.println("Pressed");
			int keyCode = e.getKeyCode();
			if(keyCode == KeyEvent.VK_ESCAPE){
				System.exit(0);
			}
			else{
				e.consume();
			}

			
			if (keyCode == KeyEvent.VK_F1){
				String input = "";
				input = JOptionPane.showInputDialog(null, "Enter a code: ");
				if (input.equals("2019")) {
					System.out.println("hello");
				}
			}

		

		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	
	public boolean getEgg(){
		return egg;
	}

	
} //closes the main class