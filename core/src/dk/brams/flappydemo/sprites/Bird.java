package dk.brams.flappydemo.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;


public class Bird {
    private static final int GRAVITY = -15;
    private static final int MOVEMENT = 100;
    private Vector3 mPosition;
    private Vector3 mVelocity;
    private Rectangle mBounds;
    private Animation mBirdAnimation;
    private Texture mBirdsTexture;
    private Sound mSound;

    public Bird(int x, int y){
        mPosition = new Vector3(x, y, 0);
        mVelocity = new Vector3(0, 0, 0);
        mBirdsTexture = new Texture("birdanimation.png");
        mBirdAnimation = new Animation(new TextureRegion(mBirdsTexture), 3, 0.5f);
        mBounds = new Rectangle(x, y, mBirdsTexture.getWidth()/3, mBirdsTexture.getHeight());
        mSound = Gdx.audio.newSound(Gdx.files.internal("sfx_wing.ogg"));
    }

    public Vector3 getPosition() {
        return mPosition;
    }

    public TextureRegion getTexture() {
        return mBirdAnimation.getFrame();
    }

    public Rectangle getBounds() {
        return mBounds;
    }

    public void update(float delta) {
        mBirdAnimation.update(delta);

        if (mPosition.y>0)
            mVelocity.add(0, GRAVITY, 0);
        mVelocity.scl(delta);
        mPosition.add(MOVEMENT*delta, mVelocity.y, 0);
        if (mPosition.y<0)
            mPosition.y = 0;
        mVelocity.scl(1 / delta);
        mBounds.setPosition(mPosition.x, mPosition.y);
    }

    public void jump() {
        mVelocity.y = 250;
        mSound.play(0.5f);
    }



    public void dispose() {
        mBirdsTexture.dispose();
        mSound.dispose();
    }
}
