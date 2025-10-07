package game.scene.world.rendering;

import game.common.rendering.screen.GameCamera;
import game.common.rendering.screen.GameScreen;
import game.config.Display;
import game.scene.world.logic.World;
import game.scene.world.logic.WorldConfiguration;
import game.scene.world.logic.WorldProcessor;

public final class WorldScreen extends GameScreen {
    private final GameCamera camera;
    private final World world;

    public WorldScreen(WorldConfiguration config) {
        super(config.tileMap);

        camera = new GameCamera();
        world = new World(config, this);

        stage.addActor(new WorldLayout());
    }

    @Override
    public void show() {
        camera.center(map.width * Display.TILE_SIZE, map.height * Display.TILE_SIZE);
        camera.offset(-Display.EDITOR_MENU_SCALE * Display.VIEWPORT_WIDTH / 2.0f, 0);
        batch.setProjectionMatrix(camera.combined);


        multiplexer.addProcessor(new WorldProcessor(world));
        multiplexer.addProcessor(stage);
    }

    @Override
    protected void update(float delta) {
        world.update(delta);
    }

    @Override
    protected void draw() {
        batch.begin();
        world.getEntities().forEach(entity -> entity.draw(batch, 1.0f));
        batch.end();
    }
}
