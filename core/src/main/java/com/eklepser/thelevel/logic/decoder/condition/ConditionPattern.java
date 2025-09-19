package com.eklepser.thelevel.logic.decoder.condition;

import java.util.ArrayList;
import java.util.List;

public enum ConditionPattern {
    FACING(FacingCondition.class, "facing", 1, "word", new ArrayList<>(List.of(
        "r", "g", "b", "red", "green", "blue"))),
    NONE(FacingCondition.class, "none", 0, "none", null);

    public final Class<? extends Condition> conditionClass;
    public final String name;
    public final int argsNum;
    public final String argsType;
    public final ArrayList<String> allowedArgs;

    ConditionPattern(Class<? extends Condition> conditionClass, String name,
                     int argsNum, String argsType, ArrayList<String> allowedArgs) {
        this.conditionClass = conditionClass;
        this.name = name;
        this.argsNum = argsNum;
        this.argsType = argsType;
        this.allowedArgs = allowedArgs;
    }

    public static ConditionPattern from(String name) {
        for (ConditionPattern conditionPattern : values()) {
            if (conditionPattern.name.equals(name)) {
                return conditionPattern;
            }
        }
        return NONE;
    }
}
