import java.awt.Image;

import javax.swing.ImageIcon;

/*
Author: William Mortensen
Date: 2/25/19
Notes:
*/
public class Weapon1 extends SpriteAB {

	Animation wep1, wep2, wep3, wep4;
	
	public Weapon1(int w){
		loadImages();
		scaledWidth = a.getImage().getWidth(null);
		scaledHeight = a.getImage().getHeight(null);
		if (w == 1){
			a = wep1;
		}
		else if (w == 2) {
			a = wep2;
		}
		else if (w == 3) {
			a = wep3;
		}
		else if (w == 4) {
			a = wep4;
		}
	}

	public void loadImages() {
	
		// First Weapon
		Image bullet = new ImageIcon("images//Bullet.png").getImage();

		wep1 = new Animation();
		wep1.addScene(bullet, 0);
		
		// Second Weapon
		Image laser1 = new ImageIcon("images//laser.png").getImage();
		
		wep2 = new Animation();
		wep2.addScene(laser1, 100);
		
		// Third Weapon
		Image asteroid1 = new ImageIcon("images//Asteroid.png").getImage();
		
		wep3 = new Animation();
		wep3.addScene(asteroid1, 100);
		
		//Easter Egg weapon
		Image dorito = new ImageIcon("images//Dorito.png").getImage();

		wep4 = new Animation();
		wep4.addScene(dorito, 100);
		
		// Assign default animation
		a = wep1;
	}
	
	public String getSound(int wc){
		if (wc == 1){
			return "sounds/Bullet.wav";
		}
		else if (wc == 2){
			return "sounds/laser.wav";
		}
		else if (wc == 3){
			return "";
		}
		else if (wc == 4){
			return "sounds/laser.wav";
		}
		
		return "";
		
	}

}