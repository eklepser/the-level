package game.scene.level.logic.editor.command;

import java.util.ArrayList;
import java.util.List;

public enum Instruction {
    MOVE(MoveCommand.class, "move", 1, "word",
        new ArrayList<>(List.of("u", "d", "l", "r",
            "up", "down", "left", "right", "forward", "f")),
        "ui/icon/move"),
    ROT(RotateCommand.class, "rot", 1, "word",
        new ArrayList<>(List.of("l", "r", "left", "right")),
        "ui/icon/rot"),
    GOTO(GotoCommand.class, "goto", 1, "num", null,
        "ui/icon/goto"),
    IF(IfCommand.class, "if", 0, "cond",
        new ArrayList<>(List.of("facing")),
        "ui/icon/if"),
//    CLONE(CloneCommand.class, "clone", 1, "word",
//        new ArrayList<>(List.of("u", "d", "l", "r",
//            "up", "down", "left", "right", "forward", "f")),
//        "ui/icon/clone"),
    END(EndCommand.class, "end", 0, "none",
        null,
        "ui/icon/end"),
    NONE(NoneCommand.class, "none", 0, "none",
        null,
        "ui/icon/none");

    public final Class<? extends Command> commandClass;
    public final String name;
    public final int argsNum;
    public final String argsType;
    public final ArrayList<String> allowedArgs;
    public final String iconPath;

    Instruction(Class<? extends Command> commandClass, String name,
                int argsNum, String argsType, ArrayList<String> allowedArgs, String iconPath) {
        this.commandClass = commandClass;
        this.name = name;
        this.argsNum = argsNum;
        this.argsType = argsType;
        this.allowedArgs = allowedArgs;
        this.iconPath = iconPath;
    }

    public static Instruction from(String name) {
        for (Instruction instruction : values()) {
            if (instruction.name.equals(name.toLowerCase())) {
                return instruction;
            }
        }
        return NONE;
    }

    public static List<Instruction> listFrom(String[] names) {
        List<Instruction> instructions = new ArrayList<>();
        for (String name : names) {
            instructions.add(from(name));
        }
        return  instructions;
    }

    // Getters:
    public String getName() {
        return name;
    }
}
