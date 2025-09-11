package com.eklepser.thelevel.graphics.ui.editor;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.eklepser.thelevel.graphics.screen.PlayScreen;
import com.eklepser.thelevel.util.Direction;

public class KeyboardProcessor extends InputAdapter {
    private final PlayScreen screen;
    private final CodeTable codeTable;

    public KeyboardProcessor(PlayScreen screen) {
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
