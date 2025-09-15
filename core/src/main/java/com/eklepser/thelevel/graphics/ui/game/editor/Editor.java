package com.eklepser.thelevel.graphics.ui.game.editor;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.eklepser.thelevel.graphics.screen.GameScreen;
import com.eklepser.thelevel.graphics.ui.common.TextLabel;
import com.eklepser.thelevel.graphics.ui.game.editor.buttons.ClearButton;
import com.eklepser.thelevel.graphics.ui.game.editor.buttons.HelpButton;
import com.eklepser.thelevel.graphics.ui.game.editor.buttons.ResetButton;
import com.eklepser.thelevel.graphics.ui.game.editor.buttons.RunButton;
import com.eklepser.thelevel.logic.decoder.Executor;
import com.eklepser.thelevel.logic.world.collision.Entity;
import com.eklepser.thelevel.logic.world.level.Level;
import com.eklepser.thelevel.logic.world.level.LevelDescription;
import com.eklepser.thelevel.util.Constants;
import com.eklepser.thelevel.util.Resources;

import java.util.List;

public class Editor extends Table {
    private final GameScreen screen;
    private final Level level;
    private final LevelDescription desc;
    private final CodeTable codeTable;
    private final Executor executor;
    private final TextLabel statusLabel;
    private final RunButton runButton;

    public Editor(GameScreen screen, Level level) {
        this.screen = screen;
        this.level = level;
        desc = level.getDesc();
        setDebug(Constants.IS_UI_DEBUGGING);

        codeTable = new CodeTable(this, desc);
        codeTable.setTemplate(Constants.EDITOR_CODE_TEMPLATE);
        executor = new Executor(desc, codeTable.getCodeLines(), level.getEntities());

        statusLabel = new TextLabel("Status:");
        statusLabel.setWrap(true);

        runButton = new RunButton(executor, statusLabel);

        setupLayout();
    }

    private void setupLayout() {
        add(new HelpButton(screen.getHelpWindow()));
        add(new ParametersTable(executor)).padBottom(10).colspan(3).fillX().padLeft(10);

        row().colspan(4).fillX().expand();
        add(codeTable);

        row().padTop(10);
        add(new TextLabel("Action:"));
        add(runButton).fillX().padRight(10);
        add(new ResetButton(this)).fillX().padRight(10);
        add(new ClearButton(this)).fillX().padRight(10);

        row().padTop(10).colspan(4).padLeft(19);
        TextLabel allowedCmdsLabel = new TextLabel("Allowed commands:\n" + desc.getAllowedInstructions());
        allowedCmdsLabel.setWrap(true);
        add(allowedCmdsLabel).fillX();

        row().padTop(10).colspan(4).padLeft(19);
        add(statusLabel).fillX();
    }

    public Level getLevel() { return level; }

    public Executor getExecutor() { return executor; }

    public CodeTable getCodeTable() {
        return codeTable;
    }

    public TextLabel getStatusLabel() { return statusLabel; }

    public RunButton getRunButton() { return runButton; }
}
