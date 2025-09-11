package com.eklepser.thelevel.graphics.ui.editor;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.eklepser.thelevel.graphics.screen.PlayScreen;
import com.eklepser.thelevel.graphics.ui.common.TextLabel;
import com.eklepser.thelevel.graphics.ui.editor.buttons.ClearButton;
import com.eklepser.thelevel.graphics.ui.editor.buttons.HelpButton;
import com.eklepser.thelevel.graphics.ui.editor.buttons.RunButton;
import com.eklepser.thelevel.graphics.ui.editor.buttons.StopButton;
import com.eklepser.thelevel.logic.decoder.Executor;
import com.eklepser.thelevel.logic.world.Entity;
import com.eklepser.thelevel.util.Constants;

import java.util.List;

public class EditorTable extends Table {
    private final PlayScreen screen;
    private final CodeTable codeTable;
    private final Executor executor;
    private final TextLabel statusLabel;
    private TextLabel debugLabel;

    public EditorTable(PlayScreen screen, List<Entity> entities, int linesAmount) {
        this.screen = screen;
        setDebug(Constants.IS_UI_DEBUGGING);

        codeTable = new CodeTable(this, linesAmount);
        codeTable.setTemplate(Constants.EDITOR_CODE_TEMPLATE);
        executor = new Executor(codeTable.getCodeLines(), entities);

        statusLabel = new TextLabel("Status:");
        statusLabel.setWrap(true);

        setupLayout();
    }

    private void setupLayout() {
        add(new HelpButton(screen.getHelpWindow()));
        add(new ParametersTable(executor)).padBottom(10).colspan(3).fillX().padLeft(10);

        row().colspan(4).fillX().expand();
        add(codeTable);

        row().padTop(10);
        add(new TextLabel("Action:"));
        add(new RunButton(executor, statusLabel));
        add(new StopButton(executor, statusLabel, codeTable));
        add(new ClearButton(executor, statusLabel, codeTable));

        row().padTop(10).colspan(4);
        add(statusLabel).fillX();

        if (Constants.IS_CODE_DEBUGGING) {
            debugLabel = new TextLabel("Debug:");
            row();
            add(debugLabel).colspan(3).fillX().height(40).top();
        }
    }

    public void setDebugText(String text) {
        if (debugLabel != null) debugLabel.setText(text);
    }

    public CodeTable getCodeTable() {
        return codeTable;
    }
}
