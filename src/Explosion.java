import java.awt.Image;

import javax.swing.ImageIcon;


public class Explosion extends SpriteAB {

	private long startTime;
	private long duration = 2500;
	
	// CONSTRUCTOR
		public Explosion(int x, int y) {
			//super();
			loadImages();
			//scaledWidth = a.getImage().getWidth(null);
			//scaledHeight = a.getImage().getHeight(null);
			scaledWidth = 50;
			scaledHeight = 50;
			this.x = x;
			this.y = y;
			startTime = System.currentTimeMillis();
		}

		public boolean endAnimation(){
			if(System.currentTimeMillis() - startTime > duration){
				return true;
			}
			return false;
		}
		
		//ADD SPRITE IMAGES HERE
		public void loadImages() {
			//Explode
			Image ex1 = new ImageIcon("images//Explosion1.png").getImage();
			Image ex2 = new ImageIcon("images//Explosion2.png").getImage();
			Image ex3 = new ImageIcon("images//Explosion3.png").getImage();
			Image ex4 = new ImageIcon("images//Explosion4.png").getImage();
			Image ex5 = new ImageIcon("images//Explosion5.png").getImage();
			Image ex6 = new ImageIcon("images//Explosion6.png").getImage();
			Image ex7 = new ImageIcon("images//Explosion7.png").getImage();
			Image ex8 = new ImageIcon("images//Explosion8.png").getImage();
			Image ex9 = new ImageIcon("images//Explosion9.png").getImage();
			Image ex10 = new ImageIcon("images//Explosion91.png").getImage();
			Image ex11 = new ImageIcon("images//Explosion92.png").getImage();
			Image ex12 = new ImageIcon("images//Explosion93.png").getImage();
			Image ex13 = new ImageIcon("images//Explosion94.png").getImage();
			Image ex14 = new ImageIcon("images//Explosion95.png").getImage();
			
			explode = new Animation();
			explode.addScene(ex1, 100);
			explode.addScene(ex2, 100);
			explode.addScene(ex3, 100);
			explode.addScene(ex4, 100);
			explode.addScene(ex5, 100);
			explode.addScene(ex6, 100);
			explode.addScene(ex7, 100);
			explode.addScene(ex8, 100);
			explode.addScene(ex9, 100);
			explode.addScene(ex10, 100);
			explode.addScene(ex11, 100);
			explode.addScene(ex12, 100);
			explode.addScene(ex13, 100);
			explode.addScene(ex14, 100);

			a = explode;
		}
	
}
