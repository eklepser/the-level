package game.scene.common.rendering.tile;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Json;

import java.util.HashMap;
import java.util.Map;

public class Tileset {
    private final TextureRegion[][] tiles;
    private final Map<Integer, TileDefinition> definitions;

    public Tileset(String path, int tileWidth, int tileHeight) {
        tiles = TextureRegion.split(new Texture(path + ".png"), tileWidth, tileHeight);
        definitions = new HashMap<>();

        Json json = new Json();
        TileDefinition[] defs = json.fromJson(TileDefinition[].class, Gdx.files.internal(path + ".json"));
        for (TileDefinition def : defs) {
            definitions.put(def.id, def);
        }
    }

    // Getters:
    public TextureRegion getTile(int index) {
        int rows = tiles.length;
        int cols = tiles[0].length;
        if (index >= rows * cols) return null;
        return tiles[index / cols][index % cols];
    }

    public TextureRegion getTile(int x, int y) {
        return tiles[y][x];
    }

    public Map<Integer, TileDefinition> getDefinitions() { return definitions; }

    public int getWidth() {
        return tiles != null && tiles.length > 0 ? tiles[0].length : 0;
    }

    public int getHeight() {
        return tiles != null ? tiles.length : 0;
    }
}
