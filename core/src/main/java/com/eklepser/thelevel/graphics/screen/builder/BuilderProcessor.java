package com.eklepser.thelevel.graphics.screen.builder;

import com.badlogic.gdx.InputAdapter;
import com.eklepser.thelevel.graphics.render.TileMap;

public class BuilderProcessor extends InputAdapter {
    private final BuilderScreen screen;
    private final TileMap map;
    private int previousKey;

    public BuilderProcessor(BuilderScreen screen, TileMap map) {
        this.screen = screen;
        this.map = map;
    }
}
