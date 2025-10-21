package game.scene.common.logic.collision.zone;

import game.scene.common.rendering.tilemap.ZoneTile;
import game.scene.level.logic.Level;

public final class LevelZoneFactory {
    private LevelZoneFactory() { }

    public static Zone levelZone(ZoneTile tile, Level level) {
        int x = tile.x;
        int y = tile.y;
        String[] properties = tile.properties;

        return switch (tile.type) {
            case "win" -> new WinZone(x, y, level);
            case "death" -> new DeathZone(x, y, level);
            case "colored" -> new ColoredZone(x, y, properties[0]);
            default -> new UnknownZone(x, y);
        };
    }
}
