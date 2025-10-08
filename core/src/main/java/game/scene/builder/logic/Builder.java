package game.scene.builder.logic;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import game.common.logic.event.EventSource;
import game.common.rendering.tilemap.TileDefinition;
import game.common.rendering.tilemap.TileMap;
import game.resources.Assets;
import game.scene.builder.logic.event.BuilderEvent;
import game.scene.builder.rendering.component.GridActor;
import game.scene.level.logic.LevelConfiguration;

import static game.utils.NumberUtils.tryParseInt;

public final class Builder extends EventSource<BuilderEvent> {
    private final LevelConfiguration config;
    private final GridActor gridActor;

    private TileDefinition selectedTileDef;

    public Builder(LevelConfiguration config) {
        this.config = config;

        gridActor = new GridActor(this);

        selectedTileDef = Assets.getTileset().getDefinitions().get(10);
    }

    public void resizeMap(int width, int height, int offsetX, int offsetY) {
        config.tileMap.resize(width, height);
        config.tileMap.offset(offsetX, offsetY);
        gridActor.resize();
    }

    private int parseSize(int startSize, String sizeText) {
        int newSize;

        if (sizeText.startsWith("-")) {
            newSize = startSize - Math.abs(tryParseInt(sizeText, startSize));
        } else if (sizeText.startsWith("+")) {
            newSize = startSize + Math.abs(tryParseInt(sizeText, startSize));
        } else {
            newSize = tryParseInt(sizeText, startSize);
        }

        if (newSize > 0) {
            return newSize;
        } else return startSize;
    }

    // Getters & setters:
    public LevelConfiguration getConfig() {
        return config;
    }

    public GridActor getGridActor() {
        return gridActor;
    }

    public TileMap getMap() {
        return config.tileMap;
    }

    public TileDefinition getSelectedTileDef() { return selectedTileDef; }

    public void setSelectedTileDef(TileDefinition selectedTileDef) {
        this.selectedTileDef = selectedTileDef;
    }

    public TextureRegion getSelectedTile() {
        return Assets.getTileset().getTile(selectedTileDef.id);
    }
}
