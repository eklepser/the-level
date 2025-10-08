package game.scene.level.logic.command;

import game.common.logic.entity.Entity;
import game.scene.level.logic.execution.Executor;

public final class EndCommand extends Command {
    private final Executor executor;

    public EndCommand(int lineNum, Executor executor) {
        super(lineNum);
        this.executor = executor;
    }

    @Override
    public void execute(Entity target) {
        System.out.println("END");
        executor.stop();
    }
}
