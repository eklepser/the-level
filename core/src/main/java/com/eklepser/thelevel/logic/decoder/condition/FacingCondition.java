package com.eklepser.thelevel.logic.decoder.condition;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.eklepser.thelevel.logic.world.collision.Entity;
import com.eklepser.thelevel.logic.world.zone.ColoredZone;
import com.eklepser.thelevel.logic.world.zone.Wall;
import com.eklepser.thelevel.logic.world.zone.Zone;

public class FacingCondition extends Condition {
    private final String targetObject;

    public FacingCondition(String arg) {
        if (arg.equals("r")) arg = "red";
        if (arg.equals("g")) arg = "green";
        if (arg.equals("b")) arg = "blue";
        this.targetObject = arg;
    }

    @Override
    public boolean matches(Entity target, Zone zone) {
        if (targetObject.equals("wall")) {
            System.out.println("wall");
            if ((zone instanceof Wall))
            {
                return target.getFacingRect().overlaps(zone.getRect());
            }
            return false;
        }
        if (!(zone instanceof ColoredZone coloredZone)) return false;
        if (!target.getFacingRect().overlaps(zone.getRect())) return false;
        return (targetObject.equals(coloredZone.getColorName()));
    }

    @Override
    public Image getIcon() {
        String iconPath = "ui/icon/facing_" + targetObject + ".png";
        return new Image(new Texture(Gdx.files.internal(iconPath)));
    }
}
