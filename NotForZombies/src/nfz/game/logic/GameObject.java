package nfz.game.logic;

import java.awt.Rectangle;

import org.lwjgl.opengl.Display;

import nfz.game.engine.Game;
import nfz.game.graphics.Sprite;
import static org.lwjgl.opengl.GL11.*;

/**
 * Superclass for every object in the game
 * @author BBPG
 *
 */
public abstract class GameObject {

	//flags
	protected boolean isSolid;
	
	//keep track of position
	protected float x;
	protected float y;
	protected float rot;

	protected Sprite sprite;
	protected Rectangle hitbox;
	
	public GameObject(float x, float y, float sx, float sy) {
		this.x = x;
		this.y = y;
		this.rot = 0;
		isSolid = false;
		hitbox = null;
	}
	
	
	public abstract void update(int delta);
	
	public abstract void collideWith(GameObject other);
	
	/**
	 * Renders gameobject to screen, if sprite is not null
	 */
	public void render() {
		
		if (sprite != null) {
			glPushMatrix();
			glTranslatef(Display.getWidth() / 2 - Game.trX, 
					Display.getHeight() / 2 - Game.trY, 0);
			glTranslatef(x - sprite.getSx() /2, y - sprite.getSy() / 2, 0);
			sprite.render();
		glPopMatrix();
		}
		
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
	/**
	 * Turns isSolid flag on this object on or off
	 * @param b
	 */
	public void setIsSolid(boolean b) {
		isSolid = b;
	}
	public boolean getIsSolid() {
		return isSolid;
	}
	public Rectangle getHitbox() {
		return hitbox;
	}

}
