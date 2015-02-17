package nfz.game.physics;

/**
 * This class contains various static utility methods
 * 
 * @author BBPG
 *
 */
public class Util {
	
	/**
	 * Returns the rotation of player based on mouse position
	 * @param mx
	 * @param my
	 * @param px
	 * @param py
	 * @return
	 */
	public static float rotateToPoint(float mx, float my, float px, float py) {
		float rot = 0;
		
		//get delta values
		float deltaY = my - py;
		float deltaX = mx - px;
				
		//calculate angle between player and mouse using arc tang
		float angle = (float) Math.atan(deltaY / deltaX);
				
		//get quadrant I, II, III, or IV
		if (deltaY >= 0 && deltaX >= 0) {
			rot = (float) Math.toDegrees(angle);
		} else if (deltaY >= 0 && deltaX < 0) {
			rot = 180 + (float) Math.toDegrees(angle);
		} else if (deltaY < 0 && deltaX < 0) {
			rot = 180 + (float) Math.toDegrees(angle);
		} else if (deltaY < 0 && deltaX >= 0) {
			rot = 360 + (float) Math.toDegrees(angle);
		}
				
		rot -= 90;
		
		return rot;
	}

}
