package com.eklepser.thelevel.logic.decoder.condition;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.eklepser.thelevel.logic.world.collision.zone.Zone;
import com.eklepser.thelevel.logic.world.entity.Entity;
import com.eklepser.thelevel.logic.world.collision.zone.ColoredZone;
import com.eklepser.thelevel.logic.world.collision.Wall;
import com.eklepser.thelevel.logic.world.collision.Collidable;
import com.eklepser.thelevel.logic.world.level.Level;

public class FacingCondition extends Condition {
    private final String targetObject;

    public FacingCondition(String arg) {
        if (arg.equals("r")) arg = "red";
        if (arg.equals("g")) arg = "green";
        if (arg.equals("b")) arg = "blue";
        this.targetObject = arg;
    }

    @Override
    public boolean check(Entity target, Level level) {
        int targetX = (int) (target.getWorldPos().x + target.getFacingDirection().vector.x);
        int targetY = (int) (target.getWorldPos().y + target.getFacingDirection().vector.y);

        if (targetObject.equals("wall")) {
            return level.getMap().collision[targetY][targetX] == 1;
        }

        for (Zone zone : level.getZones()) {
            if (!(zone instanceof ColoredZone colored)) continue;
            if (!colored.getColorName().equals(targetObject)) continue;
            return (targetX == zone.getX() && targetY == zone.getY());
        }
        return false;
    }

    @Override
    public Image getIcon() {
        String iconPath = "ui/icon/facing_" + targetObject + ".png";
        return new Image(new Texture(Gdx.files.internal(iconPath)));
    }
}
