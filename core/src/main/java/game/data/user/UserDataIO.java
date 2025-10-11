package game.data.user;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter;

public final class UserDataIO {
    private UserDataIO() { }

    public static void saveUserData(UserData userData, String localPath) {
        Json json = new Json();
        json.setOutputType(JsonWriter.OutputType.json);
        String jsonString = json.toJson(userData);

        Gdx.files.local(localPath).writeString(jsonString, false);
    }

    public static UserData loadUserData(String localPath) {
        if (!Gdx.files.local(localPath).exists()) {
            return new UserData();
        }

        String jsonString = Gdx.files.local(localPath).readString();
        Json json = new Json();
        return json.fromJson(UserData.class, jsonString);
    }
}
