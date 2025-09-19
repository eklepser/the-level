package com.eklepser.thelevel.logic.decoder.command;

import com.eklepser.thelevel.logic.decoder.execution.Executor;
import com.eklepser.thelevel.logic.world.collision.Entity;

public class GotoCommand extends Command {
    private final int lineNum;
    private final Executor executor;

    public GotoCommand(String[] args, Executor executor) {
        this.lineNum = Integer.parseInt(args[0]);
        this.executor = executor;
    }

    @Override
    public void execute(Entity target) {
        System.out.println("GOTO " + lineNum);
        executor.execute(lineNum - 1, executor.getCodeMap());
    }
}
