package game.resources;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import game.config.GraphicsConstants;
import game.scene.common.rendering.tile.Tileset;

public class Assets {
    public static final String COMMANDS_INFO = "text/commands-info.json";
    public static final String MAP_DIRECTORY = "world/map/";
    public static final String WORLD_CONFIG = "world/world.json";
    public static final String LEVEL_CONFIG = "world/level.json";

    private static Skin skin;
    private static Tileset tileset;
    private static Tileset wallTileset;
    private static Tileset zoneTileset;

    public static void load() {
        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("ui/skin/ui.atlas"));
        skin = new Skin(Gdx.files.internal("ui/skin/skin.json"), atlas);
        tileset = new Tileset("tileset/tileset" , GraphicsConstants.TILE_SIZE, GraphicsConstants.TILE_SIZE);
    }

    // Getters:
    public static Skin getSkin() {
        return skin;
    }

    public static Tileset getTileset() {
        return tileset;
    }

    public static Image getImage(String texturePath) {
        return new Image(new Texture(Gdx.files.internal(texturePath)));
    }
}
