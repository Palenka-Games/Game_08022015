package nfz.game.engine;

import java.util.ArrayList;

import org.lwjgl.opengl.Display;

import nfz.game.logic.GameObject;
import nfz.game.logic.gameobjects.Enemy;
import nfz.game.logic.gameobjects.MeleeEnemy;
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
	
	private float startX = 0;
	private float startY = 0;
	
	public static float trX, trY;
	
	public Game() {		
		objects = new ArrayList<GameObject>();
		objectsToAdd = new ArrayList<GameObject>();
		objectsToRemove = new ArrayList<GameObject>();
		
		terrain = new Terrain(20, 20);
		
		//create player at starting position
		player = new Player(startX, startY);
		objects.add(player);
		objects.add(new MeleeEnemy(600, 600, player));
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
		terrain.render((int)player.getX(), (int)player.getY());
		
		//render gameobjects
		for (GameObject go : objects) {
			go.render();
		}
	}
	
}
