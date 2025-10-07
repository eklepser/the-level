package game;

import com.badlogic.gdx.Game;
import game.common.ScreenNavigator;
import game.resources.Assets;
import game.scene.menu.rendering.MenuScreen;

public final class MyGame extends Game {
    @Override
    public void create() {
        Assets.init();
        ScreenNavigator.init(this);

        ScreenNavigator.gotoScreen(new MenuScreen());
    }
}
