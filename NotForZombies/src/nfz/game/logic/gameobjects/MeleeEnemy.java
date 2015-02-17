package nfz.game.logic.gameobjects;

import java.awt.Point;

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
	
	public MeleeEnemy(float x, float y, Player player) {
		super(x, y);
		this.target = player;
		stats = new Stats();
		stats.setSpeed(0.2f);
		stats.setMaxHealth(100);
		stats.setCurrHealth(100);
		hitbox = new Circle(x,y,SX/2);
		attackSpeed = new Delay(1000);
	}

	@Override
	public void update(int delta) {
		
		//check if player is in attack range
		if (Point.distance(x, y, target.getX(), target.getY()) <= ATTACK_RANGE) {
			attack();
			return;
		}
		
		rot = Util.rotateToPoint(target.getX(), target.getY(), x, y);
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
