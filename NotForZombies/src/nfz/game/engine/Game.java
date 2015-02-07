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
	
	public Game() {
		objects = new ArrayList<GameObject>();
		
		terrain = new Terrain(10, 10);
		
		player = new Player(0, 0);
		
		objects.add(player);
	}
	
	public void getInput() {
		player.getInput();
	}
	
	public void update() {
		for (GameObject go : objects) {
			go.update();
		}
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
