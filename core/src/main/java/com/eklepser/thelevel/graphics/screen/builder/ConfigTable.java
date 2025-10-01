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
import com.eklepser.thelevel.graphics.render.TileMap;
import com.eklepser.thelevel.graphics.screen.TableLayout;
import com.eklepser.thelevel.graphics.utils.TextLabel;
import com.eklepser.thelevel.logic.decoder.command.Instruction;
import com.eklepser.thelevel.logic.world.level.LevelConfiguration;
import com.eklepser.thelevel.util.Resources;

import java.util.ArrayList;
import java.util.List;

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

        tagField = new TextField(config.tag,
            Resources.getSkin().get("code-field", TextField.TextFieldStyle.class));
        tagField.setMaxLength(10);
        tagField.getStyle().cursor = new TextureRegionDrawable(
            new Texture(Gdx.files.internal("ui/component/code-field-cursor.png")));

        titleField = new TextField(config.title,
            Resources.getSkin().get("code-field", TextField.TextFieldStyle.class));
        titleField.setMaxLength(20);
        titleField.getStyle().cursor = new TextureRegionDrawable(
            new Texture(Gdx.files.internal("ui/component/code-field-cursor.png")));

        codeLinesNum = new TextField(String.valueOf(config.codeLinesNum),
            Resources.getSkin().get("code-field", TextField.TextFieldStyle.class));
        codeLinesNum.setMaxLength(20);
        codeLinesNum.getStyle().cursor = new TextureRegionDrawable(
            new Texture(Gdx.files.internal("ui/component/code-field-cursor.png")));

        allowedCommands = new TextField(String.valueOf(config.allowedInstructions),
            Resources.getSkin().get("code-field", TextField.TextFieldStyle.class));
        allowedCommands.setMaxLength(40);
        allowedCommands.getStyle().cursor = new TextureRegionDrawable(
            new Texture(Gdx.files.internal("ui/component/code-field-cursor.png")));

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
        newConfig.id = 1;
        newConfig.tag = tagField.getText();
        newConfig.tileMap = config.tileMap;
        newConfig.cameraZoom = 1.0f;
        newConfig.title = titleField.getText();
        newConfig.codeLinesNum = 10;
        newConfig.allowedInstructions = new ArrayList<>(List.of(Instruction.MOVE));

        Json json = new Json();
        json.setOutputType(JsonWriter.OutputType.json);
        String jsonContent = json.toJson(newConfig);

        String path = String.format("builder/level_%s_%s.json", newConfig.tag, newConfig.id);
        FileHandle file = Gdx.files.local(path);
        file.writeString(jsonContent, false);

        Gdx.app.log("Save", "Level saved to: " + file.path());

        String status = String.format("Level %s saved", newConfig.tag);
        root.getStatusBar().setActionText(status);
    }

    public boolean hasTextFieldFocus() {
        return tagField.hasKeyboardFocus() ||
            titleField.hasKeyboardFocus() ||
            codeLinesNum.hasKeyboardFocus() ||
            allowedCommands.hasKeyboardFocus();
    }
}
