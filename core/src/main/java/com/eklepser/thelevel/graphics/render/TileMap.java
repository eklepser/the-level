package com.eklepser.thelevel.graphics.render;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.eklepser.thelevel.util.Resources;

import java.util.ArrayList;
import java.util.List;

import static com.eklepser.thelevel.graphics.screen.TableLayout.TILE_SIZE;

public class TileMap {
    public String name;
    public int width;
    public int height;
    public int[][] tiles;
    public int[][] collision;
    public List<ZoneTile> zones = new ArrayList<>();

    public void draw(Batch batch) {
        drawBlocks(batch);
        drawZones(batch);
    }

    private void drawBlocks(Batch batch) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int tileId = tiles[y][x];
                if (tileId < 0) continue;

                TextureRegion region = Resources.getTileset().getTile(tileId);
                if (region == null) continue;

                float screenX = x * TILE_SIZE;
                float screenY = y * TILE_SIZE;

                batch.draw(region, screenX, screenY);
            }
        }
    }

    private void drawZones(Batch batch) {
        if (zones == null) return;
        for (ZoneTile zoneTile : zones) {
            TextureRegion region = Resources.getTileset().getTile(zoneTile.id);
            if (region == null) continue;

            float screenX = zoneTile.x * TILE_SIZE;
            float screenY = zoneTile.y * TILE_SIZE;

            batch.draw(region, screenX, screenY);
        }
    }

    public Vector2 getStartPos() {
        for (ZoneTile zoneTile : zones) {
            if (zoneTile.type.equals("start"))
                return new Vector2(zoneTile.x, zoneTile.y);
        }
        return null;
    }
}
