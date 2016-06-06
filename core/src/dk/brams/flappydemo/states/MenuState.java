package dk.brams.flappydemo.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import dk.brams.flappydemo.FlappyGame;

public class MenuState extends State {
    private Texture mBackground;
    private Texture mPlayButton;

    public MenuState(GameStateManager gsm) {
        super(gsm);

        mBackground = new Texture("bg.png");
        mPlayButton = new Texture("playbtn.png");

        mCam.setToOrtho(false, FlappyGame.WIDTH/2, FlappyGame.HEIGHT/2);
    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()){
            mGSM.set(new PlayState(mGSM));
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch spriteBatch) {

        spriteBatch.setProjectionMatrix(mCam.combined);

        spriteBatch.begin();
        spriteBatch.draw(mBackground, 0, 0);
        spriteBatch.draw(mPlayButton, mCam.position.x - mPlayButton.getWidth() / 2, mCam.position.y);
        spriteBatch.end();
    }


    @Override
    public void dispose() {
        mBackground.dispose();
        mPlayButton.dispose();
        System.out.println("Menu State disposed ");
    }
}
