package game.scene.menu.rendering;

import com.badlogic.gdx.Game;
import game.common.rendering.BaseScreen;

public final class MenuScreen extends BaseScreen {
    public MenuScreen() {
        super();
        layout = new MenuLayout();
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

