package com.eklepser.thelevel.graphics.game.world;

import com.badlogic.gdx.Game;
import com.eklepser.thelevel.graphics.builder.GameScreen;
import com.eklepser.thelevel.logic.world.world.World;
import com.eklepser.thelevel.logic.world.world.WorldConfiguration;
import com.eklepser.thelevel.logic.world.world.WorldProcessor;

public class WorldScreen extends GameScreen {
    private final World world;
    private final WorldLayout root;

    public WorldScreen(Game game, WorldConfiguration config) {
        super(config);
        world = new World(config, this);
        root = new WorldLayout(this, world);
        inputMultiplexer.addProcessor(new WorldProcessor(game, world));
    }

    @Override
    public void show() {
        stage.addActor(root);
    }

    // Getters:
    public WorldLayout getRoot() {
        return root;
    }
}
