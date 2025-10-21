package game.scene.common.logic.collision.zone;

import game.scene.common.logic.entity.Entity;
import game.scene.level.logic.Level;

public final class DeathZone extends LevelZone {
    private final Level level;

    public DeathZone(int x, int y, Level level) {
        super(x, y);
        this.level = level;
    }

    @Override
    public void onCollision(Entity entity) {
        level.kill(entity);
    }
}
