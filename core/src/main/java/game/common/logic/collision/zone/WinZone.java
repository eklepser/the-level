package game.common.logic.collision.zone;

import game.scene.level.logic.Level;
import game.scene.level.window.WinWindow;
import game.common.logic.entity.Entity;

public class WinZone extends Zone {
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
