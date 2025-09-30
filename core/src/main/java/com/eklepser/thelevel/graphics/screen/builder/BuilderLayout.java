package com.eklepser.thelevel.graphics.screen.builder;

import com.eklepser.thelevel.graphics.screen.TableLayout;
import com.eklepser.thelevel.graphics.utils.TextLabel;
import com.eklepser.thelevel.util.Resources;

public class BuilderLayout extends TableLayout {
    private final BuilderScreen screen;

    private final ConfigTable configTable;
    private final TilePalette blockPalette;
    private final TilePalette zonePalette;
    private final StatusBar statusBar;

    public BuilderLayout(BuilderScreen screen) {
        this.screen = screen;

        configTable = new ConfigTable(screen);
        blockPalette = new TilePalette(screen, Resources.getBlockTileSet(), EditMode.INSERT_BLOCK);
        zonePalette = new TilePalette(screen, Resources.getZoneTileSet(), EditMode.INSERT_ZONE);
        statusBar = new StatusBar(screen);

        setup();
    }

    @Override
    public void setup() {
        setFillParent(true);

        add(configTable).left().row();

        add(new TextLabel("Tiles:")).left().row();
        add(blockPalette).left();
        add().expandX().row();

        add(new TextLabel("Zones:")).left().row();
        add(zonePalette).left();
        add().expandX().row();

        add().expandY().row();

        add(statusBar).colspan(2).padBottom(10).fillX();
    }

    public void update() {
        statusBar.update();
    }
}
