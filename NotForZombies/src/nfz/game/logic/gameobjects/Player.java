package nfz.game.logic.gameobjects;

import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glTranslatef;

import java.awt.Rectangle;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector2f;

import nfz.game.engine.Game;
import nfz.game.graphics.Sprite;
import nfz.game.logic.GameObject;
import nfz.game.logic.Stats;
import nfz.game.physics.Physics;

public class Player extends GameObject{
	
	public static final float PLAYER_SX = 64;
	public static final float PLAYER_SY = 64;
	public static final String PLAYER_TEX_LOC = "res/player.png";
	
	private Stats stats = new Stats();
	private float xp;
	private int xpNeeded;
	private int delta;
	
	/**
	 * create player instance on coords x.y
	 * @param x
	 * @param y
	 */
	public Player (float x, float y) {
		super(x, y, PLAYER_SX, PLAYER_SY);
		isSolid = true;
		sprite = new Sprite(PLAYER_SX, PLAYER_SY, PLAYER_TEX_LOC);
		hitbox = new Rectangle((int) (x - PLAYER_SX / 2), (int) (y - PLAYER_SY / 2), 
				(int)PLAYER_SX, (int)PLAYER_SY);
		xp=0;
		xpNeeded=50;
		stats.setCurrHealth(100);
		stats.setMaxHealth(100);
		stats.setCurrEnergy(100);
		stats.setMaxEnergy(100);
		stats.setArmor(0);
		stats.setLevel(1);
		stats.setStrength(5);
		stats.setAgility(5);
		stats.setIntelligence(5);
		stats.setSpeed(0.4f);
	}
	
	/**
	 * Apply player logic
	 */
	public void update(int delta) {
		//check collisions
		this.delta = delta;
		getInput();
		rotateToMouseLocation();
		
		System.out.println("Player\tX:" + x + " Y:" + y + " ROT: " + rot);
	}
	

	@Override
	public void collideWith(GameObject other) {
		
		if (other instanceof Obstacle) {
			System.out.println("Collision with obstacle!");
		}
		
	}
	
	/**
	 * Update hitbox positions based on position
	 */
	private void updateHitbox() {
		hitbox.setBounds((int) (x - PLAYER_SX / 2), (int) (y - PLAYER_SY / 2), 
				(int)PLAYER_SX, (int)PLAYER_SY);
	}
	
	public void rotateToMouseLocation() {
		//rotate with mouse
		//get mouse coords
		float mouseX = Mouse.getX();
		float mouseY = Mouse.getY();
			
		//get player center coords
		float centerX = Display.getWidth() / 2;
		float centerY = Display.getHeight() / 2;
				
		//get delta values
		float deltaY = mouseY - centerY;
		float deltaX = mouseX - centerX;
		
		//calculate angle between player and mouse using arc tang
		float angle = (float) Math.atan(deltaY / deltaX);
		
		//get quadrant I, II, III, or IV
		if (deltaY >= 0 && deltaX >= 0) {
			rot = (float) Math.toDegrees(angle);
		} else if (deltaY >= 0 && deltaX < 0) {
			rot = 180 + (float) Math.toDegrees(angle);
		} else if (deltaY < 0 && deltaX < 0) {
			rot = 180 + (float) Math.toDegrees(angle);
		} else if (deltaY < 0 && deltaX >= 0) {
			rot = 360 + (float) Math.toDegrees(angle);
		}
		
		rot -= 90;
	}
	
	public void getInput() {
		
		//get mouse coords
		float mouseX = Mouse.getX();
		float mouseY = Mouse.getY();
			
		//get player center coords
		float centerX = Display.getWidth() / 2;
		float centerY = Display.getHeight() / 2;
				
		//get delta values
		float deltaY = mouseY - centerY;
		float deltaX = mouseX - centerX;
		
		//calculate angle between player and mouse using arc tang
		float angle = (float) Math.atan2(deltaY, deltaX);
		
		//check for keys pressed
		if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
			move(angle);
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
			move((float) (angle+Math.toRadians(180)));
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
			move((float) (angle+Math.toRadians(90)));
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
			move((float) (angle-Math.toRadians(90)));
		}
	}
	
	/**
	 * Renders player in the center of the screen
	 */
	public void render() {
		glPushMatrix();
		//translate to the center of the screen
		glTranslatef(Display.getWidth() / 2 - Player.PLAYER_SX / 2, 
				Display.getHeight() / 2 - Player.PLAYER_SY / 2, 0);
		//translate to the center of player to apply rotation
		glTranslatef(sprite.getSx() / 2, sprite.getSy() / 2, 0);
		glRotatef(rot, 0.0f, 0.0f, 1.0f);	
		glTranslatef(-(sprite.getSx() / 2), -(sprite.getSy() / 2), 0);
		sprite.render();
		glPopMatrix();
	}
	
	private void move(float angle) {
		float speed = stats.getSpeed();
		//diagonal movement
		if( (Keyboard.isKeyDown(Keyboard.KEY_W) && ( Keyboard.isKeyDown(Keyboard.KEY_A) || Keyboard.isKeyDown(Keyboard.KEY_D))) ||
		    (Keyboard.isKeyDown(Keyboard.KEY_S) && ( Keyboard.isKeyDown(Keyboard.KEY_A) || Keyboard.isKeyDown(Keyboard.KEY_D))))
					speed = (float) (speed / Math.sqrt(2));
		float newX = (float) (x + speed * Math.cos(angle) * delta);
		float newY = (float) (y + speed * Math.sin(angle) * delta);
		Rectangle newHitbox = new Rectangle((int) (newX - PLAYER_SX / 2), (int) (newY - PLAYER_SY / 2),
				(int)PLAYER_SX, (int)PLAYER_SY);
		//check collisions
		boolean collide = false;
		for (GameObject go : Game.objects) {
			if (!(go instanceof Player)) {
				//compare hitboxes
				if (Physics.checkCollision(newHitbox, go.getHitbox())) {
					System.out.println("r1 " + newHitbox + "\nr2 " + go.getHitbox());
					collide = true;
					go.collideWith(this);
					this.collideWith(go);
				}
			}
		}
		//move if collision not detected
		if (!collide) {
			x = newX;
			y = newY;
		}
		
		
	}
	
	public void addXP(float amt) {
		this.xp += amt;
		if(xp>=xpNeeded){
			levelUP();
			xp-=xpNeeded;
		}
	}
	
	private void levelUP() {
		stats.setLevel(stats.getLevel()+1);
		xpNeeded *= 2; 
	}

	public float getXp() {
		return xp;
	}

}
