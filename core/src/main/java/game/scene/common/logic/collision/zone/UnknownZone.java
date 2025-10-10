package game.scene.common.logic.collision.zone;

import game.scene.common.logic.entity.Entity;

public final class UnknownZone extends Zone {
    public UnknownZone(int x, int y) {
        super(x, y);
    }

    @Override
    public void onCollision(Entity entity) {
        System.out.printf("On unknown zone (%s, %s)%n", x, y);
    }
}
