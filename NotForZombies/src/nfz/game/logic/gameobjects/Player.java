package nfz.game.logic.gameobjects;

import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glTranslatef;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector2f;

import nfz.game.graphics.Sprite;
import nfz.game.logic.GameObject;
import nfz.game.logic.Stats;

public class Player extends GameObject{
	
	public static final float PLAYER_SX = 64;
	public static final float PLAYER_SY = 64;
	public static final String PLAYER_TEX_LOC = "res/player.png";
	
	private Stats stats = new Stats();
	private float xp;
	private int xpNeeded;
	
	/**
	 * create player instance on coords x.y
	 * @param x
	 * @param y
	 */
	public Player (float x, float y) {
		super(x, y, 0.1f, 1f, 0.5f, PLAYER_SX, PLAYER_SY);
		sprite = new Sprite(0.5f, 0.2f, 0.3f, PLAYER_SX, PLAYER_SY, PLAYER_TEX_LOC);
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
		stats.setSpeed(4f);
	}
	
	/**
	 * Apply player logic
	 */
	public void update() {
		rotateToMouseLocation();	
	}
	
	public void rotateToMouseLocation() {
		//rotate with mouse
		//get mouse coords
		float mouseX = Mouse.getX();
		float mouseY = Mouse.getY();
			
		//get player center coords
		float centerX = x + sprite.getSx() / 2;
		float centerY = y + sprite.getSy() / 2;
				
		//get delta values
		float deltaY = mouseY - centerY;
		float deltaX = mouseX - centerX;
		
		//calculate angle between player and mouse using arc tang
		float angle = (float) Math.atan(deltaY / deltaX);
		
		//get quadrant I, II, III, or IV
		int quadr = 0;
		if (deltaY >= 0 && deltaX >= 0) {
			quadr = 1;
			rot = (float) Math.toDegrees(angle);
		} else if (deltaY >= 0 && deltaX < 0) {
			quadr = 2;
			rot = 180 + (float) Math.toDegrees(angle);
		} else if (deltaY < 0 && deltaX < 0) {
			quadr = 3;
			rot = 180 + (float) Math.toDegrees(angle);
		} else if (deltaY < 0 && deltaX >= 0) {
			quadr = 4;
			rot = 360 + (float) Math.toDegrees(angle);
		}
		
		rot -= 90;
		
		System.out.println("Rotation: " + rot + ", DX: " + deltaX + ", DY: " + deltaY + 
				", QUADRANT: " + quadr);	
	}
	
	public void getInput() {
		//check for keys pressed
		if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
			move(0, 1);
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
			move(0, -1);
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
			move(-1, 0);
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
			move(1, 0);
		}
	}
	
	public void render() {
		glPushMatrix();
		glTranslatef(x, y, 0);
		glTranslatef(sprite.getSx() / 2, sprite.getSy() / 2, 0);
		glRotatef(rot, 0.0f, 0.0f, 1.0f);	
		glTranslatef(-(sprite.getSx() / 2), -(sprite.getSy() / 2), 0);
		sprite.render();
		glPopMatrix();
	}
	
	private void move(float magX, float magY) {
		//x += getSpeed() * Math.cos(Math.toRadians(rot));
		//y += getSpeed() * Math.sin(Math.toRadians(rot));
		x += stats.getSpeed() * magX;
		y += stats.getSpeed() * magY;
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
