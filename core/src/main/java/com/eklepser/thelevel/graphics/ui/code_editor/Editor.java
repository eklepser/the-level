package com.eklepser.thelevel.graphics.ui.code_editor;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.eklepser.thelevel.graphics.ui.EditorTemplates;
import com.eklepser.thelevel.logic.world.Entity;
import com.eklepser.thelevel.util.Resources;
import com.eklepser.thelevel.graphics.ui.common.TextLabel;
import com.eklepser.thelevel.util.Direction;
import com.eklepser.thelevel.logic.decoder.Executor;

public class Editor {
    private final Table editorTable;
    private final CodeField codeField;
    private final InputProcessor keyboardProcessor;
    private final Executor executor;
    private TextLabel statusLabel;

    public Editor(Entity entity) {
        editorTable = new Table();
        editorTable.add(new TextLabel("Code:")).padBottom(10);
        codeField = new CodeField(editorTable, 12);
        keyboardProcessor = createKeyboardProcessor(codeField);
        executor = new Executor(codeField.getCodeLines(), entity);

        setupLayout();
        EditorTemplates.setTemplate(codeField, EditorTemplates.MOVE_FORWARD_CYCLE);
    }

    private void setupLayout() {
        statusLabel = new TextLabel("Status:");
        statusLabel.setWrap(true);
        editorTable.row().padTop(10).padBottom(10);
        editorTable.add(new TextLabel("Action:"));
        editorTable.add(createRunButton());
        editorTable.add(createResetButton());
        editorTable.add(createClearButton());
        editorTable.row();
        editorTable.add(statusLabel).colspan(3).fillX().height(40).top();
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
                executor.stop();
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
                executor.stop();
                codeField.getCodeLines().forEach(codeLine -> codeLine.setCompleting(false));
                codeField.getCodeLines().forEach(codeLine -> codeLine.setText(""));
                statusLabel.setText("Status: ");
            }
        });
        return button;
    }

    public Table getTable() { return editorTable; }

    public InputProcessor getKeyboardProcessor() { return keyboardProcessor; }
}
