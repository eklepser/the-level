package com.eklepser.thelevel.graphics.screen.builder;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter;
import com.eklepser.thelevel.graphics.screen.TableLayout;
import com.eklepser.thelevel.graphics.util.InputField;
import com.eklepser.thelevel.graphics.util.TextLabel;
import com.eklepser.thelevel.logic.decoder.command.Instruction;
import com.eklepser.thelevel.logic.world.level.LevelConfiguration;
import com.eklepser.thelevel.util.Resources;

public class ConfigTable extends TableLayout {
    private final BuilderLayout root;
    private final LevelConfiguration config;
    private final Builder builder;

    private final TextField tagField;
    private final TextField titleField;
    private final TextField codeLinesNum;
    private final TextField allowedCommands;

    private final TextButton saveButton;

    public ConfigTable(BuilderLayout root, BuilderScreen screen) {
        this.root = root;
        config = screen.getConfig();
        builder = screen.getBuilder();

        tagField = new InputField(config.tag, 20);
        titleField = new InputField(config.title, 20);
        codeLinesNum = new InputField(String.valueOf(
            config.codeLinesNum), 3);
        allowedCommands = new InputField(String.valueOf(
            config.allowedInstructions), 30);

        saveButton = new TextButton("Save level", Resources.getSkin());
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
        add(saveButton).row();

        add(new TextLabel("Tag (file name):")).padRight(5).left();
        add(tagField).padTop(5).row();

        add(new TextLabel("Level title:")).padRight(5).left();
        add(titleField).padTop(5).row();

        add(new TextLabel("Codelines amount:")).padRight(5).left();
        add(codeLinesNum).padTop(5).row();

        add(new TextLabel("Allowed commands:")).padRight(5).left();
        add(allowedCommands).padTop(5);
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

        String status = String.format("Level %s saved", newConfig.tag);
        root.getStatusBar().setActionText(status);
    }

    private boolean isDataCorrect(LevelConfiguration newConfig) {
        newConfig.id = 0;
        newConfig.tag = tagField.getText();
        newConfig.tileMap = config.tileMap;
        newConfig.title = titleField.getText();
        newConfig.codeLinesNum = Integer.parseInt(codeLinesNum.getText());
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
