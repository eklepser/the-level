package com.eklepser.thelevel.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.eklepser.thelevel.graphics.render.TileSet;

import static com.eklepser.thelevel.graphics.screen.Layout.TILE_SIZE;

public class Resources {
    public static final String COMMANDS_INFO = "text/commands-info.json";
    public static final String MAP_DIRECTORY = "world/map/";
    public static final String WORLD_CONFIG = "world/world.json";
    public static final String LEVEL_CONFIG = "world/level.json";
    private static Skin skin;
    private static TileSet tileSet;

    public static void load() {
        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("ui/skin/ui.atlas"));
        skin = new Skin(Gdx.files.internal("ui/skin/skin.json"), atlas);
        tileSet = new TileSet("world/map/gamefield-tileset.png", TILE_SIZE, TILE_SIZE);
    }

    public static Skin getSkin() {
        return skin;
    }

    public static TileSet getTileSet() {
        return  tileSet;
    }

    public static Image getImage(String texturePath) {
        return new Image(new Texture(Gdx.files.internal(texturePath)));
    }
}
