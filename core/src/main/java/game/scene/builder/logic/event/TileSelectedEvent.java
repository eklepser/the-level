package game.scene.builder.logic.event;

import game.common.rendering.tilemap.TileDefinition;

public final class TileSelectedEvent extends BuilderEvent {
    public final TileDefinition tileDefinition;

    public TileSelectedEvent(TileDefinition tileDefinition) {
        this.tileDefinition = tileDefinition;
    }
}
