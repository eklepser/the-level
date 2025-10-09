package game.scene.builder.logic;

import game.common.logic.event.EventSource;
import game.common.rendering.tilemap.TileDefinition;
import game.common.rendering.tilemap.TileMap;
import game.resources.Assets;
import game.scene.builder.logic.event.BuilderEvent;
import game.scene.builder.logic.event.TileSelectedEvent;
import game.scene.builder.rendering.component.GridDrawer;
import game.scene.level.logic.LevelConfiguration;

public final class Builder extends EventSource<BuilderEvent> {
    private final LevelConfiguration config;
    private final GridDrawer gridDrawer;

    private TileDefinition selectedTileDef;
    private TileDefinition customZoneDef;

    public Builder(LevelConfiguration config) {
        this.config = config;

        gridDrawer = new GridDrawer(this);

        selectedTileDef = Assets.getTileset().getDefinitions().get(10);
        customZoneDef = Assets.getTileset().getDefinitions().get(92);
    }

    public void resizeMap(int width, int height, int offsetX, int offsetY) {
        config.tileMap.resize(width, height);
        config.tileMap.offset(offsetX, offsetY);
        gridDrawer.resize();
    }

    public TileDefinition getSelectedTileDef() { return selectedTileDef; }

    public void setSelectedTileDef(TileDefinition def) {
        if (def.type.equals("custom_zone")) {
            selectedTileDef = customZoneDef;
        }
        else {
            selectedTileDef = def;
        }
        fire(new TileSelectedEvent(selectedTileDef));
    }

    public void setCustomZoneDef(TileDefinition def) {
        this.customZoneDef = def;
        setSelectedTileDef(def);
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

}
