package com.eklepser.thelevel.logic.decoder.commands;

public abstract class Command {
    protected Instruction instruction;

    public Command(Instruction instruction) {
        this.instruction = instruction;
    }

    public abstract void execute();
}
