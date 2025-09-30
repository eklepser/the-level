package com.eklepser.thelevel.graphics.screen.builder;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.eklepser.thelevel.graphics.render.TileMap;
import com.eklepser.thelevel.graphics.render.Zone;

import static com.eklepser.thelevel.graphics.screen.TableLayout.TILE_SIZE;

public class GridProcessor extends InputListener {
    private final TileMap map;
    private final Builder builder;

    public GridProcessor(BuilderScreen screen) {
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

        if (builder.getMode().equals(EditMode.INSERT_BLOCK)) {
            map.tiles[mapY][mapX] = builder.getSelectedTileId();
        }
        else if (builder.getMode().equals(EditMode.INSERT_ZONE)) {
            Zone zone = new Zone(mapX, mapY, "start");
            map.zones.add(zone);
        }
    }
}
