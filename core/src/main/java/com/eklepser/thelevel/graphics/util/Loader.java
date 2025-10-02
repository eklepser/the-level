package com.eklepser.thelevel.graphics.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.eklepser.thelevel.graphics.render.TileMap;
import com.eklepser.thelevel.graphics.screen.selection.LevelMetadata;

import java.util.ArrayList;
import java.util.List;

public class Loader {
    private static final Json json = new Json();

    public static TileMap loadTileMap(String filePath) {
        FileHandle file = Gdx.files.internal(filePath);
        if (!file.exists()) {
            throw new RuntimeException("Map file not found: " + filePath);
        }
        return json.fromJson(TileMap.class, file);
    }

    public static List<LevelMetadata> loadMetadata(String directoryPath) {
        List<LevelMetadata> levels = new ArrayList<>();
        FileHandle dir = Gdx.files.local(directoryPath);

        if (!dir.exists() || !dir.isDirectory()) {
            Gdx.app.error("Loader", "Directory not found: " + dir);
            return levels;
        }

        JsonReader jsonReader = new JsonReader();

        for (FileHandle file : dir.list()) {
            if (!file.name().endsWith(".json")) continue;
            if (file.name().endsWith("template.json")) continue;

            try {
                JsonValue root = jsonReader.parse(file);
                String id = root.getString("id", "unknown id");
                String tag = root.getString("tag", "unknown tag");
                String title = root.getString("title", "No title");
                levels.add(new LevelMetadata(id, tag, title));
            } catch (Exception e) {
                Gdx.app.error("LevelScanner", "Failed to parse: " + file.name(), e);
            }
        }
        return levels;
    }
}
