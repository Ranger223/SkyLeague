import java.awt.Color;
import java.awt.DisplayMode;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Window;

//hggghj
public abstract class Core {
	
	private static DisplayMode modes[] = {
		new DisplayMode(1920, 1080, 32, 0),
		new DisplayMode(1920, 1080, 24, 0),
		new DisplayMode(1920, 1080, 16, 0),
		new DisplayMode(1680, 1050, 32, 0),
		new DisplayMode(1680, 1050, 24, 0),
		new DisplayMode(1680, 1050, 16, 0),
		new DisplayMode(1280, 800, 32, 0),
		new DisplayMode(1280, 800, 24, 0),
		new DisplayMode(1280, 800, 16, 0),
		new DisplayMode(1024, 768, 32, 0),
		new DisplayMode(1024, 768, 24, 0),
		new DisplayMode(1024, 768, 16, 0),
		new DisplayMode(800, 600, 32, 0),
		new DisplayMode(800, 600, 24, 0),
		new DisplayMode(800, 600, 16, 0),
		new DisplayMode(640, 480, 32, 0),
		new DisplayMode(640, 480, 24, 0),
		new DisplayMode(640, 480, 16, 0),
	};
	
	private boolean running;
	protected ScreenManager s;
	
	// Stop method
	public void stop() {
		running = false;
	}
	
	// Call init and gameloop
	public void run() {
		try {
			init();
			gameLoop();
		} finally {
			s.restoreScreen();
		}
		
	}
	
	// Sets to full screen
	public void init() {
		s = new ScreenManager();
		DisplayMode dm = s.findFirstCompatibleMode(modes);
		s.setFullScreen(dm);
		
		Window w = s.getFullScreenWindow();
		w.setFont(new Font("Arial", Font.PLAIN, 20));
		w.setBackground(Color.DARK_GRAY);
		w.setForeground(Color.WHITE);
		running = true;
		
	}
	
	// Main gameLoop
	public void gameLoop() {
		long startTime = System.currentTimeMillis();
		long cumulativeTime = startTime;
		
		while(running) {
			long timePassed = System.currentTimeMillis() - cumulativeTime;
			cumulativeTime += timePassed;
			
			update(timePassed);
			
			Graphics2D g = s.getGraphics();
			draw(g);
			g.dispose();
			s.update();
			
			try {
				Thread.sleep(10);
			} catch(Exception ex) { }
			
		}
		
	}
	
	// Update animation
	public void update(long timePassed) {
		// Usually overwrite this with the classes own code as each will be different.
	}
	
	// Draws to screen - You will overwrite this with your own code.
	public abstract void draw(Graphics2D g);
	
}
