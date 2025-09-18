package com.eklepser.thelevel.logic.decoder.condition;

import com.eklepser.thelevel.logic.world.collision.Entity;
import com.eklepser.thelevel.logic.world.zone.ColoredZone;
import com.eklepser.thelevel.logic.world.zone.Zone;

import java.util.List;

public class FacingCond extends Condition {
    private final String colorName;

    public FacingCond(String name, String colorName) {
        super("facing");
        this.colorName = colorName;
    }

    public boolean check(Entity target, List<Zone> zones) {
        for (Zone zone : zones) {
            if (zone instanceof ColoredZone coloredZone) {
                if (target.getFacingRect().overlaps(zone.getRect())) {
                    if (colorName.equals(coloredZone.getColorName())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
