package com.eklepser.thelevel.logic.decoder.condition;

import com.eklepser.thelevel.logic.world.collision.Entity;
import com.eklepser.thelevel.logic.world.zone.Zone;

import java.util.List;

public abstract class Condition {
    private final String name;

    public Condition(String name) {
        this.name = name;
    }

    public abstract boolean check(Entity target, List<Zone> zones);

    public static Condition from(String type, String arg) {
        return switch (type) {
            case "facing" -> new FacingCond(type, arg);
            case "staying" -> new FacingCond("stay", arg);
            default -> null;
        };
    }
}
