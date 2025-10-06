package game.scene.world.rendering;

import com.badlogic.gdx.Game;
import game.common.rendering.GameCamera;
import game.common.rendering.GameScreen;
import game.config.Display;
import game.scene.world.logic.World;
import game.scene.world.logic.WorldConfiguration;
import game.scene.world.logic.WorldProcessor;

public final class WorldScreen extends GameScreen {
    private final GameCamera camera;
    private final World world;
    private final WorldLayout layout;

    public WorldScreen(Game game, WorldConfiguration config) {
        super(game, config.tileMap);

        camera = new GameCamera();

        world = new World(config, this);
        layout = new WorldLayout();
    }

    @Override
    public void show() {
        camera.center(map.width * Display.TILE_SIZE, map.height * Display.TILE_SIZE);
        camera.offset(-Display.EDITOR_MENU_SCALE * Display.VIEWPORT_WIDTH / 2.0f, 0);
        batch.setProjectionMatrix(camera.combined);

        stage.addActor(layout);

        multiplexer.addProcessor(new WorldProcessor(game, world));
        multiplexer.addProcessor(stage);
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        world.update(delta);

        batch.begin();
        world.getEntities().forEach(entity -> entity.draw(batch, 1.0f));
        batch.end();
    }

    // Getters:
    public WorldLayout getLayout() {
        return layout;
    }
}
