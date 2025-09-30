package com.eklepser.thelevel.logic.world.common;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Json;
import com.eklepser.thelevel.graphics.render.TileMap;

import java.util.ArrayList;
import java.util.List;

public abstract class Configuration {
    public int id;
    public String name;
    public TileMap tileMap;
    //public String mapName;
    public float cameraZoom;

    public Configuration() { }

    public Configuration(int id, String name, TileMap tileMap, float cameraZoom) {
        this.id = id;
        this.name = name;
        this.tileMap = tileMap;
        this.cameraZoom = cameraZoom;
    }

    public static <T extends Configuration> T from(Class<T> configClass, String configPath) {
        Json json = new Json();
        return json.fromJson(configClass, Gdx.files.internal(configPath));
    }

    public static <T extends Configuration> List<T> listFrom(Class<T> configClass, String configPath) {
        Json json = new Json();
        return json.fromJson(ArrayList.class, configClass, Gdx.files.internal(configPath));
    }
}
