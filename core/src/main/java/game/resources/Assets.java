package game.resources;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import game.scene.common.rendering.tilemap.Tileset;
import game.config.Display;
import game.config.Paths;

public final class Assets {
    private Assets() {
        throw new UnsupportedOperationException();
    }

    private static Skin skin;
    private static Tileset tileset;

    public static void init() {
        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal(Paths.ATLAS));
        FileHandle skinFile = Gdx.files.internal(Paths.SKIN);
        skin = new Skin(skinFile, atlas);

        tileset = new Tileset(Paths.TILESET , Display.TILE_SIZE, Display.TILE_SIZE);
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
