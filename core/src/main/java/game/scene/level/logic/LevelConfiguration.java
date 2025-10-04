package game.scene.level.logic;

import game.scene.level.logic.editor.command.Instruction;
import game.common.logic.Configuration;

import java.util.List;

public final class LevelConfiguration extends Configuration {
    public String title;
    public int codeLinesNum;
    public List<Instruction> allowedInstructions;

    // Getters:
    public String[] getAllowedInstructionsAsStrings() {
        return allowedInstructions.stream()
            .map(Instruction::getName)
            .toArray(String[]::new);
    }
}
