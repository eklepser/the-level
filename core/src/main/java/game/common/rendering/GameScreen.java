package game.common.rendering;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import game.common.rendering.tile.GameCamera;
import game.common.rendering.tile.TileMap;
import game.common.rendering.tile.Tileset;
import game.config.GraphicsConstants;
import game.resources.Assets;

public abstract class GameScreen extends BaseScreen {
    protected final TileMap map;
    protected final SpriteBatch batch;
    protected final Tileset tileset;
    protected final GameCamera camera;

    public GameScreen(Game game, TileMap map) {
        super(game);
        this.map = map;

        batch = new SpriteBatch();

        tileset = Assets.getTileset();

        camera = new GameCamera(GraphicsConstants.VIEWPORT_WIDTH, GraphicsConstants.VIEWPORT_HEIGHT);
    }

    @Override
    public void render(float delta) {
        renderClear();
        renderMap();
        renderStage(delta);
    }

    protected final void renderMap() {
        batch.setProjectionMatrix(camera.combined);
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

    public GameCamera getCamera() {
        return camera;
    }
}
