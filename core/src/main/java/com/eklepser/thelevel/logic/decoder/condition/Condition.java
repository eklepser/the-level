package com.eklepser.thelevel.logic.decoder.condition;

import com.eklepser.thelevel.logic.world.collision.Entity;
import com.eklepser.thelevel.logic.world.zone.Zone;

import java.util.List;

public interface Condition {
    boolean check(Entity target, List<Zone> zones);

    static Condition from(String conditionName, String arg) {
        ConditionPattern pattern = ConditionPattern.from(conditionName);
        return switch (pattern) {
            default -> new FacingCondition(arg);
        };
    }
}
