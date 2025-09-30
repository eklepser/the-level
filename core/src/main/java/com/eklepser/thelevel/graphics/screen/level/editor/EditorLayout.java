package com.eklepser.thelevel.graphics.screen.level.editor;

import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.eklepser.thelevel.graphics.screen.TableLayout;
import com.eklepser.thelevel.graphics.screen.level.LevelLayout;
import com.eklepser.thelevel.graphics.screen.level.editor.buttons.ClearButton;
import com.eklepser.thelevel.graphics.screen.level.editor.buttons.ResetButton;
import com.eklepser.thelevel.graphics.screen.level.editor.buttons.RunButton;
import com.eklepser.thelevel.graphics.screen.level.editor.buttons.ShowCommandsButton;
import com.eklepser.thelevel.graphics.utils.TextLabel;
import com.eklepser.thelevel.logic.decoder.execution.Executor;
import com.eklepser.thelevel.logic.world.level.Level;
import com.eklepser.thelevel.logic.world.level.LevelConfiguration;
import com.eklepser.thelevel.util.Resources;

public class EditorLayout extends TableLayout {
    private final LevelLayout root;
    private final Level level;
    private final LevelConfiguration conf;
    private final CodeLayout codeLayout;
    private final Executor executor;
    private final TextLabel statusLabel;
    private final RunButton runButton;
    private final ShowCommandsButton showCommandsButton;
    private final ParametersLayout parametersLayout;
    private final CommandsLayout commandsLayout;
    private final ScrollPane codeScrollPane;

    public EditorLayout(LevelLayout root, Level level) {
        this.root = root;
        this.level = level;
        conf = (LevelConfiguration) level.getConfig();
        codeLayout = new CodeLayout(this, conf);
        codeScrollPane = new ScrollPane(codeLayout, Resources.getSkin());
        codeLayout.setCodeScrollPane(codeScrollPane);
        // Init executor after codeLayout creating!
        executor = new Executor(level, this);

        statusLabel = new TextLabel("Status:\nNo status", true);
        runButton = new RunButton(this);
        parametersLayout = new ParametersLayout(executor);
        commandsLayout = new CommandsLayout(conf);
        showCommandsButton = new ShowCommandsButton(commandsLayout);

        setup();
    }

    @Override
    public void setup() {
        // parameters menu
        add(parametersLayout).padBottom(10).colspan(3).fillX().padLeft(10);

        // code panel
        row().colspan(3).fillX().expandX();
        add(codeScrollPane).maxHeight(TableLayout.VIEWPORT_HEIGHT / 2.0f);

        // execution buttons
        row().padTop(10);
        add(runButton).fillX().padRight(10).padLeft(10);
        add(new ResetButton(this)).fillX().padRight(10).padLeft(10);
        add(new ClearButton(this)).fillX().padRight(10).padLeft(10);

        // commands panel
        Table commandsTable = new Table().left();
        HorizontalGroup group = new HorizontalGroup();
        group.addActor(new TextLabel("Allowed commands ("));
        group.addActor(showCommandsButton);
        group.addActor(new TextLabel("):"));
        commandsTable.add(group).left();
        commandsTable.row().colspan(2).fillY().expandY();
        commandsTable.add(commandsLayout).left().fillY().expandY();

        row().padTop(10).colspan(3).padLeft(19);
        add(commandsTable).left().expandY().fillY();

        // status label
        row().padTop(10).colspan(3).padLeft(19);
        add(statusLabel).fillX();

        row();
        add().expandY().fillY();
    }

    // Class logic:
    public void run() {
        resetRunning();
        root.getStatusBar().clear();
        String status = executor.runExecution();
        statusLabel.setText("Status:\n" + status);
    }

    public void clearRunning() {
        resetRunning();
        codeLayout.clearCode();
    }

    public void resetRunning() {
        System.out.println("Resetting");
        codeLayout.getCodeLines().forEach(
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
//        for (Collidable zone : level.getCollidables()) {
//            if (zone instanceof WinZone winZone) {
//                winZone.setActivated(false);
//            }
//        }
    }

    // Getters:
    public LevelLayout getRoot() { return root; }

    public Level getLevel() { return level; }

    public Executor getExecutor() { return executor; }

    public CodeLayout getCodeLayout() { return codeLayout; }

    public TextLabel getStatusLabel() { return statusLabel; }

    public RunButton getRunButton() { return runButton; }

    public ParametersLayout getParametersTable() { return parametersLayout; }
}
