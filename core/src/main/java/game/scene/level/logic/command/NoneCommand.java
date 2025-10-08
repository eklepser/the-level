package game.scene.level.logic.command;

import game.common.logic.entity.Entity;

public final class NoneCommand extends Command {

    public NoneCommand(int lineNum) {
        super(lineNum);
    }

    @Override
    public void execute( Entity target) {
        System.out.println("NONE");
    }
}
