package com.eklepser.thelevel.logic.decoder;

import java.util.ArrayList;
import java.util.List;

public enum Instruction {
    MOVE("move", 1, new ArrayList<>(List.of("up", "down", "left", "right"))),
    GOTO("goto", 1, null),
    NONE("none", 0, null);

    public final String name;
    public final int expectedArgs;
    public final ArrayList<String> allowedArgs;

    Instruction(String name, int expectedArgs, ArrayList<String> allowedArgs) {
        this.name = name;
        this.expectedArgs = expectedArgs;
        this.allowedArgs = allowedArgs;
    }

    public static Instruction fromName(String name) {
        return switch (name) {
            case "move" -> Instruction.MOVE;
            case "goto" -> Instruction.GOTO;
            default -> Instruction.NONE;
        };
    }

    public static List<Instruction> defaultAllowed() {
        return new ArrayList<>(List.of(MOVE, GOTO, NONE));
    }
}
