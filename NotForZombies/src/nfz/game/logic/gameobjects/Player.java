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

import nfz.game.engine.Delay;
import nfz.game.engine.Game;
import nfz.game.graphics.Sprite;
import nfz.game.logic.GameObject;
import nfz.game.logic.Stats;
import nfz.game.physics.Physics;
import nfz.game.physics.Util;

public class Player extends GameObject {
	
	public static final float PLAYER_SX = 64;
	public static final float PLAYER_SY = 64;
	public static final String PLAYER_TEX_LOC = "res/player.png";
	public static final long ATTACK_DELAY = 200;
	
	private Stats stats = new Stats();
	private float xp;
	private int xpNeeded;
	private int delta;
	
	private Delay attackDelay;
	
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
		attackDelay = new Delay(ATTACK_DELAY);
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

	@Override
	public void update(int delta) {
		//check collisions
		this.delta = delta;
		rot = Util.rotatePlayerToMousePosition(Mouse.getX(), Mouse.getY(), 
				Display.getWidth() / 2, Display.getHeight() / 2);
		getInput();
	}
	

	@Override
	public void collideWith(GameObject other) {
		System.out.println("Player collision!!");
	}
	
	
	public void getInput() {
		//check for keys pressed
		if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
			move(rot + 90);
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
			move(rot + 90 + 180);
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
			move(rot + 90 + 90);
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
			move(rot);
		}
		//check mouse input
		if (Mouse.isButtonDown(0)) {
			attack();
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
	
	/**
	 * Logic of player attack
	 */
	public void attack() {
		//check if attack is possible
		if (!attackDelay.isReady()) {
			return;
		}
		//get spawn position of projectile
		float pX = (float) (x + 64 * Math.cos(Math.toRadians(rot + 90))); 
		float pY = (float) (y + 64 * Math.sin(Math.toRadians(rot + 90))); 
		Projectile bullet = new Projectile(pX, pY, rot);
		Game.objectsToAdd.add(bullet);
	}
	
	private void move(float angle) {
		float speed = stats.getSpeed();
		//diagonal movement
		if( (Keyboard.isKeyDown(Keyboard.KEY_W) && ( Keyboard.isKeyDown(Keyboard.KEY_A) || Keyboard.isKeyDown(Keyboard.KEY_D))) ||
		    (Keyboard.isKeyDown(Keyboard.KEY_S) && ( Keyboard.isKeyDown(Keyboard.KEY_A) || Keyboard.isKeyDown(Keyboard.KEY_D))))
					speed = (float) (speed / Math.sqrt(2));
		float newX = (float) (x + speed * Math.cos(Math.toRadians(angle)) * delta);
		float newY = (float) (y + speed * Math.sin(Math.toRadians(angle)) * delta);
		Rectangle newHitbox = new Rectangle((int) (newX - PLAYER_SX / 2), (int) (newY - PLAYER_SY / 2),
				(int)PLAYER_SX, (int)PLAYER_SY);
		//check collisions
		boolean collide = false;
		for (GameObject go : Game.objects) {
			if (!(go instanceof Player)) {
				//compare hitboxes
				if (Physics.checkCollision(newHitbox, go.getHitbox())) {
					collide = true;
					//notify colliding objects
					go.collideWith(this);
					this.collideWith(go);
				}
			}
		}
		//move if collision not detected and update hitbox
		if (!collide) {
			x = newX;
			y = newY;
			updateHitbox(newHitbox);
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
