package game.common.logic.collision.zone;

import game.common.rendering.tilemap.ZoneTile;
import game.scene.level.logic.Level;
import game.scene.world.logic.World;

public final class WorldZoneFactory {
    private WorldZoneFactory() { }

    public static LevelZone worldZone(ZoneTile tile, World world) {
        int x = tile.x;
        int y = tile.y;
        String[] properties = tile.properties;

        return switch (tile.type) {
            default -> null;
        };
    }
}
