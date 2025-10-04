package game.scene.builder.rendering.component;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter;
import game.common.rendering.TableLayout;
import game.common.rendering.component.InputField;
import game.common.rendering.component.TextLabel;
import game.scene.level.logic.editor.command.Instruction;
import game.scene.level.logic.LevelConfiguration;
import game.resources.Assets;
import game.scene.builder.rendering.BuilderLayout;
import game.scene.builder.rendering.BuilderScreen;

public class ConfigTable extends TableLayout {
    private final BuilderScreen screen;
    private final BuilderLayout root;
    private final LevelConfiguration config;

    private final TextField tagField;
    private final TextField titleField;
    private final TextField codeLinesNum;
    private final TextField allowedCommands;

    private final TextButton saveButton;

    public ConfigTable(BuilderLayout root, BuilderScreen screen) {
        this.root = root;
        this.screen = screen;
        config = screen.getConfig();

        tagField = new InputField(config.tag, 20);
        titleField = new InputField(config.title, 20);
        codeLinesNum = new InputField(String.valueOf(
            config.codeLinesNum), 3);

        allowedCommands = new InputField(String.join(
            " ", config.getAllowedInstructionsAsStrings()), 30);

        saveButton = new TextButton("Save level", Assets.getSkin());
        saveButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                saveLevel();
            }
        });

        setup();
    }

    @Override
    public void setup() {
        add(new TextLabel("tag (file name)")).padRight(6).left();
        add(tagField).padTop(4).row();

        add(new TextLabel("title")).padRight(4).left();
        add(titleField).padTop(4).row();

        add(new TextLabel("codelines amount")).padRight(6).left();
        add(codeLinesNum).padTop(4).row();

        add(new TextLabel("allowed commands")).padRight(6).left();
        add(allowedCommands).padTop(4).row();

        add();
        add(saveButton).padTop(4).center().fillX();
    }

    // Class logic:
    private void saveLevel() {
        LevelConfiguration newConfig = new LevelConfiguration();

        if (!isDataCorrect(newConfig)) return;

        Json json = new Json();
        json.setOutputType(JsonWriter.OutputType.json);
        String jsonContent = json.toJson(newConfig);

        String path = String.format("builder/level_%s.json", newConfig.tag);
        FileHandle file = Gdx.files.local(path);
        file.writeString(jsonContent, false);

        Gdx.app.log("Save", "Level saved to: " + file.path());

        String status = String.format("/green Level %s saved", newConfig.tag);
        root.getStatusBar().setActionText(status);
    }

    private boolean isDataCorrect(LevelConfiguration newConfig) {
        // Tag parse:
        newConfig.tag = tagField.getText();
        if (newConfig.tag.isBlank()) {
            String status = "/red Level not saved\nTag cannot be empty";
            root.getStatusBar().setActionText(status);
            return false;
        }

        // TileMap parse:
        newConfig.tileMap = screen.getMap();
        boolean hasStartZone = newConfig.tileMap.zones.stream().anyMatch(obj -> obj.type.equals("start"));
        boolean hasWinZone = newConfig.tileMap.zones.stream().anyMatch(obj -> obj.type.equals("win"));
        if (!hasStartZone) {
            String status = "/red Level not saved\nThere must be at least one start zone";
            root.getStatusBar().setActionText(status);
            return false;
        }
        if (!hasWinZone) {
            String status = "/red Level not saved\nThere must be at least one win zone";
            root.getStatusBar().setActionText(status);
            return false;
        }

        // Title parse:
        newConfig.title = titleField.getText();

        // CodeLinesNum Instructions parse:
        try {
            newConfig.codeLinesNum = Integer.parseInt(codeLinesNum.getText());
        } catch (NumberFormatException e) {
            return false;
        }

        // AllowedInstructions parse:
        newConfig.allowedInstructions = Instruction.listFrom(allowedCommands.getText().split("\\s++"));

        return true;
    }

    // Getters:
    public boolean hasTextFieldFocus() {
        return tagField.hasKeyboardFocus() ||
            titleField.hasKeyboardFocus() ||
            codeLinesNum.hasKeyboardFocus() ||
            allowedCommands.hasKeyboardFocus();
    }
}
