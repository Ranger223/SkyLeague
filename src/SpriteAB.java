/*
Author: William Mortensen
Date: 2/21/19
Notes:
*/
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

public abstract class SpriteAB {

	// INSTANCE VARIABLES ***
	protected Animation a;
	protected Animation stand, flyR, flyL, flyU, flyD, newSkin, explode;
	protected float x;
	protected float y;
	protected float vx;
	protected float vy;
	protected int scaledWidth;
	protected int scaledHeight;
	protected int health;

	protected boolean remove = false;
	protected boolean isMove = true;
	protected boolean faceLeft = false;
	protected boolean isDone = false;


	// CONSTRUCTORS ***
	public SpriteAB() {	
		//scaledWidth = a.getImage().getWidth(null);
		//scaledHeight = a.getImage().getHeight(null);
	}

	public SpriteAB(Animation a) {
		this.a = a;

		scaledWidth = a.getImage().getWidth(null);
		scaledHeight = a.getImage().getHeight(null);
	}


	// ABSTRACT METHODS ***
	public abstract void loadImages();


	// OTHER METHODS ***
	public void flyLeft(){
		if(flyL != null) a = flyL;
		else a = a; // Assign to default animation
	}

	public void flyRight(){
		if(flyR != null) a = flyR;
		else a = a; // Assign to default animation
	}

	public void flyUp(){
		if(flyU != null) a = flyU;
		else a = a; // Assign to default animation
	}
	
	public void flyDown(){
		if(flyD != null) a = flyD;
		else a = a; // Assign to default animation
	}
	
	public void changeSkin(){
		if(newSkin != null) a = newSkin;
		else a = a; // Assign t default animation
	}
	
	public void destroy(){
		if(explode != null){
			a = explode;
		}	
		else a = a; // Assign t default animation
	}
	
	public boolean getIsDone(){
		return isDone;
	}
	
	// Change Position
	public void update(long timePassed, boolean collision){
		if(!collision){ // Move only if no collision
			x += vx * timePassed;
			y += vy * timePassed;
		}
		a.update(timePassed);
	}

	// Collision Detection
	public Rectangle2D getRect (){
		Rectangle2D rect = new Rectangle((int) x,(int) y, scaledWidth, scaledHeight);
		return rect;

	}

	// *** Getters and Setters ***
	//  ** Direction **
	public boolean getDirection() {
		return faceLeft;
	}
	
	public void setDirection(boolean fl) {
		faceLeft = fl;
	}
	
	//  ** Position **
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
	//  ** Velocity **
	public float getVelocityX() {
		return vx;
	}

	public void setVelocityX(float vx) {
		this.vx = vx;
	}

	public float getVelocityY() {
		return vy;
	}

	public void setVelocityY(float vy) {
		this.vy = vy;
	}
	//  ** Image Scale **
	public int getScaledWidth() {
		return scaledWidth;
	}

	public void setScaledWidth(int scaledWidth) {
		this.scaledWidth = scaledWidth;
	}

	public int getScaledHeight() {
		return scaledHeight;
	}

	public void setScaledHeight(int scaledHeight) {
		this.scaledHeight = scaledHeight;
	}
	
	public int getHealth() {
		return health;
	}
	
	public void setHealth(int health) {
		this.health = health;
	}
	//  ** Get Sprite's Image **
	public Image getImage(){
		return a.getImage();
	}

}