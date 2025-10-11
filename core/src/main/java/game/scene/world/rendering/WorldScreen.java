package game.scene.world.rendering;

import game.config.Display;
import game.data.world.WorldData;
import game.scene.common.rendering.screen.GameCamera;
import game.scene.common.rendering.screen.GameScreen;
import game.scene.world.logic.World;
import game.scene.world.logic.WorldProcessor;

public final class WorldScreen extends GameScreen {
    private final GameCamera camera;
    private final World world;

    public WorldScreen(WorldData worldData) {
        super(worldData.tileMap);

        camera = new GameCamera();
        world = new World(worldData);

        stage.addActor(new WorldLayout(world));
        multiplexer.addProcessor(new WorldProcessor(world));
    }

    @Override
    public void show() {
        float zoom = map.height / 16.0f;
        camera.center(map.width * Display.TILE_SIZE, map.height * Display.TILE_SIZE);
        camera.zoom = zoom;
        camera.update();
        batch.setProjectionMatrix(camera.combined);
    }

    @Override
    protected void update(float delta) {
        world.update(delta);
    }

    @Override
    protected void draw() {
        batch.begin();
        map.draw(batch, 10);
        world.getEntities().forEach(entity -> entity.draw(batch, 1.0f));
        batch.end();
    }
}
