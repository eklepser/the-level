package game.scene.level.logic;

import game.scene.level.logic.command.Instruction;
import game.common.rendering.tilemap.BaseConfiguration;

import java.util.List;

public final class LevelConfiguration extends BaseConfiguration {
    public String title;
    public int codeLinesNum;
    public List<Instruction> allowedInstructions;
    
    public String[] getAllowedInstructionsAsStrings() {
        return allowedInstructions.stream()
            .map(Instruction::getName)
            .toArray(String[]::new);
    }
}
