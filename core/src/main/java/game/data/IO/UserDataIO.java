package game.data.IO;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter;
import game.config.Paths;
import game.data.user.UserData;

public final class UserDataIO {
    private static final String path = Paths.USER_DATA;

    private UserDataIO() { }

    public static void saveUserData(UserData userData) {
        Json json = new Json();
        json.setOutputType(JsonWriter.OutputType.json);
        String jsonString = json.toJson(userData);
        Gdx.files.local(path).writeString(jsonString, false);
    }

    public static UserData loadUserData() {
        if (!Gdx.files.local(path).exists()) {
            return new UserData();
        }
        String jsonString = Gdx.files.local(path).readString();
        Json json = new Json();
        return json.fromJson(UserData.class, jsonString);
    }
}
