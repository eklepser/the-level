package game.scene.level.logic.condition;

import game.scene.common.logic.collision.CollisionContext;
import game.scene.common.logic.collision.zone.ColoredZone;
import game.scene.common.logic.collision.zone.Zone;
import game.scene.common.logic.entity.Entity;

public final class FacingCondition extends Condition {
    private final String zoneName;

    public FacingCondition(String zoneName) {
        this.zoneName = zoneName;
    }

    @Override
    public boolean check(Entity target, CollisionContext collisionContext) {
        int targetX = (int) (target.getWorldPos().x + target.getFacingDirection().vector.x);
        int targetY = (int) (target.getWorldPos().y + target.getFacingDirection().vector.y);

        if (zoneName.equals("wall")) {
            if (targetX < 0 || targetX >= collisionContext.collisionMap()[0].length) return false;
            if (targetY < 0 || targetY >= collisionContext.collisionMap().length) return false;
            return collisionContext.collisionMap()[targetY][targetX] == 1;
        }

        for (Zone zone : collisionContext.zones()) {
            System.out.println(zone);

            if (!(zone instanceof ColoredZone colored)) continue;
            if (!colored.getColorName().equals(zoneName)) continue;

            if (targetX == zone.getX() && targetY == zone.getY()) {
                return true;
            }
        }
        return false;
    }

    public String getZoneName() {
        return zoneName;
    }
}
