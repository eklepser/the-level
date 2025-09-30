package com.eklepser.thelevel.graphics.screen.builder;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
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
    private final BuilderScreen screen;
    private final Builder builder;

    private final TextField nameField;
    private final TextField codeLinesNum;
    private final TextField allowedCommands;

    private final TextButton saveButton;

    public ConfigTable(BuilderScreen screen) {
        this.screen = screen;
        builder = screen.getBuilder();

        nameField = new TextField("", Resources.getSkin().get(
            "code-field", TextField.TextFieldStyle.class));
        nameField.setMaxLength(20);

        codeLinesNum = new TextField("", Resources.getSkin().get(
            "code-field", TextField.TextFieldStyle.class));
        codeLinesNum.setMaxLength(20);

        allowedCommands = new TextField("", Resources.getSkin().get(
            "code-field", TextField.TextFieldStyle.class));
        allowedCommands.setMaxLength(40);

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

        add(new TextLabel("Level name:")).padRight(5).left();
        add(nameField).padTop(5).row();

        add(new TextLabel("Codelines amount:")).padRight(5).left();
        add(codeLinesNum).padTop(5).row();

        add(new TextLabel("Allowed commands:")).padRight(5).left();
        add(allowedCommands).padTop(5);
    }

    private void saveLevel() {
        LevelConfiguration config = new LevelConfiguration();
        config.id = 0;
        config.name = nameField.getText();
        config.tileMap = screen.getMap();
        config.cameraZoom = 1.0f;
        config.codeLinesNum = 10;
        config.allowedInstructions = new ArrayList<>(List.of(Instruction.MOVE));


        Json json = new Json();
        json.setOutputType(JsonWriter.OutputType.json);
        String jsonContent = json.toJson(config);

        String path = String.format("assets/builder/level_%s.json", config.id);
        FileHandle file = Gdx.files.local(path);
        file.writeString(jsonContent, false);

        Gdx.app.log("Save", "Level saved to: " + file.path());
    }

}
