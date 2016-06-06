package dk.brams.flappydemo;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import dk.brams.flappydemo.states.GameStateManager;
import dk.brams.flappydemo.states.MenuState;

public class FlappyGame extends ApplicationAdapter {
	public static final int WIDTH=480;
	public static final int HEIGHT=800;
	public static final String TITLE = "Flappy Game";

	private GameStateManager mGSM;
	private SpriteBatch mBatch;
	private Music mMusic;

	@Override
	public void create () {
		mBatch = new SpriteBatch();
		mGSM = new GameStateManager();
		Gdx.gl.glClearColor(1, 0, 0, 1);

		mMusic = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
		mMusic.setLooping(true);
		mMusic.setVolume(.1f);
		mMusic.play();

		mGSM.push(new MenuState(mGSM));

	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		mGSM.update(Gdx.graphics.getDeltaTime());
		mGSM.render(mBatch);
	}

	@Override
	public void dispose() {
		super.dispose();
		mMusic.dispose();
	}
}
