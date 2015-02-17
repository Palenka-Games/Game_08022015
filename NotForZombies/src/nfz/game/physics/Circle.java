package nfz.game.physics;

import java.awt.Point;
import java.awt.Rectangle;

public class Circle {

	private float x;
	private float y;
	private float r;
	
	public Circle(float x, float y, float r) {
		this.x = x;
		this.y = y;
		this.r = r;
	}
	
	/**
	 * Returns true, if circle c intersects with this circle,
	 * otherwise returns false
	 * @param c
	 * @return
	 */
	public boolean intersects(Circle c) {
		float dist = (float) Point.distance(x, y, c.getX(), c.getY());
		return dist <= r + c.getR();
	}
	
	public boolean intersects(Rectangle r) {
		//TODO write the algorithm 
		return true;
	}


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

	public float getR() {
		return r;
	}

	public void setR(float r) {
		this.r = r;
	}

	public void setCenter(float x, float y) {
		this.x=x;
		this.y=y;
	}

	
}