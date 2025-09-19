package com.eklepser.thelevel.logic.decoder.condition;

import com.eklepser.thelevel.logic.world.collision.Entity;
import com.eklepser.thelevel.logic.world.zone.ColoredZone;
import com.eklepser.thelevel.logic.world.zone.Zone;

public class FacingCondition extends Condition {
    private final String colorName;

    public FacingCondition(String arg) {
        if (arg.equals("r")) arg = "red";
        if (arg.equals("g")) arg = "green";
        if (arg.equals("b")) arg = "blue";
        this.colorName = arg;
    }

    @Override
    public boolean matches(Entity target, Zone zone) {
        if (!(zone instanceof ColoredZone coloredZone)) return false;
        if (!target.getFacingRect().overlaps(zone.getRect())) return false;
        return (colorName.equals(coloredZone.getColorName()));
    }

}
