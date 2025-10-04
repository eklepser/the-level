package game;

import com.badlogic.gdx.Game;
import game.resources.Assets;
import game.scene.menu.rendering.MenuScreen;

public class MyGame extends Game {
    @Override
    public void create() {
        Assets.load();
        setScreen(new MenuScreen(this));
    }
}
