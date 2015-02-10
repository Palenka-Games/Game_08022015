package nfz.game.engine;

import org.lwjgl.opengl.Display;

public class Time {

	long lastFrame;
	int fps;
	long lastFPS;
	
	/**
	 * Get the time in miliseconds
	 * @return
	 */
	public long getTime() {
		return (System.nanoTime() / 1000000);
	}
	
	/**
	 * Get the number of miliseconds that have passed since the last frame
	 * Used to move entities in frame independent way
	 * @return
	 */
	public int getDelta() {
		long time = getTime();
		int delta = (int) (time - lastFrame);
		lastFrame = time;
		
		return delta;
	}
	
	/**
	 * Calculates the FPS
	 */
	public void updateFPS() {
		if (getTime() - lastFPS > 1000) {
			Display.setTitle("FPS: " + fps);
			fps = 0;
			lastFPS += 1000;
		}
		fps++;
	}
	
}
