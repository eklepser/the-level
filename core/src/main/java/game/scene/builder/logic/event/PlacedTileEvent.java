package game.scene.builder.logic.event;

import game.common.rendering.tilemap.TileDefinition;

public class PlacedTileEvent extends BuilderEvent {
    public final TileDefinition tileDefinition;

    public PlacedTileEvent(TileDefinition tileDefinition) {
        this.tileDefinition = tileDefinition;
    }
}
