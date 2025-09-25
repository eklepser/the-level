package com.eklepser.thelevel.logic.world.common;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Json;

import java.util.ArrayList;
import java.util.List;

public abstract class Configuration {
    public int id;
    public String name;
    public String mapName;
    public int startPosX;
    public int startPosY;
    public float cameraZoom;

    public static <T extends Configuration> List<T> from(Class<T> configClass, String configPath) {
        Json json = new Json();
        return json.fromJson(ArrayList.class, configClass, Gdx.files.internal(configPath));
    }
}
