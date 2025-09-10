package com.eklepser.thelevel.graphics.ui.editor.buttons;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.eklepser.thelevel.graphics.ui.common.TextLabel;
import com.eklepser.thelevel.util.Constants;
import com.eklepser.thelevel.util.Resources;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class HelpButton extends TextButton {
    private boolean showWindow = false;

    public HelpButton(Stage rootStage) {
        super("Help", Resources.getSkin());
        Window helpWindow = getHelpWindow();
        rootStage.addActor(helpWindow);
        this.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                showWindow = !showWindow;
                helpWindow.setVisible(showWindow);
            }
        });
    }

    private Window getHelpWindow() {
        Window helpWindow = new Window("Доступные команды", Resources.getSkin());
        float editorWidth = Constants.VIEWPORT_WIDTH * Constants.EDITOR_MENU_SCALE;
        helpWindow.setSize(Constants.VIEWPORT_WIDTH - editorWidth, Constants.VIEWPORT_HEIGHT);
        helpWindow.setPosition(editorWidth, 0);
        try {
            String text = Files.readString(Path.of("assets/ui/text/help-info.txt"));
            TextLabel helpLabel = new TextLabel(text);
            helpLabel.setWrap(true);
            helpWindow.add(helpLabel).pad(10).expand().fill();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        helpWindow.setResizable(true);
        helpWindow.setVisible(false);
        helpWindow.setColor(0.5f, 0, 0.75f, 0.95f);
        return helpWindow;
    }
}
