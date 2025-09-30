package com.eklepser.thelevel.graphics.screen;

public interface Layout {
    void setup();

    int TILE_SIZE = 32;
    int WINDOW_WIDTH = 1280;
    int WINDOW_HEIGHT = 720;
    int VIEWPORT_WIDTH = 1280;
    int VIEWPORT_HEIGHT = 720;
    float EDITOR_MENU_SCALE = 0.4f; // value in range from 0 to 1.0f
}
