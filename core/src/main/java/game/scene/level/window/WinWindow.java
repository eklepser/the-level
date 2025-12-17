package game.scene.level.window;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import game.config.Display;
import game.data.IO.Assets;
import game.scene.common.rendering.component.TextLabel;
import game.scene.level.rendering.component.ExitLevelButton;

public class WinWindow extends Window {
    private final TextLabel winLabel;
    private final Image winImage;
    private final ExitLevelButton exitLevelButton;
    private final TextButton closeButton;

    public WinWindow() {
        super("", Assets.getSkin());
        winLabel = new TextLabel("YOU WIN!");
        winLabel.setAlignment(Align.center);
        winLabel.setFontScale(4.0f);

        winImage = Assets.getImage("ui/icon/win.png");

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
        setSize(Display.VIEWPORT_WIDTH, Display.VIEWPORT_HEIGHT);
        setVisible(false);
        setColor(0.5f, 0, 0.75f, 0.9f);
        setPosition(0, 0);

        float labelWidth = Display.VIEWPORT_WIDTH * 0.7f;
        float imageWidth = Display.VIEWPORT_WIDTH * 0.16f;
        float buttonWidth = Display.VIEWPORT_WIDTH * 0.4f;
        float buttonHeight = Display.VIEWPORT_HEIGHT * 0.06f;

        add(winLabel).padTop(80).padBottom(30).align(Align.center).width(labelWidth).row();
        add(winImage).padBottom(50).align(Align.center).size(imageWidth).row();
        add(exitLevelButton).width(buttonWidth).height(buttonHeight).padBottom(15).row();
        add(closeButton).width(buttonWidth).height(buttonHeight).padBottom(30);
    }

    public void invertPosition(boolean editorOnRight) {
        setPosition(0, 0);
    }

    public void toggle() {
        setVisible(!isVisible());
    }
}
