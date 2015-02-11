package nfz.game.graphics;

import static org.lwjgl.opengl.GL11.*;

import java.io.IOException;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class Sprite {

	private float sx;
	private float sy;
	private Texture texture;
	
	public Sprite(float sx, float sy, String texPath) {
		this.sx = sx;
		this.sy = sy;
		try {
			texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(texPath));
		} catch (IOException e) {
			System.err.println("Could not load texture: " + texPath);
			e.printStackTrace();
			System.exit(-1);
		}
	}
	
	/**
	 * render the sprite
	 */
	public void render() {
		//glColor3f(r,g,b);
		texture.bind();
		glBegin(GL_QUADS);
			glTexCoord2f(0, 1); glVertex2f(0, 0);
			glTexCoord2f(0, 0); glVertex2f(0, sy);
			glTexCoord2f(1, 0); glVertex2f(sx, sy);
			glTexCoord2f(1, 1); glVertex2f(sx, 0);
		glEnd();
	}
	
	public float getSx() {
		return sx;
	}
	
	public float getSy() {
		return sy;
	}

}
