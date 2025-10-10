package game.resources;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import game.scene.level.data.LevelData;
import game.scene.level.data.LevelMetadata;
import game.scene.level.logic.command.Instruction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class LevelLoader {
    private LevelLoader() {
        throw new UnsupportedOperationException();
    }

    public static LevelData loadData(String internalPath) {
        Json json = new Json();
        return json.fromJson(LevelData.class, Gdx.files.internal(internalPath));
    }

    public static Map<String, LevelData> loadDataMap(String localPath) {
        Map<String, LevelData> levelDataMap = new HashMap<>();
        FileHandle dir = Gdx.files.local(localPath);

        if (!dir.exists() || !dir.isDirectory()) {
            Gdx.app.error("Loader", "Directory not found: " + dir);
            return levelDataMap;
        }

        Json json = new Json();

        for (FileHandle file : dir.list()) {
            String fileName = file.name();
            if (!fileName.endsWith(".json")) continue;
            if (fileName.endsWith("template.json")) continue;

            try {
                LevelData data = json.fromJson(LevelData.class, file);
                if (data != null) {
                    levelDataMap.put(data.metadata.tag, data);
                } else {
                    Gdx.app.error("LevelScanner", "Parsed null config from: " + fileName);
                }
            } catch (Exception e) {
                Gdx.app.error("LevelScanner", "Failed to parse: " + fileName, e);
            }
        }
        return levelDataMap;
    }

    public static List<LevelMetadata> loadMetadata(String localDirPath) {
        List<LevelMetadata> levels = new ArrayList<>();
        FileHandle dir = Gdx.files.local(localDirPath);

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

                JsonValue metadataJson = root.get("metadata");
                if (metadataJson == null) {
                    Gdx.app.error("LevelScanner", "No metadata found in: " + file.name());
                    continue;
                }

                String tag = metadataJson.getString("tag", "unknown tag");
                String title = metadataJson.getString("title", "No title");
                int codeLinesAmount = metadataJson.getInt("codeLinesAmount", 10);

                List<Instruction> allowedInstructions = new ArrayList<>();
                JsonValue instructionsJson = metadataJson.get("allowedInstructions");
                if (instructionsJson != null) {
                    for (JsonValue instructionJson : instructionsJson) {
                        String instructionName = instructionJson.asString();
                        try {
                            Instruction instruction = Instruction.getByName(instructionName);
                            allowedInstructions.add(instruction);
                        } catch (IllegalArgumentException e) {
                            Gdx.app.error("LevelScanner", "Unknown instruction: " + instructionName + " in file: " + file.name());
                        }
                    }
                }

                levels.add(new LevelMetadata(tag, title, codeLinesAmount, allowedInstructions));

            } catch (Exception e) {
                Gdx.app.error("LevelScanner", "Failed to parse: " + file.name(), e);
            }
        }
        return levels;
    }
}
