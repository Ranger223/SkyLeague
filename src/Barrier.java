/*
Author: William Mortensen
Date: 3/20/19
Notes:
*/
import java.awt.Image;

import javax.swing.ImageIcon;


public class Barrier extends SpriteAB {

	Animation bar1;
	
	public Barrier(int imgNum){
		loadImages(imgNum);
		scaledWidth = a.getImage().getWidth(null);
		scaledHeight = a.getImage().getHeight(null);
		vx = 0f;
		vy = 0f;
	}

	public void loadImages(int imgNum) {
		bar1 = new Animation();

		if(imgNum == 1){
			Image left1 = new ImageIcon("images//Cannon.png").getImage();
			bar1.addScene(left1, 100);
		}
		else if(imgNum == 2){
			Image left1 = new ImageIcon("images//blueRock.png").getImage();
			bar1.addScene(left1, 100);
		}
		else if(imgNum == 3){
			Image left1 = new ImageIcon("images//brickBlock.png").getImage();
			bar1.addScene(left1, 100);
		}
		
		// Assign default animation
		a = bar1;
	}
	
	public String getSound(int wc){
		return "";
	}

	public void loadImages() {
		
	}
	

}