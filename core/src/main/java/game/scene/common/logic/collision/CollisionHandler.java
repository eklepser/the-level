package game.scene.common.logic.collision;

import game.scene.common.logic.collision.zone.Zone;
import game.scene.common.logic.entity.Entity;

public final class CollisionHandler {
    private final CollisionContext collisionContext;
    private final boolean hittingWalls = true;

    public CollisionHandler(CollisionContext collisionContext) {
        this.collisionContext = collisionContext;
    }

    public void update() {
        System.out.println("collisison handler update");
        wallsUpdate();
        zonesUpdate();
    }

    public void wallsUpdate() {
        for (Entity entity : collisionContext.entities()) {
            int targetX = (int) entity.getTargetWorldPos().x;
            int targetY = (int) entity.getTargetWorldPos().y;

            if (targetX < 0 || targetX >= collisionContext.collisionMap()[0].length) return;
            if (targetY < 0 || targetY >= collisionContext.collisionMap().length) return;

            if (collisionContext.collisionMap()[targetY][targetX] == 1) {
                entity.resetTargetWorldPos();
                if (hittingWalls) entity.hit();
            }
        }
    }

    public void zonesUpdate() {
        for (Entity entity : collisionContext.entities()) {
            int x = (int) entity.getWorldPos().x;
            int y = (int) entity.getWorldPos().y;

            for (Zone zone : collisionContext.zones()) {
                if (x == zone.getX() && y == zone.getY()) {
                    zone.onCollision(entity);
                }
            }
        }
    }
}
