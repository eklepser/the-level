package com.eklepser.thelevel.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.eklepser.thelevel.graphics.render.TileSet;

import static com.eklepser.thelevel.graphics.screen.TableLayout.TILE_SIZE;

public class Resources {
    public static final String COMMANDS_INFO = "text/commands-info.json";
    public static final String MAP_DIRECTORY = "world/map/";
    public static final String WORLD_CONFIG = "world/world.json";
    public static final String LEVEL_CONFIG = "world/level.json";

    private static Skin skin;
    private static TileSet groundTileSet;
    private static TileSet wallTileSet;
    private static TileSet zoneTileSet;

    public static void load() {
        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("ui/skin/ui.atlas"));
        skin = new Skin(Gdx.files.internal("ui/skin/skin.json"), atlas);
        groundTileSet = new TileSet("world/ground_tileset.png", "block" , TILE_SIZE, TILE_SIZE);
        wallTileSet = new TileSet("world/wall_tileset.png", "wall" , TILE_SIZE, TILE_SIZE);
        zoneTileSet = new TileSet("world/zone_tileset.png", "zone", TILE_SIZE, TILE_SIZE);
    }

    // Getters:
    public static Skin getSkin() {
        return skin;
    }

    public static TileSet getGroundTileSet() {
        return groundTileSet;
    }

    public static TileSet getWallTileSet() {
        return wallTileSet;
    }

    public static TileSet getZoneTileSet() {
        return zoneTileSet;
    }

    public static Image getImage(String texturePath) {
        return new Image(new Texture(Gdx.files.internal(texturePath)));
    }
}
