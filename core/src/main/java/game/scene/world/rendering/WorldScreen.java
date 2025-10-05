package game.scene.world.rendering;

import com.badlogic.gdx.Game;
import game.common.rendering.GameScreen;
import game.scene.world.logic.World;
import game.scene.world.logic.WorldConfiguration;
import game.scene.world.logic.WorldProcessor;

public final class WorldScreen extends GameScreen {
    private final World world;
    private final WorldLayout layout;

    public WorldScreen(Game game, WorldConfiguration config) {
        super(game, config.tileMap);
        world = new World(config, this);
        layout = new WorldLayout();
        multiplexer.addProcessor(new WorldProcessor(game, world));
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
