package game.scene.level.rendering.component.editor;

import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import game.config.Display;
import game.resources.Assets;
import game.scene.common.rendering.TableLayout;
import game.scene.common.rendering.component.TextLabel;
import game.scene.level.logic.Level;
import game.scene.level.rendering.LevelLayout;

public final class EditorLayout extends TableLayout {
    private final LevelLayout root;
    private final Level level;

    private final CodeField codeField;
    private final TextLabel statusLabel;
    private final ShowCommandsButton showCommandsButton;
    private final ParametersLayout parametersLayout;
    private final CommandsLayout commandsLayout;
    private final ScrollPane codeScrollPane;

    public EditorLayout(LevelLayout root, Level level) {
        this.root = root;
        this.level = level;

        codeField = new CodeField(this, level.getLevelData().metadata.codeLinesAmount);
        codeScrollPane = new ScrollPane(codeField, Assets.getSkin());
        codeField.setCodeScrollPane(codeScrollPane);

        statusLabel = new TextLabel("Status:\nNo status", true);
        parametersLayout = new ParametersLayout(level);
        commandsLayout = new CommandsLayout(level.getLevelData().metadata.allowedInstructions);
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
    public void runCode() {
        resetCode();
        root.getStatusBar().clear();
        level.runExecution(codeField.getCodeLinesText());
    }

    public void resetCode() {
        codeField.clearCompleting();
        level.resetExecution();
    }

    public void clearCode() {
        resetCode();
        codeField.clearCode();
    }

    public void stopCode() {
        level.stopExecution();
        statusLabel.setText("Status:\nEnd");
    }

    // Getters:
    public LevelLayout getRoot() { return root; }

    public CodeField getCodeLayout() { return codeField; }

    public TextLabel getStatusLabel() { return statusLabel; }

    public ParametersLayout getParametersTable() { return parametersLayout; }
}
