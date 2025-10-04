package game.scene.level.rendering.component.editor;

import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import game.config.GraphicsConstants;
import game.scene.common.rendering.TableLayout;
import game.scene.level.rendering.LevelLayout;
import game.scene.level.rendering.component.editor.buttons.ClearButton;
import game.scene.level.rendering.component.editor.buttons.ResetButton;
import game.scene.level.rendering.component.editor.buttons.RunButton;
import game.scene.level.rendering.component.editor.buttons.ShowCommandsButton;
import game.scene.common.rendering.component.TextLabel;
import game.scene.level.logic.editor.execution.Executor;
import game.scene.common.logic.collision.zone.WinZone;
import game.scene.common.logic.collision.zone.Zone;
import game.scene.level.logic.Level;
import game.scene.level.logic.LevelConfiguration;
import game.resources.Assets;

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

        conf = level.getConfig();

        codeLayout = new CodeLayout(this, conf);
        codeScrollPane = new ScrollPane(codeLayout, Assets.getSkin());
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
        add(codeScrollPane).maxHeight(GraphicsConstants.VIEWPORT_HEIGHT / 2.0f);

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
        for (Zone zone : level.getZones()) {
            if (zone instanceof WinZone winZone) {
                winZone.setActivated(false);
            }
        }
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
