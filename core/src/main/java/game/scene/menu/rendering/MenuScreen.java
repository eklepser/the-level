package game.scene.menu.rendering;

import game.scene.common.rendering.screen.BaseScreen;

public final class MenuScreen extends BaseScreen {
    public MenuScreen() {
        super();

        stage.addActor(new MenuLayout());
        multiplexer.addProcessor(stage);
    }
}

