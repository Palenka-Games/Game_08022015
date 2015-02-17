package nfz.game.logic.gameobjects;

import java.awt.Point;
import nfz.game.engine.Game;
import nfz.game.graphics.Sprite;
import nfz.game.logic.GameObject;
import nfz.game.physics.Circle;
import nfz.game.physics.Physics;

public class Projectile extends GameObject {

	public static final float SX = 24;
	public static final float SY = 24;
	public static final String TEX_LOC = "res/bullet.png";
	public static final float SPEED = 0.7f;
	public static final float MAX_DISTANCE = 300;
	
	private float distanceTraveled;
	
	public Projectile(float x, float y, float rot) {
		super(x, y, SX, SY);
		this.rot = rot;
		isSolid = true;
		sprite = new Sprite(SX, SY, TEX_LOC);
		hitbox = new Circle(x,y,SX/2);
		distanceTraveled = 0;
	}
	
	@Override
	public void update(int delta) {
		float newX = (float) (x + SPEED * Math.cos(Math.toRadians(rot + 90)) * delta);
		float newY = (float) (y + SPEED * Math.sin(Math.toRadians(rot + 90)) * delta);
		distanceTraveled +=Point.distance(newX, newY, x, y);
		
		//destroy bullet if too far
		if(distanceTraveled>MAX_DISTANCE) {
			destroy();
			return;
		}
		
		Circle newHitbox = new Circle(newX,newY,SX/2);
		
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
