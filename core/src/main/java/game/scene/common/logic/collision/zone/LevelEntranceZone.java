package game.scene.common.logic.collision.zone;

import com.badlogic.gdx.graphics.Color;
import game.data.user.LevelStatus;
import game.scene.common.logic.entity.Entity;
import game.scene.common.rendering.tilemap.ZoneTile;
import game.scene.world.logic.World;

public final class LevelEntranceZone extends WorldZone {
    private final World world;
    private final String levelTag;

    public LevelEntranceZone(ZoneTile tile, World world, String levelTag) {
        super(tile.x, tile.y);
        this.world = world;
        this.levelTag = levelTag;
        setColor(tile);
    }

    @Override
    public void onCollision(Entity entity) {
        world.setSelectedLevel(levelTag);
    }

    private void setColor(ZoneTile tile) {
        LevelStatus status = world.getLevelStatusMap().get(levelTag);
        if (status == null) return;
        switch (status) {
            case UNLOCKED -> tile.color = new Color(0x9999ffff);
            case COMPLETED -> tile.color = new Color(0xccccffff);
            default -> tile.color = new Color(0x00000000);
        }
    }
}
