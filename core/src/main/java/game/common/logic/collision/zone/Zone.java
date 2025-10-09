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

    // Getters:
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
