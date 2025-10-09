package game.common.logic.collision.zone;

import game.common.rendering.tilemap.ZoneTile;
import game.scene.world.logic.World;

public final class WorldZoneFactory {
    private WorldZoneFactory() { }

    public static Zone worldZone(ZoneTile tile, World world) {
        int x = tile.x;
        int y = tile.y;
        String[] properties = tile.properties;

        System.out.println(tile.type);

        return switch (tile.type) {
            case "level_entrance" ->
                new LevelEntranceZone(x, y, world, Integer.parseInt(properties[0]));
            default -> new UnknownZone(x, y);
        };
    }
}
