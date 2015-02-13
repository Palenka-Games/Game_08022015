package nfz.game.logic.gameobjects;

import java.awt.Point;
import java.awt.Rectangle;

import nfz.game.engine.Game;
import nfz.game.graphics.Sprite;
import nfz.game.logic.GameObject;
import nfz.game.physics.Physics;

public class Projectile extends GameObject {

	public static final float SX = 24;
	public static final float SY = 24;
	public static final String TEX_LOC = "res/bullet.png";
	public static final float SPEED = 0.5f;
	public static final float MAX_DISTANCE = 450;
	
	private float distanceTraveled;
	
	public Projectile(float x, float y, float rot) {
		super(x, y, SX, SY);
		this.rot = rot;
		isSolid = true;
		sprite = new Sprite(SX, SY, TEX_LOC);
		hitbox = new Rectangle((int) (x - SX / 2), (int) (y - SY / 2), 
				(int) SX, (int) SY);
		distanceTraveled = 0;
	}
	
	@Override
	public void update(int delta) {
		float newX = (float) (x + SPEED * Math.cos(Math.toRadians(rot + 90)) * delta);
		float newY = (float) (y + SPEED * Math.sin(Math.toRadians(rot + 90)) * delta);
		
		distanceTraveled += Point.distance(x, y, newX, newY);
		if (distanceTraveled >= MAX_DISTANCE) {
			destroy();
			return;
		}
		
		Rectangle newHitbox = new Rectangle((int) (newX - SX / 2), (int) (newY - SY / 2), 
				(int) SX, (int) SY);
		
		//check collision
		boolean collide = false;
		for (GameObject go : Game.objects) {
			if (!(go instanceof Projectile)) {
				//compare hitboxes
				if (Physics.checkCollision(newHitbox, go.getHitbox())) {
					collide = true;
					//notify colliding objects
					go.collideWith(this);
					this.collideWith(go);
				}
			}
		}
		
		if (!collide) {
			x = newX;
			y = newY;
			updateHitbox(newHitbox);
		}
	}

	@Override
	public void collideWith(GameObject other) {
		if (other instanceof Obstacle) {
			System.out.println("Cant shoot through a rock, gonna explode");
			destroy();
		}
	}
	
}
