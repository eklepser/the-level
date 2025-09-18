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
    END("end", 0, null,null),
    NONE("none", 0, null, null);

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

    public static Instruction fromName(String name) {
        return switch (name) {
            case "move" -> Instruction.MOVE;
            case "rot" -> Instruction.ROT;
            case "goto" -> Instruction.GOTO;
            case "if" -> Instruction.IF;
            case "#tp" -> Instruction.TP;
            case "end" -> Instruction.END;
            default -> Instruction.NONE;
        };
    }
}
