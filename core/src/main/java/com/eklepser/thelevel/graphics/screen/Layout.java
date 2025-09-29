package com.eklepser.thelevel.graphics.screen;

import com.badlogic.gdx.scenes.scene2d.ui.Table;

public abstract class Layout extends Table {
    public static final int TILE_SIZE = 32;
    public static final int WINDOW_WIDTH = 1280;
    public static final int WINDOW_HEIGHT = 720;
    public static final int VIEWPORT_WIDTH = 1280;
    public static final int VIEWPORT_HEIGHT = 720;
    public static final float EDITOR_MENU_SCALE = 0.4f; // value in range from 0 to 1.0f

    protected abstract void setup();
}
