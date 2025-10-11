package game.data.level;

import game.scene.common.rendering.tilemap.TileMap;

public final class LevelData {
    public LevelMetadata metadata;
    public TileMap tileMap;

    public LevelData() {
        metadata = new LevelMetadata();
        tileMap = new TileMap();
    }

    public LevelData(LevelMetadata metadata, TileMap tileMap) {
        this.metadata = metadata;
        this.tileMap = tileMap;
    }
}
