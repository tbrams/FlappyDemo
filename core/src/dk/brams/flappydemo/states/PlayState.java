package dk.brams.flappydemo.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import dk.brams.flappydemo.FlappyGame;
import dk.brams.flappydemo.sprites.Bird;
import dk.brams.flappydemo.sprites.Tube;

public class PlayState extends State {

    private static final int TUBE_SPACING = 125;
    private static final int TUBE_COUNT=4;
    private static final int GROUND_Y_OFFSET=-50;

    private Bird mBird;
    private Texture mBackground;
    private Texture mGround;
    private Vector2 mGroundPos1, mGroundPos2;
    private Array<Tube> mTubeArray;
    private Texture mGameOver;

    private boolean mIsGameOver;
    private Vector2 mGameOverPosition;

    public PlayState(GameStateManager gsm) {
        super(gsm);

        mIsGameOver = false;
        mGameOver = new Texture("gameover.png");
        mGameOverPosition = new Vector2();


        mBird = new Bird(50,300);
        mBackground = new Texture("bg.png");
        mGround = new Texture("ground.png");
        mGroundPos1 = new Vector2(mCam.position.x - mCam.viewportWidth / 2, GROUND_Y_OFFSET);
        mGroundPos2 = new Vector2(mCam.position.x - mCam.viewportWidth / 2+ mGround.getWidth(), GROUND_Y_OFFSET);
        mGameOverPosition = new Vector2(mCam.position.x + mCam.viewportWidth / 2, 100);

        mTubeArray = new Array<Tube>();
        for (int i = 0; i <= TUBE_COUNT; i++) {
            mTubeArray.add(new Tube(i*(TUBE_SPACING+Tube.TUBE_WIDTH)));
        }

        mCam.setToOrtho(false, FlappyGame.WIDTH/2, FlappyGame.HEIGHT/2);
    }


    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            if (mIsGameOver) {
                //clear game over logo and start the game
                mGSM.set(new PlayState(mGSM));
            } else {
                mBird.jump();
            }
        }
    }

    @Override
    protected void update(float delta) {
        handleInput();
        updateGround();
        mBird.update(delta);

        // Update mCam position relative to mBird
        mCam.position.x = mBird.getPosition().x + 80;
        mCam.update();

        mGameOverPosition.x = mCam.position.x - mGameOver.getWidth() / 2;
        mGameOverPosition.y = mCam.position.y;


        for (Tube tube: mTubeArray) {
            // Handle mTubeArray off view here
            if (mCam.position.x - mCam.viewportWidth / 2 > tube.getPosTopTube().x + tube.getTopTube().getWidth()) {
                tube.reposition(tube.getPosTopTube().x + ((Tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT));
            }

            // Quick and dirty check for collision...
            if (tube.collide(mBird.getBounds())) {
                mIsGameOver = true;
                break;
            }
        }

        // Ground collision check
        if (mBird.getPosition().y <= mGround.getHeight() + GROUND_Y_OFFSET) {
            mIsGameOver = true;
        }
    }


    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.setProjectionMatrix(mCam.combined);

        spriteBatch.begin();
        spriteBatch.draw(mBackground, mCam.position.x - mCam.viewportWidth / 2, 0);

        for (Tube tube: mTubeArray) {
            spriteBatch.draw(tube.getTopTube(),tube.getPosTopTube().x, tube.getPosTopTube().y);
            spriteBatch.draw(tube.getBottomTube(), tube.getPosBotTube().x, tube.getPosBotTube().y);
        }
        spriteBatch.draw(mGround, mGroundPos1.x, mGroundPos1.y);
        spriteBatch.draw(mGround, mGroundPos2.x, mGroundPos2.y);

        spriteBatch.draw(mBird.getTexture(), mBird.getPosition().x, mBird.getPosition().y);

        if (mIsGameOver) {
            spriteBatch.draw(mGameOver, mGameOverPosition.x, mGameOverPosition.y);
        }

        spriteBatch.end();
    }

    public void updateGround() {
        if (mCam.position.x - mCam.viewportWidth/2> mGroundPos1.x+ mGround.getWidth()) {
            mGroundPos1.add(mGround.getWidth() * 2, 0);
        }
        if (mCam.position.x - mCam.viewportWidth/2> mGroundPos2.x+ mGround.getWidth()) {
            mGroundPos2.add(mGround.getWidth() * 2, 0);
        }
    }


    @Override
    public void dispose() {
        mBackground.dispose();
        mGround.dispose();
        mBird.dispose();
        mGameOver.dispose();
        for (int i = 0; i < mTubeArray.size; i++) {
            mTubeArray.get(i).dispose();
        }

        System.out.println("Play State disposed ");
    }
}
