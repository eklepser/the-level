package game.scene.level.rendering.component.editor;

import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import game.config.Display;
import game.common.rendering.TableLayout;
import game.scene.level.rendering.LevelLayout;
import game.common.rendering.component.TextLabel;
import game.scene.level.logic.editor.execution.Executor;
import game.common.logic.zone.WinZone;
import game.common.logic.zone.Zone;
import game.scene.level.logic.Level;
import game.scene.level.logic.LevelConfiguration;
import game.resources.Assets;
import game.scene.level.rendering.LevelScreen;

public final class EditorLayout extends TableLayout {
    private final LevelLayout root;
    private final LevelScreen screen;

    private final CodeLayout codeLayout;
    private final Executor executor;
    private final TextLabel statusLabel;
    private final ShowCommandsButton showCommandsButton;
    private final ParametersLayout parametersLayout;
    private final CommandsLayout commandsLayout;
    private final ScrollPane codeScrollPane;

    public EditorLayout(LevelLayout root, LevelScreen screen) {
        this.root = root;
        this.screen = screen;
        
        LevelConfiguration config = screen.getConfig();
        codeLayout = new CodeLayout(this, config);
        codeScrollPane = new ScrollPane(codeLayout, Assets.getSkin());
        codeLayout.setCodeScrollPane(codeScrollPane);

        // Init executor after codeLayout creating!
        executor = new Executor(screen, this);

        statusLabel = new TextLabel("Status:\nNo status", true);
        parametersLayout = new ParametersLayout(executor);
        commandsLayout = new CommandsLayout(config);
        showCommandsButton = new ShowCommandsButton(commandsLayout);

        setup();
    }

    @Override
    public void setup() {
        // parameters menu
        add(parametersLayout).padBottom(10).colspan(3).fillX().padLeft(10);

        // code panel
        row().colspan(3).fillX().expandX();
        add(codeScrollPane).maxHeight(Display.VIEWPORT_HEIGHT / 2.0f);

        // execution buttons
        row().padTop(10);
        add(EditorButtonFactory.createRunButton(this)).fillX().padRight(10).padLeft(10);
        add(EditorButtonFactory.createResetButton(this)).fillX().padRight(10).padLeft(10);
        add(EditorButtonFactory.createClearButton(this)).fillX().padRight(10).padLeft(10);

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
        screen.getLevel().reset();
    }

    public void stop() {
        executor.stop();
        statusLabel.setText("Status:\nEnd");
    }

    private void resetWin() {
        for (Zone zone : screen.getLevel().getZones()) {
            if (zone instanceof WinZone winZone) {
                winZone.setActivated(false);
            }
        }
    }

    // Getters:
    public LevelLayout getRoot() { return root; }

    public Executor getExecutor() { return executor; }

    public CodeLayout getCodeLayout() { return codeLayout; }

    public TextLabel getStatusLabel() { return statusLabel; }

    public ParametersLayout getParametersTable() { return parametersLayout; }
}
