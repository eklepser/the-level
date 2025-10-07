package game.common.rendering;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import game.common.tilemap.TileMap;
import game.common.tilemap.Tileset;
import game.resources.Assets;

public abstract class GameScreen extends BaseScreen {
    protected final TileMap map;
    protected final SpriteBatch batch;
    protected final Tileset tileset;

    public GameScreen(TileMap map) {
        this.map = map;

        batch = new SpriteBatch();

        tileset = Assets.getTileset();
    }

    @Override
    public void render(float delta) {
        renderClear();
        renderMap();
        renderStage(delta);
    }

    protected final void renderMap() {
        batch.begin();
        map.draw(batch, 10);
        batch.end();
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

    public Tileset getTileset() {
        return tileset;
    }
}
