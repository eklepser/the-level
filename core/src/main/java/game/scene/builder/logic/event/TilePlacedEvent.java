package game.scene.builder.logic.event;

import game.common.rendering.tilemap.TileDefinition;

public final class TilePlacedEvent extends BuilderEvent {
    public final int x;
    public final int y;
    public final TileDefinition tileDefinition;

    public TilePlacedEvent(int x, int y, TileDefinition tileDefinition) {
        this.x = x;
        this.y = y;
        this.tileDefinition = tileDefinition;
    }
}
