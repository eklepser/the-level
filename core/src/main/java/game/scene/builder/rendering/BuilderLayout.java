package game.scene.builder.rendering;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import game.data.IO.Assets;
import game.scene.builder.logic.Builder;
import game.scene.builder.logic.event.BuilderEvent;
import game.scene.builder.logic.event.TilePlacedEvent;
import game.scene.builder.logic.event.TileSelectedEvent;
import game.scene.builder.rendering.component.*;
import game.scene.common.logic.event.EventListener;
import game.scene.common.rendering.TableLayout;
import game.scene.common.rendering.component.TextLabel;
import game.scene.common.rendering.tilemap.TileDefinition;

public final class BuilderLayout extends TableLayout implements EventListener<BuilderEvent> {
    private final Statusbar statusbar;
    private final ConfigTable configTable;
    private final ResizingLayout resizingLayout;

    private final TilePalette groundPalette;
    private final TilePalette wallPalette;
    private final TilePalette zonePalette;
    private final TilePalette utilPalette;

    private final CustomZoneTable customZoneTable;

    public BuilderLayout(BuilderScreen screen, Builder builder) {
        builder.subscribe(this);

        statusbar = new Statusbar(screen);
        statusbar.setSelectionStatus(builder.getSelectedTileDef());

        configTable = new ConfigTable(builder.getLevelData(), statusbar);
        resizingLayout = new ResizingLayout(builder);

        groundPalette = new TilePalette(builder, Assets.getTileset(), 10, 19);
        wallPalette = new TilePalette(builder, Assets.getTileset(), 20, 29);
        zonePalette = new TilePalette(builder, Assets.getTileset(), 30, 59);
        utilPalette = new TilePalette(builder, Assets.getTileset(), 90, 99);

        customZoneTable = new CustomZoneTable(builder);

        setup();
    }

    @Override
    public void setup() {
        setFillParent(true);

        // setup palette table
        Table paletteTable = new Table();

        paletteTable.add(new TextLabel("Ground:")).left().row();
        paletteTable.add(groundPalette).left();
        paletteTable.add().expandX().row();

        paletteTable.add(new TextLabel("Walls:")).left().row();
        paletteTable.add(wallPalette).left();
        paletteTable.add().expandX().row();

        paletteTable.add(new TextLabel("Zones:")).left().row();
        paletteTable.add(zonePalette).left();
        paletteTable.add().expandX().row();

        paletteTable.add(new TextLabel("Action:")).left().row();
        paletteTable.add(utilPalette).left();
        paletteTable.add().expandX().row();

        customZoneTable.setVisible(false);
        paletteTable.add(customZoneTable).left();
        paletteTable.add().expandX().row();

        // setup level info table
        Table infoTable = new Table();

        infoTable.add(new TextLabel("Level info:")).expandX().right().row();
        infoTable.add(configTable).left().padBottom(20).expandX().right().row();

        infoTable.add(resizingLayout).right().padBottom(20).expandX().right().row();

        // setup layout
        add(paletteTable).fillX().expandX().left().top().padLeft(10);
        add(infoTable).fillX().expandX().right().top().padRight(10);

        add().expandY().row();

        add(statusbar).colspan(2).padBottom(10).fillX();
    }

    public void update() {
        statusbar.update();
    }

    @Override
    public void onEvent(BuilderEvent event) {
        if (event instanceof TileSelectedEvent tileSelected) {
            TileDefinition def = tileSelected.tileDefinition;
            if (def.type.equals("custom_zone")) {
                customZoneTable.setVisible(true);
            }
            statusbar.setSelectionStatus(def);
        }
        if (event instanceof TilePlacedEvent tilePlaced) {
            String status = String.format("%s on (%s, %s)", tilePlaced.tileDefinition.name, tilePlaced.x, tilePlaced.y);
            statusbar.setActionStatus(status);
        }
    }
}
