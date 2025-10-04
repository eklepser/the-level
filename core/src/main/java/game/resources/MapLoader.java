package game.resources;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import game.scene.common.rendering.tile.TileMap;

public class MapLoader {
    private static final Json json = new Json();

    public static TileMap loadTileMap(String filePath) {
        FileHandle file = Gdx.files.internal(filePath);
        if (!file.exists()) {
            throw new RuntimeException("Map file not found: " + filePath);
        }
        return json.fromJson(TileMap.class, file);
    }
}
