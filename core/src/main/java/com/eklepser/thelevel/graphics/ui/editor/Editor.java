package com.eklepser.thelevel.graphics.ui.editor;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.eklepser.thelevel.graphics.ui.common.HelpWindow;
import com.eklepser.thelevel.graphics.ui.common.TextLabel;
import com.eklepser.thelevel.graphics.ui.editor.buttons.ClearButton;
import com.eklepser.thelevel.graphics.ui.editor.buttons.HelpButton;
import com.eklepser.thelevel.graphics.ui.editor.buttons.RunButton;
import com.eklepser.thelevel.graphics.ui.editor.buttons.StopButton;
import com.eklepser.thelevel.logic.decoder.Executor;
import com.eklepser.thelevel.logic.world.Entity;
import com.eklepser.thelevel.util.CodeTemplates;
import com.eklepser.thelevel.util.Constants;
import com.eklepser.thelevel.util.Direction;

import java.util.List;

public class Editor {
    private final Table editorTable;
    private final CodeTable codeTable;
    private final InputProcessor keyboardProcessor;
    private final Executor executor;
    private TextLabel statusLabel;
    private TextLabel debugLabel;

    public Editor(HelpWindow helpWindow, List<Entity> entities, int linesAmount) {
        editorTable = new Table();
        editorTable.setDebug(Constants.IS_UI_DEBUGGING);

        codeTable = new CodeTable(editorTable, linesAmount);
        executor = new Executor(codeTable.getCodeLines(), entities);
        keyboardProcessor = createKeyboardProcessor(codeTable);

        editorTable.add(new HelpButton(helpWindow));
        editorTable.add(new ParametersTable(executor)).padBottom(10).colspan(3).fillX().padLeft(10);

        setupLayout();
        CodeTemplates.setTemplate(codeTable, Constants.EDITOR_CODE_TEMPLATE);
    }

    private void setupLayout() {
        editorTable.row().colspan(4).fillX().expand();
        editorTable.add(codeTable);
        statusLabel = new TextLabel("Status:");
        statusLabel.setWrap(true);
        editorTable.row().padTop(10).padBottom(10);
        editorTable.add(new TextLabel("Action:"));
        editorTable.add(new RunButton(executor, statusLabel));
        editorTable.add(new StopButton(executor, statusLabel, codeTable));
        editorTable.add(new ClearButton(executor, statusLabel, codeTable));
        editorTable.row();
        editorTable.add(statusLabel).colspan(3).fillX().height(40).top();

        if (Constants.IS_CODE_DEBUGGING) {
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

    private InputAdapter createKeyboardProcessor(CodeTable codeTable) {
        return new InputAdapter() {
            @Override
            public boolean keyDown(int keycode) {
                if (keycode == Input.Keys.DOWN || keycode == Input.Keys.ENTER) {
                    codeTable.setSelectedLine(Direction.DOWN);
                    return true;
                } else if (keycode == Input.Keys.UP) {
                    codeTable.setSelectedLine(Direction.UP);
                    return true;
                }
                return false;
            }
        };
    }
}
