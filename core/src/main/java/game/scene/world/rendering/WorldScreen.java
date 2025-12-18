package game.scene.world.rendering;

import com.badlogic.gdx.math.Matrix4;
import game.config.Display;
import game.data.world.WorldData;
import game.scene.common.rendering.screen.GameCamera;
import game.scene.common.rendering.screen.GameScreen;
import game.scene.level.rendering.BackgroundEffect;
import game.scene.world.logic.World;
import game.scene.world.logic.WorldProcessor;

public final class WorldScreen extends GameScreen {
    private final GameCamera camera;
    private final World world;
    private final BackgroundEffect backgroundParticles;

    public WorldScreen(WorldData worldData) {
        super(worldData.tileMap);

        camera = new GameCamera();
        world = new World(worldData);
        backgroundParticles = new BackgroundEffect(BackgroundEffect.Shape.SQUARE);

        stage.addActor(new WorldLayout(world));
        multiplexer.addProcessor(new WorldProcessor(world));
    }

    @Override
    public void show() {
        float zoom = map.height / 16.0f;
        camera.center(map.width * Display.TILE_SIZE, map.height * Display.TILE_SIZE - 20);
        camera.zoom = zoom;
        camera.update();
        batch.setProjectionMatrix(camera.combined);
    }

    @Override
    protected void update(float delta) {
        world.update(delta);
        backgroundParticles.update(delta, Display.VIEWPORT_WIDTH, Display.VIEWPORT_HEIGHT);
    }

    @Override
    protected void draw() {
        Matrix4 screenMatrix = new Matrix4().setToOrtho2D(
            0, 0,
            Display.VIEWPORT_WIDTH,
            Display.VIEWPORT_HEIGHT
        );
        backgroundParticles.draw(screenMatrix);

        batch.begin();
        map.draw(batch, 10);
        world.getEntities().forEach(entity -> entity.draw(batch, 1.0f));
        batch.end();
    }
}
