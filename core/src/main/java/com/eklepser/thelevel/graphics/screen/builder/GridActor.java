package com.eklepser.thelevel.graphics.screen.builder;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.eklepser.thelevel.graphics.render.TileDefinition;
import com.eklepser.thelevel.graphics.render.TileMap;
import com.eklepser.thelevel.graphics.render.ZoneTile;

import static com.eklepser.thelevel.graphics.screen.TableLayout.TILE_SIZE;

public class GridActor extends Actor {
    private final TileMap map;

    public GridActor(BuilderScreen screen) {
        map = screen.getMap();

        setSize(map.width * TILE_SIZE,
            map.height * TILE_SIZE);

        addListener(new GridListener(screen));

        setDebug(true);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        map.draw(batch, 0);
    }

    public void update() {
        setSize(map.width * TILE_SIZE,
            map.height * TILE_SIZE);

        setDebug(true);
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

        TileDefinition def = builder.getSelectedTileDef();
        switch (def.type) {
            case "ground":
                map.tiles[mapY][mapX] = builder.getSelectedTileDef().id;
                map.collision[mapY][mapX] = 0;
                break;
            case "wall":
                map.tiles[mapY][mapX] = builder.getSelectedTileDef().id;
                map.collision[mapY][mapX] = 1;
                break;
            case "zone":
                ZoneTile newZone = new ZoneTile(def.id, mapX, mapY, def.zoneType, def.zoneProperties);
                map.removeZoneByPos(mapX, mapY);
                map.zones.add(newZone);
                break;
            case "remove_tile":
                map.tiles[mapY][mapX] = 0;
                map.collision[mapY][mapX] = 0;
                break;
            case "remove_zone":
                map.removeZoneByPos(mapX, mapY);
                break;
        }

        String message = String.format("%s (%s) placed on (%s, %s)", def.type, def.id, mapX, mapY);
        screen.getLayout().getStatusBar().setActionText(message);
    }
}
