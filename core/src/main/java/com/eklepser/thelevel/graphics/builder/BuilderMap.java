package com.eklepser.thelevel.graphics.builder;

import com.eklepser.thelevel.logic.world.common.Configuration;

public class BuilderMap {
    private final GameScreen screen;

    public BuilderMap(Configuration config) {
        screen = new GameScreen(config);
    }

    public GameScreen getScreen() {
        return screen;
    }
}
