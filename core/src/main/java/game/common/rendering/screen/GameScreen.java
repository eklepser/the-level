package game.common.rendering.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import game.common.rendering.tilemap.TileMap;

public abstract class GameScreen extends BaseScreen {
    protected final TileMap map;
    protected final SpriteBatch batch;

    public GameScreen(TileMap map) {
        super();

        this.map = map;

        batch = new SpriteBatch();
    }

    protected abstract void update(float delta);

    protected abstract void draw();

    @Override
    public void render(float delta) {
        // Clear screen
        Gdx.gl.glClearColor(0.05f, 0.03f, 0.05f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        update(delta);
        stage.act(delta);

        draw();
        stage.draw();
    }

    // Getters:
    public TileMap getMap() {
        return map;
    }

    public Stage getStage() {
        return stage;
    }

    public SpriteBatch getBatch() {
        return batch;
    }
}
