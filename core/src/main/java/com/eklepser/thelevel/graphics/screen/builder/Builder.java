package com.eklepser.thelevel.graphics.screen.builder;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.eklepser.thelevel.graphics.render.TileDefinition;
import com.eklepser.thelevel.graphics.render.TileMap;
import com.eklepser.thelevel.graphics.screen.GameScreen;
import com.eklepser.thelevel.util.Resources;

public class Builder {
    private final BuilderScreen screen;
    private final TileMap map;

    private EditMode mode = EditMode.PLACE;
    private TileDefinition selectedTileDef;

    public Builder(BuilderScreen screen) {
        this.screen = screen;
        map = screen.getMap();

        selectedTileDef = Resources.getTileset().getDefinitions().get(0);
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

    public TileDefinition getSelectedTileDef() { return selectedTileDef; }

    public void setSelectedTileDef(TileDefinition selectedTileDef) {
        this.selectedTileDef = selectedTileDef;
    }

    public TextureRegion getSelectedTile() {
        return Resources.getTileset().getTile(selectedTileDef.id);
    }
}
