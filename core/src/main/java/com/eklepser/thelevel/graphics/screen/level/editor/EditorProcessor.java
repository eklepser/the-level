package com.eklepser.thelevel.graphics.screen.level.editor;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.eklepser.thelevel.graphics.screen.level.LevelScreen;
import com.eklepser.thelevel.util.Direction;

public class EditorProcessor extends InputAdapter {
    private final LevelScreen screen;
    private final EditorLayout editorLayout;
    private int previousKey;

    public EditorProcessor(LevelScreen screen) {
        this.screen = screen;
        editorLayout = screen.getLayout().getEditor();
    }

    @Override
    public boolean keyDown(int keycode) {
        if (previousKey == Input.Keys.CONTROL_LEFT) {
            if (keycode == Input.Keys.ENTER) {
                editorLayout.run();
                previousKey = 0;
                return true;
            }
            if (keycode == Input.Keys.BACKSLASH) {
                editorLayout.resetRunning();
                previousKey = 0;
                return true;
            }
        }
        else if (keycode == Input.Keys.F1) {
            editorLayout.getParametersTable().setNextSliderValue();
            return true;
        }
        else if (keycode == Input.Keys.F2) {
            editorLayout.getParametersTable().setPrevSliderValue();
            return true;
        }
        else if (keycode == Input.Keys.F4) {
            editorLayout.resetRunning();
            return true;
        }
        else if (keycode == Input.Keys.F5) {
        editorLayout.run();
        return true;
        }
        else if (keycode == Input.Keys.ESCAPE) {
            screen.getHelpWindow().toggle();
            return true;
        }
        else if (keycode == Input.Keys.DOWN || keycode == Input.Keys.ENTER) {
            editorLayout.getCodeLayout().setSelectedLine(Direction.DOWN);
            return true;
        }
        else if (keycode == Input.Keys.UP || keycode == Input.Keys.TAB) {
            editorLayout.getCodeLayout().setSelectedLine(Direction.UP);
            return true;
        }
        if (keycode != previousKey) previousKey = keycode;
        return false;
    }
}
