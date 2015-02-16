package nfz.game.components.implement;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import nfz.game.components.InputComponent;
import nfz.game.logic.gameobjects.Player;

public class PlayerInputComponent implements InputComponent {

	Player player;
	
	public PlayerInputComponent(Player player) {
		this.player = player;
	}
	
	@Override
	public void update() {

		//check for keys pressed
				if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
					player.move(player.getRot() + 90);
				}
				if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
					player.move(player.getRot() + 90 + 180);
				}
				if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
					player.move(player.getRot() + 90 + 90);
				}
				if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
					player.move(player.getRot());
				}
				//check mouse input
				if (Mouse.isButtonDown(0)) {
					player.attack();
				}
		
	}

}
