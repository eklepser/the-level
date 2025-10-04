package game.scene.menu.rendering;

import com.badlogic.gdx.Game;
import game.common.rendering.TableLayout;
import game.common.rendering.component.ColoredString;
import game.config.GraphicsConstants;
import game.scene.menu.rendering.component.BuilderButton;
import game.scene.menu.rendering.component.ExitButton;
import game.scene.menu.rendering.component.LevelsButton;
import game.scene.menu.rendering.component.StartButton;

public class MenuLayout extends TableLayout {
    private final Game game;

    public MenuLayout(Game game) {
        this.game = game;

        setFillParent(true);

        setup();
    }

    @Override
    public void setup() {
        add(new ColoredString("/blue_4 The LEVEL")).padBottom(20);
        row();

        add(new StartButton(game)).width(GraphicsConstants.VIEWPORT_WIDTH / 4.0f).
            height(GraphicsConstants.VIEWPORT_HEIGHT / 16.0f);
        row();

        add(new LevelsButton(game)).width(GraphicsConstants.VIEWPORT_WIDTH / 4.0f).
            height(GraphicsConstants.VIEWPORT_HEIGHT / 16.0f).padTop(20);
        row();

        add(new BuilderButton(game)).width(GraphicsConstants.VIEWPORT_WIDTH / 4.0f).
            height(GraphicsConstants.VIEWPORT_HEIGHT / 16.0f).padTop(20);
        row();

        add(new ExitButton()).width(GraphicsConstants.VIEWPORT_WIDTH / 8.0f).
            height(GraphicsConstants.VIEWPORT_HEIGHT / 16.0f).padTop(20);
    }
}
