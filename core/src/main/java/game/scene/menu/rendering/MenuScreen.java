package game.scene.menu.rendering;

import game.common.BaseScreen;

public final class MenuScreen extends BaseScreen {
    public MenuScreen() {
        super();

        stage.addActor(new MenuLayout());
    }
}

