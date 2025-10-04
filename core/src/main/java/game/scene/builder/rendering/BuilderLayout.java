package game.scene.builder.rendering;

import game.scene.common.rendering.TableLayout;
import game.scene.common.rendering.component.TextLabel;
import game.resources.Assets;
import game.scene.builder.rendering.component.ConfigTable;
import game.scene.builder.rendering.component.ResizeTable;
import game.scene.builder.rendering.component.StatusBar;
import game.scene.builder.rendering.component.TilePalette;

public class BuilderLayout extends TableLayout {
    private final ConfigTable configTable;
    private final ResizeTable resizeTable;
    private final TilePalette groundPalette;
    private final TilePalette wallPalette;
    private final TilePalette zonePalette;
    private final TilePalette utilPalette;
    private final StatusBar statusBar;

    public BuilderLayout(BuilderScreen screen) {
        configTable = new ConfigTable(this, screen);
        resizeTable = new ResizeTable(screen);

        groundPalette = new TilePalette(screen, Assets.getTileset(), 10, 19);
        wallPalette = new TilePalette(screen, Assets.getTileset(), 20, 29);
        zonePalette = new TilePalette(screen, Assets.getTileset(), 30, 59);
        utilPalette = new TilePalette(screen, Assets.getTileset(), 90, 99);

        statusBar = new StatusBar(screen);

        setup();
    }

    @Override
    public void setup() {
        setFillParent(true);

        add(new TextLabel("Level info:")).left().row();
        add(configTable).left().padBottom(20).row();

        add(resizeTable).left().padBottom(20).row();

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
