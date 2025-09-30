package com.eklepser.thelevel.logic.world.common;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Json;
import com.eklepser.thelevel.graphics.render.TileMap;

import java.util.ArrayList;
import java.util.List;

public abstract class Configuration {
    public int id;
    public String tag;
    public TileMap tileMap;
    public float cameraZoom;

    public static <T extends Configuration> T from(Class<T> configClass, String configPath) {
        Json json = new Json();
        return json.fromJson(configClass, Gdx.files.internal(configPath));
    }

    public static <T extends Configuration> List<T> listFrom(Class<T> configClass, String configPath) {
        Json json = new Json();
        return json.fromJson(ArrayList.class, configClass, Gdx.files.internal(configPath));
    }
}
