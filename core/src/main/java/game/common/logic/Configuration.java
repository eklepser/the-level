package game.common.logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Json;
import game.common.rendering.tile.TileMap;

import java.util.ArrayList;
import java.util.List;

public abstract class Configuration {
    public String tag;
    public TileMap tileMap;

    public static <T extends Configuration> T from(Class<T> configClass, String configPath) {
        Json json = new Json();
        return json.fromJson(configClass, Gdx.files.internal(configPath));
    }

    public static <T extends Configuration> List<T> listFrom(Class<T> configClass, String configPath) {
        Json json = new Json();
        return json.fromJson(ArrayList.class, configClass, Gdx.files.internal(configPath));
    }
}
