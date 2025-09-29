package com.eklepser.thelevel.graphics.screen.builder;

import com.eklepser.thelevel.graphics.render.TileMap;
import com.eklepser.thelevel.graphics.screen.GameScreen;

public class Builder {
    private final BuilderScreen screen;
    private final TileMap map;

    private int selectedTile;

    public Builder(BuilderScreen screen) {
        this.screen = screen;
        map = screen.getMap();
    }

    public void update(float delta) {

    }

    // Getters & setters:
    public GameScreen getScreen() {
        return screen;
    }

    public int getSelectedTile() {
        return selectedTile;
    }

    public void setSelectedTile(int selectedTile) {
        this.selectedTile = selectedTile;
    }
}
