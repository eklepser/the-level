package com.eklepser.thelevel.graphics.screen.builder;

import com.eklepser.thelevel.graphics.screen.TableLayout;
import com.eklepser.thelevel.graphics.util.TextLabel;
import com.eklepser.thelevel.util.Resources;

public class BuilderLayout extends TableLayout {
    private final BuilderScreen screen;

    private final ConfigTable configTable;
    private final TilePalette groundPalette;
    private final TilePalette wallPalette;
    private final TilePalette zonePalette;
    private final TilePalette utilPalette;
    private final StatusBar statusBar;

    public BuilderLayout(BuilderScreen screen) {
        this.screen = screen;

        configTable = new ConfigTable(this, screen);
        groundPalette = new TilePalette(screen, Resources.getTileset(), 10, 19);
        wallPalette = new TilePalette(screen, Resources.getTileset(), 20, 29);
        zonePalette = new TilePalette(screen, Resources.getTileset(), 30, 59);
        utilPalette = new TilePalette(screen, Resources.getTileset(), 90, 99);
        statusBar = new StatusBar(screen);

        setup();
    }

    @Override
    public void setup() {
        setFillParent(true);

        add(configTable).left().padBottom(20).row();

        add(new TextLabel("Ground:")).left().row();
        add(groundPalette).left();
        add().expandX().row();

        add(new TextLabel("Walls:")).left().row();
        add(wallPalette).left();
        add().expandX().row();

        add(new TextLabel("Zones:")).left().row();
        add(zonePalette).left();
        add().expandX().row();

        add(new TextLabel("Action:")).left().row();
        add(utilPalette).left();
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
