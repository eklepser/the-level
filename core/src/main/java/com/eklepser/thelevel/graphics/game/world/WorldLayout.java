package com.eklepser.thelevel.graphics.game.world;

import com.eklepser.thelevel.graphics.Layout;
import com.eklepser.thelevel.graphics.utils.ColoredString;
import com.eklepser.thelevel.graphics.utils.TextLabel;
import com.eklepser.thelevel.logic.world.world.World;

public class WorldLayout extends Layout {
    private final WorldScreen screen;
    private final World world;
    private final SelectingLayout selectingLayout;

    public WorldLayout(WorldScreen screen, World world) {
        this.screen = screen;
        this.world = world;
        selectingLayout = new SelectingLayout(world);

        setup();
    }

    @Override
    protected void setup() {
        setFillParent(true);

        add(selectingLayout).left().padLeft(20).expandX();
        row();
        add().expandY();
    }

    //Getters:
    public SelectingLayout getSelectingLayout() {
        return selectingLayout;
    }
}
