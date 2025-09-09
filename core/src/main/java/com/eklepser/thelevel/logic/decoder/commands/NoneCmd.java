package com.eklepser.thelevel.logic.decoder.commands;

public class NoneCmd extends Command {
    public NoneCmd(Instruction instruction) {
        super(instruction);
    }

    @Override
    public void execute() {
        System.out.println("NONE");
    }
}
