package com.eklepser.thelevel.graphics.screen.builder;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.eklepser.thelevel.graphics.render.TileMap;
import com.eklepser.thelevel.graphics.render.TileSet;
import com.eklepser.thelevel.util.Resources;

import static com.eklepser.thelevel.graphics.screen.TableLayout.TILE_SIZE;

public class GridActor extends Actor {
    private final TileMap map;
    private final Builder builder;
    private final TileSet blockTileSet;
    private final TileSet zoneTileSet;

    public GridActor(BuilderScreen screen) {
        map = screen.getMap();
        builder = screen.getBuilder();
        blockTileSet = Resources.getBlockTileSet();
        zoneTileSet = Resources.getZoneTileSet();

        setSize(map.width * TILE_SIZE,
            map.height * TILE_SIZE);

        addListener(new GridProcessor(screen));
        setDebug(true);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        map.draw(batch);
    }
}
