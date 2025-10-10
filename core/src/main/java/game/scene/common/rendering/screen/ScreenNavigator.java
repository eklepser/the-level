package game.scene.common.rendering.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

public final class ScreenNavigator {
    private static Game game;
    private static Screen previousScreen;

    private ScreenNavigator() { }

    public static void init(Game game) {
        ScreenNavigator.game = game;
    }

    public static void gotoScreen(Screen screen) {
        previousScreen = game.getScreen();
        game.setScreen(screen);
    }
}
