package game.scene.level.window;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Json;
import game.config.Display;
import game.data.IO.Assets;
import game.data.world.WorldData;
import game.scene.common.rendering.component.TextLabel;
import game.scene.common.rendering.screen.ScreenNavigator;
import game.scene.level.rendering.component.ExitLevelButton;
import game.scene.world.rendering.WorldScreen;

public class WinWindow extends Window {
    private final TextLabel winLabel;
    private final ExitLevelButton exitLevelButton;
    private final TextButton closeButton;

    public WinWindow() {
        super("", Assets.getSkin());
        winLabel = new TextLabel("YOU WIN!");
        exitLevelButton = new ExitLevelButton();

        closeButton = new TextButton("Close", Assets.getSkin());
        closeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                setVisible(false);
            }
        });

        setupLayout();
    }

    private void setupLayout() {
        float editorWidth = Display.VIEWPORT_WIDTH * Display.EDITOR_MENU_SCALE;
        float freeWidth = Display.VIEWPORT_WIDTH - editorWidth;

        setSize(freeWidth, Display.VIEWPORT_HEIGHT);
        setVisible(false);
        setColor(0.5f, 0, 0.75f, 0.9f);

        add(winLabel).pad(10).width(Display.VIEWPORT_WIDTH / 8.0f).align(Align.center);
        setPosition(editorWidth, 0);

        row();
        add(exitLevelButton).width(Display.VIEWPORT_WIDTH / 8.0f)
            .height(Display.VIEWPORT_HEIGHT / 16.0f).padBottom(20);

        row();
        add(closeButton).width(Display.VIEWPORT_WIDTH / 8.0f)
            .height(Display.VIEWPORT_HEIGHT / 16.0f).padBottom(20);
    }

    public void invertPosition(boolean editorOnRight) {
        float editorWidth = Display.VIEWPORT_WIDTH * Display.EDITOR_MENU_SCALE;
        if (editorOnRight) {
            setPosition(0, 0);
        } else {
            setPosition(editorWidth, 0);
        }
    }

    public void toggle() {
        setVisible(!isVisible());
    }
}
