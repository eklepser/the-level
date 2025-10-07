package game.common;

import com.badlogic.gdx.Game;

public final class ScreenNavigator {
    private static Game game;

    private ScreenNavigator() { }

    public static void init(Game game) {
        ScreenNavigator.game = game;
    }

    public static void gotoScreen(BaseScreen screen) {
        game.setScreen(screen);
    }
}
