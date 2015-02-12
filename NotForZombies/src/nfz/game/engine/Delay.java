package nfz.game.engine;

/**
 * This class handles various delays in game, f.e. attack delay
 * 
 * @author BBPG
 *
 */
public class Delay {
	
	//time of last action
	private long last;
	//delay in ms
	private long delay;
	
	public Delay(long delay) {
		this.delay = delay;
		last = Time.getTime();
	}
	
	/**
	 * Returns true if time specified in delay field has passed,
	 * otherwise returns false
	 * @return
	 */
	public boolean isReady() {
		long now = Time.getTime();
		if (now - last < delay) {
			return false;
		}
		last = now;
		return true;
	}
	
}
