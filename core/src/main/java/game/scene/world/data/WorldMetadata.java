package game.scene.world.data;

import game.scene.level.logic.command.Instruction;

import java.util.List;

public final class WorldMetadata {
    public String tag;

    public WorldMetadata() { }

    public WorldMetadata(String tag, String title, int codeLinesAmount, List<Instruction> allowedInstructions) {
        this.tag = tag;
    }
}


