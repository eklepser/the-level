package com.eklepser.thelevel.graphics.screen.builder;

import com.eklepser.thelevel.graphics.screen.TableLayout;
import com.eklepser.thelevel.graphics.util.TextLabel;
import com.eklepser.thelevel.util.Resources;

public class BuilderLayout extends TableLayout {
    private final BuilderScreen screen;

    private final ConfigTable configTable;
    private final TilePalette groundPalette;
//    private final TilePalette wallPalette;
//    private final TilePalette zonePalette;
    private final StatusBar statusBar;

    private boolean isTextFieldFocus = false;

    public BuilderLayout(BuilderScreen screen) {
        this.screen = screen;

        configTable = new ConfigTable(this, screen);
        groundPalette = new TilePalette(screen, Resources.getTileset());
        //wallPalette = new TilePalette(screen, Resources.getWallTileSet(), EditMode.INSERT_WALL);
        //zonePalette = new TilePalette(screen, Resources.getZoneTileSet(), EditMode.INSERT_ZONE);
        statusBar = new StatusBar(screen);

        setup();
    }

    @Override
    public void setup() {
        setFillParent(true);

        add(configTable).left().padBottom(20).row();

        add(new TextLabel("Tiles:")).left().row();
        add(groundPalette).left();
        add().expandX().row();

        add(new TextLabel("Walls:")).left().row();
        //add(wallPalette).left();
        add().expandX().row();

        add(new TextLabel("Zones:")).left().row();
        //add(zonePalette).left();
        add().expandX().row();

        add().expandY().row();

        add(statusBar).colspan(2).padBottom(10).fillX();
    }

    public void update() {
        statusBar.update();
    }

    //Getters:
    public ConfigTable getConfigTable() { return configTable; }

    public StatusBar getStatusBar() {
        return statusBar;
    }
}
