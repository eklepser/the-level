package game.scene.menu.rendering;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import game.config.Display;
import game.data.IO.Assets;
import game.scene.common.rendering.TableLayout;
import game.scene.common.rendering.component.ColoredString;

public final class MenuLayout extends TableLayout {
    public MenuLayout() {
        setFillParent(true);

        setup();
    }

    @Override
    public void setup() {
        add(new ColoredString("/blue_4 The LEVEL")).padBottom(20);
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
