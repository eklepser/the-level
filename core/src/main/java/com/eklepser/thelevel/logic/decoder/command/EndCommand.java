package com.eklepser.thelevel.logic.decoder.command;

import com.eklepser.thelevel.logic.decoder.execution.Executor;
import com.eklepser.thelevel.logic.world.collision.Entity;

public class EndCommand implements Command {
    private final Executor executor;

    public EndCommand(Executor executor) {
        this.executor = executor;
    }

    @Override
    public void execute(Entity target) {
        System.out.println("END");
        executor.getEditor().stop();
    }
}
