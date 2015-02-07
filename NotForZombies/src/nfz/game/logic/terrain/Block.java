package nfz.game.logic.terrain;

import static org.lwjgl.opengl.GL11.*;

import java.io.IOException;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

/**
 * Represents one block of terrain
 * @author BBPG
 *
 */
public class Block {

	public static final float BLOCK_SIZE = 64;
	
	private Texture texture;
	BlockType blockType;
	
	public Block(BlockType blockType) {
		//set blocktype
		String texPath;
		switch (blockType) {
			case STONE:
				texPath = "res/stone.png";
				break;
			case GRASS:
				texPath = "res/grass.png";
				break;
			default:
				texPath = "res/stone.png";
		}
		//set texture based on blocktype
		try {
			texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(texPath));
		} catch (IOException e) {
			System.err.println("Could not load texture: " + texPath);
			e.printStackTrace();
			System.exit(-1);
		}
	}
	
	/**
	 * Renders one block
	 */
	public void render() {
		texture.bind();
		glBegin(GL_QUADS);
			glTexCoord2f(0, 1); glVertex2f(0, 0);
			glTexCoord2f(0, 0); glVertex2f(0, BLOCK_SIZE);
			glTexCoord2f(1, 0); glVertex2f(BLOCK_SIZE, BLOCK_SIZE);
			glTexCoord2f(1, 1); glVertex2f(BLOCK_SIZE, 0);
		glEnd();

	}
	
}
