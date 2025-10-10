package game.resources;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import game.scene.level.logic.LevelConfigurationOld;
import game.scene.level.logic.LevelMetadata;
import game.scene.level.logic.LevelMetadataOld;
import game.scene.level.logic.command.Instruction;

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

                // Получаем объект metadata из LevelData
                JsonValue metadataJson = root.get("metadata");
                if (metadataJson == null) {
                    Gdx.app.error("LevelScanner", "No metadata found in: " + file.name());
                    continue;
                }

                // Парсим поля из metadata
                String tag = metadataJson.getString("tag", "unknown tag");
                String title = metadataJson.getString("title", "No title");
                int codeLinesAmount = metadataJson.getInt("codeLinesAmount", 10);

                // Парсим allowedInstructions
                List<Instruction> allowedInstructions = new ArrayList<>();
                JsonValue instructionsJson = metadataJson.get("allowedInstructions");
                if (instructionsJson != null) {
                    for (JsonValue instructionJson : instructionsJson) {
                        String instructionName = instructionJson.asString();
                        try {
                            Instruction instruction = Instruction.valueOf(instructionName);
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

    public static Map<String, LevelConfigurationOld> loadConfigurations(String directoryPath) {
        Map<String, LevelConfigurationOld> configsMap = new HashMap<>();
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
                LevelConfigurationOld config = json.fromJson(LevelConfigurationOld.class, file);
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
