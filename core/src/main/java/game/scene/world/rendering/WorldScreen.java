package game.scene.world.rendering;

import com.badlogic.gdx.Game;
import game.config.GraphicsConstants;
import game.scene.common.rendering.GameScreen;
import game.scene.world.logic.World;
import game.scene.world.logic.WorldConfiguration;
import game.scene.world.logic.WorldProcessor;

public class WorldScreen extends GameScreen {
    private final World world;
    private final WorldLayout layout;

    public WorldScreen(Game game, WorldConfiguration config) {
        super(config.tileMap);
        world = new World(config, this);
        layout = new WorldLayout(this, world);
        inputMultiplexer.addProcessor(new WorldProcessor(game, world));
    }

    @Override
    protected void setupCamera() {
        float levelWidth = map.width * GraphicsConstants.TILE_SIZE;
        float levelHeight = map.height * GraphicsConstants.TILE_SIZE;

        float levelCenterX = levelWidth / 2f;
        float levelCenterY = levelHeight / 2f;

        camera.position.set(levelCenterX, levelCenterY, 0);
        camera.update();
    }

    @Override
    public void show() {
        stage.addActor(layout);
    }

    // Getters:
    public WorldLayout getLayout() {
        return layout;
    }
}
