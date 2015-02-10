package nfz.game.engine;

import java.util.ArrayList;

import org.lwjgl.opengl.Display;

import nfz.game.logic.GameObject;
import nfz.game.logic.gameobjects.Player;
import nfz.game.logic.terrain.Terrain;

/**
 * Sets up terrain and game objects, 
 * calls input, update and render methods of gameobjects and terrain
 * @author BBPG
 *
 */
public class Game {

	private ArrayList<GameObject> objects;
	private Player player;
	private Terrain terrain;
	
	private float startX = Display.getWidth() / 2;
	private float startY = Display.getHeight() / 2;
	
	public Game() {		
		objects = new ArrayList<GameObject>();
		
		terrain = new Terrain(20, 20);
		
		//create player at starting position
		player = new Player(startX, startY);
		
		objects.add(player);
	}
	
	public void getInput() {
		//player.getInput();
	}
	
	public void update(int delta) {
		for (GameObject go : objects) {
			go.update(delta);
		}
		//update translation based on player position
		terrain.setTranslateX(player.getX());
		terrain.setTranslateY(player.getY());
	}
	
	public void render() {
		//render terrain
		terrain.render();
		
		//render gameobjects
		for (GameObject go : objects) {
			go.render();
		}
	}
	
}
