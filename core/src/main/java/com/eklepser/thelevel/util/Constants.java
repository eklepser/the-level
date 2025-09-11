package com.eklepser.thelevel.util;

import java.util.List;

public class Constants {
    public static final int TILE_SIZE = 32;
    public static final int WINDOW_WIDTH = 1080;
    public static final int WINDOW_HEIGHT = 720;
    public static final int VIEWPORT_WIDTH = 1080;
    public static final int VIEWPORT_HEIGHT = 720;

    public static final float EDITOR_MENU_SCALE = 0.4f; // value in range from 0 to 1.0f

    public static final List<String> EDITOR_CODE_TEMPLATE = CodeTemplates.MOVE_FORWARD_CYCLE;

    public static boolean IS_UI_DEBUGGING = true;
    public static boolean IS_CODE_DEBUGGING = true;
}
