package com.eklepser.thelevel.graphics.screen.world;

import com.badlogic.gdx.Game;
import com.eklepser.thelevel.graphics.render.MapLoader;
import com.eklepser.thelevel.graphics.screen.GameScreen;
import com.eklepser.thelevel.logic.world.world.World;
import com.eklepser.thelevel.logic.world.world.WorldConfiguration;
import com.eklepser.thelevel.logic.world.world.WorldProcessor;

public class WorldScreen extends GameScreen {
    private final World world;
    private final WorldLayout layout;

    public WorldScreen(Game game, WorldConfiguration config) {
        super(MapLoader.load(config.mapName));
        world = new World(config, this);
        layout = new WorldLayout(this, world);
        inputMultiplexer.addProcessor(new WorldProcessor(game, world));
    }

    @Override
    protected void setupCamera() {
        float levelWidth = map.width * TILE_SIZE;
        float levelHeight = map.height * TILE_SIZE;

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
