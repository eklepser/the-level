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
import com.eklepser.thelevel.logic.decoder.Executor;

public class Editor {
    private final Table root;
    private final CodeField codeField;
    private final InputProcessor keyboardProcessor;
    private final Executor executor;
    private final TextLabel statusLabel;

    public Editor(Cat cat, World gameField) {
        root = new Table();
        root.add(new TextLabel("Code:")).padBottom(10);
        codeField = new CodeField(root, 12);
        keyboardProcessor = createKeyboardProcessor(codeField);
        executor = new Executor(codeField.getCodeLines(), gameField, cat);
        statusLabel = new TextLabel("Status:");
        statusLabel.setWrap(true);

        root.row().padBottom(10);
        root.add(createRunButton());
        root.add(createResetButton());
        root.add(createClearButton());
        root.row();
        root.add(statusLabel).colspan(3).fillX().height(40).top();
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

    private Button createRunButton() {
        Button button = new TextButton("Run", Resources.getSkin());
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                String status = executor.translateAll();
                statusLabel.setText(status);
            }
        });
        return button;
    }

    private Button createResetButton() {
        Button button = new TextButton("Stop", Resources.getSkin());
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Stopping");
                codeField.getCodeLines().forEach(codeLine -> codeLine.setCompleting(false));
                statusLabel.setText("Status: ");
            }
        });
        return button;
    }

    private Button createClearButton() {
        Button button = new TextButton("Clear", Resources.getSkin());
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Clearing");
                codeField.getCodeLines().forEach(codeLine -> codeLine.setCompleting(false));
                codeField.getCodeLines().forEach(codeLine -> codeLine.setText(""));
                statusLabel.setText("Status: ");
            }
        });
        return button;
    }

    public Table getTable() { return root; }

    public InputProcessor getKeyboardProcessor() { return keyboardProcessor; }
}
