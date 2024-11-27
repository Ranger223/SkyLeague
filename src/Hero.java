/*
Author: William Mortensen
Date: 2/21/19
Notes:
 */
import java.awt.Image;
import javax.swing.ImageIcon;

public class Hero extends SpriteAB {

	private int weaponChoice = 1; // 1 or 2
	private int faceDegree = 1;
	
	// CONSTRUCTOR
	public Hero() {
		//super();
		loadImages();
		scaledWidth = a.getImage().getWidth(null);
		scaledHeight = a.getImage().getHeight(null);
	}

	//ADD SPRITE IMAGES HERE
	public void loadImages() {
		// Right Images
		Image right1 = new ImageIcon("images//JetR.png").getImage();

		flyR = new Animation();
		flyR.addScene(right1, 100);
		flyL = new Animation();
		flyL.addScene(right1, 100);

		// Up Images
		Image up1 = new ImageIcon("images//JetU.png").getImage();

		flyU = new Animation();
		flyU.addScene(up1, 100);

		// Down Images
		Image down1 = new ImageIcon("images//JetD.png").getImage();

		flyD = new Animation();
		flyD.addScene(down1, 100);
		
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

		// NEW SKIN
		Image skin1 = new ImageIcon("images//HorseRight1.png").getImage();
		Image skin2 = new ImageIcon("images//HorseRight2.png").getImage();
		Image skin3 = new ImageIcon("images//HorseRight3.png").getImage();
		Image skin4 = new ImageIcon("images//HorseRight4.png").getImage();

		newSkin = new Animation();
		newSkin.addScene(skin1, 100);
		newSkin.addScene(skin2, 100);
		newSkin.addScene(skin3, 100);
		newSkin.addScene(skin4, 100);

		// Assign default animation
		a = flyR;
	}
	
	public int getFaceDegree(){
		return faceDegree;
	}
	
	public void setFaceDegree(int d){
		faceDegree = d;
	}
	
	public int getWeaponChoice(){
		return weaponChoice;
	}

	public void setWeaponChoice(int wc){
		weaponChoice = wc;
	}
	
	public void changeWeapon(){
		if (weaponChoice == 1){
			weaponChoice = 2;
		}
		else {
			weaponChoice = 1;
		}
	}

}