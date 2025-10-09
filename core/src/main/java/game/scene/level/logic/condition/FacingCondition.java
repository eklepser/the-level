package game.scene.level.logic.condition;

import game.common.logic.collision.CollisionContext;
import game.common.logic.collision.zone.ColoredZone;
import game.common.logic.collision.zone.Zone;
import game.common.logic.entity.Entity;

public final class FacingCondition extends Condition {
    private final String zoneName;

    public FacingCondition(String zoneName) {
        this.zoneName = zoneName;
    }

    @Override
    public boolean check(Entity target, CollisionContext collisionContext) {
        int targetX = (int) (target.getWorldPos().x + target.getFacingDirection().vector.x);
        int targetY = (int) (target.getWorldPos().y + target.getFacingDirection().vector.y);

        System.out.println("facing direction:" + target.getFacingDirection());
        System.out.println(targetX);
        System.out.println(targetY);

        if (zoneName.equals("wall")) {
            return collisionContext.collisionMap()[targetY][targetX] == 1;
        }

        System.out.println("zone count: " + collisionContext.zones().size());
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
