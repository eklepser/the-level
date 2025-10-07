package game.common.rendering.tilemap;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import game.resources.Assets;

import java.util.ArrayList;
import java.util.List;

import static game.config.Display.TILE_SIZE;

public class TileMap {
    public String name;
    public int width;
    public int height;
    public int[][] tiles;
    public int[][] collision;
    public List<ZoneTile> zones = new ArrayList<>();

    public void draw(Batch batch, int startId) {
        drawBlocks(batch, startId);
        drawZones(batch);
    }

    private void drawBlocks(Batch batch, int startId) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int tileId = tiles[y][x];
                if (tileId < startId) continue;

                TextureRegion region = Assets.getTileset().getTile(tileId);
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
            TextureRegion region = Assets.getTileset().getTile(zoneTile.id);
            if (region == null) continue;

            float screenX = zoneTile.x * TILE_SIZE;
            float screenY = zoneTile.y * TILE_SIZE;

            batch.draw(region, screenX, screenY);
        }
    }

    public void resize(int newWidth, int newHeight) {
        int[][] newTiles = new int[newHeight][newWidth];
        int[][] newCollision = new int[newHeight][newWidth];

        for (int y = 0; y < Math.min(height, newHeight); y++) {
            for (int x = 0; x < Math.min(width, newWidth); x++) {
                newTiles[y][x] = this.tiles[y][x];
                newCollision[y][x] = this.collision[y][x];
            }
        }

        zones.removeIf(zoneTile -> zoneTile.x >= newWidth || zoneTile.y >= newHeight);

        this.width = newWidth;
        this.height = newHeight;
        this.tiles = newTiles;
        this.collision = newCollision;
    }

    public void offset(int offsetX, int offsetY) {
        int[][] newTiles = new int[height][width];
        int[][] newCollision = new int[height][width];

        for (int y = 0; y < height; y++) {
            int newY = y + offsetY;
            if (newY >= height || newY < 0) continue;

            for (int x = 0; x < width; x++) {
                int newX = x + offsetX;
                if (newX >= width || newX < 0) continue;

                newTiles[newY][newX] = this.tiles[y][x];
                newCollision[newY][newX] = this.collision[y][x];
            }
        }

        for (ZoneTile zoneTile : zones) {
            zoneTile.x = zoneTile.x + offsetX;
            zoneTile.y = zoneTile.y + offsetY;
        }

        zones.removeIf(zoneTile -> zoneTile.x >= width || zoneTile.x < 0
            || zoneTile.y >= height || zoneTile.y < 0);

        this.tiles = newTiles;
        this.collision = newCollision;
    }

    // Getters:
    public Vector2 getStartPos() {
        for (ZoneTile zoneTile : zones) {
            if (zoneTile.type.equals("start"))
                return new Vector2(zoneTile.x, zoneTile.y);
        }
        return null;
    }

    public ZoneTile getZoneByPos(int x, int y) {
        for (ZoneTile zoneTile : zones) {
            if (zoneTile.x == x && zoneTile.y == y) return zoneTile;
        }
        return null;
    }

    public void removeZoneByPos(int x, int y) {
        ZoneTile zoneTile = getZoneByPos(x, y);
        if (zoneTile != null) zones.remove(zoneTile);
    }
}
