package nfz.game.physics;

import java.awt.Rectangle;
import nfz.game.logic.GameObject;

public class Physics {

	/**
	 * check if two objects collide
	 * @param go1
	 * @param go2
	 * @return
	 */
	public static boolean checkCollision(GameObject go1, GameObject go2) {
		
		Rectangle r1 = new Rectangle((int)go1.getX(), (int)go1.getSprite().getSx(),
				(int)go1.getY(), (int)go1.getSprite().getSy());
		Rectangle r2 = new Rectangle((int)go2.getX(), (int)go2.getSprite().getSx(),
				(int)go2.getY(), (int)go2.getSprite().getSy());
		
		return r1.intersects(r2);
	}
	
	/**
	 * Check if two rectangles collide
	 * @param r1
	 * @param r2
	 * @return
	 */
	public static boolean checkCollision(Rectangle r1, Rectangle r2) {
		return r1.intersects(r2);
	}
	
	/**
	 * Check if two circles collide
	 * @param r1
	 * @param r2
	 * @return
	 */
	public static boolean checkCollision(Circle c1, Circle c2) {
		return c1.intersects(c2);
	}
	
}
