package com.eklepser.thelevel.graphics.game.editor;

import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.eklepser.thelevel.graphics.common.TextLabel;
import com.eklepser.thelevel.graphics.game.editor.buttons.ClearButton;
import com.eklepser.thelevel.graphics.game.editor.buttons.ResetButton;
import com.eklepser.thelevel.graphics.game.editor.buttons.RunButton;
import com.eklepser.thelevel.graphics.game.root.RootTable;
import com.eklepser.thelevel.logic.decoder.execution.Executor;
import com.eklepser.thelevel.logic.world.level.Level;
import com.eklepser.thelevel.logic.world.level.LevelConfiguration;
import com.eklepser.thelevel.logic.world.zone.WinZone;
import com.eklepser.thelevel.logic.world.zone.Zone;
import com.eklepser.thelevel.util.Layout;
import com.eklepser.thelevel.util.Resources;

public class Editor extends Table {
    private final RootTable rootTable;
    private final Level level;
    private final LevelConfiguration conf;
    private final CodeTable codeTable;
    private final Executor executor;
    private final TextLabel statusLabel;
    private final RunButton runButton;
    private final ParametersPanel parametersPanel;
    private final CommandsPanel commandsPanel;
    private final ScrollPane codeScrollPane;


    public Editor(RootTable rootTable, Level level) {
        this.rootTable = rootTable;
        this.level = level;
        conf = level.getConf();

        codeTable = new CodeTable(this, conf);
        codeScrollPane = new ScrollPane(codeTable, Resources.getSkin());
        codeTable.setupLayout(codeScrollPane);

        executor = new Executor(level, conf, this);

        statusLabel = new TextLabel("Status:\nNo status");
        statusLabel.setWrap(true);

        runButton = new RunButton(this);
        parametersPanel = new ParametersPanel(executor);
        commandsPanel = new CommandsPanel(level.getConf());
        setupLayout(codeScrollPane);
    }

    private void setupLayout(ScrollPane codeScrollPane) {
        // Setup elements:
        Table commandsTable = new Table();
        commandsTable.add(new TextLabel("Allowed commands:")).left().row();
        commandsTable.add(commandsPanel).left();

        // Adding elements:
        add(parametersPanel).padBottom(10).colspan(3).fillX().padLeft(10);

        // code panel
        row().colspan(3).fillX().expandX();
        add(codeScrollPane).maxHeight(Layout.VIEWPORT_HEIGHT / 2.0f);

        // execution buttons
        row().padTop(10);
        //add(new TextLabel("Action:"));
        add(runButton).fillX().padRight(10).padLeft(10);
        add(new ResetButton(this)).fillX().padRight(10).padLeft(10);
        add(new ClearButton(this)).fillX().padRight(10).padLeft(10);

        // commands panel
        row().padTop(10).colspan(3).padLeft(19);
        add(commandsTable).left();
//        TextLabel allowedCmdsLabel = new TextLabel("Allowed commands:\n" + conf.getAllowedInstructions());
//        allowedCmdsLabel.setWrap(true);
//        add(allowedCmdsLabel).fillX();

        row().padTop(10).colspan(3).padLeft(19);
        add(statusLabel).fillX();

        row();
        add().expandY().fillY();
    }

    public void run() {
        resetRunning();
        rootTable.getStatusRow().clear();
        String status = executor.runExecution();
        statusLabel.setText("Status:\n" + status);
    }

    public void clearRunning() {
        resetRunning();
        codeTable.clearCode();
    }

    public void resetRunning() {
        System.out.println("Resetting");
        codeTable.getCodeLines().forEach(
            codeLine -> codeLine.setCompleting(false));
        executor.stop();
        statusLabel.setText("Status:\nReset");
        resetWin();
        level.reset();
    }

    public void stop() {
        executor.stop();
        statusLabel.setText("Status:\nEnd");
    }

    private void resetWin() {
        for (Zone zone : level.getZones()) {
            if (zone instanceof WinZone winZone) {
                winZone.setActivated(false);
            }
        }
    }

    public RootTable getRootTable() { return rootTable; }

    public Level getLevel() { return level; }

    public Executor getExecutor() { return executor; }

    public CodeTable getCodeTable() { return codeTable; }

    public TextLabel getStatusLabel() { return statusLabel; }

    public RunButton getRunButton() { return runButton; }

    public ParametersPanel getParametersTable() { return parametersPanel; }
}
