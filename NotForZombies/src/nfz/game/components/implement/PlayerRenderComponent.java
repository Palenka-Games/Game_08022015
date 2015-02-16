package nfz.game.components.implement;

import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glTranslatef;

import org.lwjgl.opengl.Display;

import nfz.game.components.RenderComponent;
import nfz.game.logic.gameobjects.Player;

public class PlayerRenderComponent implements RenderComponent {

	private Player player;
	
	public PlayerRenderComponent(Player player) {
		this.player = player;
	}
	
	@Override
	public void update() {
		glPushMatrix();
		//translate to the center of the screen
		glTranslatef(Display.getWidth() / 2 - Player.PLAYER_SX / 2, 
				Display.getHeight() / 2 - Player.PLAYER_SY / 2, 0);
		//translate to the center of player to apply rotation
		glTranslatef(player.getSprite().getSx() / 2, player.getSprite().getSy() / 2, 0);
		glRotatef(player.getRot(), 0.0f, 0.0f, 1.0f);	
		glTranslatef(-(player.getSprite().getSx() / 2), -(player.getSprite().getSy() / 2), 0);
		player.getSprite().render();
		glPopMatrix();
	}

}
