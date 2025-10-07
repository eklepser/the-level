package game.scene.menu.rendering;

import game.common.rendering.screen.BaseScreen;

public final class MenuScreen extends BaseScreen {
    public MenuScreen() {
        super();

        stage.addActor(new MenuLayout());
    }
}

