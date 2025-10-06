package game.common.logic.zone;

import game.common.tilemap.ZoneTile;
import game.scene.level.rendering.LevelLayout;
import game.scene.level.window.WinWindow;
import game.scene.level.logic.editor.execution.Executor;
import game.common.logic.collision.Collidable;

public abstract class Zone implements Collidable {
    private final int x;
    private final int y;

    public Zone(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Zone from(ZoneTile tile) {
        int x = tile.x;
        int y = tile.y;
        String[] properties = tile.properties;

        Zone zone = switch (tile.type) {
            case "win" -> {
                WinWindow winWindow = new WinWindow(null);
                yield new WinZone(x, y, winWindow);
            }
            case "colored" -> new ColoredZone(x, y, properties[0]);
            //case "level" -> new LevelZone(x, y, winWindow, executor);
            default -> null;
        };
        return zone;
    }

    // Getters:
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
