package game.scene.builder.rendering.component;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import game.common.rendering.TableLayout;
import game.common.rendering.component.InputField;
import game.common.rendering.component.TextLabel;
import game.resources.Assets;
import game.resources.LevelSaver;
import game.scene.level.logic.LevelConfigurationOld;
import game.scene.level.logic.LevelData;
import game.scene.level.logic.LevelMetadata;
import game.scene.level.logic.command.Instruction;
import game.utils.TimeUtils;

public final class ConfigTable extends TableLayout {
    private final LevelData levelData;

    private final Statusbar statusbar;

    private final TextField tagField;
    private final TextField titleField;
    private final TextField codeLinesNum;
    private final TextField allowedCommands;

    private final TextButton saveButton;

    public ConfigTable(LevelData levelData, Statusbar statusbar) {
        this.levelData = levelData;
        this.statusbar = statusbar;

        tagField = new InputField(levelData.metadata.tag, 20);
        titleField = new InputField(levelData.metadata.title, 20);
        codeLinesNum = new InputField(String.valueOf(
            levelData.metadata.codeLinesAmount), 3);

        allowedCommands = new InputField(String.join(
            " ", levelData.metadata.getAllowedInstructionsAsStrings()), 30);

        saveButton = new TextButton("Save level", Assets.getSkin());
        saveButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                LevelConfigurationOld newConfig = parseInput();
                if (newConfig == null) return;

                LevelSaver.saveLevel(newConfig);

                String time = TimeUtils.getFormattedTime("HH:mm:ss");
                String status = String.format("/green Level %s saved (%s)", newConfig.tag, time);
                statusbar.setActionStatus(status);
            }
        });

        setup();
    }

    @Override
    public void setup() {
        add(new TextLabel("tag (file name)")).padRight(6).right();
        add(tagField).padTop(4).row();

        add(new TextLabel("title")).padRight(4).right();
        add(titleField).padTop(4).row();

        add(new TextLabel("codelines amount")).padRight(6).right();
        add(codeLinesNum).padTop(4).row();

        add(new TextLabel("allowed commands")).padRight(6).right();
        add(allowedCommands).padTop(4).row();

        add();
        add(saveButton).padTop(4).center().fillX();
    }

    private LevelConfigurationOld parseInput() {
        LevelConfigurationOld newConfig = new LevelConfigurationOld();

        // parse tag
        newConfig.tag = tagField.getText();
        if (newConfig.tag.isBlank()) {
            String status = "/red Level not saved\nTag cannot be empty";
            statusbar.setActionStatus(status);
            return null;
        }

        //parse tilemap
        newConfig.tileMap = levelData.tileMap;
        boolean hasStartZone = newConfig.tileMap.zones.stream().anyMatch(obj -> obj.type.equals("start"));
        boolean hasWinZone = newConfig.tileMap.zones.stream().anyMatch(obj -> obj.type.equals("win"));
        if (!hasStartZone) {
            String status = "/red Level not saved\nThere must be at least one start zone";
            statusbar.setActionStatus(status);
            return null;
        }
        if (!hasWinZone) {
            String status = "/red Level not saved\nThere must be at least one win zone";
            statusbar.setActionStatus(status);
            return null;
        }

        // tile
        newConfig.title = titleField.getText();

        // parse codelines amount
        try {
            newConfig.codeLinesNum = Integer.parseInt(codeLinesNum.getText());
        } catch (NumberFormatException e) {
            String status = "/red Level not saved\nCodelines amount must be integer";
            statusbar.setActionStatus(status);
            return null;
        }

        // parse allowed instructions
        newConfig.allowedInstructions = Instruction.getListByName(allowedCommands.getText().split("\\s++"));

        return newConfig;
    }
}
