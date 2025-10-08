package game.scene.level.logic.command;

import game.common.logic.entity.Entity;
import game.scene.level.logic.execution.Executor;

public final class GotoCommand extends Command {
    private final int targetLineNum;
    private final Executor executor;

    public GotoCommand(int lineNum, int targetLineNum, Executor executor) {
        super(lineNum);
        this.targetLineNum = targetLineNum;
        this.executor = executor;
    }

    @Override
    public void execute(Entity target) {
        System.out.println("GOTO " + targetLineNum);
        executor.execute(targetLineNum - 1, executor.getCodeMap());
    }
}
