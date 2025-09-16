package com.eklepser.thelevel.graphics.game.editor;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.eklepser.thelevel.graphics.game.GameScreen;
import com.eklepser.thelevel.util.Direction;

public class KeyboardProcessor extends InputAdapter {
    private final GameScreen screen;
    private final CodeTable codeTable;

    public KeyboardProcessor(GameScreen screen) {
        this.screen = screen;
        codeTable = screen.getEditor().getCodeTable();
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.ESCAPE) {
            screen.getHelpWindow().toggle();
            return true;
        } else if (keycode == Input.Keys.DOWN || keycode == Input.Keys.ENTER) {
            codeTable.setSelectedLine(Direction.DOWN);
            return true;
        } else if (keycode == Input.Keys.UP) {
            codeTable.setSelectedLine(Direction.UP);
            return true;
        }
        return false;
    }
}
