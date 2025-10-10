package game.scene.level.logic;

import game.common.rendering.tilemap.TileMap;

public final class LevelData {
    public LevelMetadata metadata;
    public TileMap tileMap;

    public LevelData() { }

    public LevelData(LevelMetadata metadata, TileMap tileMap) {
        this.metadata = metadata;
        this.tileMap = tileMap;
    }
}
