package com.eklepser.thelevel.graphics.level.editor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.eklepser.thelevel.graphics.common.TextLabel;
import com.eklepser.thelevel.logic.decoder.command.Instruction;
import com.eklepser.thelevel.logic.world.level.LevelConfiguration;

import java.util.List;

public class CommandsPanel extends Table {
    private final List<Instruction> allowedInstructions;
    private final int itemsPerRow = 4;


    public CommandsPanel(LevelConfiguration conf) {
        allowedInstructions = conf.allowedInstructions;
        loadIcons(allowedInstructions, false);
    }

    public void update(boolean showLabels) {
        clearChildren();
        loadIcons(allowedInstructions, showLabels);
    }

    private void loadIcons(List<Instruction> allowedInstructions, boolean loadLabels) {
        int count = 0;

        for (Instruction instruction : allowedInstructions) {
            String iconPath = instruction.iconPath + ".png";
            Image iconImage = new Image(new Texture(Gdx.files.internal(iconPath)));
            add(iconImage).padRight(5);

            if (loadLabels) {
                TextLabel label = new TextLabel("(" + instruction.name + ")");
                add(label).padRight(10);
            }

            count++;
            if (count % itemsPerRow == 0) row();
        }
    }
}
