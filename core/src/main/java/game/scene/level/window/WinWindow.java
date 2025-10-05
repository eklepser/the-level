package game.scene.level.window;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.Align;
import game.config.Display;
import game.scene.level.rendering.component.ExitLevelButton;
import game.common.rendering.component.TextLabel;
import game.resources.Assets;

public class WinWindow extends Window {
    private final Game game;
    private final TextLabel winLabel;
    private final ExitLevelButton exitLevelButton;

    public WinWindow(Game game) {
        super("", Assets.getSkin());
        this.game = game;
        winLabel = new TextLabel("YOU WIN!");
        exitLevelButton = new ExitLevelButton(game);

        setupLayout();
    }

    private void setupLayout() {
        float editorWidth = Display.VIEWPORT_WIDTH * Display.EDITOR_MENU_SCALE;
        setSize(Display.VIEWPORT_WIDTH - editorWidth, Display.VIEWPORT_HEIGHT);
        setPosition(editorWidth, 0);

        add(winLabel).pad(10).width(Display.VIEWPORT_WIDTH / 8.0f).align(Align.center);
        setVisible(false);
        setColor(0.5f, 0, 0.75f, 0.9f);

        row();
        add(exitLevelButton).width(Display.VIEWPORT_WIDTH / 8.0f)
            .height(Display.VIEWPORT_HEIGHT / 16.0f).padBottom(20);
    }

    public void toggle() {
        setVisible(!isVisible());
    }
}
