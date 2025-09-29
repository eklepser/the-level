package com.eklepser.thelevel.graphics.screen.builder;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.eklepser.thelevel.graphics.render.TileMap;

import static com.eklepser.thelevel.graphics.screen.Layout.TILE_SIZE;

public class GridProcessor extends InputListener {
    private final TileMap map;
    private final Builder builder;

    public GridProcessor(BuilderScreen screen) {
        map = screen.getMap();
        builder = screen.getBuilder();
    }

    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        int mapX = (int) x / TILE_SIZE;
        int mapY = (int) y / TILE_SIZE;

        System.out.println(x / TILE_SIZE);
        System.out.println(y / TILE_SIZE);

        map.tiles[mapY][mapX] = builder.getSelectedTile();
        return true;
    }

    @Override
    public void touchDragged(InputEvent event, float x, float y, int pointer) {
        System.out.println("DRAGGED");
    }
}
