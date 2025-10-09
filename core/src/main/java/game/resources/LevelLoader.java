package game.resources;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import game.scene.level.logic.LevelConfiguration;
import game.scene.selection.logic.LevelMetadata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class LevelLoader {
    private LevelLoader() {
        throw new UnsupportedOperationException();
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

    public static Map<String, LevelConfiguration> loadConfigurations(String directoryPath) {
        Map<String, LevelConfiguration> configsMap = new HashMap<>();
        FileHandle dir = Gdx.files.local(directoryPath);

        if (!dir.exists() || !dir.isDirectory()) {
            Gdx.app.error("Loader", "Directory not found: " + dir);
            return configsMap;
        }

        Json json = new Json();

        for (FileHandle file : dir.list()) {
            String fileName = file.name();
            if (!fileName.endsWith(".json")) continue;
            if (fileName.endsWith("template.json")) continue;

            try {
                LevelConfiguration config = json.fromJson(LevelConfiguration.class, file);
                if (config != null) {
                    configsMap.put(config.tag, config);
                } else {
                    Gdx.app.error("LevelScanner", "Parsed null config from: " + fileName);
                }
            } catch (Exception e) {
                Gdx.app.error("LevelScanner", "Failed to parse: " + fileName, e);
            }
        }
        return configsMap;
    }
}
