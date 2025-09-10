package com.eklepser.thelevel.logic.decoder.commands;

import com.eklepser.thelevel.logic.decoder.Executor;
import com.eklepser.thelevel.logic.world.Entity;

public class GotoCmd extends Command {
    private final Executor executor;
    private final int lineNum;

    public GotoCmd(Instruction instruction, Executor executor, int lineNum) {
        super(instruction);
        this.executor = executor;
        this.lineNum = lineNum;
    }

    @Override
    public void execute(Entity target) {
        System.out.println("GOTO " + lineNum);
        executor.executeAll(lineNum - 1, executor.getCodeMap());
    }
}
