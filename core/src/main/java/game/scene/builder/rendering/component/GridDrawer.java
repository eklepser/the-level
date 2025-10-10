package game.scene.builder.rendering.component;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import game.config.Display;
import game.scene.builder.logic.Builder;
import game.scene.builder.logic.event.TilePlacedEvent;
import game.scene.common.rendering.tilemap.TileDefinition;
import game.scene.common.rendering.tilemap.TileMap;
import game.scene.common.rendering.tilemap.ZoneTile;

public final class GridDrawer extends Actor {
    private final TileMap map;

    public GridDrawer(Builder builder) {
        this.map = builder.getMap();
        setDebug(true);

        setSize(map.width * Display.TILE_SIZE,
            map.height * Display.TILE_SIZE);

        addListener(new GridListener(builder));
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        map.draw(batch, 0);
    }

    public void resize() {
        setSize(map.width * Display.TILE_SIZE,
            map.height * Display.TILE_SIZE);
    }
}

// Input listener class for GridActor:
final class GridListener extends InputListener {
    private final TileMap map;
    private final Builder builder;

    public GridListener(Builder builder) {
        this.builder = builder;
        map = builder.getMap();
    }

    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        int mapX = (int) x / Display.TILE_SIZE;
        int mapY = (int) y / Display.TILE_SIZE;

        if (mapX < 0 || mapX >= map.width || mapY < 0 || mapY >= map.height) {
            return false;
        }
        drawTile(mapX, mapY);
        return true;
    }

    @Override
    public void touchDragged(InputEvent event, float x, float y, int pointer) {
        int mapX = (int) x / Display.TILE_SIZE;
        int mapY = (int) y / Display.TILE_SIZE;

        if (mapX < 0 || mapX >= map.width || mapY < 0 || mapY >= map.height) {
            return;
        }
        drawTile(mapX, mapY);
    }

    private void drawTile(int mapX, int mapY) {
        TileDefinition tileDefinition = builder.getSelectedTileDef();

        builder.fire(new TilePlacedEvent(mapX, mapY, tileDefinition));

        switch (tileDefinition.type) {
            case "ground":
                map.tiles[mapY][mapX] = builder.getSelectedTileDef().id;
                map.collision[mapY][mapX] = 0;
                break;
            case "wall":
                map.tiles[mapY][mapX] = builder.getSelectedTileDef().id;
                map.collision[mapY][mapX] = 1;
                break;
            case "zone", "custom_zone":
                ZoneTile newZone = new ZoneTile(tileDefinition.id, mapX, mapY, tileDefinition.zoneType, tileDefinition.zoneProperties);
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
    }
}
