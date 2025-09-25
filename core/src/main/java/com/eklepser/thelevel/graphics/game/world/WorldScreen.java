package com.eklepser.thelevel.graphics.game.world;

import com.badlogic.gdx.Game;
import com.eklepser.thelevel.graphics.game.GameScreen;
import com.eklepser.thelevel.logic.world.world.World;

public class WorldScreen extends GameScreen {
    private final WorldLayout root;

    public WorldScreen(Game game, World world) {
        super(world);
        root = new WorldLayout(this, world);
    }

    @Override
    public void show() {
        stage.addActor(root);
        inputMultiplexer.addProcessor(stage);
    }

    // Getters:
    public WorldLayout getRoot() {
        return root;
    }
}
