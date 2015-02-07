package nfz.game.logic.terrain;

import static org.lwjgl.opengl.GL11.*;

import java.util.Random;

/**
 * Represents the world/level stored in 2d grid
 * @author BBPG
 *
 */
public class Terrain {
	
	//size of terrain
	private int xSize;
	private int ySize;
	//2d array of blocks
	private Block[][] blocks;
	
	public Terrain(int xSize, int ySize) {
		this.xSize = xSize;
		this.ySize = ySize;
		blocks = new Block[ySize][xSize];
		
		Random rand = new Random();
		for (int i = 0; i < ySize; i++) {
			for (int j = 0; j < xSize; j++) {
				//create random blocktype on coords i,j
				//this will not be randomized in future development,
				//when we create the level creator tools
				int type = rand.nextInt(10);
				if (type < 8) {
					blocks[i][j] = new Block(BlockType.STONE);
				}
				else {
					blocks[i][j] = new Block(BlockType.GRASS);
				}
			}
		}
	}
	
	/**
	 * Render all blocks in terrain
	 */
	public void render() {
		for (int i = 0; i < ySize; i++) {
			for (int j = 0; j < xSize; j++) {
				glPushMatrix();
					//translate to the position of the rendered block
					//and call render method on that block
					glTranslatef(j * Block.BLOCK_SIZE, i * Block.BLOCK_SIZE, 0);
					blocks[i][j].render();
				glPopMatrix();	
			}
		}
	}
	
}
