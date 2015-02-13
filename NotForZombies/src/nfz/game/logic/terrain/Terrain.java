package nfz.game.logic.terrain;

import static org.lwjgl.opengl.GL11.*;

import java.util.Random;

import org.lwjgl.opengl.Display;

import nfz.game.engine.Game;

/**
 * Represents the world/level stored in 2d grid
 * @author BBPG
 *
 */
public class Terrain {
	
	//how many we will render around player
	public static final int WIDTH_BLOCKS = 16;
	public static final int HEIGHT_BLOCKS = 12;
	
	//size of terrain
	private int xSize;
	private int ySize;
	//2d array of blocks
	private Block[][] blocks;
	
	private float translateX;
	private float translateY;
	
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
	public void render(int x, int y) {
		
		//index of blocks the player is standing on
		int playerI = (int) (y / Block.BLOCK_SIZE);
		int playerJ = (int) (x / Block.BLOCK_SIZE);
		
		//get starting indexes of terrain rendering
		int startI = playerI - (HEIGHT_BLOCKS / 2);
		int startJ = playerJ - (WIDTH_BLOCKS / 2);
		int endI = playerI + (HEIGHT_BLOCKS / 2);;
		int endJ = playerJ + (WIDTH_BLOCKS / 2);
		
		//check array bounds
		if (startI < 0) {
			startI = 0;
		}
		if (startJ < 0) {
			startJ = 0;
		}
		if (endI > ySize) {
			endI = ySize;
		}
		if (endJ > xSize) {
			endJ = xSize;
		}
		
		//System.out.println("X:" + x + " Y:" + y + "\niX:" + startI + " iY:" + startJ);
		//translate rendering based on player position
		glTranslatef(-translateX + Display.getWidth() / 2, -translateY + Display.getHeight() / 2, 0);
		for (int i = startI; i < endI; i++) {
			for (int j = startJ; j < endJ; j++) {
					glPushMatrix();
					//translate to the position of the rendered block
					//and call render method on that block
					glTranslatef(j * Block.BLOCK_SIZE, i * Block.BLOCK_SIZE, 0);
					blocks[i][j].render();
					glPopMatrix();
			}
		}
		//translate back
		glTranslatef(translateX - Display.getWidth() / 2, translateY - Display.getHeight() / 2, 0);
	}
	
	public void setTranslateX(float x) {
		this.translateX = x;
	}
	public void setTranslateY(float y) {
		this.translateY = y;
	}
	
}
