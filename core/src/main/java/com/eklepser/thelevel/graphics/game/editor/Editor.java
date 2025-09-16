package com.eklepser.thelevel.graphics.game.editor;

import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.eklepser.thelevel.graphics.common.TextLabel;
import com.eklepser.thelevel.graphics.game.GameScreen;
import com.eklepser.thelevel.graphics.game.editor.buttons.ClearButton;
import com.eklepser.thelevel.graphics.game.editor.buttons.HelpButton;
import com.eklepser.thelevel.graphics.game.editor.buttons.ResetButton;
import com.eklepser.thelevel.graphics.game.editor.buttons.RunButton;
import com.eklepser.thelevel.logic.decoder.execution.Executor;
import com.eklepser.thelevel.logic.world.level.Level;
import com.eklepser.thelevel.logic.world.level.LevelConfiguration;
import com.eklepser.thelevel.util.Resources;

public class Editor extends Table {
    private final GameScreen screen;
    private final Level level;
    private final LevelConfiguration conf;
    private final CodeTable codeTable;
    private final Executor executor;
    private final TextLabel statusLabel;
    private final TextLabel hotKeysLabel;
    private final RunButton runButton;
    private final ParametersTable parametersTable;
    private final ScrollPane codeScrollPane;

    public Editor(GameScreen screen, Level level) {
        this.screen = screen;
        this.level = level;
        conf = level.getConf();

        codeTable = new CodeTable(this, conf);
        codeScrollPane = new ScrollPane(codeTable, Resources.getSkin());
        codeTable.setupLayout(codeScrollPane);

        executor = new Executor(conf, codeTable.getCodeLines(), level.getEntities());

        statusLabel = new TextLabel("Status:\nNo status");
        statusLabel.setWrap(true);
        hotKeysLabel = new TextLabel("Hotkeys:\n" +
            "F1 and F2 -> change speed\n" +
            "F4 or CTRL+\\ -> reset code\n" +
            "F5 or CTRL+ENTER -> run code\n");

        runButton = new RunButton(this);
        parametersTable = new ParametersTable(executor);
        setupLayout(codeScrollPane);
    }

    private void setupLayout(ScrollPane codeScrollPane) {
        add(new HelpButton(screen.getHelpWindow()));
        add(parametersTable).padBottom(10).colspan(3).fillX().padLeft(10);

        row().colspan(4).fillX().expandX();
        add(codeScrollPane);

        row().padTop(10);
        add(new TextLabel("Action:"));
        add(runButton).fillX().padRight(10);
        add(new ResetButton(this)).fillX().padRight(10);
        add(new ClearButton(this)).fillX().padRight(10);

        row().padTop(10).colspan(4).padLeft(19);
        TextLabel allowedCmdsLabel = new TextLabel("Allowed commands:\n" + conf.getAllowedInstructions());
        allowedCmdsLabel.setWrap(true);
        add(allowedCmdsLabel).fillX();

        row().padTop(10).colspan(4).padLeft(19);
        add(statusLabel).fillX();

        row();
        add().expandY().fillY();

        row().padTop(10).colspan(4).padLeft(19);
        add(hotKeysLabel).fillX();
    }

    public void run() {
        resetRunning();
        String status = executor.checkAndExecute();
        statusLabel.setText("Status:\n" + status);
    }

    public void resetRunning() {
        System.out.println("Resetting");
        getCodeTable().getCodeLines().forEach(
            codeLine -> codeLine.setCompleting(false));
        getExecutor().stop();
        getStatusLabel().setText("Status:\nReset");
        getLevel().reset();
    }

    public void clearRunning() {
        resetRunning();
        codeTable.clearCode();
    }

    public Level getLevel() { return level; }

    public Executor getExecutor() { return executor; }

    public CodeTable getCodeTable() { return codeTable; }

    public TextLabel getStatusLabel() { return statusLabel; }

    public RunButton getRunButton() { return runButton; }

    public ParametersTable getParametersTable() { return parametersTable; }
}
