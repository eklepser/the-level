package game.scene.menu.rendering;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import game.config.Display;
import game.data.IO.Assets;
import game.scene.common.rendering.TableLayout;

public final class MenuLayout extends TableLayout {
    public MenuLayout() {
        setFillParent(true);

        setup();
    }

    @Override
    public void setup() {
        Image logoImage = Assets.getImage("logo.png");
        add(logoImage).padBottom(20);
        row();

        row();

        add(MenuButtonFactory.startButton()).width(Display.VIEWPORT_WIDTH / 4.0f).
            height(Display.VIEWPORT_HEIGHT / 16.0f);
        row();

        add(MenuButtonFactory.levelsButton()).width(Display.VIEWPORT_WIDTH / 4.0f).
            height(Display.VIEWPORT_HEIGHT / 16.0f).padTop(20);
        row();

        add(MenuButtonFactory.builderButton()).width(Display.VIEWPORT_WIDTH / 4.0f).
            height(Display.VIEWPORT_HEIGHT / 16.0f).padTop(20);
        row();

//        add(MenuButtonFactory.optionsButton()).width(Display.VIEWPORT_WIDTH / 4.0f).
//            height(Display.VIEWPORT_HEIGHT / 16.0f).padTop(20);
//        row();

        add(MenuButtonFactory.exitButton()).width(Display.VIEWPORT_WIDTH / 8.0f).
            height(Display.VIEWPORT_HEIGHT / 16.0f).padTop(20);
    }
}
