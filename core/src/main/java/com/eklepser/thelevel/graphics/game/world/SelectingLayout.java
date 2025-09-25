package com.eklepser.thelevel.graphics.game.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.eklepser.thelevel.graphics.Layout;
import com.eklepser.thelevel.graphics.utils.ColoredString;
import com.eklepser.thelevel.graphics.utils.TextLabel;
import com.eklepser.thelevel.logic.decoder.command.Instruction;
import com.eklepser.thelevel.logic.world.level.LevelConfiguration;
import com.eklepser.thelevel.logic.world.world.World;

public class SelectingLayout extends Layout {
    private final World world;
    private final ColoredString levelString;
    private final ColoredString limitString;
    private final ColoredString commandsString;
    private final Table commandsTable;
    private final int itemsPerRow = 2;

    public SelectingLayout(World world) {
        this.world = world;
        levelString = new ColoredString();
        limitString = new ColoredString();
        commandsString = new ColoredString();
        commandsTable = new Table();

        setup();
    }

    @Override
    protected void setup() {
        add(levelString).left();
        row();
        add(limitString).left();
        row();
        add(commandsString).left();
        row();
        add(commandsTable).left().padTop(4);
    }

    //Class logic:
    public void update() {
        LevelConfiguration config = world.getSelectedLevelConfig();

        levelString.setText("/_3 " + config.name);
        limitString.setText("/gray_1.5 Lines limit: /white_2 " + config.codeLinesNum);
        commandsString.setText("/gray_1.5 Allowed commands:");

        commandsTable.clear();
        int count = 0;
        for (Instruction instruction : config.allowedInstructions) {
            String iconPath = instruction.iconPath + ".png";
            Image iconImage = new Image(new Texture(Gdx.files.internal(iconPath)));
            commandsTable.add(iconImage).padRight(5);
            TextLabel label = new TextLabel("(" + instruction.name + ")");
            commandsTable.add(label).padRight(10);
            if (++count % itemsPerRow == 0) commandsTable.row().padTop(4);
        }
    }
}
