package game.data.IO;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.JsonWriter;
import game.config.Paths;
import game.data.level.LevelData;
import game.data.level.LevelMetadata;
import game.scene.level.logic.command.Instruction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class LevelDataIO {
    private LevelDataIO() {
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

                List<String> unlocks = new ArrayList<>();
                JsonValue unlocksJson = metadataJson.get("unlocks");
                if (unlocksJson != null && unlocksJson.isArray()) {
                    for (JsonValue unlockJson : unlocksJson) {
                        if (unlockJson.isString()) {
                            unlocks.add(unlockJson.asString());
                        } else {
                            Gdx.app.error("LevelScanner", "Non-string value in 'unlocks' array in file: " + file.name());
                        }
                    }
                }

                levels.add(new LevelMetadata(tag, title, codeLinesAmount, allowedInstructions, unlocks));

            } catch (Exception e) {
                Gdx.app.error("LevelScanner", "Failed to parse: " + file.name(), e);
            }
        }
        return levels;
    }

    public static void saveLevel(LevelData levelData) {
        Json json = new Json();
        json.setOutputType(JsonWriter.OutputType.json);
        String jsonContent = json.toJson(levelData);

        String path = String.format("%slevel_%s.json", Paths.BUILDER_DATA, levelData.metadata.tag);
        FileHandle file = Gdx.files.local(path);
        file.writeString(jsonContent, false);

        Gdx.app.log("Save", "Level saved to: " + file.path());
    }
}
