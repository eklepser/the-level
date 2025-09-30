package com.eklepser.thelevel.graphics.screen.builder;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.eklepser.thelevel.graphics.render.TileMap;
import com.eklepser.thelevel.graphics.render.TileSet;
import com.eklepser.thelevel.graphics.screen.GameScreen;
import com.eklepser.thelevel.util.Resources;

public class Builder {
    private final BuilderScreen screen;
    private final TileMap map;

    private EditMode mode = EditMode.INSERT_BLOCK;
    private int selectedTileId;

    public Builder(BuilderScreen screen) {
        this.screen = screen;
        map = screen.getMap();
    }

    // Getters & setters:
    public GameScreen getScreen() {
        return screen;
    }

    public EditMode getMode() {
        return mode;
    }

    public void setMode(EditMode mode) {
        this.mode = mode;
    }

    public int getSelectedTileId() {
        return selectedTileId;
    }

    public void setSelectedTileId(int selectedTileId) {
        this.selectedTileId = selectedTileId;
    }

    public TextureRegion getSelectedTile() {
        if (mode.equals(EditMode.INSERT_ZONE)) return Resources.getZoneTileSet().getTile(selectedTileId);
        return Resources.getBlockTileSet().getTile(selectedTileId);
    }
}
