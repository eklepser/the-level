package game.scene.common.logic.collision.zone;

import game.scene.common.rendering.tilemap.ZoneTile;
import game.scene.world.logic.World;

public final class WorldZoneFactory {
    private WorldZoneFactory() { }

    public static Zone worldZone(ZoneTile tile, World world) {
        return switch (tile.type) {
            case "entrance" -> levelEntranceZone(tile, world);

            default -> new UnknownZone(tile.x, tile.y);
        };
    }

    private static Zone levelEntranceZone(ZoneTile tile, World world) {
        return new LevelEntranceZone(tile, world, tile.properties[0]);
    }
}
