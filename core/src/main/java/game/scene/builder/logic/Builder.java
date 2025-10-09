package game.scene.builder.logic;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import game.common.logic.event.EventSource;
import game.common.rendering.tilemap.TileDefinition;
import game.common.rendering.tilemap.TileMap;
import game.resources.Assets;
import game.scene.builder.logic.event.BuilderEvent;
import game.scene.builder.rendering.component.GridDrawer;
import game.scene.level.logic.LevelConfiguration;

import static game.utils.NumberUtils.tryParseInt;

public final class Builder extends EventSource<BuilderEvent> {
    private final LevelConfiguration config;
    private final GridDrawer gridDrawer;

    private TileDefinition selectedTileDef;

    public Builder(LevelConfiguration config) {
        this.config = config;

        gridDrawer = new GridDrawer(this);

        selectedTileDef = Assets.getTileset().getDefinitions().get(10);
    }

    public void resizeMap(int width, int height, int offsetX, int offsetY) {
        config.tileMap.resize(width, height);
        config.tileMap.offset(offsetX, offsetY);
        gridDrawer.resize();
    }

    public LevelConfiguration getConfig() {
        return config;
    }

    public GridDrawer getGridActor() {
        return gridDrawer;
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
