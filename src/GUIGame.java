/*
Authors: Steven Bates and William Mortensen
Date: 4/8/19
Notes: Sprite Game Team Project - Team Members: William Mortensen & Steven Bates, Game Name: Sky League
 */
import java.awt.Color;
import java.awt.Container;
import java.awt.DisplayMode;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class GUIGame extends Core implements KeyListener, MouseMotionListener, MouseListener, MouseWheelListener{
	private Image bg, bg2;
	private Explosion explosion;
	private ArrayList <Explosion> explosions = new ArrayList <Explosion>();
	private Hero jet;
	private Enemy eJet;
	private Weapon1 wep, barrierWep;
	private Barrier bar1;
	private ArrayList <Weapon1> weapons = new ArrayList <Weapon1>();
	private ArrayList <Barrier> barriers = new ArrayList <Barrier>();
	private ArrayList <Weapon1> barrierWeps = new ArrayList <Weapon1>();
	private ArrayList <Enemy> enemies = new ArrayList <Enemy>();
	private String mess = "";
	private Random gen = new Random();
	private int points = 0;
	private int wepCount = 0;
	private int hiScore;
	private int backX = 0;
	private int waveLength = 0;
	private int waveNum = 0;
	private int enemyCount = 3;
	private int cntr = 0;
	private int homeBase = 200;
	private final int homeBaseMax = 200;
	private final int maxHealth = 100;
	private boolean gameRun = true;
	private static boolean easter;


	public static void main(String[] args) {
		StartScreen start = new StartScreen();
		start.startScreen(start);
		easter = start.getEgg();
		new GUIGame().run();


	}

	// init calls super
	public void init(){
		super.init();
		
		Window w = s.getFullScreenWindow();
		w.setFocusable(true);
		w.addMouseListener(this);
		w.addMouseMotionListener(this);
		w.addMouseWheelListener(this);
		w.addKeyListener(this);
		playSound("sounds/Platformer.wav");
		loadImages();
	}

	//draw
	public synchronized void draw(Graphics2D g){
		Window w = s.getFullScreenWindow();

		cntr = bg.getWidth(w);
		for (int i = 0; i < 100; i++) {
			if (i == 0) {
				g.drawImage(bg2, backX, 0, null);
			}

			g.drawImage(bg, backX + cntr, 0, null);

			cntr += bg.getWidth(w);

		}


		if(!gameRun){
			bg = new ImageIcon("images/gameovercar.png").getImage();
			
			//Records High Score
			PrintWriter writer;

			try {
				Scanner reader = new Scanner(new  File("score.txt"));
				while(reader.hasNext()){
					hiScore = reader.nextInt();
				}
			} catch (FileNotFoundException e) {

				e.printStackTrace();
			}

			if (points > hiScore) {

				try {
					writer = new PrintWriter (new File("score.txt"));
					writer.println(points);
					writer.close();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}

			Font f1 = new Font("Stencil", Font.BOLD, 40);
			g.setFont(f1);
			g.setColor(Color.cyan);
			g.drawString("Your Overall Score Is: " + points + " points", (s.getWidth()/2) - 300, 200);
			g.setColor(Color.green);
			g.drawString("High Score: " + hiScore + " points", (s.getWidth()/2) - 200, 300);
			jet.setVelocityX(0);
			jet.setVelocityY(0);

		}


		//In draw method
		Font f1 = new Font("Century", Font.BOLD, 30);
		g.setFont(f1);
		g.setColor(Color.red);
		g.drawString("Home Base: " + homeBase + "   Score: " + points + "   Health: " + jet.getHealth() + "   Wave Number: " + waveNum,  40, 50);
		g.fillRect((int)jet.getX() + 15,(int)jet.getY() - 30, 50, 10);
		g.setColor(Color.green);
		int healthPct = (int)((double)jet.getHealth()/maxHealth * 50.0);

		if (jet.getHealth() <= 0){
			jet.setHealth(0);;
			jet.destroy();
			gameRun = false;
		}

		g.fillRect((int)jet.getX() + 15,(int)jet.getY() - 30, healthPct, 10);


		g.setColor(Color.red);
		g.fillRect(40, 60, 100, 10);
		g.setColor(Color.green);
		int homeBaseHealthPct = (int)((double)homeBase / homeBaseMax * 100.0);

		if (homeBase <= 0){
			homeBase = 0;
			gameRun = false;
		}

		g.fillRect(40, 60, homeBaseHealthPct, 10);

		g.setColor(w.getForeground());
		
		//Paint Explosion
		if(explosion != null){
			g.drawImage(explosion.getImage(), Math.round(explosion.getX()), Math.round(explosion.getY()), explosion.getScaledWidth(), explosion.getScaledHeight(), null);
		}
		
		
		// THIS FLIPS SPRITE LEFT/RIGHT DEPENDING ON DIRECTION
		if (jet.getVelocityX() < 0) {
			g.drawImage(jet.getImage(), Math.round(jet.getX()), Math.round(jet.getY()), jet.getScaledWidth(), jet.getScaledHeight(), null);
		}
		else{
			g.drawImage(jet.getImage(), Math.round(jet.getX()), Math.round(jet.getY()), jet.getScaledWidth(), jet.getScaledHeight(), null);
		}

		if (!weapons.isEmpty()){
			try {
				for (Weapon1 w1 : weapons){
					g.drawImage(w1.getImage(), Math.round(w1.getX()), Math.round(w1.getY()), w1.getScaledWidth(), w1.getScaledHeight(), null);
				}
			} catch (Exception e) {
				System.out.println("Weapon Error");
			}
		}

		if (!barrierWeps.isEmpty()){
			try {
				for (Weapon1 bw1 : barrierWeps){
					g.drawImage(bw1.getImage(), Math.round(bw1.getX()), Math.round(bw1.getY()), bw1.getScaledWidth(), bw1.getScaledHeight(), null);
				}
			} catch (Exception e) {
				System.out.println("Barrier Weapon Error");
			}
		}



		for (Enemy b : enemies){
			g.drawImage(b.getImage(), Math.round(b.getX()), Math.round(b.getY()), b.getScaledWidth(), b.getScaledHeight(), null);
		}

		// Draw Barriers
		if (!barriers.isEmpty()){
			for (Barrier b : barriers) {
				g.drawImage(b.getImage(), Math.round(b.getX()), Math.round(b.getY()), b.getScaledWidth(), b.getScaledHeight(), null);
				b.setX(b.getX());
			}
		}


	}


	public void loadImages(){
		// ADD BACKGROUND HERE
		
		bg = new ImageIcon("images//Back1.jpg").getImage();
		bg2 = new ImageIcon("images//Back12.jpg").getImage();
		System.out.println(bg.getWidth(null));
		if (s.getWidth() == 1920) {
			bg = new ImageIcon("images//Back2.jpg").getImage();
			bg2 = new ImageIcon("images//Back122.jpg").getImage();
		}
		
		
		// HERO
		jet = new Hero();

		jet.setX(500);
		jet.setY(700);
		jet.setScaledHeight(75);
		jet.setScaledWidth(75);
		jet.setVelocityX(-.07f);
		jet.setHealth(100);
		jet.setDirection(true);
		
		//Sets Easter Egg theme*************************************************************
		if (easter) {
			bg = new ImageIcon("images//MLG.jpg").getImage();
			jet.setWeaponChoice(4);
		}
		// ENEMY
		/*for (int i = 0; i < 3; i++){
			makeEnemy(gen.nextInt(5));
		}*/

		// BARRIER
		makeBarrier();


	}
	

	private boolean checkBarriers(SpriteAB spr, long timePassed){
		for (Barrier bar: barriers){
			Rectangle rect = (Rectangle) spr.getRect();
			rect.x = Math.round(spr.getX() + spr.getVelocityX() * timePassed);
			rect.y = Math.round(spr.getY() + spr.getVelocityY() * timePassed);
			if (rect.intersects(bar.getRect())){
				return true;
			}
		}
		return false;
	}

	// BARRIER
	private void makeBarrier() {

		int xTotal = 0;

		for (int i = 0; i < 50; i++){

			bar1 = new Barrier(1);
			bar1.setX(xTotal + gen.nextInt(2000) + 700);
			bar1.setY(950);
			bar1.setScaledHeight(100);
			bar1.setScaledWidth(100);
			xTotal = (int)bar1.getX();
			barriers.add(bar1);

		}


	}

	private void makeEnemy(int angle){
		
		eJet = new Enemy();
		eJet.setX(jet.getX() + gen.nextInt(4500) + 1920);
		//eJet.setX(s.getWidth() + gen.nextInt(3200) + 500);
		eJet.setY(gen.nextInt((s.getHeight() - 250)) + 50);
		eJet.setScaledHeight(40);
		eJet.setScaledWidth(85);
		eJet.setVelocityX(-.2f);

		if(angle == 1){
			eJet.setVelocityY(.05f);
		}

		else if(angle == 2){
			eJet.setVelocityY(.03f);
		}

		else if (angle == 3){
			eJet.setVelocityY(-.05f);
		}

		else if(angle == 4){
			eJet.setVelocityY(-.03f);
		}

		else{
			eJet.setVelocityY(0f);
		}

		eJet.setHealth(2);
		enemies.add(eJet);
	}

	private void fireWeapon(){
		wep = new Weapon1(jet.getWeaponChoice());
		playSound(wep.getSound(jet.getWeaponChoice()));
		
		wep.setScaledHeight(5);
		wep.setScaledWidth(15);
		
		if (easter) {
			wep.setScaledHeight(30);
			wep.setScaledWidth(20);
		}

		if(jet.getFaceDegree() == 0){ // Facing down
			wep.setY(jet.getY() + jet.getScaledHeight()/2 + 10);
			wep.setVelocityY(0.15f);
			wep.setX(jet.getX() + jet.getScaledWidth() - wep.getScaledWidth());
			wep.setVelocityX(0.5f);
		}
		else if(jet.getFaceDegree() == 1){ // Facing Forward
			wep.setY(jet.getY() + (jet.getScaledHeight() / 2));
			wep.setVelocityY(0f);
			wep.setX(jet.getX() + jet.getScaledWidth() - wep.getScaledWidth());
			wep.setVelocityX(0.5f);
		}
		else if(jet.getFaceDegree() == 2){ // Facing Up
			wep.setY(jet.getY());
			wep.setVelocityY(-0.15f);
			wep.setX(jet.getX() + jet.getScaledWidth() - wep.getScaledWidth());
			wep.setVelocityX(0.5f);
		}

		weapons.add(wep);

	}

	public void barAttack(){
		for (Barrier b : barriers) {
			barrierWep = new Weapon1(3);
			barrierWep.setX(b.getX() + 60);
			barrierWep.setY(b.getY() + 5);
			barrierWep.setVelocityX(0.5f);
			barrierWep.setVelocityY(-0.5f);
			barrierWep.setScaledHeight(25);
			barrierWep.setScaledWidth(50);

			barrierWeps.add(barrierWep);
		}

	}

	public synchronized void update(long timePassed){

		// ENEMY
		if (waveLength >= backX + s.getWidth()) {
			for (int i = 0; i < enemyCount; i++){
				makeEnemy(gen.nextInt(5));
			}
			if (waveNum % 2 == 0) {
				enemyCount += 3;
			}
			else {
				enemyCount += 1;
			}
			waveNum++;
			waveLength -= s.getWidth() * 2;
		}


		for (Barrier b : barriers) {
			b.setX(b.getX() - 2);
		}

		/*if (System.currentTimeMillis() % 20 == 0){
			barAttack();
		}*/
		
		if (wepCount == 5){
			barAttack();
			wepCount = 0;
		}

		//Moves Background
		backX -= 5;


		// Keeps hero in bounds
		if(jet.getX() < 0){
			jet.setVelocityX((Math.abs(jet.getVelocityX()))/5);
		}
		else if (jet.getX() + jet.getScaledWidth() >= s.getWidth()){
			jet.setVelocityX(-Math.abs(jet.getVelocityX()));
		}

		if(jet.getY() < 50){
			jet.setVelocityY(Math.abs(jet.getVelocityY()));
		}
		else if (jet.getY() + jet.getScaledHeight() >= s.getHeight()){
			jet.setVelocityY(-Math.abs(jet.getVelocityY()));
		}

		// jet collide with barrier
		for (Barrier b : barriers) {
			if (jet.getRect().intersects(b.getRect())){
				jet.setHealth(0);
				
			}
		}



		jet.update(timePassed, checkBarriers(jet, timePassed));
		
		if(explosion != null){
			if (explosion.endAnimation()){
				explosion = null;
			}
			else{
				explosion.update(timePassed, false);
			}
		}


		// Remove weapons that go out of bounds & collision detection between weapons & enemies
		if(!weapons.isEmpty()){

			for (int i = 0; i < weapons.size(); i++) {
				Weapon1 w1 = weapons.get(i);

				// Check weapon collision with enemies
				for (int j = 0; j < enemies.size(); j++){
					Enemy e1 = enemies.get(j);
					if(w1.getRect().intersects(e1.getRect())){
						if (jet.getWeaponChoice() == 1) {
							e1.setHealth(e1.getHealth() - 1);
						}
						else if (jet.getWeaponChoice() == 2) {
							e1.setHealth(e1.getHealth() - 2);
						}
						else if (jet.getWeaponChoice() == 4) {
							e1.setHealth(0);
						}

						if (e1.getHealth() == 0 && jet.getHealth() > 0) {
							explosion = new Explosion((int)e1.getX(), (int)e1.getY());
							points += 10;
							enemies.remove(e1);		
							playSound("sounds/Explosion.wav");
						}

						weapons.remove(w1);
						
					}
				}



				// Out of bounds
				if(w1.getX() < -500 || w1.getX() > s.getWidth() + 500){ // Weapon out of bounds
					weapons.remove(w1);
				}

				else{
					if(checkBarriers(w1, timePassed)){ // if weapon hits barrier, remove weapon
						weapons.remove(w1);
					}
					w1.update(timePassed, false);
				}
			}
		}



		if(!barrierWeps.isEmpty()){

			for (int i = 0; i < barrierWeps.size(); i++) {
				Weapon1 bw1 = barrierWeps.get(i);

				// Check collision with jet
				if (gameRun) {
					for (int j = 0; j < enemies.size(); j++){
						Enemy e1 = enemies.get(j);
						if(bw1.getRect().intersects(e1.getRect())){
							explosion = new Explosion((int)e1.getX(), (int)e1.getY());
							enemies.remove(e1);
							playSound("sounds/Explosion.wav");
						}
					}
				}
				

				// Out of bounds
				if(bw1.getX() < -500 || bw1.getX() > s.getWidth() + 500){ // Weapon out of bounds
					weapons.remove(bw1);
				}

				else{
					bw1.update(timePassed, false);
				}
			}
		}


		if (!barriers.isEmpty()) {
			for (int i = 0; i < barriers.size(); i++) {
				Barrier b = barriers.get(i);

				// Out of bounds
				if(b.getX() < -200){ // Weapon out of bounds
					barriers.remove(b);
				}

				else{
					b.update(timePassed, false);
				}
			}
		}

		// Check for jet collision with enemies
		if(!enemies.isEmpty()){

			for (int j = 0; j < enemies.size(); j++){

				Enemy e1 = enemies.get(j);

				if(jet.getRect().intersects(e1.getRect())){
					
					if (gameRun) {
						e1.setHealth(0);
						explosion = new Explosion((int)e1.getX(), (int)e1.getY());
						jet.setHealth(jet.getHealth()-10);
						enemies.remove(e1);
						playSound("sounds/Explosion.wav");
					}
		
					System.out.println("Collision");
				}
			}

		}

		for (int i = 0; i < enemies.size(); i++){

			Enemy en = enemies.get(i);

			
			// If enemy passes far left edge of screen
			if (en.getX() < -100) {
				enemies.remove(en);
				if (homeBase > 0) {
					homeBase -= 10;
				}
				
				
			}
			

			if (en.getY() < 0) {
				en.setVelocityY(Math.abs(en.getVelocityY()));
			}else if (en.getY() + en.getScaledHeight() >= s.getHeight() - 150) {
				en.setVelocityY(-Math.abs(en.getVelocityY()));
			}

			if (en != null){
				if(checkBarriers(en, timePassed)){
					en.setVelocityX(-en.getVelocityX());
					en.setVelocityY(-en.getVelocityY());
				}
				en.update(timePassed, false);
			}

		}

	}



	//*****************    LISTENERS   *******************************************	
	// mouse listener interface
	public void mousePressed(MouseEvent e){
		mess = "You pressed down the mouse.";

	}

	public void mouseReleased(MouseEvent e){
		mess = "You released the mouse.";
	}

	public void mouseClicked(MouseEvent e){}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}


	// mouse motion interface
	public void mouseDragged(MouseEvent e) {
		mess = "You are dragging the mouse";		
	}

	public void mouseMoved(MouseEvent e) {
		mess = "You are moving the mouse";	

	}


	// mouse wheel listener
	public void mouseWheelMoved(MouseWheelEvent e) {
		mess = "moving mouse wheel";			
	}



	// key listener interface
	// keypressed
	public void keyPressed(KeyEvent e){
		int keyCode = e.getKeyCode();
		if(keyCode == KeyEvent.VK_ESCAPE){
			stop();
			System.exit(0);
		}
		else{
			mess = "Pressed: " + KeyEvent.getKeyText(keyCode); // stores the key pressed
			e.consume();
		}

		// Move main character

		if (keyCode == KeyEvent.VK_LEFT){
			if (gameRun) {
				jet.flyLeft();
				jet.setVelocityX(-.25f);
				jet.setDirection(true);
				jet.setFaceDegree(1);
			}
			
		}

		else if (keyCode == KeyEvent.VK_RIGHT){
			if (gameRun) {
				jet.flyRight();
				jet.setVelocityX(.25f);
				jet.setDirection(false);
				jet.setFaceDegree(1);
			}
			
		}

		if (keyCode == KeyEvent.VK_UP){
			if (gameRun) {
				jet.flyUp();
				jet.setVelocityY(-.25f);
				jet.setFaceDegree(2);
			}
			
		}

		else if (keyCode == KeyEvent.VK_DOWN){
			if (gameRun) {
				jet.flyDown();
				jet.setVelocityY(.25f);
				jet.setFaceDegree(0);
			}
			
		}

		if (keyCode == KeyEvent.VK_F1){
			String input = "";
			input = JOptionPane.showInputDialog(null, "Enter a code: ");
			if (input.equals("2019")) {
				System.out.println("hello");
			}
		}

		if (keyCode == KeyEvent.VK_F2){
			jet.changeWeapon();
		}

		if (keyCode == KeyEvent.VK_SPACE){
			if (gameRun) {
				fireWeapon();
				wepCount++;
			}
			
		}

		if (keyCode == KeyEvent.VK_ALT){
			barAttack();
		}

	}


	// key released
	public void keyReleased(KeyEvent e){
		int keyCode = e.getKeyCode();
		mess = "Released: " + KeyEvent.getKeyText(keyCode); // stores the key pressed

		// Move main character
		if (keyCode == KeyEvent.VK_UP){
			jet.setVelocityY(0f);
		}

		else if (keyCode == KeyEvent.VK_DOWN){
			jet.setVelocityY(0f);
		}

		if (keyCode == KeyEvent.VK_LEFT){
			jet.setVelocityX(-.07f);
		}

		else if (keyCode == KeyEvent.VK_RIGHT){
			jet.setVelocityX(-.07f);
		}

		e.consume();
	}

	// last method from interface
	public void keyTyped(KeyEvent e){
		e.consume();
	}


	//*************  SOUND  **************************************
	public void playSound(String SndFile) {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(SndFile).getAbsoluteFile());
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
		} catch(Exception ex) {
			System.out.println("Error with playing sound.");
			ex.printStackTrace();
		}
	}


}  // END CLASS