package com.eklepser.thelevel.graphics.screen.builder;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.eklepser.thelevel.graphics.render.TileMap;
import com.eklepser.thelevel.graphics.render.TileSet;
import com.eklepser.thelevel.util.Resources;

import static com.eklepser.thelevel.graphics.screen.Layout.TILE_SIZE;

public class GridActor extends Actor {
    private final TileMap map;
    private final TileSet tileSet;

    public GridActor(BuilderScreen screen) {
        map = screen.getMap();
        tileSet = Resources.getTileSet();

        setSize(map.width * TILE_SIZE,
            map.height * TILE_SIZE);

        addListener(new GridProcessor(screen));
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        for (int y = 0; y < map.height; y++) {
            for (int x = 0; x < map.width; x++) {
                int tileId = map.tiles[y][x];
                if (tileId < 0) continue;

                TextureRegion region = tileSet.getTile(tileId);
                if (region == null) continue;

                float screenX = x * TILE_SIZE;
                float screenY = y * TILE_SIZE;

                batch.draw(region, screenX, screenY);
            }
        }
    }
}
