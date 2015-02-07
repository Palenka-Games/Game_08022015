package nfz.game.logic;

import nfz.game.graphics.Sprite;

import static org.lwjgl.opengl.GL11.*;

/**
 * Superclass for every object in the game
 * @author BBPG
 *
 */
public abstract class GameObject {

	//keep track of position
	protected float x;
	protected float y;
	protected float rot;

	protected Sprite sprite;
	
	public GameObject(float x, float y, float r, float g, float b, float sx, float sy) {
		this.x = x;
		this.y = y;
		this.rot = 0;
	}
	
	
	public void update() {
		
	}
	
	public void render() {
		
		glPushMatrix();
		glTranslatef(x, y, 0);
		if (sprite != null) {
			sprite.render();
		}
		glPopMatrix();
		
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
	
	public Sprite getSprite() {
		return sprite;
	}


}
