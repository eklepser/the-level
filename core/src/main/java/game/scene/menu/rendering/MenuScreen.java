package game.scene.menu.rendering;

import com.badlogic.gdx.Game;
import game.common.rendering.BaseScreen;

public class MenuScreen extends BaseScreen {
    public MenuScreen(Game game) {
        super(game);
        layout = new MenuLayout(game);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
    }

    @Override
    public void show() {
        stage.addActor(layout);
        multiplexer.addProcessor(stage);
    }
}

