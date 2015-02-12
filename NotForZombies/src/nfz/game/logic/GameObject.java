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
			//translate based on current game translation
			glTranslatef(Display.getWidth() / 2 - Game.trX, 
					Display.getHeight() / 2 - Game.trY, 0);
			//translate to gameobject position
			glTranslatef(x, y, 0);
			//apply rotation
			glRotatef(rot, 0f, 0f, 1f);
			//translate to bottom left corner for texture rendering
			glTranslatef(-sprite.getSx() / 2, -sprite.getSy() / 2, 0);
			sprite.render();
		glPopMatrix();
		}
		
	}
 
	/**
	 * Destroy this game object
	 */
	public void destroy() {
		Game.objectsToRemove.add(this);
	}
	
	/**
	 * Update hitbox based on position
	 */
	public void updateHitbox(Rectangle r) {
		hitbox.setBounds(r);
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
