package game.common.tilemap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Json;

import java.util.ArrayList;
import java.util.List;

public abstract class MapConfiguration {
    public String tag;
    public TileMap tileMap;

    public static <T extends MapConfiguration> T from(Class<T> configClass, String configPath) {
        Json json = new Json();
        return json.fromJson(configClass, Gdx.files.internal(configPath));
    }

    public static <T extends MapConfiguration> List<T> listFrom(Class<T> configClass, String configPath) {
        Json json = new Json();
        return json.fromJson(ArrayList.class, configClass, Gdx.files.internal(configPath));
    }
}
