package dk.brams.flappydemo.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import dk.brams.flappydemo.FlappyGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.height = FlappyGame.HEIGHT;
		config.width = FlappyGame.WIDTH;
		config.title = FlappyGame.TITLE;

		new LwjglApplication(new FlappyGame(), config);
	}
}
