package com.eklepser.thelevel.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class Resources {
    public static final String COMMANDS_INFO = "text/commands-info.json";
    public static final String MAP_DIRECTORY = "world/map/";
    public static final String WORLD_CONFIG = "world/world.json";
    public static final String LEVEL_CONFIG = "world/level.json";
    private static Skin skin;

    public static void load() {
        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("ui/skin/ui.atlas"));
        skin = new Skin(Gdx.files.internal("ui/skin/skin.json"), atlas);
    }

    public static Skin getSkin() {
        return skin; }
}
