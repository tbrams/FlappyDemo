package dk.brams.flappydemo.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;


public class Tube {
    private static final int FLUCTUATION = 130;
    private static final int TUBE_GAP = 100;
    private static final int LOWEST_OPENING= 120;
    public static final int TUBE_WIDTH=52;
    private Texture mTopTube;
    private Texture mBotTube;
    private Vector2 posTopTube, posBotTube;
    private Rectangle mBoundsTop, mBoundsBot;
    private Random mRand;

    public Texture getTopTube() {
        return mTopTube;
    }

    public Texture getBottomTube() {
        return mBotTube;
    }

    public Vector2 getPosTopTube() {
        return posTopTube;
    }

    public Vector2 getPosBotTube() {
        return posBotTube;
    }

    public Tube(float x){
        mTopTube = new Texture("toptube.png");
        mBotTube = new Texture("bottomtube.png");
        mRand = new Random();

        posTopTube = new Vector2(x, mRand.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
        posBotTube = new Vector2(x, posTopTube.y - mBotTube.getHeight()-TUBE_GAP);

        mBoundsTop = new Rectangle(getPosTopTube().x, getPosTopTube().y, mTopTube.getWidth(), mTopTube.getHeight());
        mBoundsBot = new Rectangle(getPosBotTube().x, getPosBotTube().y, mBotTube.getWidth(), mBotTube.getHeight());
    }

    public void reposition(float x) {
        posTopTube.set(x, mRand.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
        posBotTube.set(x, posTopTube.y - mBotTube.getHeight()-TUBE_GAP);
        mBoundsTop.setPosition(posTopTube.x, posTopTube.y);
        mBoundsBot.setPosition(posBotTube.x, posBotTube.y);
    }

    public boolean collide(Rectangle player) {
        return player.overlaps(mBoundsTop) || player.overlaps(mBoundsBot);
    }

    public void dispose(){
        mTopTube.dispose();
        mBotTube.dispose();
    }

}
