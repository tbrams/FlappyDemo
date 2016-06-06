package dk.brams.flappydemo.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public abstract class State {
    protected OrthographicCamera mCam;
    protected Vector3 mMouse;
    protected GameStateManager mGSM;

    protected State(GameStateManager gsm) {
        mGSM = gsm;
        mCam = new OrthographicCamera();
        mMouse = new Vector3();
    }

    protected abstract void handleInput();

    protected abstract void update(float delta);

    public abstract void render(SpriteBatch spriteBatch);

    public abstract void dispose();
}
