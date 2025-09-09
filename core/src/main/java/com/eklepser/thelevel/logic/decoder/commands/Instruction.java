package com.eklepser.thelevel.logic.decoder.commands;

import java.util.ArrayList;
import java.util.List;

public enum Instruction {
    MOVE("move", 1, new ArrayList<>(List.of(
        "u", "d", "l", "r", "up", "down", "left", "right", "forw", "f"))),
    ROT("rot", 1, new ArrayList<>(List.of(
        "l", "r", "left", "right"))),
    GOTO("goto", 1, null),
    TP("#tp", 2, null),
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
            case "rot" -> Instruction.ROT;
            case "goto" -> Instruction.GOTO;
            case "#tp" -> Instruction.TP;
            default -> Instruction.NONE;
        };
    }

    public static List<Instruction> defaultAllowed() {
        return new ArrayList<>(List.of(MOVE, GOTO, NONE));
    }
}
