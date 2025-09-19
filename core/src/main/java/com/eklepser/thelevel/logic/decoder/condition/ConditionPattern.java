package com.eklepser.thelevel.logic.decoder.condition;

import java.util.ArrayList;
import java.util.List;

public enum ConditionPattern {
    FACING("facing", 1, "word", new ArrayList<>(List.of(
        "r", "g", "b", "red", "green", "blue"))),
    NONE("none", 0, null, null);

    public final String name;
    public final int argsNum;
    public final String argsType;
    public final ArrayList<String> allowedArgs;

    ConditionPattern(String name, int argsNum, String argsType, ArrayList<String> allowedArgs) {
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
