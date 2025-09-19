package com.eklepser.thelevel.logic.decoder.command;

import java.util.ArrayList;
import java.util.List;

public enum Instruction {
    MOVE("move", 1, "word", new ArrayList<>(List.of(
        "u", "d", "l", "r", "up", "down", "left", "right", "forward", "f"))),
    ROT("rot", 1, "word", new ArrayList<>(List.of(
        "l", "r", "left", "right"))),
    GOTO("goto", 1, "num", null),
    IF("if", 0, "cond", new ArrayList<>(List.of("facing"))),
    TP("#tp", 2, "num", null),
    END("end", 0, "none",null),
    NONE("none", 0, "none", null);

    public final String name;
    public final int argsNum;
    public final String argsType;
    public final ArrayList<String> allowedArgs;

    Instruction(String name, int argsNum, String argsType, ArrayList<String> allowedArgs) {
        this.name = name;
        this.argsNum = argsNum;
        this.argsType = argsType;
        this.allowedArgs = allowedArgs;
    }

    public static Instruction from(String name) {
        for (Instruction instruction : values()) {
            if (instruction.name.equals(name)) {
                return instruction;
            }
        }
        return NONE;
    }
}
