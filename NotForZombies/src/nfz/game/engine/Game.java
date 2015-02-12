package nfz.game.engine;

import java.util.ArrayList;

import org.lwjgl.opengl.Display;

import nfz.game.logic.GameObject;
import nfz.game.logic.gameobjects.Obstacle;
import nfz.game.logic.gameobjects.Player;
import nfz.game.logic.gameobjects.Projectile;
import nfz.game.logic.terrain.Terrain;

/**
 * Sets up terrain and game objects, 
 * calls input, update and render methods of gameobjects and terrain
 * @author BBPG
 *
 */
public class Game {

	public static ArrayList<GameObject> objects;
	public static ArrayList<GameObject> objectsToAdd;
	public static ArrayList<GameObject> objectsToRemove;
	private Player player;
	private Terrain terrain;
	
	private float startX = 256;
	private float startY = 256;
	
	public static float trX, trY;
	
	public Game() {		
		objects = new ArrayList<GameObject>();
		objectsToAdd = new ArrayList<GameObject>();
		objectsToRemove = new ArrayList<GameObject>();
		
		terrain = new Terrain(10, 10);
		
		//create player at starting position
		player = new Player(startX, startY);
		objects.add(player);
		objects.add(new Obstacle(128, 128));
		objects.add(new Obstacle(350, 64));
		objects.add(new Obstacle(98, 500));
		objects.add(new Obstacle(500, 480));
	}
	
	public void getInput() {
		//player.getInput();
	}
	
	public void update(int delta) {
		//update all gameobjects
		for (GameObject go : objects) {
			go.update(delta);
		}
		//add new gameobjects
		objects.addAll(objectsToAdd);
		objectsToAdd.clear();
		
		//remove objects
		objects.removeAll(objectsToRemove);
		objectsToRemove.clear();

		//update translation based on player position
		trX = player.getX();
		trY = player.getY();
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
