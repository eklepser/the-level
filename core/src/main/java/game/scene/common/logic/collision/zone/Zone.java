package game.scene.common.logic.collision.zone;

import game.scene.common.logic.collision.Collidable;

public abstract class Zone implements Collidable {
    protected final int x;
    protected final int y;

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
