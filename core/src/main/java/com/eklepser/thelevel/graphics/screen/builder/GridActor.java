package com.eklepser.thelevel.graphics.screen.builder;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.eklepser.thelevel.graphics.render.TileMap;
import com.eklepser.thelevel.graphics.render.TileSet;
import com.eklepser.thelevel.graphics.render.Zone;
import com.eklepser.thelevel.util.Resources;

import static com.eklepser.thelevel.graphics.screen.TableLayout.TILE_SIZE;

public class GridActor extends Actor {
    private final TileMap map;
    private final TileSet blockTileSet;
    private final TileSet zoneTileSet;

    public GridActor(BuilderScreen screen) {
        map = screen.getMap();

        blockTileSet = Resources.getGroundTileSet();
        zoneTileSet = Resources.getZoneTileSet();

        setSize(map.width * TILE_SIZE,
            map.height * TILE_SIZE);

        addListener(new GridListener(screen));

        setDebug(true);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        map.draw(batch);
    }
}


// Input listener class for GridActor:
class GridListener extends InputListener {
    private final BuilderScreen screen;
    private final TileMap map;
    private final Builder builder;

    public GridListener(BuilderScreen screen) {
        this.screen = screen;
        map = screen.getMap();
        builder = screen.getBuilder();
    }

    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        drawTile((int) x, (int) y);
        return true;
    }

    @Override
    public void touchDragged(InputEvent event, float x, float y, int pointer) {
        try {
            drawTile((int) x, (int) y);
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("out of map");
        }
    }

    private void drawTile(int x, int y) {
        int mapX = x / TILE_SIZE;
        int mapY = y / TILE_SIZE;

        if (builder.getMode().equals(EditMode.INSERT_GROUND)) {
            map.tiles[mapY][mapX] = builder.getSelectedTileId();

            String message = String.format("Ground placed (%s, %s)", mapX, mapY);
            screen.getLayout().getStatusBar().setActionText(message);
        }
        else if (builder.getMode().equals(EditMode.INSERT_WALL)) {
            map.tiles[mapY][mapX] = builder.getSelectedTileId();

            String message = String.format("Wall placed (%s, %s)", mapX, mapY);
            screen.getLayout().getStatusBar().setActionText(message);
        }
        else if (builder.getMode().equals(EditMode.INSERT_ZONE)) {
            Zone zone = new Zone(mapX, mapY, "start");
            map.zones.add(zone);

            String message = String.format("Zone placed (%s, %s)", mapX, mapY);
            screen.getLayout().getStatusBar().setActionText(message);
        }
    }
}
