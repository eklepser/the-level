package game.scene.world.data;

import game.scene.common.rendering.tilemap.TileMap;

public final class WorldData {
    public WorldMetadata metadata;
    public TileMap tileMap;

    public WorldData() {
        metadata = new WorldMetadata();
        tileMap = new TileMap();
    }

    public WorldData(WorldMetadata metadata, TileMap tileMap) {
        this.metadata = metadata;
        this.tileMap = tileMap;
    }
}
