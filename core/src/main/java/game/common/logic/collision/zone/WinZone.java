package game.common.logic.collision.zone;

import game.common.logic.entity.Entity;
import game.scene.level.logic.Level;

public final class WinZone extends Zone {
    private final Level level;
    private boolean activated = false;

    public WinZone(int x, int y, Level level) {
        super(x, y);
        this.level = level;
    }

    @Override
    public void onCollision(Entity entity) {
        if (!activated) {
            level.win();
        }
        activated = true;
    }

    public void setActivated(boolean activated) { this.activated = activated; }
}
