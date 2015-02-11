package nfz.game.logic.gameobjects;

import java.awt.Rectangle;

import nfz.game.graphics.Sprite;
import nfz.game.logic.GameObject;

public class Obstacle extends GameObject {

	public static final float SX = 64;
	public static final float SY = 64;
	public static final String TEX_LOC = "res/rock.png";
	
	public Obstacle(float x, float y) {
		super(x,y, SX, SY);
		isSolid = true;
		sprite = new Sprite(SX, SY, TEX_LOC);
		hitbox = new Rectangle((int) (x - SX / 2), (int) (y - SY / 2), 
				(int) SX, (int) SY);
	}
	
	@Override
	public void update(int delta){
	}

	@Override
	public void collideWith(GameObject other) {
		// TODO Auto-generated method stub
		
	}

}
