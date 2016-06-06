package dk.brams.flappydemo.sprites;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class Animation {
    private Array<TextureRegion> mFrames;
    private float mMaxFrameTime;
    private float mCurrentFrameTime;
    private int mFrameCount;
    private int mFrame;

    public Animation(TextureRegion texture, int frameCount, float cycleTime) {
        mFrames = new Array<TextureRegion>();
        int frameWidth = texture.getRegionWidth()/frameCount;
        for (int i = 0; i < frameCount; i++) {
            mFrames.add(new TextureRegion(texture, i * frameWidth, 0, frameWidth, texture.getRegionHeight()));
        }
        this.mFrameCount =frameCount;
        mMaxFrameTime = cycleTime/frameCount;
        mFrame = 0;
    }

    public void update(float delta){
        mCurrentFrameTime +=delta;
        if (mCurrentFrameTime > mMaxFrameTime){
            mFrame++;
            mCurrentFrameTime =0;
        }
        if (mFrame == mFrameCount){
            mFrame =0;
        }
    }

    public TextureRegion getFrame(){
        return mFrames.get(mFrame);
    }
}
