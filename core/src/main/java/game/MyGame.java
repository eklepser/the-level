package game;

import com.badlogic.gdx.Game;
import game.resources.Assets;
import game.scene.menu.rendering.MenuScreen;

public final class MyGame extends Game {
    @Override
    public void create() {
        Assets.init();
        setScreen(new MenuScreen(this));
    }
}
