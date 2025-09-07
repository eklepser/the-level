package com.eklepser.thelevel;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class Resources {
    private static TextureAtlas atlas;
    private static Skin skin;

    public static void load() {
        atlas = new TextureAtlas(Gdx.files.internal("ui/ui.atlas"));
        skin = new Skin(Gdx.files.internal("ui/skin.json"), atlas);
    }

    public static TextureAtlas getAtlas() { return atlas; }

    public static Skin getSkin() { return skin; }
}
