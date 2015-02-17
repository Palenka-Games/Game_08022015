package nfz.game.logic.gameobjects;

import nfz.game.graphics.Sprite;
import nfz.game.logic.GameObject;
import nfz.game.physics.Circle;

public class Obstacle extends GameObject {

	public static final float SX = 64;
	public static final float SY = 64;
	public static final String TEX_LOC = "res/rock.png";
	
	public Obstacle(float x, float y) {
		super(x,y, SX, SY);
		isSolid = true;
		sprite = new Sprite(SX, SY, TEX_LOC);
		hitbox = new Circle(x,y,SX/2);
	}
	
	@Override
	public void update(int delta){
	}

	@Override
	public void collideWith(GameObject other) {
	}

}
