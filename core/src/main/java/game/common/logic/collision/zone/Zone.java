package game.common.logic.collision.zone;

import game.common.logic.collision.Collidable;
import game.common.rendering.tilemap.ZoneTile;
import game.scene.level.logic.Level;

public abstract class Zone implements Collidable {
    private final int x;
    private final int y;

    public Zone(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Zone from(ZoneTile tile, Level level) {
        int x = tile.x;
        int y = tile.y;
        String[] properties = tile.properties;

        //case "level" -> new LevelZone(x, y, winWindow, executor);
        return switch (tile.type) {
            case "win" -> new WinZone(x, y, level);
            case "colored" -> new ColoredZone(x, y, properties[0]);
            //case "level" -> new LevelZone(x, y, winWindow, executor);
            default -> null;
        };
    }

    // Getters:
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
