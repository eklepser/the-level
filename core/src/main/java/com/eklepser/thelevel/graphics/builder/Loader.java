package com.eklepser.thelevel.graphics.builder;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;

public class Loader {
    public static TileMap loadMap(String filePath) {
        Json json = new Json();
        FileHandle file = Gdx.files.internal(filePath);
        if (!file.exists()) {
            throw new RuntimeException("Level file not found: " + filePath);
        }
        return json.fromJson(TileMap.class, file);
    }
}
