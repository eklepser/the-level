package com.eklepser.thelevel.logic.decoder.command;

import com.eklepser.thelevel.logic.decoder.execution.Executor;
import com.eklepser.thelevel.logic.world.collision.Entity;

public class GotoCommand implements Command {
    private final Executor executor;
    private final int lineNum;

    public GotoCommand(Executor executor, int lineNum) {
        this.executor = executor;
        this.lineNum = lineNum;
    }

    @Override
    public void execute(Entity target) {
        System.out.println("GOTO " + lineNum);
        executor.execute(lineNum - 1, executor.getCodeMap());
    }
}
