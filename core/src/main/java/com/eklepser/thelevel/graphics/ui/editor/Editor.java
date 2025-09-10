package com.eklepser.thelevel.graphics.ui.editor;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.eklepser.thelevel.graphics.ui.common.TextLabel;
import com.eklepser.thelevel.graphics.ui.editor.buttons.*;
import com.eklepser.thelevel.logic.decoder.Executor;
import com.eklepser.thelevel.logic.world.Entity;
import com.eklepser.thelevel.util.CodeTemplates;
import com.eklepser.thelevel.util.Constants;
import com.eklepser.thelevel.util.Direction;

import java.util.List;

public class Editor {
    private final Table editorTable;
    private final CodeField codeField;
    private final InputProcessor keyboardProcessor;
    private final Executor executor;
    private TextLabel statusLabel;
    private TextLabel debugLabel;

    public Editor(Stage rootStage, List<Entity> entities, int linesAmount) {
        editorTable = new Table();
        editorTable.add(new HelpButton(rootStage));
        editorTable.add(new TextLabel("Code:")).padBottom(10).colspan(3);

        codeField = new CodeField(editorTable, linesAmount);
        keyboardProcessor = createKeyboardProcessor(codeField);
        executor = new Executor(codeField.getCodeLines(), entities);

        setupLayout();
        CodeTemplates.setTemplate(codeField, Constants.EDITOR_CODE_TEMPLATE);
    }

    private void setupLayout() {
        statusLabel = new TextLabel("Status:");
        statusLabel.setWrap(true);
        editorTable.row().padTop(10).padBottom(10);
        editorTable.add(new TextLabel("Action:"));
        editorTable.add(new RunButton(executor, statusLabel));
        editorTable.add(new StopButton(executor, statusLabel, codeField));
        editorTable.add(new ClearButton(executor, statusLabel, codeField));
        editorTable.row();
        editorTable.add(statusLabel).colspan(3).fillX().height(40).top();
        if (Constants.IS_DEBUG_MODE) {
            debugLabel = new TextLabel("Debug:");
            editorTable.row();
            editorTable.add(debugLabel).colspan(3).fillX().height(40).top();
        }
    }

    public Table getTable() { return editorTable; }

    public InputProcessor getKeyboardProcessor() { return keyboardProcessor; }

    public void setDebugText(String text) {
        if (debugLabel != null) debugLabel.setText(text);
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
}
