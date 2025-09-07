package com.eklepser.thelevel.graphics.ui.code_editor;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.eklepser.thelevel.Resources;
import com.eklepser.thelevel.graphics.ui.common.TextLabel;
import com.eklepser.thelevel.graphics.world.World;
import com.eklepser.thelevel.logic.Cat;
import com.eklepser.thelevel.logic.Direction;
import com.eklepser.thelevel.logic.decoder.Translator;

public class Editor {
    private final Table root;
    private final InputProcessor keyboardProcessor;

    public Editor(Cat cat, World gameField) {
        root = new Table();
        root.add(new TextLabel("Code:")).padBottom(10);
        CodeField codeField = new CodeField(root, 8);
        Button runButton = createRunButton(codeField, cat, gameField);
        root.row();
        root.add(runButton);
        keyboardProcessor = createKeyboardProcessor(codeField);
    }

    private InputAdapter createKeyboardProcessor(CodeField codeField) {
        return new InputAdapter() {
            @Override
            public boolean keyDown(int keycode) {
                if (keycode == Input.Keys.DOWN || keycode == Input.Keys.ENTER) {
                    codeField.setSelectedLine(Direction.DOWN);
                    return true;
                } else if (keycode == Input.Keys.UP) {
                    codeField.setSelectedLine(Direction.UP);
                    return true;
                }
                return false;
            }
        };
    }

    private Button createRunButton(CodeField codeField, Cat cat, World gameField) {
        Button button = new TextButton("Run", Resources.getSkin());
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Running");
                Translator.translateAll(codeField.getCodeLines(), cat, gameField);
            }
        });
        return button;
    }

    public Table getTable() { return root; }

    public InputProcessor getKeyboardProcessor() { return keyboardProcessor; }
}
