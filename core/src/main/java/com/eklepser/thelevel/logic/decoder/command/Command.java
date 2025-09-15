package com.eklepser.thelevel.logic.decoder.command;

import com.eklepser.thelevel.logic.world.collision.Entity;

public abstract class Command {
    protected final Instruction instruction;

    public Command(Instruction instruction) {
        this.instruction = instruction;
    }

    public abstract void execute(Entity target);
}
