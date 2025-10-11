package game.data.level;

import game.scene.level.logic.command.Instruction;

import java.util.List;

public final class LevelMetadata {
    public String tag;
    public String title;
    public int codeLinesAmount;
    public List<Instruction> allowedInstructions;

    public LevelMetadata() { }

    public LevelMetadata(String tag, String title, int codeLinesAmount, List<Instruction> allowedInstructions) {
        this.tag = tag;
        this.title = title;
        this.codeLinesAmount = codeLinesAmount;
        this.allowedInstructions = allowedInstructions;
    }

    public String[] getAllowedInstructionsAsStrings() {
        return allowedInstructions.stream()
            .map(Instruction::getName)
            .toArray(String[]::new);
    }
}


