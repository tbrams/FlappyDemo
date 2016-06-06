package dk.brams.flappydemo.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.sun.org.apache.xpath.internal.operations.String;

import dk.brams.flappydemo.FlappyGame;
import dk.brams.flappydemo.sprites.Bird;
import dk.brams.flappydemo.sprites.Tube;

/**
 * Created by tbrams on 6/6/2016 AD.
 */
public class PlayState extends State {

    private static final int TUBE_SPACING = 125;
    private static final int TUBE_COUNT=4;

    private Bird bird;
    private Texture bg;
    private Array<Tube> tubes;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        bird = new Bird(50,300);
        cam.setToOrtho(false, FlappyGame.WIDTH/2, FlappyGame.HEIGHT/2);
        bg = new Texture("bg.png");

        tubes = new Array<Tube>();
        for (int i = 0; i <= TUBE_COUNT; i++) {
            tubes.add(new Tube(i*(TUBE_SPACING+Tube.TUBE_WIDTH)));
        }
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            bird.jump();
        }
    }

    @Override
    protected void update(float dt) {
        handleInput();
        bird.update(dt);

        // Update cam position relative to bird
        cam.position.x = bird.getPosition().x + 80;
        cam.update();

        
        for (Tube tube: tubes) {
            // Handle tubes off view here
            if (cam.position.x-cam.viewportWidth/2 > tube.getPosTopTube().x+tube.getTopTube().getWidth()) {
                tube.reposition(tube.getPosTopTube().x+((Tube.TUBE_WIDTH+TUBE_SPACING)*TUBE_COUNT));
            }

            // Qucik and dirty check for collision...
            if (tube.collide(bird.getBounds())) {
                gsm.set(new PlayState(gsm));
                break;
            }
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(bg, cam.position.x - cam.viewportWidth / 2, 0);
        for (Tube tube:tubes) {
            sb.draw(tube.getTopTube(),tube.getPosTopTube().x, tube.getPosTopTube().y);
            sb.draw(tube.getBottomTube(), tube.getPosBottomTube().x, tube.getPosBottomTube().y);
        }
        sb.draw(bird.getBird(),bird.getPosition().x, bird.getPosition().y);
        sb.end();
    }

    @Override
    public void dispose() {
        bg.dispose();
        bird.dispose();
        for (Tube tube : tubes) {
            tube.dispose();
        }
        System.out.println("PlayState disposed ");
    }
}
