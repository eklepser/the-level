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
import game.config.Paths;
import game.resources.LevelSaver;
import game.scene.level.logic.command.Instruction;
import game.scene.level.logic.LevelConfiguration;
import game.resources.Assets;

public final class ConfigTable extends TableLayout {
    private final LevelConfiguration config;

    private final Statusbar statusbar;

    private final TextField tagField;
    private final TextField titleField;
    private final TextField codeLinesNum;
    private final TextField allowedCommands;

    private final TextButton saveButton;

    public ConfigTable(LevelConfiguration config, Statusbar statusbar) {
        this.config = config;
        this.statusbar = statusbar;

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
                LevelConfiguration newConfig = parseInput();
                if (newConfig == null) return;

                LevelSaver.saveLevel(newConfig);

                String status = String.format("/green Level %s saved", newConfig.tag);
                statusbar.setActionText(status);
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

    private LevelConfiguration parseInput() {
        LevelConfiguration newConfig = new LevelConfiguration();

        // parse tag
        newConfig.tag = tagField.getText();
        if (newConfig.tag.isBlank()) {
            String status = "/red Level not saved\nTag cannot be empty";
            statusbar.setActionText(status);
            return null;
        }

        //parse tilemap
        newConfig.tileMap = config.tileMap;
        boolean hasStartZone = newConfig.tileMap.zones.stream().anyMatch(obj -> obj.type.equals("start"));
        boolean hasWinZone = newConfig.tileMap.zones.stream().anyMatch(obj -> obj.type.equals("win"));
        if (!hasStartZone) {
            String status = "/red Level not saved\nThere must be at least one start zone";
            statusbar.setActionText(status);
            return null;
        }
        if (!hasWinZone) {
            String status = "/red Level not saved\nThere must be at least one win zone";
            statusbar.setActionText(status);
            return null;
        }

        newConfig.title = titleField.getText();

        // parse codelines amount
        try {
            newConfig.codeLinesNum = Integer.parseInt(codeLinesNum.getText());
        } catch (NumberFormatException e) {
            return null;
        }

        // parse allowed instructions
        newConfig.allowedInstructions = Instruction.getListByName(allowedCommands.getText().split("\\s++"));

        return newConfig;
    }

    // Getters:
    public boolean hasTextFieldFocus() {
        return tagField.hasKeyboardFocus() ||
            titleField.hasKeyboardFocus() ||
            codeLinesNum.hasKeyboardFocus() ||
            allowedCommands.hasKeyboardFocus();
    }
}
