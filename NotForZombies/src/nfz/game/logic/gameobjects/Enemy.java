package nfz.game.logic.gameobjects;

import nfz.game.graphics.Sprite;
import nfz.game.logic.GameObject;
import nfz.game.logic.Stats;
import nfz.game.physics.Circle;

public abstract class Enemy extends GameObject{

	public static final float SX = 64;
	public static final float SY = 64;
	public static final String TEX_LOC = "res/enemy.png";
	protected Stats stats;
	
	public Enemy(float x, float y) {
		super(x, y, SX, SY);
		isSolid = true;
		sprite = new Sprite(SX, SY, TEX_LOC);
	}
	
}
