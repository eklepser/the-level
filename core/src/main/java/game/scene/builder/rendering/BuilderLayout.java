package game.scene.builder.rendering;

import game.common.rendering.TableLayout;
import game.common.rendering.component.TextLabel;
import game.resources.Assets;
import game.scene.builder.rendering.component.ConfigTable;
import game.scene.builder.rendering.component.ResizingLayout;
import game.scene.builder.rendering.component.Statusbar;
import game.scene.builder.rendering.component.TilePalette;

public final class BuilderLayout extends TableLayout {
    private final Statusbar statusbar;
    private final ConfigTable configTable;
    private final ResizingLayout resizingLayout;

    private final TilePalette groundPalette;
    private final TilePalette wallPalette;
    private final TilePalette zonePalette;
    private final TilePalette utilPalette;

    public BuilderLayout(BuilderScreen screen) {
        statusbar = new Statusbar(screen);
        configTable = new ConfigTable(screen.getConfig(), statusbar);
        resizingLayout = new ResizingLayout(screen);

        groundPalette = new TilePalette(screen.getBuilder(), Assets.getTileset(), 10, 19);
        wallPalette = new TilePalette(screen.getBuilder(), Assets.getTileset(), 20, 29);
        zonePalette = new TilePalette(screen.getBuilder(), Assets.getTileset(), 30, 59);
        utilPalette = new TilePalette(screen.getBuilder(), Assets.getTileset(), 90, 99);

        setup();
    }

    @Override
    public void setup() {
        setFillParent(true);

        add(new TextLabel("Level info:")).left().row();
        add(configTable).left().padBottom(20).row();

        add(resizingLayout).left().padBottom(20).row();

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

        add(statusbar).colspan(2).padBottom(10).fillX();
    }

//    public void update() {
//        statusbar.update();
//    }

    //Getters:
    public ConfigTable getConfigTable() { return configTable; }

    public Statusbar getStatusBar() {
        return statusbar;
    }
}
