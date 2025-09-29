package com.eklepser.thelevel.graphics.screen.builder;

import com.eklepser.thelevel.graphics.screen.Layout;

public class BuilderLayout extends Layout {
    private final BuilderScreen screen;
    private final TilePalette tilePalette;

    public BuilderLayout(BuilderScreen screen) {
        this.screen = screen;

        tilePalette = new TilePalette(screen);

        setup();
    }

    @Override
    protected void setup() {
        setFillParent(true);

        add(tilePalette);
        add().expandX();
    }
}
