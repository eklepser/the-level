package com.eklepser.thelevel.logic.decoder.command;

import com.eklepser.thelevel.logic.world.collision.Entity;

public class NoneCommand implements Command {
    @Override
    public void execute(Entity target) {
        System.out.println("NONE");
    }
}
