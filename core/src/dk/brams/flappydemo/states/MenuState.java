package dk.brams.flappydemo.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import dk.brams.flappydemo.FlappyGame;

/**
 * Created by tbrams on 6/6/2016 AD.
 */
public class MenuState extends State {
    private Texture background;
    private Texture playBtn;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("bg.png");
        playBtn = new Texture("playBtn.png");
    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()){
            gsm.set(new PlayState(gsm));
            dispose();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void dispose() {
        background.dispose();
        playBtn.dispose();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(background,0,0, FlappyGame.WIDTH, FlappyGame.HEIGHT);
        sb.draw(playBtn, (FlappyGame.WIDTH / 2) - playBtn.getWidth() / 2, FlappyGame.HEIGHT / 2);
        sb.end();
    }
}
