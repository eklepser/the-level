package com.eklepser.thelevel.logic.decoder.condition;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.eklepser.thelevel.logic.world.entity.Entity;
import com.eklepser.thelevel.logic.world.collision.zone.ColoredZoneOld;
import com.eklepser.thelevel.logic.world.collision.Wall;
import com.eklepser.thelevel.logic.world.collision.Collidable;

public class FacingCondition extends Condition {
    private final String targetObject;

    public FacingCondition(String arg) {
        if (arg.equals("r")) arg = "red";
        if (arg.equals("g")) arg = "green";
        if (arg.equals("b")) arg = "blue";
        this.targetObject = arg;
    }

    @Override
    public boolean matches(Entity target, Collidable collidable) {
        if (targetObject.equals("wall")) {
            if ((collidable instanceof Wall wall)) {
                return target.getFacingRect().overlaps(wall.getRect());
            }
            return false;
        }
        if (collidable instanceof ColoredZoneOld coloredZone) {
            if (target.getFacingRect().overlaps(coloredZone.getRect())) {
                return (targetObject.equals(coloredZone.getColorName()));
            }
        }
        return false;
    }

    @Override
    public Image getIcon() {
        String iconPath = "ui/icon/facing_" + targetObject + ".png";
        return new Image(new Texture(Gdx.files.internal(iconPath)));
    }
}
