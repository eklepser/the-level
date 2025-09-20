package com.eklepser.thelevel.graphics.game.editor;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.eklepser.thelevel.graphics.game.GameScreen;
import com.eklepser.thelevel.util.Direction;

public class KeyboardProcessor extends InputAdapter {
    private final GameScreen screen;
    private final Editor editor;
    private int previousKey;

    public KeyboardProcessor(GameScreen screen) {
        this.screen = screen;
        editor = screen.getRootTable().getEditor();
    }

    @Override
    public boolean keyDown(int keycode) {
        if (previousKey == Input.Keys.CONTROL_LEFT) {
            if (keycode == Input.Keys.ENTER) {
                editor.run();
                previousKey = 0;
                return true;
            }
            if (keycode == Input.Keys.BACKSLASH) {
                editor.resetRunning();
                previousKey = 0;
                return true;
            }
        }
        else if (keycode == Input.Keys.F1) {
            editor.getParametersTable().setNextSliderValue(0);
            return true;
        }
        else if (keycode == Input.Keys.F2) {
            editor.getParametersTable().setPrevSliderValue(0);
            return true;
        }
        else if (keycode == Input.Keys.F4) {
            editor.resetRunning();
            return true;
        }
        else if (keycode == Input.Keys.F5) {
        editor.run();
        return true;
    }

        else if (keycode == Input.Keys.ESCAPE) {
            screen.getHelpWindow().toggle();
            return true;
        }
        else if (keycode == Input.Keys.DOWN || keycode == Input.Keys.ENTER) {
            editor.getCodeTable().setSelectedLine(Direction.DOWN);
            return true;
        }
        else if (keycode == Input.Keys.UP || keycode == Input.Keys.TAB) {
            editor.getCodeTable().setSelectedLine(Direction.UP);
            return true;
        }
        if (keycode != previousKey) previousKey = keycode;
        return false;
    }
}
