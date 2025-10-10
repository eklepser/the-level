package game.resources;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter;
import game.config.Paths;
import game.scene.level.logic.LevelConfigurationOld;

public final class LevelSaver {
    private LevelSaver() {
        throw new UnsupportedOperationException();
    }

    public static void saveLevel(LevelConfigurationOld configuration) {
        Json json = new Json();
        json.setOutputType(JsonWriter.OutputType.json);
        String jsonContent = json.toJson(configuration);

        String path = String.format("%slevel_%s.json", Paths.BUILDER_DATA, configuration.tag);
        FileHandle file = Gdx.files.local(path);
        file.writeString(jsonContent, false);

        Gdx.app.log("Save", "Level saved to: " + file.path());
    }
}
