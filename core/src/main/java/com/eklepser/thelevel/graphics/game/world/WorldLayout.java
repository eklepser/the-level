package com.eklepser.thelevel.graphics.game.world;

import com.eklepser.thelevel.graphics.Layout;
import com.eklepser.thelevel.graphics.utils.ColoredString;
import com.eklepser.thelevel.logic.world.world.World;

public class WorldLayout extends Layout {
    private final WorldScreen screen;
    private final World world;
    private final SelectingLayout selectingLayout;
    private final ColoredString levelIdString;

    public WorldLayout(WorldScreen screen, World world) {
        this.screen = screen;
        this.world = world;
        selectingLayout = new SelectingLayout(world);
        levelIdString = new ColoredString();

        setup();
    }

    @Override
    protected void setup() {
        setFillParent(true);

        add(selectingLayout).left().padLeft(20).expandX();
        row();
        add().expandY();
        row();
        add(levelIdString).left().pad(0, 20, 15, 0).expandX();
    }

    //Getters:
    public SelectingLayout getSelectingLayout() {
        return selectingLayout;
    }

    public ColoredString getLevelIdString() {
        return levelIdString;
    }
}
