package game.config;

public final class Display {
    private Display() {
        throw new UnsupportedOperationException();
    }

    public static final int WINDOW_WIDTH = 1280;
    public static final int WINDOW_HEIGHT = 720;
    public static final int VIEWPORT_WIDTH = 1280;
    public static final int VIEWPORT_HEIGHT = 720;

    public static final int TILE_SIZE = 32;

    // value in range from 0 to 1.0f
    public static final float EDITOR_MENU_SCALE = 0.4f;
}
