package game.scene.level.logic.condition;

import game.common.logic.collision.CollisionContext;
import game.common.logic.collision.zone.ColoredZone;
import game.common.logic.collision.zone.Zone;
import game.common.logic.entity.Entity;

public class FacingCondition extends Condition {
    private final String zoneName;

    public FacingCondition(String zoneName) {
        this.zoneName = zoneName;
    }

    @Override
    public boolean check(Entity target, CollisionContext collisionContext) {
        int targetX = (int) (target.getWorldPos().x + target.getFacingDirection().vector.x);
        int targetY = (int) (target.getWorldPos().y + target.getFacingDirection().vector.y);

        System.out.println(target.getFacingDirection());

        if (zoneName.equals("wall")) {
            return collisionContext.collisionMap()[targetY][targetX] == 1;
        }

        for (Zone zone : collisionContext.zones()) {
            if (!(zone instanceof ColoredZone colored)) continue;
            if (!colored.getColorName().equals(zoneName)) continue;
            return (targetX == zone.getX() && targetY == zone.getY());
        }
        return false;
    }

    public String getZoneName() {
        return zoneName;
    }
}
