package com.eklepser.thelevel.graphics.game.editor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.eklepser.thelevel.logic.decoder.command.Instruction;
import com.eklepser.thelevel.logic.world.level.LevelConfiguration;

import java.util.List;

public class CommandsPanel extends Table {
    public CommandsPanel(LevelConfiguration conf) {
        loadIcons(conf.getAllowedInstructions());
    }

    private void loadIcons(List<Instruction> allowedInstructions) {
        for (Instruction instruction : allowedInstructions) {
            String iconPath = instruction.iconPath + ".png";
            Image winImage1 = new Image(new Texture(Gdx.files.internal(iconPath)));
            add(winImage1);
        }
    }
}
