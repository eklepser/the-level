package com.eklepser.thelevel.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Json;
import com.eklepser.thelevel.logic.game.level.LevelConfiguration;
import com.eklepser.thelevel.logic.world.WorldConfiguration;

import java.util.ArrayList;

public class ConfigurationLoader {
    static public ArrayList<LevelConfiguration> loadLevelConfigurations() {
        Json json = new Json();
        return json.fromJson(
            ArrayList.class, LevelConfiguration.class,
            Gdx.files.internal("world/level/levels.json"));
    }

    static public ArrayList<WorldConfiguration> loadWorldConfigurations() {
        Json json = new Json();
        return json.fromJson(
            ArrayList.class, WorldConfiguration.class,
            Gdx.files.internal("world/world.json"));
    }
}
