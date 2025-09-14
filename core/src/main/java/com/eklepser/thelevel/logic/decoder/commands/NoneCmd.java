package com.eklepser.thelevel.logic.decoder.commands;

import com.eklepser.thelevel.logic.world.collision.Entity;

public class NoneCmd extends Command {
    public NoneCmd(Instruction instruction) {
        super(instruction);
    }

    @Override
    public void execute(Entity target) {
        System.out.println("NONE");
    }
}
