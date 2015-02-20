package nfz.game.logic.gameobjects;

import java.awt.Point;

import javax.vecmath.Vector2f;

import nfz.game.engine.Delay;
import nfz.game.engine.Game;
import nfz.game.logic.GameObject;
import nfz.game.logic.Stats;
import nfz.game.physics.Circle;
import nfz.game.physics.Physics;
import nfz.game.physics.Util;

public class MeleeEnemy extends Enemy{

	public static final float ATTACK_RANGE = 85;
	
	private Player target;
	private Delay attackSpeed;
	
	private Vector2f position;
	private Vector2f velocity;
	private Vector2f steerForce;
	private Vector2f desiredVelocity;
	private float distanceToTarget;
	
	public MeleeEnemy(float x, float y, Player player) {
		super(x, y);
		this.target = player;
		
		desiredVelocity = new Vector2f();
		position = new Vector2f(x, y);
		velocity = new Vector2f(0 ,0);
		steerForce = new Vector2f();
		distanceToTarget = 0f;
		
		stats = new Stats();
		stats.setSpeed(1.5f);
		stats.setMaxHealth(100);
		stats.setCurrHealth(100);
		hitbox = new Circle(x,y,SX/2);
		attackSpeed = new Delay(1000);
	}

	/**
	 * Steer enemy to move towards target
	 */
	public void seek(Vector2f targetPosition) {
		desiredVelocity.sub(targetPosition, position);
		distanceToTarget = desiredVelocity.length();
		desiredVelocity.normalize();
		desiredVelocity.scale(stats.getSpeed());
		steerForce.sub(desiredVelocity, velocity);
	}
	
	/**
	 * Steer the enemy to move away from target position
	 * @param targetPosition
	 */
	public void flee(Vector2f targetPosition) {
		desiredVelocity.sub(position, targetPosition);
		distanceToTarget = desiredVelocity.length();
		desiredVelocity.normalize();
		desiredVelocity.scale(stats.getSpeed());
		steerForce.sub(desiredVelocity, velocity);
	}
	
	@Override
	public void update(int delta) {
		Vector2f targetPos = new Vector2f(target.getX(), target.getY());
		
		seek(targetPos);
		
		Vector2f acceleration = new Vector2f();
		acceleration.setX(steerForce.getX());
		acceleration.setY(steerForce.getY());
		velocity.add(acceleration);
		position.add(velocity);
		
		x = position.getX();
		y = position.getY();
		rot = Util.rotateToPoint(targetPos.getX(), targetPos.getY(), 
				x, y);
		updateHitbox(new Circle(x, y, hitbox.getR()));
		
		/*
		//check if player is in attack range
		if (Point.distance(x, y, target.getX(), target.getY()) <= ATTACK_RANGE) {
			attack();
		}
		
		//rot = Util.rotateToPoint(target.getX(), target.getY(), x, y);
		
		float newX = (float) (x + stats.getSpeed() * Math.cos(Math.toRadians(rot + 90)) * delta);
		float newY = (float) (y + stats.getSpeed() * Math.sin(Math.toRadians(rot + 90)) * delta);
		
		Circle newHitbox = new Circle(newX,newY,SX/2);
		
		//check collision
		boolean collide = false;
		for (GameObject go : Game.objects) {
			//compare hitboxes
			if(!go.equals(this)){
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
		if(stats.getCurrHealth()<=0){
			destroy();
			Game.objectsToAdd.add(new MeleeEnemy(0, 0, target));
			target.addXP(29); 
			System.out.println("Exp: " + target.getXp() + "/" + target.getXpNeeded());
			
		}
		*/
	}

	private void attack() {
		if (attackSpeed.isReady()) {
			target.getStats().setCurrHealth(target.getStats().getCurrHealth() - 10);
		}
	}

	@Override
	public void collideWith(GameObject other) {
		if(other instanceof Projectile){
			this.stats.setCurrHealth(stats.getCurrHealth() - 10);
		}
	}

}
