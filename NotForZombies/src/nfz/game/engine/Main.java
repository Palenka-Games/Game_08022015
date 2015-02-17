package nfz.game.engine;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;

/**
 * Starts the game
 * @author BBPG
 *
 */
public class Main {
	
	//screen details
	private static final String GAME_TITLE = "NotForZombies";
	private static final int SCREEN_WIDTH = 800;
	private static final int SCREEN_HEIGHT = 600;
	
	private static Game game;
	private Time time;
	
	/**
	 * Get the engine up and running
	 */
	public void run() {
		initDisplay();
		initGL();
		initGame();
		gameLoop();
		cleanUp();
	}
	
	/**
	 * Initialize and show window
	 */
	public void initDisplay() {
		try {
			Display.setDisplayMode(new DisplayMode(SCREEN_WIDTH, SCREEN_HEIGHT));
			Display.setTitle(GAME_TITLE);
			Display.create();
			Keyboard.create();
			Display.setVSyncEnabled(true);
		} catch (LWJGLException ex) {
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	/**
	 * Initializes openGL
	 */
	public void initGL() {
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, Display.getWidth(), 0, Display.getHeight(), -1, 1);
		glMatrixMode(GL_MODELVIEW);
		
		//enable and disable opengl functionality
		//glDisable(GL_DEPTH_TEST);
		glEnable(GL_TEXTURE_2D);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		glClearColor(0, 0, 0, 0);
	}
	
	/**
	 * Initialize game
	 */
	public void initGame() {
		game = new Game();
	}
	
	/**
	 * starts the game loop then collects input, updates and renders game
	 */
	public void gameLoop() {
		time = new Time();
		time.getDelta(); //call once before loop to init lastFrame
		time.lastFPS = time.getTime(); // call before loop to init fps timer
		
		while(!Display.isCloseRequested()) {
			update();
			time.updateFPS();
			render();
		}
	}
	
	private void update() {
		game.update(time.getDelta());
	}
	
	/**
	 * renders the game
	 */
	private void render() {
		//delete whats on screen
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		//glClear(GL_DEPTH_BUFFER_BIT);
		//reset maatrix for the frame
		glLoadIdentity();
		
		//draw
		game.render();
		
		//display on screen
		Display.update();
		Display.sync(60);
	}
	
	/**
	 * destroys display and keyboard
	 */
	private void cleanUp() {
		Display.destroy();
		Keyboard.destroy();
	}
	
	public static void main(String[] args) { 
		Main main = new Main();
		main.run();
	}
	
}