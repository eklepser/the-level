package game.scene.builder.logic;

import game.scene.common.logic.event.EventSource;
import game.scene.common.rendering.tilemap.TileDefinition;
import game.scene.common.rendering.tilemap.TileMap;
import game.resources.Assets;
import game.scene.builder.logic.event.BuilderEvent;
import game.scene.builder.logic.event.TileSelectedEvent;
import game.scene.builder.rendering.component.GridDrawer;
import game.scene.level.data.LevelData;

public final class Builder extends EventSource<BuilderEvent> {
    private final LevelData levelData;
    private final GridDrawer gridDrawer;

    private TileDefinition selectedTileDef;
    private TileDefinition customZoneDef;

    public Builder(LevelData levelData) {
        this.levelData = levelData;

        gridDrawer = new GridDrawer(this);

        selectedTileDef = Assets.getTileset().getDefinitions().get(10);

        customZoneDef = Assets.getTileset().getDefinitions().get(92);
        customZoneDef.zoneType = "";
        customZoneDef.zoneProperties = new String[] { };
    }

    public void resizeMap(int width, int height, int offsetX, int offsetY) {
        levelData.tileMap.resize(width, height);
        levelData.tileMap.offset(offsetX, offsetY);
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

    public LevelData getLevelData() {
        return levelData;
    }

    public GridDrawer getGridActor() {
        return gridDrawer;
    }

    public TileMap getMap() {
        return levelData.tileMap;
    }

}
